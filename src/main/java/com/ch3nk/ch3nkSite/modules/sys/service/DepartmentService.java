package com.ch3nk.ch3nkSite.modules.sys.service;

import com.ch3nk.ch3nkSite.common.base.baseService.BaseService;
import com.ch3nk.ch3nkSite.modules.sys.entity.Department;
import com.ch3nk.ch3nkSite.modules.sys.mappers.DepartmentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@SuppressWarnings("unused")
public class DepartmentService extends BaseService<Department> {
    @Autowired
    private DepartmentMapper mapper;

    public int count(Department department) {
        return mapper.count(department);
    }
}
