package com.leyou.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @Author: FuJiaCheng
 * @Date: Create In 21:40 2018/11/2
 * @Modified:
 * @annotation:
 */

@Getter
@NoArgsConstructor //生成一个无参构造函数
@AllArgsConstructor //生成一个所有参数的构造函数
public enum ExceptionEnum {
    /*
     * 枚举必须定义在类的最前边
     * */
    PRICE_CANNOT_BE_NULL(400, "价格不能为空"),
    CATEGORY_NOT_FOND(404,"商品分类未查到");
    
    private int code;
    private String msg;
}
