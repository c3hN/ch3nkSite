package com.ch3nk.ch3nkSite.modules.sys.mapper;


import com.ch3nk.ch3nkSite.modules.sys.entity.SysOperate;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysOperationMapper {

    int insertSelective(SysOperate record);

    SysOperate selectByPrimaryKey(@Param("id") String id);

    List<SysOperate> selectBy(SysOperate sysOperation);

    List<SysOperate> selectByPage(@Param("sysOperation") SysOperate sysOperation, @Param("pageNum") int pageNum, @Param("pageSize") int pageSize);

}