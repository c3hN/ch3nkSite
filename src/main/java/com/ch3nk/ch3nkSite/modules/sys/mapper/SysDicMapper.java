package com.ch3nk.ch3nkSite.modules.sys.mapper;

import java.util.List;

import com.ch3nk.ch3nkSite.modules.sys.entity.SysDic;
import org.apache.ibatis.annotations.Param;

public interface SysDicMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SysDic sysDic);

    int insertSelective(SysDic sysDic);

    SysDic selectByPrimaryKey(Integer id);

    /**
     * 查询所有未删除父节点
     * @return
     */
    List<SysDic> selectParentNodes();

    int updateByPrimaryKeySelective(SysDic sysDic);

    int updateByPrimaryKey(SysDic sysDic);


    int deleteByDicId(@Param("dicId")String dicId);

    SysDic selectByDicId(@Param("dicId")String dicId);

    /**
     * 根据父节点查询
     * @param perentId
     * @return
     */
    List<SysDic> selectByParentId(@Param("parentId") String perentId);

    int updateByDicIdSelective(SysDic sysDic);
}