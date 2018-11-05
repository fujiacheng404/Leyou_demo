package com.leyou.item.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.leyou.common.enums.ExceptionEnum;
import com.leyou.common.exception.LyException;
import com.leyou.common.vo.PageResult;
import com.leyou.item.mapper.BrandMapper;
import com.leyou.item.pojo.Brand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @Author: FuJiaCheng
 * @Date: Create In 23:38 2018/11/3
 * @Modified:
 * @annotation:
 */

@Service
public class BrandService {
    
    @Autowired
    private BrandMapper brandMapper;
    
    public PageResult<Brand> queryBrandByPage(Integer page, Integer rows, String sortBy, Boolean desc, String key) {
        // 分页
        PageHelper.startPage(page, rows);
        // 过滤
        Example example = new Example(Brand.class);
        if (key != null && !key.trim().equals("")) {
            example.createCriteria().orLike("name", "%" + key + "%").orEqualTo("letter", key.toUpperCase());
        }
        // 排序
        if (sortBy != null && !sortBy.trim().equals("")) {
            String orderByClause = sortBy + (desc ? " DESC" : " ASC");
            example.setOrderByClause(orderByClause);
        }
        List<Brand> info = brandMapper.selectByExample(example);  //当前页的数据
        if (CollectionUtils.isEmpty(info)) {
            throw new LyException(ExceptionEnum.BRAND_NOT_FOND);
        }
        PageInfo<Brand> brandPageInfo = new PageInfo<>(info); //解析分页结果
        return new PageResult<Brand>(brandPageInfo.getTotal(), brandPageInfo.getPages(), info);
        //return brandPageInfo;
    }
}
