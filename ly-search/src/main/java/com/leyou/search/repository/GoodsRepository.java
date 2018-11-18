package com.leyou.search.repository;

import com.leyou.search.pojo.Goods;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @Author: FuJiaCheng
 * @Date: Create In 21:53 2018/11/17
 * @Modified:
 * @annotation: 使用Es的工具实现增删改查
 */
public interface GoodsRepository extends ElasticsearchRepository<Goods, Long> {
}
