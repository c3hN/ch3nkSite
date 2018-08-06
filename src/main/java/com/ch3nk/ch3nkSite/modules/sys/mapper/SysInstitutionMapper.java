package com.ch3nk.ch3nkSite.modules.sys.mapper;

import com.ch3nk.ch3nkSite.modules.sys.entity.SysInstitution;

public interface SysInstitutionMapper {
    int deleteByPrimaryKey(String inst_id);

    int insert(SysInstitution record);

    int insertSelective(SysInstitution record);

    SysInstitution selectByPrimaryKey(String inst_id);

    int updateByPrimaryKeySelective(SysInstitution record);

    int updateByPrimaryKey(SysInstitution record);
}
