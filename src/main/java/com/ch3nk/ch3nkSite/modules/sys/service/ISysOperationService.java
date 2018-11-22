package com.ch3nk.ch3nkSite.modules.sys.service;

import com.ch3nk.ch3nkSite.modules.sys.entity.SysOperation;

import java.util.List;

public interface ISysOperationService {
    int saveOperate(SysOperation sysOperation);

    SysOperation findById(String id);

    List<SysOperation> findOperations(SysOperation sysOperation);

    List<SysOperation> findOperationsByPage(SysOperation sysOperation, int pageNum, int pageSize);
}
