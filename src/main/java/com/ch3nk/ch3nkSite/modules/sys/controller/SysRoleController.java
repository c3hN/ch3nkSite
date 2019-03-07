package com.ch3nk.ch3nkSite.modules.sys.controller;

import com.ch3nk.ch3nkSite.common.base.baseController.BaseController;
import com.ch3nk.ch3nkSite.common.exception.AffectedRowIsZeroException;
import com.ch3nk.ch3nkSite.common.response.AjaxRespBean;
import com.ch3nk.ch3nkSite.modules.sys.entity.*;
import com.ch3nk.ch3nkSite.modules.sys.service.CompanyService;
import com.ch3nk.ch3nkSite.modules.sys.service.SysMenuService;
import com.ch3nk.ch3nkSite.modules.sys.service.SysRoleMenuService;
import com.ch3nk.ch3nkSite.modules.sys.service.SysRoleService;
import com.ch3nk.ch3nkSite.modules.utils.SQLUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;

/**
 * com.ch3nk.ch3nkSite.modules.sys.controller
 * chenkai
 */
@Controller
@RequestMapping("role")
public class SysRoleController extends BaseController {

    @Autowired
    private SysRoleService sysRoleService;
    @Autowired
    private CompanyService companyService;
    @Autowired
    private SysMenuService sysMenuService;
    @Autowired
    private SysRoleMenuService sysRoleMenuService;


    @RequestMapping("/view/index")
    public ModelAndView index() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Company company = new Company();
        company.setIsDeleted("0");
        List<Company> companies = companyService.find(company);
        return new ModelAndView("sys/role/role_list_1").
                addObject("nodes",mapper.writeValueAsString(companies));
    }

    @RequestMapping("/view/add")
    public ModelAndView add(@RequestParam(required = false)String companyId) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Company company = new Company();
        company.setIsDeleted("0");
        List<Company> companies = companyService.find(company);
        if (StringUtils.isNotEmpty(companyId)) {
            Company byPKey = companyService.findByPKey(companyId);
            return new ModelAndView("sys/role/role_form").
                    addObject("company",byPKey).
                    addObject("nodes",mapper.writeValueAsString(companies));
        }else {
            return new ModelAndView("sys/role/role_form").
                    addObject("nodes",mapper.writeValueAsString(companies));
        }
    }
    @RequestMapping("/view/edit")
    public ModelAndView edit(String roleId) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Company company = new Company();
        company.setIsDeleted("0");
        List<Company> companies = companyService.find(company);
        SysRole byPKey = sysRoleService.findByPKey(roleId);
        return new ModelAndView("sys/role/role_form").
                addObject("role",byPKey).
                addObject("nodes",mapper.writeValueAsString(companies));
    }

    @RequestMapping("/view/recover")
    public ModelAndView recover() {
        return new ModelAndView("sys/role/role_recover");
    }

    @RequestMapping(value = "/info/query")
    @ResponseBody
    public Map<String,Object> query(@RequestParam(required = false) String companyId,
                                    @RequestParam(required = false)String likeName,
                                    @RequestParam(required = false)String likeCode,
                                    @RequestParam(required = false)String likeType,
                                    @RequestParam("offset") int pageNum,
                                    @RequestParam("limit") int pageSize) {
        Map json = new HashMap<>();
        Company company = new Company();
        company.setId(companyId);
        SysRole sysRole = new SysRole();
        sysRole.setCompany(company);
        sysRole.setIsDeleted("0");
        if (StringUtils.isNotEmpty(likeName)) {
            sysRole.setLikeName(SQLUtil.escapeLike(likeName));
        }
        if (StringUtils.isNotEmpty(likeCode)) {
            sysRole.setLikeCode(SQLUtil.escapeLike(likeCode));
        }
        if (StringUtils.isNotEmpty(likeType)) {
            sysRole.setLikeType(likeType);
        }
        List<SysRole> byPage = sysRoleService.findByPage(sysRole, pageNum, pageSize);
        int count = sysRoleService.count(sysRole);
        json.put("rows",byPage);
        json.put("total",count);
        return json;
    }

    @RequestMapping("/info/queryRecover")
    @ResponseBody
    public Map<String,Object> queryRecover() {
        Map json = new HashMap<>();
        SysRole sysRole = new SysRole();
        sysRole.setIsDeleted("1");
        List<SysRole> sysRoles = sysRoleService.find(sysRole);
        int count = sysRoleService.count(sysRole);
        json.put("rows",sysRoles);
        json.put("total",count);
        return json;
    }

    @RequestMapping("/info/saveOrUpdate")
    public ModelAndView saveOrUpdate(SysRole sysRole) {
        if (StringUtils.isNotEmpty(sysRole.getRoleId())) {
            sysRole.beforeUpdate();
            sysRoleService.updateByPKey(sysRole);
        }else {
            sysRole.beforeInsert();
            sysRoleService.insert(sysRole);
        }
        return new ModelAndView("forward:/role/view/index");
    }

    @RequestMapping("/info/abandon")
    @ResponseBody
    public AjaxRespBean abandon(String roleId) {
        AjaxRespBean ajaxRespBean = null;
        SysRole byPKey = sysRoleService.findByPKey(roleId);
        if ("1".equals(byPKey.getIsDeleted())) {
            ajaxRespBean = AjaxRespBean.failResponse("已删除，请勿重复操作");
            return ajaxRespBean;
        }else if ("0".equals(byPKey.getIsDeleted())) {
            SysRole sysRole = new SysRole();
            sysRole.setRoleId(roleId);
            sysRole.setIsDeleted("1");
            try {
                sysRoleService.deleteByPKey(sysRole);
                ajaxRespBean = AjaxRespBean.successResponse();
            } catch (AffectedRowIsZeroException e) {
                ajaxRespBean = AjaxRespBean.failResponse("删除失败，请重试");
            }
        }
        return ajaxRespBean;
    }

    @RequestMapping("/info/recover")
    @ResponseBody
    public AjaxRespBean recover(String roleId) {
        AjaxRespBean ajaxRespBean = null;
        SysRole byPKey = sysRoleService.findByPKey(roleId);
        if ("0".equals(byPKey.getIsDeleted())) {
            ajaxRespBean = AjaxRespBean.failResponse("已还原，请勿重复操作");
            return ajaxRespBean;
        }else if ("1".equals(byPKey.getIsDeleted())) {
            SysRole sysRole = new SysRole();
            sysRole.setRoleId(roleId);
            sysRole.setIsDeleted("0");
            try {
                sysRoleService.updateByPKey(sysRole);
                ajaxRespBean = AjaxRespBean.successResponse("还原成功");
            } catch (AffectedRowIsZeroException e) {
                ajaxRespBean = AjaxRespBean.failResponse("还原失败，请重试");
            }
        }
        return ajaxRespBean;
    }

    @RequestMapping("/info/queryMenus")
    public ModelAndView queryMenus(String roleId) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        List<String> list = new LinkedList<String>();
        if (StringUtils.isNotEmpty(roleId)) {   //查询已拥有
            List<SysRoleMenu> roleMenus = sysRoleMenuService.findRoleMenus(roleId);
            for (SysRoleMenu s:roleMenus) {
                list.add(s.getMenu().getMenuId());
            }
        }
        SysMenu sysMenu = new SysMenu();
        sysMenu.setIsDeleted("0");
        List<SysMenu> sysMenus = sysMenuService.find(sysMenu);
        return new ModelAndView("sys/role/menus").
                addObject("roleId",roleId).
                addObject("checked",mapper.writeValueAsString(list)).
                addObject("nodes",mapper.writeValueAsString(sysMenus));
    }

    @RequestMapping("/info/queryOperations")
    public ModelAndView queryOperations(String roleId) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        List<SysRoleMenu> roleMenus = sysRoleMenuService.findRoleMenus(roleId);
        List<String> menuIds = new ArrayList<String>();
        for (int i=0,len=roleMenus.size(); i < len; i++) {
            menuIds.add(roleMenus.get(i).getMenu().getMenuId());
        }
        //查询菜单操作权限
        List<SysOperate> menuOperations = sysMenuService.findMenuOperations(menuIds);
//        operations.add(menuOperations);
        return new ModelAndView("sys/role/operations").
                addObject("roleId",roleId).
                addObject("nodes",mapper.writeValueAsString(menuOperations));
    }

    @RequestMapping("/info/delete")
    @ResponseBody
    public AjaxRespBean delete(String roleId) {
        AjaxRespBean ajaxRespBean = null;
        if (StringUtils.isNotEmpty(roleId)) {
            try {
                sysRoleService.deleteForceByPKey(roleId);
                ajaxRespBean = AjaxRespBean.successResponse("删除成功");
            } catch (Exception e) {
                ajaxRespBean = AjaxRespBean.failResponse("删除失败");
            }

        }else {
            ajaxRespBean = AjaxRespBean.failResponse("删除失败");
        }
        return ajaxRespBean;
    }

    @RequestMapping("/info/addMenus")
    @ResponseBody
    public AjaxRespBean addMenus(String roleId,String menuIds) {
        AjaxRespBean ajaxRespBean = null;
        if (StringUtils.isEmpty(roleId)) {
            ajaxRespBean = AjaxRespBean.failResponse("操作失败，请重试");
            return ajaxRespBean;
        }
        try {
            sysRoleService.addRoleMenus(roleId,menuIds);
            ajaxRespBean = AjaxRespBean.successResponse("添加成功");
        } catch (AffectedRowIsZeroException e) {
            ajaxRespBean = AjaxRespBean.failResponse("操作失败，请重试");
        }
        return ajaxRespBean;
    }

    @RequestMapping("/info/checkRoleMenus")
    @ResponseBody
    public AjaxRespBean checkRoleMenus(String roleId){
        AjaxRespBean ajaxRespBean = null;
        List<SysRoleMenu> roleMenus = sysRoleMenuService.findRoleMenus(roleId);
        if (roleMenus.size() == 0) {
            ajaxRespBean = AjaxRespBean.failResponse("请先分配菜单");
        }else {
            ajaxRespBean = AjaxRespBean.successResponse();
        }
        return ajaxRespBean;
    }

}
