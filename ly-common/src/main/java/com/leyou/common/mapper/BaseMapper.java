package com.leyou.common.mapper;

import tk.mybatis.mapper.additional.idlist.IdListMapper;
import tk.mybatis.mapper.additional.insert.InsertListMapper;
import tk.mybatis.mapper.annotation.RegisterMapper;
import tk.mybatis.mapper.common.Mapper;

/**
 * @Author: FuJiaCheng
 * @Date: Create In 22:52 2018/11/14
 * @Modified:
 * @annotation: 所有Mapper的基类，用于被继承
 */

@RegisterMapper //加上这个注解才可以被?扫描到
public interface BaseMapper<T> extends Mapper<T>, IdListMapper<T, T>, InsertListMapper<T> {
}
