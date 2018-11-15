package com.leyou.item.pojo;

import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @Author: FuJiaCheng
 * @Date: Create In 11:46 2018/11/9
 * @Modified:
 * @annotation:
 */

@Table(name = "tb_spec_group") //对应数据库的实体类名称
@Data
public class SpecGroup {
    @Id
    @KeySql(useGeneratedKeys = true)
    private Long id; //主键
    private Long cid; //商品分类
    private String name; //商品名称
}
