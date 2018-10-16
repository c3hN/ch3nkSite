package com.ch3nk.ch3nkSite.modules.sys.service;

import com.ch3nk.ch3nkSite.modules.sys.entity.SysDic;
import com.ch3nk.ch3nkSite.modules.sys.mapper.SysDicMapper;
import com.ch3nk.ch3nkSite.modules.utils.UUIDutil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 字典Service
 */
@Service
@Transactional(readOnly = true)
public class SysDicServiceImpl implements ISysDicService{
    @Autowired
    private SysDicMapper sysDicMapper;

    @Override
    public int saveDic(SysDic sysDic) {
        sysDic.setDicId(UUIDutil.getUUID());
        return sysDicMapper.insertSelective(sysDic);
    }

    @Override
    public void deleteByDicId(String dicId) {
        sysDicMapper.deleteByPrimaryKey(dicId);
    }

    @Override
    public int updateDic(SysDic sysDic) {
        return sysDicMapper.updateByPrimaryKeySelective(sysDic);
    }

    @Override
    public SysDic findByDicId(String dicId) {
        return sysDicMapper.selectByPrimaryKey(dicId);
    }

    @Override
    public List<SysDic> findAll() {
        return sysDicMapper.selectAll();
    }

    @Override
    public List<SysDic> findParents() {
        return sysDicMapper.selectParents();
    }

    @Override
    public List<SysDic> findByParentId(String parentId) {
        return sysDicMapper.selectByParentId(parentId);
    }

    @Override
    public List<SysDic> findByParentType(String dicType) {
        return sysDicMapper.selectByParentType(dicType);
    }
}
