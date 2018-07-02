package com.ch3nk.ch3nkSite.modules.sys.mapper;

import java.util.List;

import com.ch3nk.ch3nkSite.modules.sys.entity.SysDic;
import org.apache.ibatis.annotations.Param;

public interface SysDicMapper {
//    long countByExample(SysDicExample example);

//    int deleteByExample(SysDicExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SysDic record);

    int insertSelective(SysDic record);

//    List<SysDic> selectByExample(SysDicExample example);

    SysDic selectByPrimaryKey(Integer id);

//    int updateByExampleSelective(@Param("record") SysDic record, @Param("example") SysDicExample example);

//    int updateByExample(@Param("record") SysDic record, @Param("example") SysDicExample example);

    int updateByPrimaryKeySelective(SysDic record);

    int updateByPrimaryKey(SysDic record);
}