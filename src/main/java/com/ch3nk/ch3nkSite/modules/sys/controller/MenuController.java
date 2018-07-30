package com.ch3nk.ch3nkSite.modules.sys.controller;

import com.ch3nk.ch3nkSite.modules.sys.entity.SysMenu;
import com.ch3nk.ch3nkSite.modules.sys.service.ISysMenuService;
import com.ch3nk.ch3nkSite.modules.utils.UUIDutil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import sun.plugin.util.UIUtil;

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
    public String toList() {
        return "sys/menu_list";
    }

    @RequestMapping(value = "/toAddOrEdit")
    public String toAddOrEdit() {
        return "sys/menu_addOrEdit";
    }

    @RequestMapping(value = "/tolistchildren")
    public String tolistchildren() {
        return "sys/menu_list_children";
    }

    @RequestMapping(value = "/list")
    @ResponseBody
    public Map<String, Object> list(String category, Model model,@RequestParam(value = "page")int pageNum,
                                    @RequestParam(value = "limit")int pageSize) {
        jsonResult = new HashMap<String, Object>();
        SysMenu sysMenu = new SysMenu();
        sysMenu.setCategory(category);
        sysMenu.setDeleteFlag("1");
        List<SysMenu> list = sysMenuService.findBy(sysMenu, pageNum, pageSize);
        int count = sysMenuService.findCount(sysMenu);
        jsonResult.put("code",0);
        jsonResult.put("msg","操作成功");
        jsonResult.put("count",count);
        jsonResult.put("data",list);
        return jsonResult;
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