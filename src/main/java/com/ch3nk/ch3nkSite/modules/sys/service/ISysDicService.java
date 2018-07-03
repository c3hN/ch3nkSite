package com.ch3nk.ch3nkSite.modules.sys.service;


import com.ch3nk.ch3nkSite.modules.sys.entity.SysDic;

import java.util.List;

public interface ISysDicService {
    /**
     * 查询所有未删除的父节点
     * @return
     */
    List<SysDic> findAllParentNod();

    List<SysDic> findNodeByParentId(String parentId);

}
