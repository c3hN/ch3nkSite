package com.ch3nk.ch3nkSite.modules.sys.controller;

import com.ch3nk.ch3nkSite.modules.sys.entity.SysMenu;
import com.ch3nk.ch3nkSite.modules.utils.UUIDutil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/menu")
public class MenuController {


    @RequestMapping(value = "/tolist")
    public String toList() {
        return "sys/menu_list";
    }

    @RequestMapping(value = "/list")
    @ResponseBody
    public Map<Object, Object> list(String category, Model model) {
        SysMenu menu1 = new SysMenu();
        SysMenu menu2 = new SysMenu();
        menu1.setMenuId(UUIDutil.getUUID());
        menu1.setCategory("0");
        menu1.setName("menu01");
        menu1.setMenuCode("m01");
        menu2.setMenuId(UUIDutil.getUUID());
        menu2.setCategory("0");
        menu2.setName("menu01");
        menu2.setMenuCode("m01");
        List list = new ArrayList<>();
        list.add(menu1);
        list.add(menu2);
        Map<Object, Object> map = new HashMap<>();
        map.put("code",0);
        map.put("msg","");
        map.put("count",2);
        map.put("data",list);
        return map;
    }

}
