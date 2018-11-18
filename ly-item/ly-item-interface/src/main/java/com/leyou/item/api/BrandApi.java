package com.leyou.item.api;

import com.leyou.item.pojo.Brand;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @Author: FuJiaCheng
 * @Date: Create In 21:36 2018/11/17
 * @Modified:
 * @annotation:
 */

public interface BrandApi {
    
    /**
     * 根据ID查询品牌
     *
     * @param id
     * @return
     */
    @GetMapping("brand/{id}")
    Brand queryBrandById(@PathVariable("id") Long id);
}
