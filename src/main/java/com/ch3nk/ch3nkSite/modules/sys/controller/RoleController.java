package com.ch3nk.ch3nkSite.modules.sys.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/role")
public class RoleController {
    @RequestMapping(value = "/tolist")
    public String toList() {
        return "sys/role_list";
    }
}
