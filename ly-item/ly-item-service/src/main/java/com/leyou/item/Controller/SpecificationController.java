package com.leyou.item.Controller;

import com.leyou.item.pojo.SpecGroup;
import com.leyou.item.pojo.SpecParam;
import com.leyou.item.service.SpecificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: FuJiaCheng
 * @Date: Create In 12:05 2018/11/9
 * @Modified:
 * @annotation: 这部分前台没实现
 */

@RestController
@RequestMapping("spec")
public class SpecificationController {
    
    @Autowired
    private SpecificationService specificationService;
    
    /**
     * 根据分类ID查询规格组
     *
     * @param cid
     * @return
     */
    @GetMapping("groups/{cid}")
    public ResponseEntity<List<SpecGroup>> queryGroupByCid(@PathVariable("cid") Long cid) {
        return ResponseEntity.ok(specificationService.queryGroupByCid(cid));
    }
    
    /**
     * 查询参数的集合
     *
     * @param gid       根据组ID查询
     * @param cid       根据种类ID去查
     * @param searching 根据是否是搜索字段去查
     * @return
     */
    @GetMapping("params")
    public ResponseEntity<List<SpecParam>> queryParamList(
            @RequestParam(value = "gid", required = false) Long gid,
            @RequestParam(value = "cid", required = false) Long cid,
            @RequestParam(value = "searching", required = false) Boolean searching) {
        return ResponseEntity.ok(specificationService.queryParamList(gid, cid, searching));
    }
}
