package com.leyou.item.mapper;

import com.leyou.item.pojo.Brand;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

/**
 * @Author: FuJiaCheng
 * @Date: Create In 23:33 2018/11/3
 * @Modified:
 * @annotation: 品牌Mapper
 */
public interface BrandMapper extends Mapper<Brand> {
    
    /**
     * 新增商品分类和品牌中间表数据
     *
     * @param cid
     * @param bid
     * @return
     */
    @Insert("INSERT INTO tb_category_brand (category_id, brand_id) VALUES (#{cid},#{bid})")
    int insertCategoryBrand(@Param("cid") Long cid, @Param("bid") Long bid);
}
