package com.leyou.item.service;

import com.leyou.common.enums.ExceptionEnum;
import com.leyou.common.exception.LyException;
import com.leyou.item.mapper.CategoryMapper;
import com.leyou.item.pojo.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @Author: FuJiaCheng
 * @Date: Create In 9:56 2018/11/3
 * @Modified:
 * @annotation: 商品类别Service
 */

@Service
public class CategoryService {
    
    @Autowired
    private CategoryMapper categoryMapper;
    
    
    public List<Category> queryCategoryListByPid(Long pid) {
        Category category = new Category();
        category.setParentId(pid);
        // select方法传入一个对象，根据对象中的非空属性作为查询条件，生成sql语句 select * from xxx where parentid = pid
        List<Category> list = categoryMapper.select(category);
        
        if (CollectionUtils.isEmpty(list)) {
            // 返回404
            throw new LyException(ExceptionEnum.CATEGORY_NOT_FOND);
        }
        return list;
    }
}
