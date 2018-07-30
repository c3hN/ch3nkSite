package com.ch3nk.ch3nkSite.modules.sys.mapper;

import com.ch3nk.ch3nkSite.modules.sys.entity.SysMenu;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysMenuMapper {
    int deleteByPrimaryKey(@Param("menuId") String menuId);

    int insert(SysMenu record);

    int insertSelective(SysMenu record);

    SysMenu selectByPrimaryKey(@Param("menuId") String menuId);

    /**
     * 选择查询
     * @param record
     * @return
     */
    List<SysMenu> selectBy(SysMenu record);

    /**
     * 选择查询条数
     * @param record
     * @return
     */
    int selectCountSelective(SysMenu record);

    int updateByPrimaryKeySelective(SysMenu record);

    int updateByPrimaryKey(SysMenu record);

    /**
     * 批量逻辑删除
     * @param menuIds
     * @return
     */
    int tombstoneByPKBatch(@Param("menuIds") String [] menuIds);

    /**
     * 批量逻辑恢复
     * @param menuIds
     * @return
     */
    int recoveByPKBatch(@Param("menuIds") String [] menuIds);
}
