package com.ch3nk.ch3nkSite.modules.sys.service;

import com.ch3nk.ch3nkSite.modules.sys.entity.SysDepartment;

import java.util.List;

public interface ISysDeptService {

    int findCount(SysDepartment sysDepartment);

    SysDepartment findByDeptId(String deptId);

    List<SysDepartment> findByParentId(String parentId);

    List<SysDepartment> findBy(SysDepartment sysDepartment);

    List<SysDepartment> findAll();

    List<SysDepartment> findAllParents();

    List<SysDepartment> findAllChildren();

    int saveSysDept(SysDepartment sysDepartment);

    int updateSysDept(SysDepartment sysDepartment);

    void deleteByDeptId(String deptId);

}
