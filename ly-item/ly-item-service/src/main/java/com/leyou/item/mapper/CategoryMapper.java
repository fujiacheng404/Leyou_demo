package com.leyou.item.mapper;

import com.leyou.item.pojo.Category;
import tk.mybatis.mapper.additional.idlist.IdListMapper;
import tk.mybatis.mapper.common.Mapper;

/**
 * @Author: FuJiaCheng
 * @Date: Create In 9:54 2018/11/3
 * @Modified:
 * @annotation: 商品类别Mapper
 */

/**
 * 继承 IdListMapper 可以根据多个ID去查询
 */
public interface CategoryMapper extends Mapper<Category>, IdListMapper<Category, Long> {
}
