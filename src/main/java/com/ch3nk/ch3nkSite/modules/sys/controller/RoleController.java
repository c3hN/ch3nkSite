//package com.ch3nk.ch3nkSite.modules.sys.controller;
//
//import com.alibaba.fastjson.JSONObject;
//import com.ch3nk.ch3nkSite.modules.sys.entity.SysDepartment;
//import com.ch3nk.ch3nkSite.modules.sys.entity.SysMenu;
//import com.ch3nk.ch3nkSite.modules.sys.entity.SysRole;
//import com.ch3nk.ch3nkSite.modules.sys.service.ISysDeptService;
//import com.ch3nk.ch3nkSite.modules.sys.service.ISysMenuService;
//import com.ch3nk.ch3nkSite.modules.sys.service.ISysRoleService;
//import com.ch3nk.ch3nkSite.modules.utils.SQLUtil;
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.apache.commons.lang3.StringUtils;
//import org.apache.ibatis.jdbc.SQL;
//import org.apache.shiro.SecurityUtils;
//import org.apache.shiro.authz.annotation.Logical;
//import org.apache.shiro.authz.annotation.RequiresPermissions;
//import org.apache.shiro.cache.CacheManagerAware;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.ResponseBody;
//import java.io.IOException;
//import java.util.Arrays;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//@Controller
//@RequestMapping(value = "/role")
//public class RoleController {
//    private Map<String,Object> jsonResult;
//
//    @Qualifier("sysRoleServiceImpl")
//    @Autowired
//    private ISysRoleService sysRoleService;
//
//    @Qualifier("sysDeptServiceImpl")
//    @Autowired
//    private ISysDeptService sysDeptService;
//
//    @Qualifier("sysMenuServiceImpl")
//    @Autowired
//    private ISysMenuService sysMenuService;
//
//    /**
//     * 首页
//     * @param model
//     * @return
//     * @throws JsonProcessingException
//     */
//    @RequestMapping(value = "/index")
//    @RequiresPermissions(value = "role:index")
//    public String toList(Model model) throws JsonProcessingException {
//        ObjectMapper mapper = new ObjectMapper();
//        SysDepartment department = new SysDepartment();
//        SysMenu menu = new SysMenu();
//        menu.setDeleteFlag("1");
//        List<SysDepartment> departmentList = sysDeptService.findBy(department);
//        model.addAttribute("nodes",mapper.writeValueAsString(departmentList));
//        List<SysMenu> by = sysMenuService.findBy(menu);
//        model.addAttribute("menus",mapper.writeValueAsString(by));
//        return "sys/role_list_1";
//    }
//
//    /**
//     * 跳转新增
//     * @param deptId
//     * @param model
//     * @return
//     */
//    @RequestMapping(value = "/operate",params = "op=add")
//    @RequiresPermissions(value = "role:add")
//    public String addOperate(@RequestParam(required = false)String deptId,Model model)
//            throws JsonProcessingException {
//        ObjectMapper mapper = new ObjectMapper();
//        SysDepartment department = new SysDepartment();
//        department.setState("1");
//        List<SysDepartment> by = sysDeptService.findBy(department);
//        model.addAttribute("depts",mapper.writeValueAsString(by));
//        if (StringUtils.isNotEmpty(deptId)) {
//            SysDepartment byDeptId = sysDeptService.findByDeptId(deptId);
//            model.addAttribute("dept",byDeptId);
//        }
//        return "sys/role_form";
//    }
//
//    /**
//     * 跳转编辑
//     * @param roleId
//     * @param model
//     * @return
//     */
//    @RequestMapping(value = "/operate",params = "op=edit")
//    @RequiresPermissions(value = "role:edit")
//    public String editOperate(String roleId,Model model) throws JsonProcessingException {
//        ObjectMapper mapper = new ObjectMapper();
//        SysDepartment department = new SysDepartment();
//        department.setState("1");
//        List<SysDepartment> by = sysDeptService.findBy(department);
//        model.addAttribute("depts",mapper.writeValueAsString(by));
//        if (StringUtils.isNotEmpty(roleId)) {
//            SysRole byRoleId = sysRoleService.findByRoleId(roleId);
//            model.addAttribute("role",byRoleId);
//        }
//        return "sys/role_form";
//    }
//    @RequestMapping(value = "/operate",params = "op=roleAddMenu")
//    @RequiresPermissions(value = "role:addMenus")
//    public String roleAddMenuOperate(String roleId,Model model) throws JsonProcessingException {
//        ObjectMapper mapper = new ObjectMapper();
//        if (StringUtils.isNotEmpty(roleId)) {
//            SysRole byRoleId = sysRoleService.findByRoleId(roleId);
//            model.addAttribute("role",byRoleId);
////            查询已授权的菜单用于回显
//            List<SysMenu> menusForRole = sysRoleService.findMenusForRole(roleId);
//            String[] menuIds = new String[menusForRole.size()];
//            for (int i = 0; i < menusForRole.size(); i++) {
//                menuIds[i] = menusForRole.get(i).getMenuId();
//            }
//            model.addAttribute("checkedMenus",mapper.writeValueAsString(menuIds));
//        }
//        SysMenu sysMenu = new SysMenu();
//        sysMenu.setDeleteFlag("1");
//        List<SysMenu> by = sysMenuService.findBy(sysMenu);
//        model.addAttribute("menuNodes",mapper.writeValueAsString(by));
//        return "sys/role_menu_form";
//    }
//    /**
//     * 跳转回收站
//     * @return
//     */
//    @RequestMapping(value = "/operate",params = "op=toRecover")
//    @RequiresPermissions(value = "role:toRecover")
//    public String recoverOperate () {
//        return "sys/role_recover";
//    }
//    @RequestMapping(value = "/operate",params = "op=detail")
//    @RequiresPermissions(value = "role:detail")
//    public String detailOperate(String roleId,Model model) throws JsonProcessingException {
//        ObjectMapper mapper = new ObjectMapper();
//        if (StringUtils.isNotEmpty(roleId)) {
//            SysRole byRoleId = sysRoleService.findByRoleId(roleId);
//            model.addAttribute("role",byRoleId);
//            List<SysMenu> menusForRole = sysRoleService.findMenusForRole(roleId);
//            model.addAttribute("menus",mapper.writeValueAsString(menusForRole));
//        }
//        return "sys/role_detail";
//    }
//    /**
//     * 查询
//     * @param order
//     * @param deptId
//     * @param pageNum
//     * @param pageSize
//     * @return
//     */
//    @RequestMapping(value = "/list")
//    @ResponseBody
//    @RequiresPermissions(value = "role:index")
//    public Map<String,Object> list(String order,
//                                   @RequestParam(required = false) String deptId,
//                                   @RequestParam(required = false) String likeName,
//                                   @RequestParam(required = false) String likeEName,
//                                   @RequestParam(required = false) String likeUseFlag,
//                                   @RequestParam("offset") int pageNum,
//                                   @RequestParam("limit")int pageSize) {
//        jsonResult = new HashMap<String,Object>();
//        SysRole sysRole = new SysRole();
//        sysRole.setDeleteFlag("1");
////      模糊查询处理
//        if (StringUtils.isNotEmpty(likeName)) {
//            sysRole.setLikeName(SQLUtil.escapeLike(likeName));
//        }
//        if (StringUtils.isNotEmpty(likeEName)) {
//            sysRole.setLikeEName(SQLUtil.escapeLike(likeEName));
//        }
//        if (StringUtils.isNotEmpty(likeUseFlag)) {
//            sysRole.setLikeUseFlag(SQLUtil.escapeLike(likeUseFlag));
//        }
//        if (StringUtils.isNotEmpty(deptId)) {
//            SysDepartment department = new SysDepartment();
//            department.setDeptId(deptId);
//            sysRole.setDepartment(department);
//        }
//        int countBy = sysRoleService.findCountBy(sysRole);
//        List<SysRole> byPage = sysRoleService.findByPage(sysRole, pageNum, pageSize);
//        jsonResult.put("rows",byPage);
//        jsonResult.put("total",countBy);
//        return jsonResult;
//    }
//    @RequestMapping(value = "/listMenusForRole")
//    @ResponseBody
//    @RequiresPermissions(value = "role:detail")
//    public Map<String,Object> listMenusForRole(String roleId) {
//        jsonResult = new HashMap<String, Object>();
//        if (StringUtils.isNotEmpty(roleId)) {
//            List<SysMenu> menusForRole = sysRoleService.findMenusForRole(roleId);
//            jsonResult.put("menus",menusForRole);
//        }
//        return jsonResult;
//    }
//
//    /**
//     * 查询
//     * @param order
//     * @param pageNum
//     * @param pageSize
//     * @param likeName
//     * @param likeEName
//     * @param likeUseFlag
//     * @return
//     */
//    @RequestMapping(value = "/listRolesDeleted")
//    @ResponseBody
//    @RequiresPermissions(value = "role:toRecover")
//    public Map<String,Object> listRolesDeleted(String order,
//                                               @RequestParam("offset")int pageNum,
//                                               @RequestParam("limit")int pageSize,
//                                               @RequestParam(required = false)String likeName,
//                                               @RequestParam(required = false)String likeEName,
//                                               @RequestParam(required = false)String likeUseFlag
//                                               ) {
//        jsonResult = new HashMap<String,Object>();
//        SysRole sysRole = new SysRole();
//        sysRole.setDeleteFlag("0");
////      模糊查询处理
//        if (StringUtils.isNotEmpty(likeName)) {
//            sysRole.setLikeName(SQLUtil.escapeLike(likeEName));
//        }
//        if (StringUtils.isNotEmpty(likeName)) {
//            sysRole.setLikeEName(SQLUtil.escapeLike(likeEName));
//        }
//        if (StringUtils.isNotEmpty(likeName)) {
//            sysRole.setLikeUseFlag(SQLUtil.escapeLike(likeUseFlag));
//        }
//        List<SysRole> byPage = sysRoleService.findByPage(sysRole,pageNum, pageSize);
//        int countBy = sysRoleService.findCountBy(sysRole);
//        jsonResult.put("rows",byPage);
//        jsonResult.put("total",countBy);
//        return jsonResult;
//    }
//
//
////    @RequestMapping(value = "/toAddOrEdit")
////    public String toAddOrEdit(@RequestParam(required = false) String deptId,
////                              @RequestParam(required = false) String roleId,Model model) throws JsonProcessingException {
////        ObjectMapper mapper = new ObjectMapper();
////        SysDepartment department1 = new SysDepartment();
////        department1.setState("1");
////        SysMenu menu = new SysMenu();
////        menu.setDeleteFlag("1");
////        List<SysDepartment> by = sysDeptService.findBy(department1);
////        model.addAttribute("nodes1",mapper.writeValueAsString(by));
////        List<SysMenu> by1 = sysMenuService.findBy(menu);
////        model.addAttribute("nodes2",mapper.writeValueAsString(by1));
////        if (StringUtils.isNotEmpty(roleId)) {
////            SysRole sysRole = new SysRole();
////            sysRole.setRoleId(roleId);
////            SysRole role = sysRoleService.findBy(sysRole).get(0);   //待编辑对象
////            SysDepartment department = new SysDepartment();
////            department.setDeptId(role.getDepartment().getDeptId());
////            SysDepartment sysDepartment = sysDeptService.findBy(department).get(0);
////            model.addAttribute("sysDept",sysDepartment);
////            model.addAttribute("sysRole",role);
////            return "sys/role_edit";
////        }else if(StringUtils.isNotEmpty(deptId)) {
////            SysDepartment department = new SysDepartment();
////            department.setDeptId(deptId);
////            SysDepartment sysDepartment = sysDeptService.findBy(department).get(0);
////            model.addAttribute("sysDept",sysDepartment);
////            return "sys/role_add";
////        }
////        return "sys/role_add";
////    }
//
////    @RequestMapping(value = "/toAddMenus")
////    public String toAddMenus(String roleId,Model model) throws JsonProcessingException {
////        ObjectMapper mapper = new ObjectMapper();
////        SysRole byRoleId = sysRoleService.findByRoleId(roleId);
////        model.addAttribute("role",byRoleId);
////        SysMenu menu = new SysMenu();
////        menu.setDeleteFlag("1");
////        List<SysMenu> by = sysMenuService.findBy(menu);
////        model.addAttribute("menus",mapper.writeValueAsString(by));
////        List<SysMenu> menusByRoleId = sysRoleService.findMenusByRoleId(roleId);
////        model.addAttribute("checkedMenus",menusByRoleId);
////        return "sys/role_toAddMenus";
////    }
//
//
//    @RequestMapping(value = "/toRecove")
//    @RequiresPermissions(value = "role:toRecover")
//    public String toRecove() {
//        return "sys/role_recove";
//    }
//
//    /**
//     * 保存/更新
//     * @param sysRole
//     * @return
//     */
//    @RequestMapping(value = "/saveOrUpdate")
//    @RequiresPermissions(value = {"role:add","role:edit"},logical = Logical.OR)
//    public String save(SysRole sysRole) {
//        if (StringUtils.isNotEmpty(sysRole.getRoleId())) {  //编辑
//            sysRoleService.updateRole(sysRole);
//        }else{
//            sysRoleService.saveRole(sysRole);
//        }
//        return "forward:/role/index.do";
//    }
//
//    /**
//     * 逻辑删除
//     * @param roleId
//     * @return
//     */
//    @RequestMapping(value = "/roleAbandon")
//    @ResponseBody
//    @RequiresPermissions(value = "role:abandon")
//    public Map<String,Object> roleAbandon(String roleId) {
//        jsonResult = new HashMap<String, Object>();
//        if (StringUtils.isNotEmpty(roleId)) {
//            SysRole sysRole = new SysRole();
//            sysRole.setRoleId(roleId);
//            sysRole.setUseFlag("0");
//            sysRole.setDeleteFlag("0");
//            int i = sysRoleService.updateRole(sysRole);
//            if (i > 0) {
//                jsonResult.put("data","success");
//                return jsonResult;
//            }
//        }
//        jsonResult.put("data","error");
//        return jsonResult;
//    }
//
//    @RequestMapping(value = "/rolesAbandonBatch")
//    @ResponseBody
//    @RequiresPermissions(value = "role:abandonBatch")
//    public Map<String,Object> rolesAbandonBatch(String[] roleIds) {
//        jsonResult = new HashMap<String, Object>();
//        if (roleIds.length != 0) {
//            sysRoleService.updateStateBatch(roleIds,"0","0");
//            jsonResult.put("msg","success");
//            return jsonResult;
//        }
//        jsonResult.put("msg","error");
//        return jsonResult;
//    }
//    /**
//     * 还原
//     * @param roleId
//     * @return
//     */
//    @RequestMapping(value = "/recoveRole")
//    @ResponseBody
//    @RequiresPermissions(value = {"role:toRecover","role:recover"},logical = Logical.AND)
//    public Map<String,Object> recoveRole(String roleId) {
//        jsonResult = new HashMap<String,Object>();
//        if (StringUtils.isNotEmpty(roleId)) {
//            SysRole sysRole = new SysRole();
//            sysRole.setRoleId(roleId);
//            sysRole.setUseFlag("1");
//            sysRole.setDeleteFlag("1");
//            sysRoleService.updateRole(sysRole);
//            jsonResult.put("msg","success");
//            return jsonResult;
//        }
//        jsonResult.put("msg","error");
//        return jsonResult;
//    }
//
//    /**
//     * 批量还原
//     * @param roleIds
//     * @return
//     */
//    @RequestMapping(value = "/recoveRolesBatch")
//    @ResponseBody
//    @RequiresPermissions(value = {"role:toRecover","role:recoverBatch"},logical = Logical.AND)
//    public Map<String,Object> recoveRolesBatch(String[] roleIds) {
//        jsonResult = new HashMap<String, Object>();
//        if (roleIds.length != 0) {
//            sysRoleService.updateStateBatch(roleIds,"1","1");
//            jsonResult.put("msg","success");
//            return jsonResult;
//        }
//        jsonResult.put("msg","error");
//        return jsonResult;
//    }
//
//    /**
//     * 删除
//     * @param roleId
//     * @return
//     */
//    @RequestMapping(value = "/deleteRole")
//    @ResponseBody
//    @RequiresPermissions(value = {"role:toRecover","role:delete"},logical = Logical.AND)
//    public Map<String,Object> deleteRole(String roleId) {
//        jsonResult = new HashMap<String, Object>();
//        if (StringUtils.isNotEmpty(roleId)) {
//            sysRoleService.deleteRole(roleId);
//            jsonResult.put("msg","success");
//            return jsonResult;
//        }
//        jsonResult.put("msg","error");
//        return jsonResult;
//    }
//
//    /**
//     * 批量删除
//     * @param roleIds
//     * @return
//     */
//    @RequestMapping(value = "/deleteRolesBatch")
//    @ResponseBody
//    @RequiresPermissions(value = {"role:toRecover","role:deleteBatch"})
//    public Map<String,Object> deleteRolesBatch(String[] roleIds) {
//        jsonResult = new HashMap<String, Object>();
//        return jsonResult;
//    }
//
//
//    @RequestMapping(value = "/formCheck")
//    @ResponseBody
//    @RequiresPermissions(value = {"role:add","role:edit"},logical = Logical.OR)
//    public Map<String,Object> formCheck(SysRole sysRole) {
//        jsonResult = new HashMap<String, Object>();
//        boolean flag = false;
//        if (StringUtils.isEmpty(sysRole.getRoleId())) { //新增输入校验
//            int i = sysRoleService.findBy(sysRole).size();
//            if (i == 0) {
//                flag = true;
//            }
//        }else{ //编辑输入校验
//            SysRole role = new SysRole();
//            if (StringUtils.isNotEmpty(sysRole.getName())) {
//                role.setName(sysRole.getName());
//            }
//            if (StringUtils.isNotEmpty(sysRole.geteName())) {
//                role.seteName(sysRole.geteName());
//            }
//            List<SysRole> list = sysRoleService.findBy(role);
//            if (list.size() == 0 || StringUtils.equals(sysRole.getRoleId (),list.get(0).getRoleId())) {
//                flag = true;
//            }
//         }
//        if (flag) {
//            jsonResult.put("ok","可以使用");
//        }else {
//            jsonResult.put("error","已经被使用");
//        }
//        return jsonResult;
//    }
//
//    @RequestMapping(value = "/saveRoleMenus")
//    @ResponseBody
//    @RequiresPermissions(value = "role:addMenus")
//    public String saveRoleMenus(String roleId, String[] menuIds) throws IOException {
//        if (StringUtils.isNotEmpty(roleId)) {
//            sysRoleService.deleteRoleMenus(roleId);
//            if (menuIds.length != 0) {
//                sysRoleService.saveRoleMenus(roleId,menuIds);
//                return "success";
//            }
//        }
//        return "error";
//    }
//
//}