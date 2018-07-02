package com.ch3nk.ch3nkSite.modules.sys.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("dic")
public class SysDicController {

    @RequestMapping("list")
    @ResponseBody
    public List list(String dicId) {

        return null;
    }
}
