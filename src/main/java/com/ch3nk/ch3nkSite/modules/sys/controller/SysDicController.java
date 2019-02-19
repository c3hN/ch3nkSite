//package com.ch3nk.ch3nkSite.modules.sys.controller;
//
//import com.ch3nk.ch3nkSite.modules.sys.entity.SysDic;
//import com.ch3nk.ch3nkSite.modules.sys.service.ISysDicService;
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//import java.util.List;
//import java.util.Map;
//
//@Controller
//@RequestMapping("dic")
//public class SysDicController {
//    private Map<String,Object> jsonResult;
//
//    @Qualifier("sysDicServiceImpl")
//    @Autowired
//    private ISysDicService sysDicService;
//    /**
//     *加载字典树
//     */
//    @RequestMapping("index")
//    public String toList(Model model) throws JsonProcessingException {
//        ObjectMapper mapper = new ObjectMapper();
//        List<SysDic> all = sysDicService.findAll();
//        model.addAttribute("dicNodes",mapper.writeValueAsString(all));
//         return "sys/dic_list";
//    }
//
//
//
//
//}
