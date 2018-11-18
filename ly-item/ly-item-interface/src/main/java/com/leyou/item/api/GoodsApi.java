package com.leyou.item.api;

import com.leyou.common.vo.PageResult;
import com.leyou.item.pojo.Sku;
import com.leyou.item.pojo.Spu;
import com.leyou.item.pojo.SpuDetail;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @Author: FuJiaCheng
 * @Date: Create In 0:07 2018/11/17
 * @Modified:
 * @annotation: 提供对外服务的接口
 */

public interface GoodsApi {
    /**
     * 根据spuId查询商品详情表
     *
     * @param spuId
     */
    @GetMapping("spu/detail/{spuId}")
    SpuDetail queryDetailById(@PathVariable("spuId") Long spuId);
    
    /**
     * 根据Spu的Id查询sku信息
     *
     * @param spuId
     */
    @GetMapping("sku/list")
    List<Sku> querySkuBySpuId(@RequestParam("spuId") Long spuId);
    
    /**
     * 分页查询 Spu
     *
     * @param page
     * @param rows
     * @param saleable
     * @param key
     */
    @GetMapping("/spu/page")
    PageResult<Spu> querySpuByPage(
            @RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "rows", defaultValue = "5") Integer rows,
            @RequestParam(value = "saleable", required = false) Boolean saleable,
            @RequestParam(value = "key", required = false) String key
    );
}
