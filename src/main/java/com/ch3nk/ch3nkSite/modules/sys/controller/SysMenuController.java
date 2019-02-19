package com.ch3nk.ch3nkSite.modules.sys.controller;

import com.ch3nk.ch3nkSite.common.base.baseController.BaseController;
import com.ch3nk.ch3nkSite.common.exception.AffectedRowIsZeroException;
import com.ch3nk.ch3nkSite.modules.sys.entity.SysMenu;
import com.ch3nk.ch3nkSite.modules.sys.service.SysMenuService;
import com.ch3nk.ch3nkSite.modules.utils.UUIDutil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Slf4j
@Controller
@RequestMapping("menu")
@SuppressWarnings("unused")
public class SysMenuController extends BaseController {

    @Autowired
    private SysMenuService sysMenuService;


    @RequestMapping("/view/index")
    public ModelAndView index() {
//        SysMenu sysMenu = new SysMenu();
//        sysMenu.setIsDeleted("0");
//        List<SysMenu> sysMenus = sysMenuService.find(sysMenu);
//        for (int i = 0; i < sysMenus.size(); i++) {
//            if (sysMenus.get(i).getParentId() == null) {
//                sysMenus.remove(i);
//            }
//        }
        return new ModelAndView("sys/menu/list_1");
//                addObject("list",sysMenus);
    }
    @RequestMapping("/view/add")
    public ModelAndView add() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        SysMenu menu = new SysMenu();
        menu.setIsDeleted("0");
        List<SysMenu> sysMenus = sysMenuService.find(menu);
        return new ModelAndView("sys/menu/menu_form").
                addObject("nodes",mapper.writeValueAsString(sysMenus));
    }

    @RequestMapping("/view/edit")
    public ModelAndView edit(String menuId) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        List<SysMenu> list = new ArrayList<>();
        SysMenu menu = new SysMenu();
        menu.setIsDeleted("0");
        List<SysMenu> sysMenus = sysMenuService.find(menu);
        for (SysMenu m : sysMenus) {
            if (! menuId.equals(m.getMenuId()) && !menuId.equals(m.getParentId())) {
                list.add(m);
            }
        }

        SysMenu byPKey = sysMenuService.findByPKey(menuId);
        return new ModelAndView("/sys/menu/menu_form").
                addObject("sysMenu",byPKey).
                addObject("nodes",mapper.writeValueAsString(list));
    }

    /**
     * 查询
     * @param parentId 父菜单id
     * @return
     */
    @RequestMapping("/info/query")
    @ResponseBody
    public Map<String,Object> query(@RequestParam(value = "nodeid",required = false)String parentId) {
        Map<String,Object> json = new HashMap();
        SysMenu menu = new SysMenu();
        if (StringUtils.isEmpty(parentId)) {
            menu.setIsDeleted("0");
            List<SysMenu> roots = new ArrayList<>();
            List<SysMenu> sysMenus = sysMenuService.find(menu);
            for (SysMenu m : sysMenus) {
                if (StringUtils.isEmpty(m.getParentId())) {
                    m.setLevel("0");
                    for (SysMenu s : sysMenus) {
                        if (StringUtils.equals(m.getMenuId(),s.getParentId())) {
                            m.setIsLeaf("false");
                            m.setExpanded("false");
                        }
                    }
                    roots.add(m);
                }
            }

            json.put("rows",roots);
        }else {
            menu.setParentId(parentId);
            List<SysMenu> sysMenus = sysMenuService.find(menu);
            for (SysMenu m : sysMenus) {
                m.setLevel("1");
                m.setExpanded("false");
            }
            json.put("rows",sysMenus);
        }
        return json;
    }

    @RequestMapping("/info/detail")
    public ModelAndView detail(String menuId) {
        SysMenu byPKey = sysMenuService.findByPKey(menuId);
        return new ModelAndView("sys/menu/menu_detail").
                addObject("menu",byPKey);
    }

    @RequestMapping("/info/saveOrUpdate")
    public ModelAndView save(SysMenu sysMenu) {
        if (StringUtils.isNotEmpty(sysMenu.getMenuId())) {
            SysMenu byPKey = sysMenuService.findByPKey(sysMenu.getMenuId());
            sysMenu.beforeUpdate(byPKey);
            try {
                sysMenuService.updateByPKey(sysMenu);
            } catch (AffectedRowIsZeroException e) {
                log.error(e.getMessage(),e);
            }
        }else {
            sysMenu.beforeInsert();
            try {
                int insert = sysMenuService.insert(sysMenu);
            } catch (AffectedRowIsZeroException e) {
                log.error(e.getMessage(),e);
            }
        }
        return new ModelAndView("redirect:/menu/view/index");
    }

    @RequestMapping("/info/queryDemo")
    @ResponseBody
    public Map queryDemo () {
        Map json = new HashMap();
        SysMenu menu = new SysMenu();
        menu.setIsDeleted("0");
        List<SysMenu> sysMenus = sysMenuService.find(menu);
        json.put("rows",sysMenus);
        return json;
    }

}
