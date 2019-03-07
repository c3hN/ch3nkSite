package com.ch3nk.ch3nkSite.modules.sys.controller;

import com.ch3nk.ch3nkSite.common.base.baseController.BaseController;
import com.ch3nk.ch3nkSite.common.response.AjaxRespBean;
import com.ch3nk.ch3nkSite.modules.sys.entity.SysAccount;
import com.ch3nk.ch3nkSite.modules.sys.entity.SysAccountRole;
import com.ch3nk.ch3nkSite.modules.sys.entity.SysRole;
import com.ch3nk.ch3nkSite.modules.sys.service.SysAccountService;
import com.ch3nk.ch3nkSite.modules.sys.service.SysRoleService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.weaver.loadtime.Aj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * com.ch3nk.ch3nkSite.modules.sys.controller
 * chenkai
 */
@Controller
@RequestMapping("account")
public class SysAccountController extends BaseController {
    @Autowired
    private SysAccountService sysAccountService;
    @Autowired
    private SysRoleService sysRoleService;


    @RequestMapping("/view/index")
    public ModelAndView index() {
        return new ModelAndView("sys/account/list");
    }

    @RequestMapping("/view/add")
    public ModelAndView add() {
        return new ModelAndView("sys/account/form");
    }

    @RequestMapping("/view/edit")
    public ModelAndView edit(String acId) {
        SysAccount account = null;
        if (StringUtils.isNotEmpty(acId)) {
            account = sysAccountService.findByPKey(acId);
        }
        return new ModelAndView("sys/account/form").
                addObject("account",account);
    }

    @RequestMapping("/view/addRole")
    public ModelAndView addRole(String acId) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        List<String> checked = new ArrayList<>();
        List<SysAccountRole> accountRoles = sysAccountService.findAccountRoles(acId);
        for (SysAccountRole s : accountRoles) {
            checked.add(s.getRole().getRoleId());
        }
        SysRole role = new SysRole();
        role.setIsDeleted("0");
        List<SysRole> sysRoles = sysRoleService.find(role);
        return new ModelAndView("sys/account/addRole").
                addObject("acId",acId).
                addObject("checked",mapper.writeValueAsString(checked)).
                addObject("nodes",mapper.writeValueAsString(sysRoles));
    }

    @RequestMapping("/info/query")
    @ResponseBody
    public Map<String,Object> query() {
        Map<String,Object> json = new HashMap<>();
        SysAccount account = new SysAccount();
        account.setIsDeleted("0");
        List<SysAccount> sysAccounts = sysAccountService.find(account);
        int count = sysAccountService.count(account);
        json.put("rows",sysAccounts);
        json.put("total",count);
        return json;
    }

    @RequestMapping("/info/saveOrUpdate")
    public ModelAndView saveOrUpdate(SysAccount sysAccount) {
        if (StringUtils.isNotEmpty(sysAccount.getAcId())) {
            sysAccount.setPassword(null);
            sysAccount.setBeforeUpdate();
            sysAccountService.updateByPKey(sysAccount);
        }else {
            sysAccount.beforeInsert();
            sysAccountService.insert(sysAccount);
        }
        return new ModelAndView("forward:/account/view/index.do");
    }

    @RequestMapping("/info/abandon")
    @ResponseBody
    public AjaxRespBean abandon(String acId) {
        AjaxRespBean ajaxRespBean = null;
        if(StringUtils.isEmpty(acId)) {
            ajaxRespBean = AjaxRespBean.failResponse("请求参数错误");
            return ajaxRespBean;
        }else {
            SysAccount account = new SysAccount();
            account.setAcId(acId);
            account.setIsDeleted("1");
            try {
                sysAccountService.deleteByPKey(account);
                ajaxRespBean = AjaxRespBean.successResponse("删除成功");
            } catch (Exception e) {
                ajaxRespBean = AjaxRespBean.failResponse("删除失败");
                return ajaxRespBean;
            }
        }
        return ajaxRespBean;
    }

    @RequestMapping("/info/saveRoles")
    @ResponseBody
    public AjaxRespBean saveRoles(String acId,String roleIds) {
        AjaxRespBean ajaxRespBean = null;
        try {
            sysAccountService.saveAccountRoles(acId,roleIds);
            ajaxRespBean = AjaxRespBean.successResponse("保存成功");
        } catch (Exception e) {
            ajaxRespBean = AjaxRespBean.failResponse("保存失败");
            return ajaxRespBean;
        }
        return ajaxRespBean;
    }
}
