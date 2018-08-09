package com.ch3nk.ch3nkSite.modules.sys.controller;

import com.ch3nk.ch3nkSite.modules.sys.entity.SysMenu;
import com.ch3nk.ch3nkSite.modules.sys.service.ISysMenuService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/menu")
public class MenuController {
    private Map<String,Object> jsonResult;

    @Qualifier("sysMenuServiceImpl")
    @Autowired
    private ISysMenuService sysMenuService;

    @RequestMapping(value = "/show")
    public String index() {
        return "sys/BMap";
    }

    @RequestMapping(value = "/tolist")
    public String toList(Model model) {
        List<SysMenu> list = sysMenuService.findAll();
        model.addAttribute("list",list);
        return "sys/menu_list_new";
    }


    @RequestMapping(value = "toAddOrEdit")
    public String toEdit(@RequestParam(required = false)String menuId, Model model) {
        if (StringUtils.isNotEmpty(menuId)) {
            SysMenu sysMenu = new SysMenu();
            sysMenu.setMenuId(menuId);
            List<SysMenu> list = sysMenuService.findBy(sysMenu);
            model.addAttribute("sysMenu",list);
            return "sys/menu_addOrEdit";
        }
        return "sys/menu_addOrEdit";
    }




    @RequestMapping(value = "/deleteBatch")
    @ResponseBody
    public Map<String,Object> deleteBatch(@RequestBody String[] menuIds) {
        jsonResult = new HashMap<String, Object>();
        sysMenuService.tombstoneBatch(menuIds);
        jsonResult.put("msg","success");
        return jsonResult;
    }


    @RequestMapping(value = "/saveMenu")
    @ResponseBody
    public Map<String,Object> saveMenu(SysMenu sysMenu) {
        jsonResult = new HashMap<String, Object>();
        if (sysMenu == null) {
            jsonResult.put("msg","fail");
            return jsonResult;
        }else {
            int count = sysMenuService.saveSysMenu(sysMenu);
            String res = count > 0 ? "success" : "fail";
            jsonResult.put("msg",res);
            return jsonResult;
        }
    }



}