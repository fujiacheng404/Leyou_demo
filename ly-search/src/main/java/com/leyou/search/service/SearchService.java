package com.leyou.search.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.leyou.common.enums.ExceptionEnum;
import com.leyou.common.exception.LyException;
import com.leyou.common.utils.JsonUtils;
import com.leyou.common.vo.PageResult;
import com.leyou.item.pojo.*;
import com.leyou.search.client.BrandClient;
import com.leyou.search.client.CategoryClient;
import com.leyou.search.client.GoodsClient;
import com.leyou.search.client.SpecificationClient;
import com.leyou.search.pojo.Goods;
import com.leyou.search.pojo.SearchRequest;
import com.leyou.search.repository.GoodsRepository;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.query.FetchSourceFilter;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @Author: FuJiaCheng
 * @Date: Create In 22:11 2018/11/17
 * @Modified:
 * @annotation:
 */
@Service
public class SearchService {
    
    @Autowired
    private CategoryClient categoryClient;
    
    @Autowired
    private BrandClient brandClient;
    
    @Autowired
    private GoodsClient goodsClient;
    
    @Autowired
    private SpecificationClient specificationClient;
    
    @Autowired
    private GoodsRepository goodsRepository;
    
    /**
     * 构建商品对象
     *
     * @param spu
     * @return
     */
    public Goods buildGoods(Spu spu) {
        // 查询分类
        List<Category> categories = categoryClient.queryCategoryByIds(Arrays.asList(spu.getCid1(), spu.getCid2(), spu.getCid3()));
        if (CollectionUtils.isEmpty(categories)) {
            throw new LyException(ExceptionEnum.CATEGORY_NOT_FOND);
        }
        List<String> names = categories.stream().map(Category::getName).collect(Collectors.toList()); //查询所有的分类
        // 查询品牌
        Brand brand = brandClient.queryBrandById(spu.getBrandId());
        if (brand == null) {
            throw new LyException(ExceptionEnum.BRAND_NOT_FOND);
        }
        // 搜索字段
        String all = spu.getTitle() + StringUtils.join(names, " ") + brand.getName();
        //查询sku
        List<Sku> skuList = goodsClient.querySkuBySpuId(spu.getId()); //返回sku的集合
        if (CollectionUtils.isEmpty(skuList)) {
            throw new LyException(ExceptionEnum.GOODS_SKU_NOT_FOND);
        }
        // 对skuList中的每个sku对象进行处理
        List<Map<String, Object>> skus = new ArrayList<>();
        // 定义一个价格集合
        Set<Long> priceList = new HashSet<>();
        for (Sku sku : skuList) {
            priceList.add(sku.getPrice());
            
            Map<String, Object> map = new HashMap<>();
            map.put("id", sku.getId());
            map.put("title", sku.getTitle());
            map.put("price", sku.getPrice());
            map.put("images", StringUtils.substringBefore(sku.getImages(), ",")); //根据 ,进行切割
            
            skus.add(map);
        }
        //Set<Long> priceList = skuList.stream().map(Sku::getPrice).collect(Collectors.toSet());//得到sku中的所有商品价格
        
        
        // 查询规格参数
        List<SpecParam> paramList = specificationClient.queryParamList(null, spu.getCid3(), true);
        if (CollectionUtils.isEmpty(paramList)) {
            throw new LyException(ExceptionEnum.SPEC_PARAM_NOT_FOUND);
        }
        // 查询商品详情
        SpuDetail spuDetail = goodsClient.queryDetailById(spu.getId());
        // 获取通用规格参数
        Map<String, String> genericSpec = JsonUtils.parseMap(spuDetail.getGenericSpec(), String.class, String.class);
        // 获取特有规格参数
        Map<String, List<String>> specialSpec = JsonUtils.nativeRead(spuDetail.getSpecialSpec(), new TypeReference<Map<String, List<String>>>() {
        });
        // 规格参数,key是规格参数的名字，value是规格参数的值
        Map<String, Object> specs = new HashMap<>();
        for (SpecParam param : paramList) {
            String key = param.getName(); //得到规格参数名称
            Object value = "";
            //判断是否是通用规格参数
            if (param.getGeneric()) { //判断是否是通用属性
                value = genericSpec.get(param.getId().toString());
                // 判断是否是数值类型
                if (param.getNumeric()) {
                    // 处理成段
                    value = chooseSegment(value.toString(), param);
                }
            } else { //否则就是特有的规格参数
                value = specialSpec.get(param.getId().toString());
            }
            // 存入map
            specs.put(key, value);
        }
        
        Goods goods = new Goods();
        goods.setBrandId(spu.getBrandId());
        goods.setCid1(spu.getCid1());
        goods.setCid2(spu.getCid2());
        goods.setCid3(spu.getCid3());
        goods.setCreateTime(spu.getCreateTime());
        goods.setId(spu.getId());
        goods.setSubTitle(spu.getSubTitle());
        goods.setAll(all); //需要搜索的字段
        goods.setPrice(priceList); //所有sku的价格集合
        goods.setSkus(JsonUtils.serialize(skuList)); //所有sku的集合的json格式
        goods.setSpecs(null); //所有的可搜索规格参数
        
        return goods;
    }
    
    /**
     * 工具
     *
     * @param value
     * @param p
     * @return
     */
    private String chooseSegment(String value, SpecParam p) {
        //todo 这里需要调用 com.leyou.common.utils; 这个包下的 NumberUtils类，但是没有这个类似与 toDouble方法是怎么回事？
        double val = NumberUtils.toDouble(value);
        String result = "其它";
        // 保存数值段
        for (String segment : p.getSegments().split(",")) {
            String[] segs = segment.split("-");
            // 获取数值范围
            double begin = NumberUtils.toDouble(segs[0]);
            double end = Double.MAX_VALUE;
            if (segs.length == 2) {
                end = NumberUtils.toDouble(segs[1]);
            }
            // 判断是否在范围内
            if (val >= begin && val < end) {
                if (segs.length == 1) {
                    result = segs[0] + p.getUnit() + "以上";
                } else if (begin == 0) {
                    result = segs[1] + p.getUnit() + "以下";
                } else {
                    result = segment + p.getUnit();
                }
                break;
            }
        }
        return result;
    }
    
    
    public PageResult<Goods> search(SearchRequest searchRequest) {
        int page = searchRequest.getPage() - 1;
        int size = searchRequest.getDefaultSize();
        // 创建查询构建器
        NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();
        // 结果过滤
        queryBuilder.withSourceFilter(new FetchSourceFilter(new String[]{"id", "subTitle", "skus"}, null));
        // 分页
        queryBuilder.withPageable(PageRequest.of(page, size));
        // 过滤
        queryBuilder.withQuery(QueryBuilders.matchQuery("all", searchRequest.getKey()));
        // 查询
        Page<Goods> result = goodsRepository.search(queryBuilder.build());
        
        // 解析结果
        long total = result.getTotalElements();
        int totalPages = result.getTotalPages();
        List<Goods> goodsList = result.getContent();
        
        return new PageResult<>(total, totalPages, goodsList);
    }
}
