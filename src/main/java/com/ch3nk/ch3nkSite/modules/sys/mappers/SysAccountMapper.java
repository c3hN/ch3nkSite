package com.ch3nk.ch3nkSite.modules.sys.mappers;

import com.ch3nk.ch3nkSite.common.base.baseMapper.BaseMapper;
import com.ch3nk.ch3nkSite.modules.sys.entity.SysAccount;
import org.apache.ibatis.annotations.Param;

public interface SysAccountMapper extends BaseMapper<SysAccount> {
    SysAccount selectByAccount(@Param("account") String account);
}
