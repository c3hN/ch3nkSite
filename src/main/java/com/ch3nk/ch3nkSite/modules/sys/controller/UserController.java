package com.ch3nk.ch3nkSite.modules.sys.controller;

import com.ch3nk.ch3nkSite.common.entity.LayuiTableData;
import com.ch3nk.ch3nkSite.modules.sys.entity.SysUser;
import com.ch3nk.ch3nkSite.modules.sys.service.ISysUserService;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.util.List;

@Controller
@RequestMapping(value = "/user")
public class UserController  {
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

    //用户列表页
    @RequestMapping(value = "/tolist")
    public String tolist() {
        return "sys/user_list";
    }

    //新增页面
    @RequestMapping(value = "/toAddOrEdit")
    public String toAdd(@RequestParam(required = false) String userId,Model model) {
        if (StringUtils.isNotBlank(userId)) {
            SysUser userById = sysUserService.findUserById(userId);
            model.addAttribute("sysUser",userById);
            return "sys/user_addOrEdit";
        }
        return "sys/user_addOrEdit";
    }

    //查看
    @RequestMapping(value = "/toDetail")
    public String show(String userId,Model model) {
        SysUser userById = sysUserService.findUserById(userId);
        model.addAttribute("sysUser",userById);
        return "sys/user_detail";
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


    //加载用户列表
    @RequestMapping(value = "/list")
    @ResponseBody
    public LayuiTableData list(@RequestParam(value = "page",defaultValue = "1") int pageNum,
                               @RequestParam(value = "limit",defaultValue = "10") int pageSize) {
        List<SysUser> userByPage = sysUserService.findUserByPage(pageNum, pageSize);
        LayuiTableData tableData = new LayuiTableData();
        tableData.setCode(0);
        tableData.setCount(sysUserService.findUserCount());
        tableData.setMsg("");
        tableData.setData(userByPage);
        return tableData;
    }

    @RequestMapping(value = "/importUsers")
    @ResponseBody
    public String importUsers(@RequestParam("file") MultipartFile file) {
        System.out.println(file);
        return  null;
    }
}
