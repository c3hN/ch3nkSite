package com.ch3nk.ch3nkSite.modules.sys.controller;

import com.ch3nk.ch3nkSite.modules.sys.entity.SysMenu;
import com.ch3nk.ch3nkSite.modules.sys.service.ISysMenuService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/menu")
public class  MenuController {
    private Map<String,Object> jsonResult;

    @Qualifier("sysMenuServiceImpl")
    @Autowired
    private ISysMenuService sysMenuService;

    @RequestMapping(value = "/tolist")
    public String toList(Model model) {
        List<SysMenu> list = sysMenuService.findAllParents();
        model.addAttribute("list",list);
        return "sys/menu_list";
    }

    @RequestMapping(value = "/loadTreeBranch")
    @ResponseBody
    public Map<String, Object> loadTreeBranch(String menuId) {
        jsonResult = new HashMap<String, Object>();
        SysMenu sysMenu = new SysMenu();
        sysMenu.setParentId(menuId);
        List<SysMenu> list = sysMenuService.findBy(sysMenu);
        List<String> list1 = new ArrayList<>();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String category = "菜单";
        String deleteFlag = "启用";
        for (SysMenu menu : list) {
            String createDate = format.format(menu.getCreateDate());
            if (StringUtils.equals("1",menu.getCategory())) {
                category = "操作";
            }
            if (StringUtils.equals("0",menu.getDeleteFlag())) {
                category = "禁用";
            }
            String tr = "<tr data-tt-id=\""+menu.getMenuId()+"\"data-tt-parent-id=\""+menu.getParentId()+"\" data-tt-branch=\""+menu.getHasBranch()+"\">" +
                    "<td>"+menu.getName()+"</td>" +
                    "<td  hidden=\"hidden\">"+menu.getMenuId()+"</td>" +
                    "<td>"+category+"</td>"+
                    "<td>"+menu.getHref()+"</td>" +
                    "<td>"+menu.getPermission()+"</td>" +
                    "<td>"+createDate+"</td>" +
                    "<td>"+deleteFlag+"</td>"+
                    "<td>"+menu.getRemark()+"</td>" +
                    "<td style=\"text-align: center\">" +
                    "<button class=\"btn btn-default btn-xs\" onclick=\"detailMenu(this)\">查看</button> \r"+
                    "<button class=\"btn btn-default btn-xs\" onclick=\"editMenu(this)\">编辑</button> \r" +
                    "<button class=\"btn btn-default btn-xs btn-danger\" onclick=\"deleteMenu(this)\">删除</button>" +
                    "</td></tr>";
            list1.add(tr);
        }
        jsonResult.put("data",list1);
        jsonResult.put("count",list.size());
        return jsonResult;
    }


    @RequestMapping(value = "toAddOrEdit")
    public String toEdit(@RequestParam(required = false)String menuId, Model model)
            throws JsonProcessingException {
        SysMenu menu1 = new SysMenu();
        menu1.setDeleteFlag("1");
        menu1.setCategory("0");
        List<SysMenu> list = sysMenuService.findBy(menu1);
        ObjectMapper mapper = new ObjectMapper();
        String value = mapper.writeValueAsString(list);
        model.addAttribute("nodes",value);
        if (StringUtils.isNotEmpty(menuId)) {
            SysMenu sysMenu = new SysMenu();
            sysMenu.setMenuId(menuId);
            SysMenu menu = sysMenuService.findBy(sysMenu).get(0);
            model.addAttribute("sysMenu",menu);
            return "sys/menu_edit";
        }
        return "sys/menu_add";
    }




    @RequestMapping(value = "/deleteMenu")
    @ResponseBody
    public String deleteMenu(String menuId) {
        String result = "error";
        if (StringUtils.isNotEmpty(menuId)) {
            sysMenuService.deleteByPK(menuId);
            result = "success";
        }
        return result;
    }


    @RequestMapping(value = "/saveOrUpdate",method = RequestMethod.POST)
    public String saveOrUpdate(SysMenu sysMenu) {
        if (StringUtils.isNotEmpty(sysMenu.getMenuId())) {  //编辑
            sysMenuService.updateMenu(sysMenu);
        }else{
            sysMenuService.saveSysMenu(sysMenu);
        }
        return "forward:/menu/tolist";
    }

}