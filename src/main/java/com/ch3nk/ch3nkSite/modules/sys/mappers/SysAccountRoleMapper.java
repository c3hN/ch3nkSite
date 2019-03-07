package com.ch3nk.ch3nkSite.modules.sys.mappers;

import com.ch3nk.ch3nkSite.common.base.baseMapper.BaseMapper;
import com.ch3nk.ch3nkSite.modules.sys.entity.SysAccountRole;
import com.ch3nk.ch3nkSite.modules.sys.entity.SysRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * com.ch3nk.ch3nkSite.modules.sys.mappers
 * chenkai
 */
public interface SysAccountRoleMapper extends BaseMapper {

    /**
     * 保存账号角色关联
     * @param acId
     * @param roleIds
     * @return
     */
    int insertAccountRoles(@Param("acId")String acId, @Param("roleIds")String[] roleIds);

    /**
     * 查询账号角色关联
     * @param acId
     * @return
     */
    List<SysAccountRole> selectAccountRoles(@Param("acId")String acId);

    /**
     * 删除账号角色关联
     * @param acId
     * @return
     */
    int deleteAccountRoles(@Param("acId")String acId);


}
