package com.ch3nk.ch3nkSite.modules.sys.controller;

import com.ch3nk.ch3nkSite.modules.sys.entity.SysMenu;
import com.ch3nk.ch3nkSite.modules.sys.service.ISysMenuService;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Principal;
import java.util.List;

@Controller
public class LoginController {

    @Qualifier("sysMenuServiceImpl")
    @Autowired
    private ISysMenuService menuServiceImpl;

    @RequestMapping("/main")
    public String main(Model model){
        SysMenu menu = new SysMenu();
        menu.setDeleteFlag("1");
        List<SysMenu> menuList = menuServiceImpl.findBy(menu);
        model.addAttribute("menus",menuList);
//        return "sys/index";
        return "sys/main";
    }

    @RequestMapping("/login")
    public String login(HttpServletRequest request, HttpServletResponse response,  Model model) throws ServletException, IOException {
        String exceptionClassName = (String) request.getAttribute("shiroLoginFailure");
        if(exceptionClassName!=null){
            if (UnknownAccountException.class.getName().equals(exceptionClassName)) {
                model.addAttribute("errorMsg", "账号不存在");
            } else if (IncorrectCredentialsException.class.getName().equals(
                    exceptionClassName)) {
                model.addAttribute("errorMsg", "用户名/密码错误");
            } else if (DisabledAccountException.class.getName().equals(exceptionClassName)){
                model.addAttribute("errorMsg","账户禁用");
            }else{
                model.addAttribute("errorMsg", "其他异常信息");
            }
        }
        //此方法不处理登陆成功（认证成功），shiro认证成功会自动跳转到上一个请求路径
        return "sys/login_new";
    }



}
