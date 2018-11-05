package com.leyou.item.pojo;

import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @Author: FuJiaCheng
 * @Date: Create In 23:34 2018/11/3
 * @Modified:
 * @annotation:
 */

@Data
@Table(name = "tb_brand")
public class Brand {
    @Id
    @KeySql(useGeneratedKeys = true) // 自增ID
    private Long id;
    private String name; //品牌名称
    private String image; //品牌图片
    private Character letter;
}
