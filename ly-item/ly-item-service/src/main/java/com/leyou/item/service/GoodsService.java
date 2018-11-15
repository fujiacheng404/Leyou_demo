package com.leyou.item.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.leyou.common.enums.ExceptionEnum;
import com.leyou.common.exception.LyException;
import com.leyou.common.vo.PageResult;
import com.leyou.item.mapper.SkuMapper;
import com.leyou.item.mapper.SpuDetailMapper;
import com.leyou.item.mapper.SpuMapper;
import com.leyou.item.mapper.StockMapper;
import com.leyou.item.pojo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: FuJiaCheng
 * @Date: Create In 10:50 2018/11/13
 * @Modified:
 * @annotation:
 */

@Service
public class GoodsService {
    
    @Autowired
    private SpuMapper spuMapper;
    
    @Autowired
    private SpuDetailMapper spuDetailMapper;
    
    @Autowired
    private CategoryService categoryService;
    
    @Autowired
    private BrandService brandService;
    
    @Autowired
    private SkuMapper skuMapper;
    
    @Autowired
    private StockMapper stockMapper;
    
    public PageResult<Spu> querySpuByPage(Integer page, Integer rows, Boolean saleable, String key) {
        // 分页
        PageHelper.startPage(page, rows);
        // 过滤
        Example example = new Example(Spu.class);
        Example.Criteria criteria = example.createCriteria();
        // 搜索字段过滤
        if (key != null && !key.trim().equals("")) {
            criteria.andLike("title", "%" + key + "%");
        }
        // 上下架过滤
        if (saleable != null) {
            criteria.andEqualTo("saleable", saleable);
        }
        // 默认根据商品的更新时间进行排序
        example.setOrderByClause("last_update_time DESC");
        
        // 发出查询语句
        List<Spu> spus = spuMapper.selectByExample(example);
        // 判断一下返回结果
        if (CollectionUtils.isEmpty(spus)) {
            throw new LyException(ExceptionEnum.GOODS_NOT_FOND);
        }
        
        // 解析分类和品牌的名称
        loadCategoryAndBrandName(spus);
        
        // 解析分页结果
        PageInfo<Spu> spuPageInfo = new PageInfo<>(spus);
        
        return new PageResult<>(spuPageInfo.getTotal(), spus);
    }
    
    /**
     * 解析分类和品牌的名称
     *
     * @param spus
     */
    private void loadCategoryAndBrandName(List<Spu> spus) {
        for (Spu spu : spus) {
            // 处理分类名称
            List<String> collect = categoryService.queryByIds(Arrays.asList(spu.getCid1(), spu.getCid2(), spu.getCid3()))
                    .stream().map(Category::getName).collect(Collectors.toList());
            
            // 可以简写成 StringUtils.join(names , "/")
            for (String names : collect) {
                spu.setCname(names + "/");
            }
            
            // 处理品牌名称
            spu.setBname(brandService.queryById(spu.getBrandId()).getName());
        }
    }
    
    /**
     * 商品的新增
     *
     * @param spu
     */
    @Transactional //加入事物
    public void saveGoods(Spu spu) {
        // 新增spu
        spu.setCreateTime(new Date());
        spu.setLastUpdateTime(spu.getCreateTime());
        spu.setId(null);
        spu.setSaleable(true); //商品上下架
        spu.setValid(false); //是否删除
        
        int insertSpuResult = spuMapper.insert(spu);
        if (insertSpuResult < 1) {
            throw new LyException(ExceptionEnum.GOODS_SAVE_ERROR);
        }
        
        // 新增detail
        SpuDetail spuDetail = spu.getSpuDetail();
        spuDetail.setSpuId(spu.getId());
        int insertSpuDetailResult = spuDetailMapper.insert(spuDetail);
        
        // 定义库存对象集合
        List<Stock> stockList = new ArrayList<>();
        
        // 新增sku
        List<Sku> skus = spu.getSkus();
        for (Sku sku : skus) {
            sku.setCreateTime(new Date());
            sku.setLastUpdateTime(sku.getCreateTime());
            sku.setSpuId(spu.getId());
            
            /**
             * 保存sku信息
             * ⚠ 不能用于批量新增,因为我们需要sku的ID 如果批量新增了就不会返回ID了！
             */
            int insertSkuResult = skuMapper.insert(sku);
            if (insertSkuResult < 1) {
                throw new LyException(ExceptionEnum.GOODS_SAVE_ERROR);
            }
            
            // 新增库存
            Stock stock = new Stock();
            stock.setSkuId(sku.getId());
            stock.setStock(sku.getStock());
            
            // 向库存List中添加对象
            stockList.add(stock);
        }
        
        // 批量新增库存
        stockMapper.insertList(stockList);
    }
    
}
