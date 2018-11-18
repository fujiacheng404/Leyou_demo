package com.leyou.search.repository;

import com.leyou.common.vo.PageResult;
import com.leyou.item.pojo.Spu;
import com.leyou.search.client.GoodsClient;
import com.leyou.search.pojo.Goods;
import com.leyou.search.service.SearchService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

@SpringBootTest
@RunWith(SpringRunner.class)
public class GoodsRepositoryTest {
    
    @Autowired
    private GoodsRepository goodsRepository;
    
    @Autowired
    private ElasticsearchTemplate template;
    
    @Autowired
    private GoodsClient goodsClient;
    
    @Autowired
    private SearchService searchService;
    
    /**
     * 通过javaAPI创建索引库
     */
    @Test
    public void testCreateIndex() {
        //创建索引
        template.createIndex(Goods.class);
        //创建映射
        template.putMapping(Goods.class);
    }
    
    
    @Test
    public void loadSpu() {
        int page = 1;
        int rows = 100;
        int size = 0; //定义一个当前页的数据大小
        do {
            //批量查询spu的信息
            PageResult<Spu> spuPageResult = goodsClient.querySpuByPage(page, rows, true, null);//最后两个参数的意思：查询所有上架商品，和有无搜索过滤字段
            List<Spu> spuList = spuPageResult.getItems(); //得到当前页结果
            if (CollectionUtils.isEmpty(spuList)) {
                return;
            }
            // 构建成Goods
            List<Goods> goodsList = spuList.stream().map(searchService::buildGoods).collect(Collectors.toList());
            // 存入索引库
            goodsRepository.saveAll(goodsList);
            
            // 翻页
            page++;
            
            size = spuList.size(); //size存储当前页的数据量大小
        } while (size == 100);
    }
}