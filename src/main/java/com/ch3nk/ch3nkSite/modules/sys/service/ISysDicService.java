package com.ch3nk.ch3nkSite.modules.sys.service;


import com.ch3nk.ch3nkSite.modules.sys.entity.SysDic;

import java.util.List;

public interface ISysDicService {
    /**
     * 查询所有未删除的父节点
     * @return
     */
    List<SysDic> findAllParentNod();

    /**
     * 根据父节点查询子节点
     * @param parentId
     * @return
     */
    List<SysDic> findNodeByParentId(String parentId);

    /**
     * 查询所有节点
     * @return
     */
    List<SysDic> findAllNodes();

}
