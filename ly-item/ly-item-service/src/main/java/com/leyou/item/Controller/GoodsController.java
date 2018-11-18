package com.leyou.item.Controller;

import com.leyou.common.vo.PageResult;
import com.leyou.item.pojo.Sku;
import com.leyou.item.pojo.Spu;
import com.leyou.item.pojo.SpuDetail;
import com.leyou.item.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: FuJiaCheng
 * @Date: Create In 10:51 2018/11/13
 * @Modified:
 * @annotation:
 */

@RestController
public class GoodsController {
    
    @Autowired
    private GoodsService goodsService;
    
    
    /**
     * 分页查询 Spu
     *
     * @param page
     * @param rows
     * @param saleable
     * @param key
     * @return
     */
    @GetMapping("/spu/page")
    public ResponseEntity<PageResult<Spu>> querySpuByPage(
            @RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "rows", defaultValue = "5") Integer rows,
            @RequestParam(value = "saleable", required = false) Boolean saleable,
            @RequestParam(value = "key", required = false) String key
    ) {
        return ResponseEntity.ok(goodsService.querySpuByPage(page, rows, saleable, key));
    }
    
    /**
     * 商品的新增
     *
     * @param spu
     * @return
     */
    @PostMapping("goods")
    public ResponseEntity<Void> saveGoods(@RequestBody Spu spu) {
        goodsService.saveGoods(spu);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    
    /**
     * 商品的修改
     *
     * @param spu
     * @return
     */
    @PutMapping
    public ResponseEntity<Void> updateGoods(@RequestBody Spu spu) {
        goodsService.updateGoods(spu);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build(); //返回状态码 204
    }
    
    /**
     * 根据spuId查询商品详情表
     *
     * @param spuId
     * @return
     */
    @GetMapping("/spu/detail/{spuId}")
    public ResponseEntity<SpuDetail> queryDetailById(@PathVariable("spuId") Long spuId) {
        return ResponseEntity.ok(goodsService.queryDetailById(spuId));
    }
    
    /**
     * 根据Spu的Id查询sku信息
     *
     * @param spuId
     * @return
     */
    @GetMapping("sku/list")
    public ResponseEntity<List<Sku>> querySkuBySpuId(@RequestParam("spuId") Long spuId) {
        return ResponseEntity.ok(goodsService.querySkuBySpuId(spuId));
    }
    
}
