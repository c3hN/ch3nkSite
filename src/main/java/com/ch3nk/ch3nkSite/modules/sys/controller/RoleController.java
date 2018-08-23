package com.ch3nk.ch3nkSite.modules.sys.controller;

import com.ch3nk.ch3nkSite.modules.sys.entity.SysDepartment;
import com.ch3nk.ch3nkSite.modules.sys.entity.SysRole;
import com.ch3nk.ch3nkSite.modules.sys.service.ISysDeptService;
import com.ch3nk.ch3nkSite.modules.sys.service.ISysRoleService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/role")
public class RoleController {
    private Map<String,Object> jsonResult;

    @Qualifier("sysRoleServiceImpl")
    @Autowired
    private ISysRoleService sysRoleService;

    @Qualifier("sysDeptServiceImpl")
    @Autowired
    private ISysDeptService sysDeptService;

    @RequestMapping(value = "/tolist")
    public String toList(Model model) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        SysDepartment department = new SysDepartment();
        List<SysDepartment> departmentList = sysDeptService.findBy(department);
        String value = mapper.writeValueAsString(departmentList);
        model.addAttribute("nodes",value);
        return "sys/role_list";
    }

    @RequestMapping(value = "/list")
    @ResponseBody
    public Map<String,Object> list(String order, String deptId) {
        jsonResult = new HashMap<String,Object>();
        if (StringUtils.isEmpty(deptId)) {
            return jsonResult;
        }
        SysDepartment department = new SysDepartment();
        department.setDeptId(deptId);
        SysRole sysRole = new SysRole();
        sysRole.setDepartment(department);
        sysRole.setDeleteFlag("1");
        List<SysRole> list = sysRoleService.findBy(sysRole);
        int count = sysRoleService.findCountBy(sysRole);
        jsonResult.put("data",list);
        return jsonResult;
    }


    @RequestMapping(value = "/toDetail")
    public String toDetail(String roleId,Model model) {
        if (StringUtils.isNotEmpty(roleId)) {
            SysRole role = new SysRole();
            role.setRoleId(roleId);
            SysRole sysRole = sysRoleService.findBy(role).get(0);
            model.addAttribute("sysRole",role);
            return "sys/role_detail";
        }
        return "sys/role_list";
    }

    @RequestMapping(value = "/toAddOrEdit")
    public String toAddOrEdit(@RequestParam(required = false) String deptId,
                              @RequestParam(required = false) String roleId,Model model) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        SysDepartment sysDepartment1 = new SysDepartment();
        List<SysDepartment> departmentList = sysDeptService.findBy(sysDepartment1);
        String value = mapper.writeValueAsString(departmentList);
        model.addAttribute("nodes",value);
        if (StringUtils.isNotEmpty(roleId)) {
            SysRole sysRole = new SysRole();
            sysRole.setRoleId(roleId);
            SysRole role = sysRoleService.findBy(sysRole).get(0);
            model.addAttribute("sysRole",role);
            return "sys/role_addOrEdit";
        }else if(StringUtils.isNotEmpty(deptId)) {
            SysDepartment department = new SysDepartment();
            department.setDeptId(deptId);
            SysDepartment sysDepartment = sysDeptService.findBy(department).get(0);
            model.addAttribute("sysDept",sysDepartment);
            return "sys/role_addOrEdit";
        }
        return "sys/role_addOrEdit";
    }

    @RequestMapping(value = "/logicalDelete")
    @ResponseBody
    public Map<String,Object> logicalDelete(String roleId) {
        jsonResult = new HashMap<String, Object>();
        if (StringUtils.isNotEmpty(roleId)) {
            SysRole sysRole = new SysRole();
            sysRole.setRoleId(roleId);
            sysRole.setDeleteFlag("0");
            int i = sysRoleService.updateRole(sysRole);
            if (i > 0) {
                jsonResult.put("data","success");
                return jsonResult;
            }
        }
        jsonResult.put("data","error");
        return jsonResult;
    }
	
	 @RequestMapping(value = "/save")
    public String save(SysRole sysRole) {
        return "forward:/role/tolist.do";
    }

}
