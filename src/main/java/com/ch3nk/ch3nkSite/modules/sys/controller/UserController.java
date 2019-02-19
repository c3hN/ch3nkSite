//package com.ch3nk.ch3nkSite.modules.sys.controller;
//
//import com.alibaba.fastjson.JSONArray;
//import com.ch3nk.ch3nkSite.modules.sys.entity.SysDepartment;
//import com.ch3nk.ch3nkSite.modules.sys.entity.SysRole;
//import com.ch3nk.ch3nkSite.modules.sys.entity.SysUser;
//import com.ch3nk.ch3nkSite.modules.sys.service.ISysDeptService;
//import com.ch3nk.ch3nkSite.modules.sys.service.ISysRoleService;
//import com.ch3nk.ch3nkSite.modules.sys.service.ISysUserService;
//import com.ch3nk.ch3nkSite.modules.utils.SQLUtil;
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.apache.commons.lang3.StringUtils;
//import org.apache.shiro.authz.annotation.Logical;
//import org.apache.shiro.authz.annotation.RequiresPermissions;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.multipart.MultipartFile;
//import java.text.ParseException;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//@Controller
//@RequestMapping(value = "/user")
//public class UserController  {
//
//    private Map<String,Object> jsonResult;
//
//    @Qualifier("sysUserServiceImpl")
//    @Autowired
//    private ISysUserService sysUserService;
//
//    @Qualifier("sysRoleServiceImpl")
//    @Autowired
//    private ISysRoleService sysRoleService;
//
//    @Qualifier("sysDeptServiceImpl")
//    @Autowired
//    private ISysDeptService sysDeptService;
//
//    /**
//     *
//     * @param model
//     * @return
//     * @throws JsonProcessingException
//     */
//    @RequestMapping(value = "/index")
//    @RequiresPermissions(value = "user:index")
//    public String index(Model model) throws JsonProcessingException {
//        ObjectMapper mapper = new ObjectMapper();
//        List<SysDepartment> by = sysDeptService.findBy(new SysDepartment());
//        model.addAttribute("deptsNodes",mapper.writeValueAsString(by));
//        return "sys/user_list_2";
//    }
//
//    /**
//     * 保存/更新
//     * @param sysUser
//     * @return
//     */
//    @RequiresPermissions(value = {"user:add","user:edit"},logical = Logical.OR)
//    @RequestMapping(value = "/saveOrUpdate",method = RequestMethod.POST)
//    public String saveOne(SysUser sysUser) {
//        if (StringUtils.isNotEmpty(sysUser.getUserId())) {
//            sysUserService.updateUser(sysUser);
//        }else{
//            sysUserService.saveUser(sysUser);
//        }
//        return "forward:/user/index";
//    }
//
//    /**
//     * 跳转新增
//     * @param deptId
//     * @param model
//     * @return
//     * @throws JsonProcessingException
//     */
//    @RequestMapping(value = "/operate",params = "op=add")
//    @RequiresPermissions(value = "user:add")
//    public String addOperate (@RequestParam(required = false)String deptId,Model model)
//            throws JsonProcessingException {
//        ObjectMapper mapper = new ObjectMapper();
//        if (StringUtils.isNotEmpty(deptId)) {
//            SysDepartment byDeptId = sysDeptService.findByDeptId(deptId);
//            model.addAttribute("dept",byDeptId);
//        }
//        //新增时选择部门
//        SysDepartment department = new SysDepartment();
//        department.setState("1");
//        List<SysDepartment> by = sysDeptService.findBy(department);
//        model.addAttribute("deptsNodes",mapper.writeValueAsString(by));
//        return "sys/user_form";
//    }
//
//    /**
//     * 跳转编辑
//     * @param userId
//     * @param model
//     * @return
//     */
//    @RequestMapping(value = "/operate",params = "op=edit")
//    @RequiresPermissions(value = "user:edit")
//    public String editOperate (String userId,Model model) {
//        if (StringUtils.isNotEmpty(userId)) {
//            SysUser userById = sysUserService.findUserById(userId);
//            model.addAttribute("sysUser",userById);
//        }
//        return "sys/user_form";
//    }
//
//    /**
//     * 跳转回收站
//     * @return
//     */
//    @RequestMapping(value = "/operate",params = "op=toRecover")
//    @RequiresPermissions(value = "user:toRecover")
//    public String recoverOperate () {
//        return "sys/user_recover";
//    }
//
//    /**
//     * 跳转详情
//     * @param userId
//     * @param model
//     * @return
//     */
//    @RequestMapping(value = "/operate",params = "op=detail")
//    @RequiresPermissions(value = "user:detail")
//    public String userDetail(String userId,Model model) {
//        SysUser userById = sysUserService.findUserById(userId);
//        model.addAttribute("user",userById);
//        return "sys/user_detail";
//    }
//    @RequestMapping(value = "/operate",params = "op=addRoles")
//    @RequiresPermissions(value = "user:addRoles")
//    public String addRolesOperate(String userId,Model model) throws JsonProcessingException {
//        ObjectMapper objectMapper = new ObjectMapper();
//        SysUser userById = sysUserService.findUserById(userId);     //用户信息
//        model.addAttribute("user",userById);
//        SysRole sysRole = new SysRole();
//        SysDepartment department = new SysDepartment();
//        department.setDeptId(userById.getDepartment().getDeptId());
//        sysRole.setUseFlag("1");
//        sysRole.setDepartment(department);
//        List<SysRole> by = sysRoleService.findBy(sysRole);      //所属部门下角色
//        model.addAttribute("roles",objectMapper.writeValueAsString(by));
//        List<SysRole> rolesForUser = sysUserService.findRolesForUser(userId);
//        String[] roleIds = new String[rolesForUser.size()];
//        for (int i = 0; i < rolesForUser.size(); i++) {
//            roleIds[i] = rolesForUser.get(i).getRoleId();
//        }
//        model.addAttribute("checkedRoles",objectMapper.writeValueAsString(roleIds));
//        return "sys/user_role_form";
//    }
//    /**
//     * 查询
//     * @param deptId
//     * @param pageNum
//     * @param pageSize
//     * @param likeAccount
//     * @param likeNickName
//     * @param likeCreateTime
//     * @return
//     */
//    @RequestMapping(value = "/list")
//    @ResponseBody
//    @RequiresPermissions(value = "user:index")
//    public Map<String,Object> list( String order,
//                                    @RequestParam(required = false)String deptId,
//                                    @RequestParam("offset")int pageNum,
//                                    @RequestParam("limit")int pageSize,
//                                    @RequestParam(required = false)String likeAccount,
//                                    @RequestParam(required = false)String likeNickName,
//                                    @RequestParam(required = false)String likeCreateTime) {
//        jsonResult = new HashMap<String, Object>();
//        SysUser user = new SysUser();
//        user.setDeleteFlag("1");
//        //处理模糊查询
//        if (StringUtils.isNotEmpty(likeAccount)) {
//            user.setLikeAccount(SQLUtil.escapeLike(likeAccount));
//        }
//        if (StringUtils.isNotEmpty(likeNickName)) {
//            user.setLikeNickName(SQLUtil.escapeLike(likeNickName));
//        }
//        if (StringUtils.isNotEmpty(likeCreateTime)) {
//            user.setLikeCreateTime(SQLUtil.escapeLike(likeCreateTime));
//        }
//        if (StringUtils.isNotEmpty(deptId)) {
//            SysDepartment department = new SysDepartment();
//            department.setDeptId(deptId);
//            user.setDepartment(department);
//        }
////        List<SysUser> allBy = sysUserService.findAllBy(user);
//        int userCount = sysUserService.findUserCount(user);
//        List<SysUser> userByPage = sysUserService.findUserByPage(pageNum, pageSize, user);
//        jsonResult.put("rows",userByPage);
//        jsonResult.put("total",userCount);
//        return jsonResult;
//    }
//
//    /**
//     * 查询已移入回收站
//     * @return
//     */
//    @RequestMapping(value = "/listUsersDeleted")
//    @ResponseBody
//    @RequiresPermissions(value = "user:toRecover")
//    public Map<String,Object> listUsersDeleted(String order,
//                                               @RequestParam("offset")int pageNum,
//                                               @RequestParam("limit")int pageSize,
//                                               @RequestParam(required = false)String likeAccount,
//                                               @RequestParam(required = false)String likeNickName,
//                                               @RequestParam(required = false)String likeCreateTime) {
//        jsonResult = new HashMap<String, Object>();
//        SysUser user = new SysUser();
//        user.setDeleteFlag("0");
////      模糊查询处理
//        if (StringUtils.isNotEmpty(likeAccount)) {
//            user.setLikeAccount(SQLUtil.escapeLike(likeAccount));
//        }
//        if (StringUtils.isNotEmpty(likeNickName)) {
//            user.setLikeNickName(SQLUtil.escapeLike(likeNickName));
//        }
//        if (StringUtils.isNotEmpty(likeCreateTime)) {
//            user.setLikeCreateTime(SQLUtil.escapeLike(likeCreateTime));
//        }
//        List<SysUser> userByPage = sysUserService.findUserByPage(pageNum, pageSize, user);
//        int userCount = sysUserService.findUserCount(user);
//        jsonResult.put("rows",userByPage);
//        jsonResult.put("total",userCount);
//        return jsonResult;
//    }
//
//    /**
//     * 移入回收站
//     * @param userId
//     * @return
//     */
//    @RequestMapping(value = "/userAbandon",method = RequestMethod.POST)
//    @ResponseBody
//    @RequiresPermissions(value = "user:abandon")
//    public Map<String,Object> userAbandon(String userId) {
//        jsonResult = new HashMap<String, Object>();
//        if (StringUtils.isNotEmpty(userId)) {
//            SysUser sysUser = new SysUser();
//            sysUser.setUserId(userId);
//            sysUser.setLoginFlag("0");
//            sysUser.setDeleteFlag("0");
//            int i = sysUserService.updateUser(sysUser);
//            if (i != 0) {
//                jsonResult.put("msg","success");
//                jsonResult.put("total",i);
//                return jsonResult;
//            }
//        }
//        jsonResult.put("msg","error");
//        return jsonResult;
//    }
//    /**
//     * 批量移入回收站
//     * @param userIds
//     * @return
//     */
//    @RequestMapping(value = "/userAbandonBatch",method = RequestMethod.POST)
//    @ResponseBody
//    @RequiresPermissions(value = "user:abandonBatch")
//    public Map<String,Object> userAbandonBatch(String[] userIds) {
//        jsonResult = new HashMap<String, Object>();
//        if (userIds != null) {
//            int i = sysUserService.updateUserStateBatch(userIds, "0");
//            jsonResult.put("msg","success");
//            jsonResult.put("total",i);
//            return jsonResult;
//        }else {
//            jsonResult.put("msg","error");
//            return jsonResult;
//        }
//    }
//
//    /**
//     * 还原
//     * @param userId
//     * @return
//     */
//    @RequestMapping(value = "/userRecover",method = RequestMethod.POST)
//    @ResponseBody
//    @RequiresPermissions(value = "user:recover")
//    public Map<String,Object> userRecover(String userId) {
//        jsonResult = new HashMap<String, Object>();
//        if (StringUtils.isNotEmpty(userId)) {
//            SysUser sysUser = new SysUser();
//            sysUser.setUserId(userId);
//            sysUser.setLoginFlag("1");
//            sysUser.setDeleteFlag("1");
//            int i = sysUserService.updateUser(sysUser);
//            if (i != 0) {
//                jsonResult.put("msg","success");
//                jsonResult.put("total",i);
//                return jsonResult;
//            }
//        }
//        jsonResult.put("msg","error");
//        return jsonResult;
//    }
//    /**
//     *批量还原
//     * @param userIds
//     * @return
//     */
//    @RequestMapping(value = "/userRecoverBatch",method = RequestMethod.POST)
//    @ResponseBody
//    @RequiresPermissions(value = "user:recoverBatch")
//    public Map<String,Object> userRecoverBatch(String[] userIds) {
//        jsonResult = new HashMap<String, Object>();
//        if (userIds != null) {
//            int i = sysUserService.updateUserStateBatch(userIds, "1");
//            jsonResult.put("msg","success");
//            jsonResult.put("total",i);
//            return jsonResult;
//        }
//        jsonResult.put("msg","error");
//        return jsonResult;
//    }
//
//
//
//    /**
//     * 删除
//     * @param userId
//     * @return
//     */
//    @RequestMapping(value = "/deleteUser",method = RequestMethod.POST)
//    @ResponseBody
//    @RequiresPermissions(value = "user:delete")
//    public Map<String,Object> deleteUser(String userId) {
//        jsonResult = new HashMap<String, Object>();
//        if (StringUtils.isNotEmpty(userId)) {
//            sysUserService.deleteUser(userId);
//            jsonResult.put("msg","success");
//            return jsonResult;
//        }
//        jsonResult.put("msg","error");
//        return jsonResult;
//    }
//
//    /**
//     * 批量删除
//     * @param userIds
//     * @return
//     */
//    @RequestMapping(value = "/deleteUserBatch",method = RequestMethod.POST)
//    @ResponseBody
//    @RequiresPermissions(value = "user:deleteBatch")
//    public Map<String,Object> deleteUserBatch(String[] userIds) {
//        jsonResult = new HashMap<String,Object>();
//        if (userIds != null) {
//            sysUserService.deleteUserBatch(userIds);
//            jsonResult.put("msg","success");
//            return jsonResult;
//        }
//        jsonResult.put("msg","error");
//        return jsonResult;
//    }
//
//    /**
//     * 导入
//     * @param file
//     * @return
//     */
//    @RequestMapping(value = "/importUsers")
//    @ResponseBody
//    @RequiresPermissions(value = "user:import")
//    public Map<String, Object> importUsers(@RequestParam("file") MultipartFile file) {
//        jsonResult = sysUserService.importUsersFromExc(file);
//        return jsonResult;
//    }
//    @RequestMapping(value = "/exportUsersInfo")
//    public void exportUsersInfo() {
//        System.out.println("export");
//    }
//    /**
//     * 表单验证
//     * @param sysUser
//     * @return
//     */
//    @RequestMapping(value = "/formCheck")
//    @ResponseBody
//    @RequiresPermissions(value = {"user:add","user:edit"},logical = Logical.OR)
//    public Map<String,Object> formCheck(SysUser sysUser) {
//        jsonResult = new HashMap<String, Object>();
//        if (StringUtils.isNotEmpty(sysUser.getAccount())) {
//            SysUser byAccount = sysUserService.findByAccount(sysUser.getAccount());
//            if (byAccount == null) {
//                jsonResult.put("ok","可以使用");
//            }else if (StringUtils.equals(byAccount.getUserId(),sysUser.getUserId())) {
//                jsonResult.put("ok","可以使用");
//            }else{
//                jsonResult.put("error","账号已存在");
//            }
//        }
//        return jsonResult;
//    }
//
//    @RequestMapping(value = "saveUserRoles")
//    @ResponseBody
//    @RequiresPermissions(value = "user:addRoles")
//    public String addRoles(String userId,String[] roleIds) {
//        if (StringUtils.isNotEmpty(userId)) {
//            sysUserService.deleteRolesForUser(userId);
//            if (roleIds != null && roleIds.length != 0) {
//                sysUserService.saveRolesForUser(userId,roleIds);
//                return "success";
//            }
//        }
//        return "error";
//    }
//
//
//    @RequestMapping(value = "/demo")
//    public String demo () {
//        throw new NullPointerException();
//    }
//}
