package com.ch3nk.ch3nkSite.modules.sys.mapper;

import java.util.List;

import com.ch3nk.ch3nkSite.modules.sys.entity.SysDic;
import org.apache.ibatis.annotations.Param;

public interface SysDicMapper {

    int deleteByPrimaryKey(@Param("dicId") String dicId);

    int insert(SysDic record);

    int insertSelective(SysDic record);

    SysDic selectByPrimaryKey(@Param("dicId") String dicId);

    List<SysDic> selectAll();

    List<SysDic> selectParents();

    List<SysDic> selectByParentId(@Param("parentId") String parentId);

    List<SysDic> selectByParentType(@Param("dicType")String dicType );

    int updateByPrimaryKeySelective(SysDic record);

    int updateByPrimaryKey(SysDic record);
}