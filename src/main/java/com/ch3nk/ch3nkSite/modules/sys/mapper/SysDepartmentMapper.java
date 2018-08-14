package com.ch3nk.ch3nkSite.modules.sys.mapper;

import com.ch3nk.ch3nkSite.modules.sys.entity.SysDepartment;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysDepartmentMapper {
    int deleteByPrimaryKey(@Param("deptId") String deptId);

    int insert(SysDepartment record);

    int insertSelective(SysDepartment record);

    SysDepartment selectByPrimaryKey(@Param("deptId") String deptId);

    int selectCount(SysDepartment sysDepartment);

    List<SysDepartment> selectBy(SysDepartment sysDepartment);

    List<SysDepartment> selectParents();

    int updateByPrimaryKeySelective(SysDepartment record);

    int updateByPrimaryKey(SysDepartment record);
}
