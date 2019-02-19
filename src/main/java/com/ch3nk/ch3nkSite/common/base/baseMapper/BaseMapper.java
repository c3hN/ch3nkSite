package com.ch3nk.ch3nkSite.common.base.baseMapper;

import com.ch3nk.ch3nkSite.common.base.entity.BaseEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@SuppressWarnings("unused")
public interface BaseMapper<T extends BaseEntity> {
    /**
     * 分页查询
     * @param param     查询条件
     * @param pageNum   当前页
     * @param pageSize  分页条数
     * @return
     */
    List<T> selectByPage(@Param("sysBean")T param,@Param("pageNum") Integer pageNum,@Param("pageSize")Integer pageSize);

    /**
     * 条件查询
     * @param param     查询条件
     * @return
     */
    List<T> selectBy(@Param("sysBean") T param);

    /**
     * 条件查询条数
     * @param param  查询条件
     * @return       结果条数
     */
    int count(@Param("sysBean")T param);

    /**
     * 主键查询
     * @param key   主键
     * @return      结果对象
     */
    T selectByPrimaryKey(@Param("key") String key);

    /**
     * 选择插入
     * @param param     插入对象
     * @return          影响行数
     */
    int insertSelective(@Param("sysBean") T param);

    /**
     * 选择更新
     * @param param     更新对象
     * @return          影响行数
     */
    int updateSelective(@Param("sysBean") T param);

    /**
     * 主键删除
     * @param key
     * @return
     */
    int delete(@Param("key") String key);

    /**
     * 主键批量删除
     * @param keys   主键
     * @return      影响行数
     */
    int deleteBatch(@Param("keys")String[] keys);
}
