package com.leyou.item.api;

import com.leyou.item.pojo.Category;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @Author: FuJiaCheng
 * @Date: Create In 0:25 2018/11/17
 * @Modified:
 * @annotation: 提供对外服务的接口
 */

public interface CategoryApi {
    
    /**
     * 根据ID查询商品分类
     *
     * @param ids
     */
    @GetMapping("category/list/ids")
    List<Category> queryCategoryByIds(@RequestParam("ids") List<Long> ids);
}
