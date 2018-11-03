package com.leyou.item.pojo;

import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @Author: FuJiaCheng
 * @Date: Create In 9:44 2018/11/3
 * @Modified:
 * @annotation:
 */

@Table(name = "tb_category")
@Data
public class Category {
    @Id
    @KeySql(useGeneratedKeys = true)
    private Long id;
    private String name;
    private Long parentId;
    private boolean isParent;
    private Integer sort;
}
