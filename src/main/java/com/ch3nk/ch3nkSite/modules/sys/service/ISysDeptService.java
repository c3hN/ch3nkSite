package com.ch3nk.ch3nkSite.modules.sys.service;

import com.ch3nk.ch3nkSite.modules.sys.entity.SysDepartment;

import java.util.List;

public interface ISysDeptService {

    int findCount(SysDepartment sysDepartment);

    List<SysDepartment> findBy(SysDepartment sysDepartment);

    List<SysDepartment> findAllParents();

    int saveSysDept(SysDepartment sysDepartment);

    int updateSysDept(SysDepartment sysDepartment);

    void deleteSysDept(SysDepartment sysDepartment);

}
