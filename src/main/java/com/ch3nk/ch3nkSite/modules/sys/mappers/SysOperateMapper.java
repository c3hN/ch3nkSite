package com.ch3nk.ch3nkSite.modules.sys.mappers;

import com.ch3nk.ch3nkSite.common.base.baseMapper.BaseMapper;
import com.ch3nk.ch3nkSite.modules.sys.entity.SysOperate;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * com.ch3nk.ch3nkSite.modules.sys.mappers
 * chenkai
 */
public interface SysOperateMapper extends BaseMapper<SysOperate> {


    List<SysOperate> selectByMenuIds(@Param("menuIds")List menuIds);
}
