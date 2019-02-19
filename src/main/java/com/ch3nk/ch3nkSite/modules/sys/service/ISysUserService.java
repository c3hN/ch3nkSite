//package com.ch3nk.ch3nkSite.modules.sys.service;
//
//import com.ch3nk.ch3nkSite.modules.sys.entity.SysRole;
//import com.ch3nk.ch3nkSite.modules.sys.entity.SysUser;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.util.List;
//import java.util.Map;
//
///**
// * 用户服务接口
// */
//public interface  ISysUserService {
//    /**
//     * 分页查询用户
//     * @param pageNum   当前页
//     * @param pageSize  每页数量
//     * @return
//     */
//    List<SysUser> findUserByPage(int pageNum, int pageSize, SysUser sysUser);
//
//    /**
//     * 查询用户总数
//     * @return
//     */
//    int findUserCount(SysUser sysUser);
//
//    /**
//     *
//     * @param sysUser
//     * @return
//     */
//    List<SysUser> findAllBy(SysUser sysUser);
//
//    /**
//     * 根据用户id查询
//     * @param userId
//     * @return
//     */
//    SysUser findUserById(String userId);
//
//    /**
//     * 根据登录账户查询
//     * @param account
//     * @return
//     */
//    SysUser findByAccount(String account);
//
//    /**
//     * 查询用户拥有的角色
//     * @param userId
//     * @return
//     */
//    List<SysRole> findRolesForUser(String userId);
//
//    /**
//     * 保存用户信息
//     * @param sysUser
//     * @return
//     */
//    int saveUser(SysUser sysUser);
//
//    /**
//     * 保存用户角色信息
//     * @param userId
//     * @param roleIds
//     * @return
//     */
//    int saveRolesForUser(String userId, String[] roleIds);
//
//    /**
//     * 更新用户信息
//     * @param sysUser
//     * @return
//     */
//    int updateUser(SysUser sysUser);
//
//    /**
//     *  批量更新用户删除状态
//     * @param userIds
//     * @param deleteFlag
//     * @return
//     */
//    int updateUserStateBatch(String[] userIds,String deleteFlag);
//
//    /**
//     * 删除用户
//     * @param userId
//     */
//    void deleteUser(String userId);
//
//    /**
//     * 批量删除用户
//     * @param userIds
//     */
//    void deleteUserBatch(String[] userIds);
//
//    /**
//     * 删除用户角色信息
//     * @param userId
//     */
//    void deleteRolesForUser(String userId);
//
//    /**
//     * 用户导入
//     * @param file
//     */
//    Map<String, Object> importUsersFromExc(MultipartFile file);
//
//}
