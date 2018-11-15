package com.leyou.item.service;

import com.leyou.common.enums.ExceptionEnum;
import com.leyou.common.exception.LyException;
import com.leyou.item.mapper.SpecGroupMapper;
import com.leyou.item.mapper.SpecParamMapper;
import com.leyou.item.pojo.SpecGroup;
import com.leyou.item.pojo.SpecParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @Author: FuJiaCheng
 * @Date: Create In 12:04 2018/11/9
 * @Modified:
 * @annotation:
 */

@Service
public class SpecificationService {
    
    @Autowired
    private SpecGroupMapper specGroupMapper;
    
    @Autowired
    private SpecParamMapper specParamMapper;
    
    /**
     * 根据分类id查询规格组
     *
     * @param cid
     * @return
     */
    public List<SpecGroup> queryGroupByCid(Long cid) {
        SpecGroup specGroup = new SpecGroup();
        specGroup.setCid(cid);
        
        List<SpecGroup> list = specGroupMapper.select(specGroup);
        if (CollectionUtils.isEmpty(list)) {
            throw new LyException(ExceptionEnum.SPEC_GROUP_NOT_FOUND);
        }
        return list;
    }
    
    /**
     * 查询参数的集合
     *
     * @param gid       根据组ID查询
     * @param cid       根据种类ID去查
     * @param searching 根据是否是搜索字段去查
     * @return
     */
    public List<SpecParam> queryParamList(Long gid, Long cid, Boolean searching) {
        SpecParam specParam = new SpecParam();
        specParam.setGroupId(gid);
        specParam.setCid(cid);
        specParam.setSearching(searching);
        
        List<SpecParam> paramList = specParamMapper.select(specParam);
        if (CollectionUtils.isEmpty(paramList)) {
            throw new LyException(ExceptionEnum.SPEC_PARAM_NOT_FOUND);
        }
        return paramList;
    }
    
}
