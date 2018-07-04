package com.ch3nk.ch3nkSite.modules.sys.controller;

import com.ch3nk.ch3nkSite.modules.sys.entity.SysDic;
import com.ch3nk.ch3nkSite.modules.sys.service.ISysDicService;
import org.apache.commons.lang3.StringUtils;
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
    /**
     *加载字典树
     */
    @RequestMapping("loadTree")
    @ResponseBody
    public List list(String dicId) {
        //判断是否为初始化树或者获取子节点数据
        if (StringUtils.isBlank(dicId)){
            return sysDicService.findAllParentNod();
        }else {
            return sysDicService.findNodeByParentId(dicId);
        }
    }





}
