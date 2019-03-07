package com.ch3nk.ch3nkSite.common.base.baseService;

import com.ch3nk.ch3nkSite.common.base.entity.BaseEntity;
import com.ch3nk.ch3nkSite.common.base.baseMapper.BaseMapper;
import com.ch3nk.ch3nkSite.common.exception.AffectedRowIsZeroException;
import com.ch3nk.ch3nkSite.common.exception.IllegalPasswordException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
@SuppressWarnings("ALL")
@Slf4j
public abstract class BaseService<T extends BaseEntity> {
    @Autowired
    private BaseMapper<T> mapper;

    public BaseService() {
        log.debug("init baseService... ");
    }

    /**
     * 插入数据
     * @param param 参数
     * @return      插入行数
     * @throws AffectedRowIsZeroException
     */
    public int insert(T param) throws AffectedRowIsZeroException, IllegalPasswordException {
        int i = mapper.insertSelective(param);
        if (i == 0) {
            throw new AffectedRowIsZeroException("0 row(s) inserted");
        }
        return i;
    }

    /**
     * 主键更新
     * @param param 参数
     * @return      更新行数
     * @throws AffectedRowIsZeroException
     */
    public int updateByPKey(T param) throws AffectedRowIsZeroException {
        int i = mapper.updateSelective(param);
        if (i == 0) {
            throw new AffectedRowIsZeroException("0 row(s) updated");
        }
        return i;
    }

    /**
     * 物理删除
     * @param key   主键
     * @return      删除行数
     */
    public int deleteForceByPKey(String key) {
        int i = mapper.delete(key);
        if (i == 0) {
            throw new AffectedRowIsZeroException("0 row(s) deleted");
        }
        return i;
    }

    /**
     * 逻辑删除
     * @param param   参数
     * @return        删除行数
     */
    public int deleteByPKey(T param) {
        int i = mapper.updateSelective(param);
        if (i == 0) {
            throw new AffectedRowIsZeroException("0 row(s) deleted");
        }
        return i;
    }

    /**
     * 主键查询
     * @param key   主键
     * @return      结果对象
     */
    public T findByPKey(String key) {
        return mapper.selectByPrimaryKey(key);
    }

    /**
     * 批量条件查询
     * @param param     参数
     * @return          结果集合
     */
    public List<T> find(T param) {
        return mapper.selectBy(param);
    }

    /**
     * 条件分页查询
     * @param param     参数
     * @param pageNum   当前页
     * @param pageSize  查询条数
     * @return          结果集合
     */
    public List<T> findByPage(T param,int pageNum,int pageSize) {return mapper.selectByPage(param,pageNum,pageSize);}


}
