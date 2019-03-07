//package com.ch3nk.ch3nkSite.modules.sys.service;
//
//import com.ch3nk.ch3nkSite.modules.sys.entity.SysOperateMapper;
//import com.ch3nk.ch3nkSite.modules.sys.mapper.SysOperateMapper;
//import com.ch3nk.ch3nkSite.modules.utils.UUIDutil;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import java.util.Date;
//import java.util.List;
//
//public class SysOperationServiceImpl implements ISysOperationService {
//
//    @Autowired
//    private SysOperateMapper sysOperationMapper;
//
//    @Override
//    public int saveOperate(SysOperateMapper sysOperation) {
//        sysOperation.setId(UUIDutil.getUUID());
//        sysOperation.setCreateDate(new Date());
//        return sysOperationMapper.insertSelective(sysOperation);
//    }
//
//    @Override
//    public SysOperateMapper findById(String id) {
//        return sysOperationMapper.selectByPrimaryKey(id);
//    }
//
//    @Override
//    public List<SysOperateMapper> findOperations(SysOperateMapper sysOperation) {
//        return sysOperationMapper.selectBy(sysOperation);
//    }
//
//    @Override
//    public List<SysOperateMapper> findOperationsByPage(SysOperateMapper sysOperation, int pageNum, int pageSize) {
//        return sysOperationMapper.selectByPage(sysOperation,pageNum,pageSize);
//    }
//
//}
