package com.ch3nk.ch3nkSite.modules.sys.controller;

import com.ch3nk.ch3nkSite.modules.sys.entity.SysRole;
import com.ch3nk.ch3nkSite.modules.sys.entity.SysUser;
import com.ch3nk.ch3nkSite.modules.sys.service.ISysRoleService;
import com.ch3nk.ch3nkSite.modules.sys.service.ISysUserService;
import com.ch3nk.ch3nkSite.modules.utils.SQLUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/user")
public class UserController  {

    private Map<String,Object> jsonResult;

    @Qualifier("sysUserServiceImpl")
    @Autowired
    private ISysUserService sysUserService;

    @Qualifier("sysRoleServiceImpl")
    @Autowired
    private ISysRoleService sysRoleService;

//    @RequestMapping(value = "/register")
//    public String register(SysUser sysUser,Model model){
//        int result = sysUserService.saveUser(sysUser);
//        if (result == 0) {
//            model.addAttribute("errorMsg","注册失败，请重试");
//            return "sys/register";
//        }else {
//            UsernamePasswordToken token = new UsernamePasswordToken(sysUser.getAccount(), sysUser.getUserPwd());
//            SecurityUtils.getSubject().login(token);
//            return "redirect:sys/home";
//        }
//    }

    @RequestMapping(value = "/tolist")
    public String tolist(){
        return "sys/user_list";
    }

    @RequestMapping(value = "/saveOrUpdate",method = RequestMethod.POST)
    public String saveOne(SysUser sysUser) {
        if (StringUtils.isNotEmpty(sysUser.getUserId())) {
            sysUserService.updateUser(sysUser);
        }else{
            sysUserService.saveUser(sysUser);
        }
        return "forward:/user/tolist";
    }


    @RequestMapping(value = "toAddOrEdit")
    public String toAdd(@RequestParam(required = false)String userId,Model model)
            throws JsonProcessingException {
        SysRole role = new SysRole();
        role.setUseFlag("1");
        role.setDeleteFlag("1");
        List<SysRole> list = sysRoleService.findBy(role);
        ObjectMapper mapper = new ObjectMapper();
        String value = mapper.writeValueAsString(list);
        model.addAttribute("nodes",value);
        if (StringUtils.isNotEmpty(userId)) {
            SysUser userById = sysUserService.findUserById(userId);
            model.addAttribute("sysUser",userById);
            return "sys/user_edit";
        }
        return "sys/user_add";
    }

    @RequestMapping(value = "/toDetail")
    public String detail(String userId,Model model) {
        SysUser userById = sysUserService.findUserById(userId);
        model.addAttribute("sysUser",userById);
        return "sys/user_detail";
    }

    @RequestMapping(value = "/toRecove")
    public String toRecove() {
        return "sys/user_recove";
    }

    @RequestMapping(value = "/listUsersDeleted")
    @ResponseBody
    public Map<String,Object> listUsersDeleted() {
        jsonResult = new HashMap<String, Object>();
        SysUser user = new SysUser();
        user.setDeleteFlag("0");
        List<SysUser> allBy = sysUserService.findAllBy(user);
        jsonResult.put("data",allBy);
        return jsonResult;
    }

    @RequestMapping(value = "/logicalDel")
    @ResponseBody
    public Map<String, Object> logicalDel(String userId) {
        jsonResult = new HashMap<String, Object>();
        if (StringUtils.isNotEmpty(userId)) {
            SysUser user = new SysUser();
            user.setUserId(userId);
            user.setLoginFlag("0");
            user.setDeleteFlag("0");
            int i = sysUserService.updateUser(user);
            if (i != 0) {
                jsonResult.put("msg","success");
                return jsonResult;
            }
        }
        jsonResult.put("msg","error");
        return jsonResult;
    }


    @RequestMapping(value = "/list")
    @ResponseBody
    public Map<String, Object> list(@RequestParam(value = "page",defaultValue = "1") int pageNum,
                                    @RequestParam(value = "limit",defaultValue = "10") int pageSize,
                                    @RequestParam(required = false)String likeAccount,
                                    @RequestParam(required = false)String likeNickName,
                                    @RequestParam(required = false)String likeCreateTime) throws ParseException {
        jsonResult = new HashMap<String,Object>();
        SysUser user = new SysUser();
        user.setDeleteFlag("1");
        if (StringUtils.isNotEmpty(likeAccount)) {
            user.setLikeAccount(SQLUtil.escapeLike(likeAccount));
        }
        if (StringUtils.isNotEmpty(likeNickName)) {
            user.setLikeNickName(SQLUtil.escapeLike(likeNickName));
        }
        if (StringUtils.isNotEmpty(likeCreateTime)) {
            user.setLikeCreateTime(SQLUtil.escapeLike(likeCreateTime));
        }
        List<SysUser> userByPage = sysUserService.findUserByPage(pageNum, pageSize, user);
        int count = sysUserService.findUserCount(user);
        jsonResult.put("code",0);
        jsonResult.put("msg","操作成功");
        jsonResult.put("count",count);
        jsonResult.put("data",userByPage);
        return jsonResult;
    }

    @RequestMapping(value = "/importUsers")
    @ResponseBody
    public Map<String, Object> importUsers(@RequestParam("file") MultipartFile file) {
        jsonResult = sysUserService.importUsersFromExc(file);
        return jsonResult;
    }


    @RequestMapping(value = "/switchLoginFlag")
    @ResponseBody
    public Map<String, Object> switchLoginFlag(String userId, String loginFlag) {
        jsonResult = new HashMap<String, Object>();
        SysUser sysUser = new SysUser();
        sysUser.setUserId(userId);
        sysUser.setLoginFlag(loginFlag);
        int i = sysUserService.updateUser(sysUser);
        jsonResult.put("count",i);
        jsonResult.put("msg","success");
        return jsonResult;
    }


    @RequestMapping(value = "/formCheck")
    @ResponseBody
    public Map<String,Object> formCheck(SysUser sysUser) {
        jsonResult = new HashMap<String, Object>();
        if (StringUtils.isNotEmpty(sysUser.getAccount())) {
            SysUser byAccount = sysUserService.findByAccount(sysUser.getAccount());
            if (byAccount == null) {
                jsonResult.put("ok","可以使用");
            }else if (StringUtils.equals(byAccount.getUserId(),sysUser.getUserId())) {
                jsonResult.put("ok","可以使用");
            }else{
                jsonResult.put("error","账号已存在");
            }
        }
        return jsonResult;
    }

    @RequestMapping(value ="/recoveUser")
    @ResponseBody
    public Map<String,Object> recoveUser(String userId) {
        jsonResult = new HashMap<String, Object>();
        if (StringUtils.isNotEmpty(userId)) {
            SysUser user = new SysUser();
            user.setUserId(userId);
            user.setLoginFlag("1");
            user.setDeleteFlag("1");
            sysUserService.updateUser(user);
            jsonResult.put("msg","success");
        }
        return jsonResult;
    }

    @RequestMapping(value = "/delete")
    @ResponseBody
    public Map<String,Object> delete(String userId) {
        jsonResult = new HashMap<String, Object>();
        if (StringUtils.isNotEmpty(userId)) {
            sysUserService.deleteUser(userId);
            jsonResult.put("msg","success");
        }
        return jsonResult;
    }
}
