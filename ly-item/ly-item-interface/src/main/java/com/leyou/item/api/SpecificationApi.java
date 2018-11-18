package com.leyou.item.api;

import com.leyou.item.pojo.SpecParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @Author: FuJiaCheng
 * @Date: Create In 21:39 2018/11/17
 * @Modified:
 * @annotation:
 */

public interface SpecificationApi {
    /**
     * 查询参数的集合
     *
     * @param gid       根据组ID查询
     * @param cid       根据种类ID去查
     * @param searching 根据是否是搜索字段去查
     */
    @GetMapping("spec/params")
    List<SpecParam> queryParamList(
            @RequestParam(value = "gid", required = false) Long gid,
            @RequestParam(value = "cid", required = false) Long cid,
            @RequestParam(value = "searching", required = false) Boolean searching);
}
