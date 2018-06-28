package com.ch3nk.ch3nkSite.modules.sys.controller;

import com.ch3nk.ch3nkSite.common.entity.LayuiTableData;
import com.ch3nk.ch3nkSite.modules.sys.entity.SysUser;
import com.ch3nk.ch3nkSite.modules.sys.mapper.SysUserMapper;
import com.ch3nk.ch3nkSite.modules.sys.service.ISysUserService;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private ISysUserService sysUserService;

    @RequestMapping("/register")
    @RequiresPermissions("sys:register")
    public void saveOrUpdate(SysUser sysUser, HttpServletRequest request, HttpServletResponse response,
                             HttpSession session,Model model) throws ServletException, IOException {
        sysUserService.saveUser(sysUser);
    }

    @RequestMapping("toList")
    public String toList() {
        return "sys/user_list";
    }
    @RequestMapping("list")
    @ResponseBody
    public LayuiTableData  list(@RequestParam(value = "page",defaultValue = "1") int pageNum,
                                @RequestParam(value = "limit",defaultValue = "10") int pageSize) {
        List<SysUser> userList = sysUserService.findByPage(pageNum, pageSize);
        LayuiTableData tableData = new LayuiTableData();
        tableData.setCode(0);
        tableData.setCount(sysUserService.findCount());
        tableData.setMsg("");
        tableData.setData(userList);
        return tableData;
    }

}
