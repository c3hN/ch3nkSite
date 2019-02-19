//package com.ch3nk.ch3nkSite.modules.sys.service;
//
//import com.ch3nk.ch3nkSite.modules.sys.entity.SysMenu;
//import com.ch3nk.ch3nkSite.modules.sys.entity.SysRole;
//
//import java.util.List;
//
//public interface ISysRoleService {
//    int saveRole(SysRole sysRole);
//
//    void deleteRole(String roleId);
//
//    int updateRole(SysRole sysRole);
//
//    /**
//     * 批量更新角色删除标记和可用状态
//     * @param roleIds
//     * @param deleteFlag
//     * @param useFlag
//     * @return
//     */
//    int updateStateBatch(String[] roleIds,String deleteFlag,String useFlag);
//
//    List<SysRole> findBy(SysRole sysRole);
//
//    SysRole findByRoleId(String roleId);
//
//    List<SysRole> findByPage(SysRole sysRole,int pageNum,int pageSize);
//
//    List<SysRole> findByDeptId(String deptId);
//
//    /**
//     * 查询角色授权的功能菜单
//     * @param roleId
//     * @return
//     */
//    List<SysMenu> findMenusForRole(String roleId);
//
//    List<SysMenu> findMenusForRole(String[] roleIds);
//
//    int findCountBy(SysRole sysRole);
//
//    int saveRoleMenus(String roleId,String[] menuIds);
//
//    void deleteRoleMenus(String roleId);
//
//    List<SysMenu> findMenusByRoleId(String roleId);
//
//}
