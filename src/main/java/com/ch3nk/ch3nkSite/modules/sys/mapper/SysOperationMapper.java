package com.ch3nk.ch3nkSite.modules.sys.mapper;


import com.ch3nk.ch3nkSite.modules.sys.entity.SysOperation;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysOperationMapper {

    int insertSelective(SysOperation record);

    SysOperation selectByPrimaryKey(@Param("id") String id);

    List<SysOperation> selectBy(SysOperation sysOperation);

    List<SysOperation> selectByPage(@Param("sysOperation") SysOperation sysOperation,@Param("pageNum") int pageNum,@Param("pageSize") int pageSize);

}