//package com.ch3nk.ch3nkSite.modules.sys.service;
//
//import com.ch3nk.ch3nkSite.modules.sys.entity.SysDepartment;
//import com.ch3nk.ch3nkSite.modules.sys.mapper.SysDepartmentMapper;
//import com.ch3nk.ch3nkSite.modules.utils.UUIDutil;
//import org.apache.commons.lang3.StringUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.Date;
//import java.util.List;
//
//@Service
//@Transactional(readOnly = true)
//public class SysDeptServiceImpl implements ISysDeptService{
//    @Autowired
//    private SysDepartmentMapper sysDepartmentMapper;
//
//    @Override
//    public int findCount(SysDepartment sysDepartment) {
//        return sysDepartmentMapper.selectCount(sysDepartment);
//    }
//
//    @Override
//    public SysDepartment findByDeptId(String deptId) {
//        return sysDepartmentMapper.selectByPrimaryKey(deptId);
//    }
//
//    @Override
//    public List<SysDepartment> findByParentId(String parentId) {
//        SysDepartment sysDepartment = new SysDepartment();
//        sysDepartment.setParentId(parentId);
//        return sysDepartmentMapper.selectBy(sysDepartment);
//    }
//
//    @Override
//    public List<SysDepartment> findByState(String state) {
//        SysDepartment department = new SysDepartment();
//        department.setState(state);
//        return sysDepartmentMapper.selectBy(department);
//    }
//
//    @Override
//    public List<SysDepartment> findBy(SysDepartment sysDepartment) {
//        return sysDepartmentMapper.selectBy(sysDepartment);
//    }
//
//    @Override
//    public List<SysDepartment> findAll() {
//        return sysDepartmentMapper.selectBy(new SysDepartment());
//    }
//
//    @Override
//    public List<SysDepartment> findAllParents() {
//        return sysDepartmentMapper.selectParents();
//    }
//
//    @Override
//    public List<SysDepartment> findAllChildren() {
//        return sysDepartmentMapper.selectChildren();
//    }
//
//    @Override
//    public int saveSysDept(SysDepartment sysDepartment) {
//        if (StringUtils.isEmpty(sysDepartment.getDeptId())) {
//            Date date = new Date();
//            sysDepartment.setDeptId(UUIDutil.getUUID());
//            sysDepartment.setCreateDate(date);
//            sysDepartment.setUpdateDate(date);
//            return sysDepartmentMapper.insertSelective(sysDepartment);
//        }
//        return 0;
//    }
//
//    @Override
//    public int updateSysDept(SysDepartment sysDepartment) {
//        if (StringUtils.isNotEmpty(sysDepartment.getDeptId())) {
//            Date date = new Date();
//            sysDepartment.setUpdateDate(date);
//            return sysDepartmentMapper.updateByPrimaryKeySelective(sysDepartment);
//        }
//        return 0;
//    }
//
//    @Override
//    public int updateHasBranch(String deptId, String hasBranch) {
//        if (StringUtils.isAnyEmpty(deptId,hasBranch)) {
//            return 0;
//        }
//        SysDepartment department = new SysDepartment();
//        department.setDeptId(deptId);
//        department.setHasBranch(hasBranch);
//        return sysDepartmentMapper.updateByPrimaryKeySelective(department);
//    }
//
//    @Override
//    public void deleteByDeptId(String deptId) {
//        sysDepartmentMapper.deleteByPrimaryKey(deptId);
//    }
//}
