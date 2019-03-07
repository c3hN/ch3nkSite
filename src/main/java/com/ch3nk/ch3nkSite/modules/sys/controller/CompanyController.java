package com.ch3nk.ch3nkSite.modules.sys.controller;

import com.ch3nk.ch3nkSite.common.base.baseController.BaseController;
import com.ch3nk.ch3nkSite.common.base.entity.BaseEntity;
import com.ch3nk.ch3nkSite.common.response.AjaxRespBean;
import com.ch3nk.ch3nkSite.modules.sys.entity.Company;
import com.ch3nk.ch3nkSite.modules.sys.entity.Department;
import com.ch3nk.ch3nkSite.modules.sys.service.CompanyService;
import com.ch3nk.ch3nkSite.modules.sys.service.DepartmentService;
import com.ch3nk.ch3nkSite.modules.utils.SQLUtil;
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
public class CompanyController extends BaseController {

    @Autowired
    private CompanyService companyService;
    @Autowired
    private DepartmentService departmentService;


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

    @RequestMapping("/view/detail")
    public ModelAndView detail(String id) {
        Company byPKey = companyService.findByPKey(id);
        return new ModelAndView("sys/detail").
                addObject("company",byPKey);
    }

    @RequestMapping("/info/query")
    @ResponseBody
    public Map query(@RequestParam("offset")int pageNum,
                     @RequestParam("limit") int pageSize,
                     @RequestParam(required = false)String likeName,
                     @RequestParam(required = false)String likeCode) {
        Map<Object, Object> json = new HashMap<>();
        Company company = new Company();
        company.setIsDeleted("0");
        if (StringUtils.isNotEmpty(likeName)) {
            company.setLikeName(SQLUtil.escapeLike(likeName));
        }
        if (StringUtils.isNotEmpty(likeCode)) {
            company.setLikeCode(SQLUtil.escapeLike(likeCode));
        }
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

    /**
     * 逻辑删除
     * @param key
     * @return
     */
    @RequestMapping("/info/abandon")
    @ResponseBody
    public AjaxRespBean abandon(@RequestParam("id")String key) {
        List<Department> departments = departmentService.find(null);
        for (Department d : departments) {
            if(d.getCompany().getId() == key) {
                return AjaxRespBean.failResponse("被使用，无法删除");
            }
        }
        Company company = new Company();
        company.setId(key);
        company.setIsDeleted("1");
        try {
            companyService.deleteByPKey(company);
        } catch (Exception e) {
            return AjaxRespBean.failResponse("删除失败，请重试");
        }
        return AjaxRespBean.successResponse("删除成功");
    }

}
