package com.ch3nk.ch3nkSite.modules.sys.controller;

import com.ch3nk.ch3nkSite.modules.sys.entity.Company;
import com.ch3nk.ch3nkSite.modules.sys.service.CompanyService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("company")
@SuppressWarnings("unused")
public class CompanyController {

    @Autowired
    private CompanyService companyService;


    @RequestMapping("/view/index")
    public ModelAndView index() {
        return new ModelAndView("sys/company/list_1");
    }

    @RequestMapping("/view/add")
    public String add() {
        return "sys/company/form";
    }

    @RequestMapping("/view/edit")
    public ModelAndView edit(String id) {
        Company byPKey = companyService.findByPKey(id);
        return new ModelAndView("sys/company/form").
                addObject("company",byPKey);
    }

    @RequestMapping("/info/query")
    @ResponseBody
    public Map query(@RequestParam("offset")int pageNum,
                     @RequestParam("limit") int pageSize) {
        Map<Object, Object> json = new HashMap<>();
        Company company = new Company();
        company.setIsDeleted("0");
        List<Company> byPage = companyService.findByPage(company, pageNum, pageSize);
        int count = companyService.count(company);
        json.put("rows",byPage);
        json.put("total",count);
        return json;
    }

    @RequestMapping("/info/saveOrUpdate")
    public ModelAndView saveOrUpdate(Company company) {
        if (StringUtils.isNotEmpty(company.getId())) {
            Company byPKey = companyService.findByPKey(company.getId());
            company.beforeUpdate(byPKey);
            companyService.updateByPKey(company);
        }else {
            company.beforeInsert();
            companyService.insert(company);
        }
        return new ModelAndView("redirect:/company/view/index");
    }

}
