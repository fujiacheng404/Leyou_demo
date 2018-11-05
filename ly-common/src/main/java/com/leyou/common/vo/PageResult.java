package com.leyou.common.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Author: FuJiaCheng
 * @Date: Create In 23:48 2018/11/3
 * @Modified:
 * @annotation:
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageResult<T> {
    private Long total; //总条数
    private Integer totalPage; //总页数
    private List<T> items; //当前页数据
}
