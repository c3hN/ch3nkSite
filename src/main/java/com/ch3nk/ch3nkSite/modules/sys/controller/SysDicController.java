package com.ch3nk.ch3nkSite.modules.sys.controller;

import com.ch3nk.ch3nkSite.modules.sys.entity.SysDic;
import com.ch3nk.ch3nkSite.modules.sys.service.ISysDicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("dic")
public class SysDicController {

    @Autowired
    private ISysDicService sysDicService;

    @RequestMapping("init")
    @ResponseBody
    public List list() {
        List<SysDic> sysDicList = sysDicService.findAllParentNod();
        return sysDicList;
    }

    @RequestMapping("tolist")
    public String tolist() {
        return "sys/ztree";
    }
}
