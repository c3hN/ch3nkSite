package com.ch3nk.ch3nkSite.modules.sys.controller;

import com.ch3nk.ch3nkSite.modules.sys.entity.SysUser;
import com.ch3nk.ch3nkSite.modules.sys.service.ISysUserService;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

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

    @RequestMapping(value = "/register")
    public String register(SysUser sysUser,Model model){
        int result = sysUserService.saveUser(sysUser);
        if (result == 0) {
            model.addAttribute("errorMsg","注册失败，请重试");
            return "sys/register";
        }else {
            UsernamePasswordToken token = new UsernamePasswordToken(sysUser.getAccount(), sysUser.getUserPwd());
            SecurityUtils.getSubject().login(token);
            return "redirect:sys/home";
        }
    }

    @RequestMapping(value = "/saveOne")
    @ResponseBody
    public String saveOne(SysUser sysUser) {
        return "success";
    }

    @RequestMapping(value = "/tolist")
    public String tolist(){
        return "sys/user_list";
    }

    @RequestMapping(value = "toAddOrEdit")
    public String toAdd(@RequestParam(required = false)String userId,Model model) {
        if (StringUtils.isNotBlank(userId)) {
            SysUser userById = sysUserService.findUserById(userId);
            model.addAttribute("sysUser",userById);
            return "sys/user_addOrEdit";
        }
        return "sys/user_addOrEdit";
    }

    @RequestMapping(value = "/toDetail")
    public String detail(String userId,Model model) {
        SysUser userById = sysUserService.findUserById(userId);
        model.addAttribute("sysUser",userById);
        return "sys/user_detail";
    }

    @RequestMapping(value = "/toEdit")
    public String edit(String userId,Model model) {
        SysUser userById = sysUserService.findUserById(userId);
        model.addAttribute("sysUser",userById);
        return "sys/user_addOrEdit";
    }

    @RequestMapping(value = "/delete")
    @ResponseBody
    public String delete(String userId) {
        if (StringUtils.isNotBlank(userId)) {
            int res = sysUserService.tombstone(userId);
            if (res != 0) {
                return "success";
            }
        }
        return "error";
    }


    @RequestMapping(value = "/list")
    @ResponseBody
    public Map<String, Object> list(@RequestParam(value = "page",defaultValue = "1") int pageNum,
                                    @RequestParam(value = "limit",defaultValue = "10") int pageSize){
        jsonResult = new HashMap<String,Object>();
        List<SysUser> users = sysUserService.findUserByPage(pageNum,pageSize,"1");
        int count = sysUserService.findUserCount("1");
        jsonResult.put("code",0);
        jsonResult.put("msg","操作成功");
        jsonResult.put("count",count);
        jsonResult.put("data",users);
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
}
