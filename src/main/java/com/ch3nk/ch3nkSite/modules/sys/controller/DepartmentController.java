package com.ch3nk.ch3nkSite.modules.sys.controller;

import com.ch3nk.ch3nkSite.common.base.baseController.BaseController;
import com.ch3nk.ch3nkSite.modules.sys.entity.Company;
import com.ch3nk.ch3nkSite.modules.sys.entity.Department;
import com.ch3nk.ch3nkSite.modules.sys.service.CompanyService;
import com.ch3nk.ch3nkSite.modules.sys.service.DepartmentService;
import com.ch3nk.ch3nkSite.modules.utils.SQLUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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
@RequestMapping("department")
@SuppressWarnings("unused")
public class DepartmentController extends BaseController {

    @Autowired
    private DepartmentService departmentService;
    @Autowired
    private CompanyService companyService;


    @RequestMapping("/view/index")
    public ModelAndView index() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Company company = new Company();
        company.setIsDeleted("0");
        List<Company> companies = companyService.find(company);
        return new ModelAndView("sys/department/list").
                addObject("deptsNodes",mapper.writeValueAsString(companies));
    }

    @RequestMapping("/view/add")
    public ModelAndView add() {
        return new ModelAndView("sys/department/dept_form");
    }



    @RequestMapping("/info/query")
    @ResponseBody
    public Map query(@RequestParam("companyId")String companyId,
                     @RequestParam("offset")int pageNum,
                     @RequestParam("limit")int pageSize,
                     @RequestParam(required = false)String likeName,
                     @RequestParam(required = false)String likeCode) {
        Map<Object, Object> json = new HashMap<>();
        Company company = new Company();
        company.setId(companyId);
        Department department = new Department();
        department.setCompany(company);
        department.setIsDeleted("0");
        if (StringUtils.isNotEmpty(likeName)) {
            department.setLikeFullName(SQLUtil.escapeLike(likeName));
            department.setLikeShortName(SQLUtil.escapeLike(likeName));
        }
        if (StringUtils.isNotEmpty(likeCode)) {
            department.setCode(SQLUtil.escapeLike(likeCode));
        }
        List<Department> departments = departmentService.findByPage(department,pageNum,pageSize);
        int count = departmentService.count(department);
        json.put("rows",departments);
        json.put("total",count);
        return json;
    }
}
