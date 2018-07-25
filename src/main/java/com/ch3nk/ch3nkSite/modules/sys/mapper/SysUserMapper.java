package com.ch3nk.ch3nkSite.modules.sys.mapper;

import com.ch3nk.ch3nkSite.modules.sys.entity.SysUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysUserMapper {
    /**
     * 根据主键删除
     * @param userId
     * @return
     */
    int deleteByPrimaryKey(@Param("userId") String userId);

    /**
     * 插入数据
     * @param record
     * @return
     */
    int insert(SysUser record);

    /**
     * 批量插入
     * @param list
     * @return
     */
    int insertBatch(List<SysUser> list);

    /**
     * 选择性插入数据
     * @param record
     * @return
     */
    int insertSelective(SysUser record);

    /**
     * 根据主键查询
     * @param userId
     * @return
     */
    SysUser selectByPrimaryKey(@Param("userId") String userId);

    /**
     * 根据登录账号查询
     * @param account
     * @return
     */
    SysUser selectByAccount(@Param("account") String account);

    /**
     * 条件查询所有用户
     * @return
     */
    List<SysUser> selectAll(@Param("deleteFlag")String deleteFlag);

    /**
     * 条件查询用户总数
     * @return
     */
    int selectCount(@Param("deleteFlag")String deleteFlag);

    /**
     * 根据主键选择性更新
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(SysUser record);

    /**
     * 根据主键更新
     * @param record
     * @return
     */
    int updateByPrimaryKey(SysUser record);

}
