package com.ch3nk.ch3nkSite.modules.sys.service;

import com.ch3nk.ch3nkSite.modules.sys.entity.SysDepartment;
import com.ch3nk.ch3nkSite.modules.sys.mapper.SysDepartmentMapper;
import com.ch3nk.ch3nkSite.modules.utils.UUIDutil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class SysDeptServiceImpl implements ISysDeptService{
    @Autowired
    private SysDepartmentMapper sysDepartmentMapper;

    @Override
    public int findCount(SysDepartment sysDepartment) {
        return sysDepartmentMapper.selectCount(sysDepartment);
    }

    @Override
    public List<SysDepartment> findBy(SysDepartment sysDepartment) {
        return sysDepartmentMapper.selectBy(sysDepartment);
    }

    @Override
    public List<SysDepartment> findAllParents() {
        return sysDepartmentMapper.selectParents();
    }

    @Override
    public int saveSysDept(SysDepartment sysDepartment) {
        if (StringUtils.isEmpty(sysDepartment.getDeptId())) {
            Date date = new Date();
            sysDepartment.setDeptId(UUIDutil.getUUID());
            sysDepartment.setCreateDate(date);
            sysDepartment.setUpdateDate(date);
            return sysDepartmentMapper.insertSelective(sysDepartment);
        }
        return 0;
    }

    @Override
    public int updateSysDept(SysDepartment sysDepartment) {
        if (StringUtils.isNotEmpty(sysDepartment.getDeptId())) {
            Date date = new Date();
            sysDepartment.setUpdateDate(date);
            return sysDepartmentMapper.updateByPrimaryKeySelective(sysDepartment);
        }
        return 0;
    }

    @Override
    public void deleteSysDept(SysDepartment sysDepartment) {
        if (StringUtils.isNotEmpty(sysDepartment.getDeptId())) {
            sysDepartmentMapper.deleteByPrimaryKey(sysDepartment.getDeptId());
        }
    }
}