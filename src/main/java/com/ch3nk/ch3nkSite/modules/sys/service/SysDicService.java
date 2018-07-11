package com.ch3nk.ch3nkSite.modules.sys.service;

import com.ch3nk.ch3nkSite.modules.sys.entity.SysDic;
import com.ch3nk.ch3nkSite.modules.sys.mapper.SysDicMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 字典Service
 */
@Service
@Transactional(readOnly = true)
public class SysDicService implements ISysDicService{
    @Autowired
    private SysDicMapper sysDicMapper;

    @Override
    public List<SysDic> findAllParentNod() {
        return sysDicMapper.selectParentNodes();
    }

    @Override
    public List<SysDic> findNodeByParentId(String parentId) {
        return sysDicMapper.selectByParentId(parentId);
    }

    @Override
    public List<SysDic> findAllNodes() {
        return sysDicMapper.selectAll();
    }
}
