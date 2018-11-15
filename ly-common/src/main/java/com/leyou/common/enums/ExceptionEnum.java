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
    CATEGORY_NOT_FOND(404, "商品分类未查到"),
    BRAND_NOT_FOND(404, "品牌未查到"),
    BRAND_SAVE_ERROR(500, "新增品牌失败"),
    SPEC_GROUP_NOT_FOUND(404, "商品规格组未查到"),
    SPEC_PARAM_NOT_FOUND(404, "商品规格组下具体参数未查到"),
    GOODS_NOT_FOND(404, "商品不存在"),
    CATEGORY_BRAND_SAVE_ERROR(500, "新增品牌失败"),
    GOODS_SAVE_ERROR(500, "新增商品失败"),
    UPLOAD_ERROR(500, "文件上传失败"),
    INVALID_FILE_TYPE(400, "无效的文件类型");
    
    private int code;
    private String msg;
}
