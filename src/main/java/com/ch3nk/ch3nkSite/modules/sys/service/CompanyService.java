package com.ch3nk.ch3nkSite.modules.sys.service;

import com.ch3nk.ch3nkSite.common.base.baseService.BaseService;
import com.ch3nk.ch3nkSite.modules.sys.entity.Company;
import com.ch3nk.ch3nkSite.modules.sys.mappers.CompanyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@SuppressWarnings("unused")
public class CompanyService extends BaseService<Company> {
    @Autowired
    private CompanyMapper mapper;

    /**
     * 查询行数
     * @param company   参数
     * @return          行数
     */
    public int count(Company company) {
        return mapper.count(company);
    }
}
