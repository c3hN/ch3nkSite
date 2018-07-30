package com.ch3nk.ch3nkSite.modules.sys.controller;

import com.ch3nk.ch3nkSite.modules.sys.service.ISysDicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("dic")
public class SysDicController {

    @Qualifier("sysDicServiceImpl")
    @Autowired
    private ISysDicService sysDicService;
    /**
     *加载字典树
     */
    @RequestMapping("loadTree")
    @ResponseBody
    public List list(String dicId) {
        return  sysDicService.findAllNodes();
    }





}
