package test.mappers;

import com.ch3nk.ch3nkSite.modules.sys.entity.SysRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysRoleMapper {
    int deleteByPrimaryKey(@Param("roleId") String roleId);

    int insert(SysRole record);

    int insertSelective(SysRole record);

    SysRole selectByPrimaryKey(@Param("roleId") String roleId);

    int updateByPrimaryKeySelective(SysRole record);

    int updateByPrimaryKey(SysRole record);

    List<SysRole> selectBy(SysRole sysRole);

    int selectCountBy(SysRole sysRole);

    int insertRoleMenus(@Param("roleId") String roleId, @Param("menuIds") String[] menuIds);

}
