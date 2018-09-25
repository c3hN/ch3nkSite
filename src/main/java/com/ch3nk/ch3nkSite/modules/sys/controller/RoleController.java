package com.ch3nk.ch3nkSite.modules.sys.controller;

import com.ch3nk.ch3nkSite.modules.sys.entity.SysDepartment;
import com.ch3nk.ch3nkSite.modules.sys.entity.SysMenu;
import com.ch3nk.ch3nkSite.modules.sys.entity.SysRole;
import com.ch3nk.ch3nkSite.modules.sys.service.ISysDeptService;
import com.ch3nk.ch3nkSite.modules.sys.service.ISysMenuService;
import com.ch3nk.ch3nkSite.modules.sys.service.ISysRoleService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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

    @Qualifier("sysMenuServiceImpl")
    @Autowired
    private ISysMenuService sysMenuService;

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
        SysDepartment department1 = new SysDepartment();
        department1.setState("1");
        SysMenu menu = new SysMenu();
        menu.setDeleteFlag("1");
        List<SysDepartment> by = sysDeptService.findBy(department1);
        model.addAttribute("nodes1",mapper.writeValueAsString(by));
        List<SysMenu> by1 = sysMenuService.findBy(menu);
        model.addAttribute("nodes2",mapper.writeValueAsString(by1));
        if (StringUtils.isNotEmpty(roleId)) {
            SysRole sysRole = new SysRole();
            sysRole.setRoleId(roleId);
            SysRole role = sysRoleService.findBy(sysRole).get(0);   //待编辑对象
            SysDepartment department = new SysDepartment();
            department.setDeptId(role.getDepartment().getDeptId());
            SysDepartment sysDepartment = sysDeptService.findBy(department).get(0);
            model.addAttribute("sysDept",sysDepartment);
            model.addAttribute("sysRole",role);
            return "sys/role_edit";
        }else if(StringUtils.isNotEmpty(deptId)) {
            SysDepartment department = new SysDepartment();
            department.setDeptId(deptId);
            SysDepartment sysDepartment = sysDeptService.findBy(department).get(0);
            model.addAttribute("sysDept",sysDepartment);
            return "sys/role_add";
        }
        return "sys/role_add";
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
	
    @RequestMapping(value = "/saveOrUpdate")
    public String save(SysRole sysRole) {
        if (StringUtils.isNotEmpty(sysRole.getRoleId())) {  //编辑
            sysRoleService.updateRole(sysRole);
        }else{
            sysRoleService.saveRole(sysRole);
        }
        return "forward:/role/tolist.do";
    }

    @RequestMapping(value = "/toRecove")
    public String toRecove() {
        return "sys/role_recove";
    }

    @RequestMapping(value = "/listRolesDeleted")
    @ResponseBody
    public Map<String,Object> listRolesDeleted(@RequestParam(required = false) String order) {
        jsonResult = new HashMap<String, Object>();
        SysRole sysRole = new SysRole();
        sysRole.setDeleteFlag("0");
        List<SysRole> by = sysRoleService.findBy(sysRole);
        jsonResult.put("data",by);
        return jsonResult;
    }

    @RequestMapping(value = "/delete")
    @ResponseBody
    public Map<String,Object> delete(String roleId) {
        jsonResult = new HashMap<String, Object>();
        if (StringUtils.isNotEmpty(roleId)) {
            sysRoleService.deleteRole(roleId);
            jsonResult.put("msg","success");
        }
        return jsonResult;
    }

    @RequestMapping(value = "/recoveRole")
    @ResponseBody
    public Map<String,Object> recoveRole(String roleId) {
        jsonResult = new HashMap<String,Object>();
        if (StringUtils.isNotEmpty(roleId)) {
            SysRole sysRole = new SysRole();
            sysRole.setRoleId(roleId);
            sysRole.setDeleteFlag("1");
            sysRoleService.updateRole(sysRole);
            jsonResult.put("msg","success");
        }
        return jsonResult;
    }



    @RequestMapping(value = "/formCheck")
    @ResponseBody
    public Map<String,Object> formCheck(SysRole sysRole) {
        jsonResult = new HashMap<String, Object>();
        boolean flag = false;
        if (StringUtils.isEmpty(sysRole.getRoleId())) { //新增输入校验
            int i = sysRoleService.findBy(sysRole).size();
            if (i == 0) {
                flag = true;
            }
        }else{ //编辑输入校验
            SysRole role = new SysRole();
            if (StringUtils.isNotEmpty(sysRole.getName())) {
                role.setName(sysRole.getName());
            }
            if (StringUtils.isNotEmpty(sysRole.geteName())) {
                role.seteName(sysRole.geteName());
            }
            List<SysRole> list = sysRoleService.findBy(role);
            if (list.size() == 0 || StringUtils.equals(sysRole.getRoleId(),list.get(0).getRoleId())) {
                flag = true;
            }
         }
        if (flag) {
            jsonResult.put("ok","可以使用");
        }else {
            jsonResult.put("error","已经被使用");
        }
        return jsonResult;
    }

}