package com.ch3nk.ch3nkSite.modules.sys.controller;

import com.ch3nk.ch3nkSite.common.base.baseController.BaseController;
import com.ch3nk.ch3nkSite.common.exception.AffectedRowIsZeroException;
import com.ch3nk.ch3nkSite.common.response.AjaxRespBean;
import com.ch3nk.ch3nkSite.modules.sys.entity.SysMenu;
import com.ch3nk.ch3nkSite.modules.sys.entity.SysOperate;
import com.ch3nk.ch3nkSite.modules.sys.service.OperateService;
import com.ch3nk.ch3nkSite.modules.sys.service.SysMenuService;
import com.ch3nk.ch3nkSite.modules.utils.UUIDutil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.awt.*;
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
    @Autowired
    private OperateService operateService;


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
        SysMenu parent = null;
        String parentId = sysMenuService.findByPKey(menuId).getParentId();
        if (StringUtils.isNotEmpty(parentId)) {
            parent = sysMenuService.findByPKey(parentId);
        }
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
                addObject("parent",parent).
                addObject("nodes",mapper.writeValueAsString(list));
    }

    @RequestMapping("/view/detail")
    public ModelAndView detail(String menuId) {
        SysMenu byPKey = sysMenuService.findByPKey(menuId);
        return new ModelAndView("sys/menu/menu_detail").
                addObject("menu",byPKey);
    }

    @RequestMapping("/view/addOperate")
    public ModelAndView addOperate(String menuId) {
        return new ModelAndView("sys/menu/operateForm").
                addObject("menuId",menuId);
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


    @RequestMapping("/info/delete")
    @ResponseBody
    public AjaxRespBean delete (String menuId) {
        AjaxRespBean ajaxRespBean = null;
        if (StringUtils.isEmpty(menuId)) {
            ajaxRespBean = AjaxRespBean.failResponse("请求参数错误");
            return ajaxRespBean;
        }else {
            SysMenu sysMenu = new SysMenu();
            sysMenu.setParentId(menuId);
            List<SysMenu> sysMenus = sysMenuService.find(sysMenu);
            if (sysMenus.size() != 0) {
                ajaxRespBean = AjaxRespBean.failResponse("存在子菜单，不能删除");
                return ajaxRespBean;
            }else {
                try {
                    sysMenuService.deleteForceByPKey(menuId);
                } catch (Exception e) {
                    ajaxRespBean = AjaxRespBean.failResponse("删除失败，请重试");
                    return ajaxRespBean;
                }
                ajaxRespBean = AjaxRespBean.successResponse("删除成功");
            }
        }
        return ajaxRespBean;
    }


    @RequestMapping("/info/queryOperations")
    public ModelAndView queryOperations(String menuId) {
        List<SysOperate> operates = new ArrayList<SysOperate>();
        if (StringUtils.isNotEmpty(menuId)) {
            SysMenu sysMenu = new SysMenu();
            sysMenu.setMenuId(menuId);
            SysOperate sysOperate = new SysOperate();
            sysOperate.setMenu(sysMenu);
            operates = operateService.find(sysOperate);
        }
        return new ModelAndView("sys/menu/operate_list").
                addObject("operates",operates);
    }

    @RequestMapping("/info/checkOperate")
    @ResponseBody
    public AjaxRespBean checkOperate(String menuId) {
        AjaxRespBean ajaxRespBean = null;
        if (StringUtils.isEmpty(menuId)) {
            ajaxRespBean = AjaxRespBean.failResponse("请求参数错误");
            return ajaxRespBean;
        }else {
            SysMenu menu = new SysMenu();
            menu.setMenuId(menuId);
            SysOperate sysOperate = new SysOperate();
            sysOperate.setMenu(menu);
            List<SysOperate> sysOperates = operateService.find(sysOperate);
            if (sysOperates.size() == 0) {
                ajaxRespBean = AjaxRespBean.failResponse("还未添加权限");
                return ajaxRespBean;
            }else {
                ajaxRespBean = AjaxRespBean.successResponse();
            }
        }
        return ajaxRespBean;
    }
}
