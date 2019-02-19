package com.ch3nk.ch3nkSite.modules.sys.controller;

import com.ch3nk.ch3nkSite.modules.sys.entity.SysUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("demo")
public class DemoController {
    @RequestMapping("jqgrid")
    public String demoJq(HttpServletRequest request, SysUser sysUser) {
        return "sys/jqDemo";
    }
}
