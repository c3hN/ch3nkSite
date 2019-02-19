//package com.ch3nk.ch3nkSite.modules.sys.service;
//
//import com.ch3nk.ch3nkSite.modules.sys.entity.SysOperation;
//import com.ch3nk.ch3nkSite.modules.sys.mapper.SysOperationMapper;
//import com.ch3nk.ch3nkSite.modules.utils.UUIDutil;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import java.util.Date;
//import java.util.List;
//
//public class SysOperationServiceImpl implements ISysOperationService {
//
//    @Autowired
//    private SysOperationMapper sysOperationMapper;
//
//    @Override
//    public int saveOperate(SysOperation sysOperation) {
//        sysOperation.setId(UUIDutil.getUUID());
//        sysOperation.setCreateDate(new Date());
//        return sysOperationMapper.insertSelective(sysOperation);
//    }
//
//    @Override
//    public SysOperation findById(String id) {
//        return sysOperationMapper.selectByPrimaryKey(id);
//    }
//
//    @Override
//    public List<SysOperation> findOperations(SysOperation sysOperation) {
//        return sysOperationMapper.selectBy(sysOperation);
//    }
//
//    @Override
//    public List<SysOperation> findOperationsByPage(SysOperation sysOperation, int pageNum, int pageSize) {
//        return sysOperationMapper.selectByPage(sysOperation,pageNum,pageSize);
//    }
//
//}
