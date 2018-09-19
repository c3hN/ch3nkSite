package com.ch3nk.ch3nkSite.modules.sys.service;

import com.ch3nk.ch3nkSite.modules.sys.entity.SysUser;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

/**
 * 用户服务接口
 */
public interface  ISysUserService {
    /**
     * 分页查询用户
     * @param pageNum   当前页
     * @param pageSize  每页数量
     * @return
     */
    List<SysUser> findUserByPage(int pageNum, int pageSize, SysUser sysUser);

    /**
     * 查询用户总数
     * @return
     */
    int findUserCount(SysUser sysUser);

    /**
     *
     * @param sysUser
     * @return
     */
    List<SysUser> findAllBy(SysUser sysUser);

    /**
     * 根据用户id查询
     * @param userId
     * @return
     */
    SysUser findUserById(String userId);

    /**
     * 根据登录账户查询
     * @param account
     * @return
     */
    SysUser findByAccount(String account);

    /**
     * 保存用户信息
     * @param sysUser
     * @return
     */
    int saveUser(SysUser sysUser);

    /**
     * 更新用户信息
     * @param sysUser
     * @return
     */
    int updateUser(SysUser sysUser);

    void deleteUser(String userId);


    /**
     * 用户导入
     * @param file
     */
    Map<String, Object> importUsersFromExc(MultipartFile file);

}
