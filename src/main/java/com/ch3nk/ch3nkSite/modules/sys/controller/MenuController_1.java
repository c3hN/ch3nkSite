//package com.ch3nk.ch3nkSite.modules.sys.controller;
//
//import com.ch3nk.ch3nkSite.modules.sys.entity.SysMenu;
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.apache.commons.lang3.StringUtils;
//import org.apache.shiro.authz.annotation.Logical;
//import org.apache.shiro.authz.annotation.RequiresPermissions;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.*;
//
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//@Controller
//@RequestMapping(value = "/menu")
//public class MenuController_1 {
//    private Map<String,Object> jsonResult;
//
//    @Qualifier("sysMenuServiceImpl")
//    @Autowired
//    private ISysMenuService sysMenuService;
//
//    @RequestMapping(value = "/index")
//    @RequiresPermissions(value = "menu:index")
//    public String toList(Model model) {
//        List<SysMenu> list = sysMenuService.findAllParents();
//        model.addAttribute("list",list);
//        return "sys/menu_list_1";
//    }
//
//    @RequestMapping(value = "operate",params = "op=add")
//    @RequiresPermissions(value = "menu:add")
//    public String addOperate(Model model) throws JsonProcessingException {
//        ObjectMapper mapper = new ObjectMapper();
//        List<SysMenu> byCategory = sysMenuService.findByCategory("0");
//        model.addAttribute("nodes",mapper.writeValueAsString(byCategory));
//        return "sys/menu_form";
//    }
//    @RequestMapping(value = "operate",params = "op=edit")
//    @RequiresPermissions(value = "menu:edit")
//    public String editOperate(String menuId,Model model) throws JsonProcessingException {
////        for (int i = 0; i < byCategory.size(); i++) {           //编辑时不能把上级节点选择当前节点的子节点
////            for (int j = 0; j < byParent.size(); j++) {
////                if (byCategory.get(i).getMenuId().equals(byParent.get(j).getMenuId())) {
////                    byCategory.remove(i);
////                }
////            }
////        }
////        String value = mapper.writeValueAsString(byCategory);
////        model.addAttribute("nodes", value);
////        SysMenu byMenuId = sysMenuService.findByMenuId(menuId);
////        model.addAttribute("sysMenu", byMenuId);
////        if (StringUtils.isNotEmpty(parentId = byMenuId.getParentId())) {
////            SysMenu parent = sysMenuService.findByMenuId(parentId);
////            model.addAttribute("parentMenu", parent);
////        }
//
//        ObjectMapper mapper = new ObjectMapper();       //jackson
//        String parentId;
//        List<SysMenu> byCategory = sysMenuService.findByCategory("0");  //过滤“操作”
//        SysMenu sysMenu = new SysMenu();
//        sysMenu.setParentId(menuId);
//        sysMenu.setCategory("0");
//        List<SysMenu> byParent = sysMenuService.findBy(sysMenu);    //子菜单节点
////        List<SysMenu> byParent = sysMenuService.findByParent(menuId);   //子节点
//        int childSize = byParent.size();
//        for (int i = 0; i < byCategory.size(); i++) {
//            for (int j = 0; j < childSize; j++) {
//                if (byCategory.get(i).getMenuId().equals(byParent.get(j).getMenuId()) || byCategory.get(i).getMenuId().equals(menuId)) {
//                    byCategory.remove(i);
//                }
//            }
//        }
//        model.addAttribute("nodes",mapper.writeValueAsString(byCategory));
//        SysMenu byMenuId = sysMenuService.findByMenuId(menuId);
//        model.addAttribute("sysMenu",byMenuId);
//        if (StringUtils.isNotEmpty(parentId = byMenuId.getParentId())) {    //上级菜单信息
//            sysMenuService.findByMenuId(parentId);
//            model.addAttribute("parentMenu",parentId);
//        }
//        return "sys/menu_form";
//    }
//
//    @RequestMapping(value = "/loadTreeBranch")
//    @ResponseBody
//    public Map<String, Object> loadTreeBranch(String menuId) {
//        jsonResult = new HashMap<String, Object>();
//        List<SysMenu> list = sysMenuService.findByParent(menuId);
//        List<String> list1 = new ArrayList<>();
//        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
//        String category = "菜单";
//        String deleteFlag = "启用";
//        for (SysMenu menu : list) {
//            String createDate = format.format(menu.getCreateDate());
//            if (StringUtils.equals("1",menu.getCategory())) {
//                category = "操作";
//            }
//            if (StringUtils.equals("0",menu.getDeleteFlag())) {
//                deleteFlag = "禁用";
//            }
//            String tr = "<tr data-tt-id=\""+menu.getMenuId()+"\"data-tt-parent-id=\""+menu.getParentId()+"\" data-tt-branch=\""+menu.getHasBranch()+"\">" +
//                    "<td style=\"text-align: left;\">"+menu.getName()+"</td>" +
//                    "<td  hidden=\"hidden\">"+menu.getMenuId()+"</td>" +
//                    "<td>"+category+"</td>"+
//                    "<td>"+menu.getHref()+"</td>" +
//                    "<td>"+menu.getPermission()+"</td>" +
//                    "<td>"+createDate+"</td>" +
//                    "<td>"+deleteFlag+"</td>"+
//                    "<td>"+menu.getRemark()+"</td>" +
//                    "<td style=\"text-align: center\">" +
//                    "<button class=\"btn btn-default btn-xs\" onclick=\"detailMenu(this)\">查看</button> \r"+
//                    "<button class=\"btn btn-default btn-xs\" onclick=\"editMenu(this)\">编辑</button> \r" +
//                    "<button class=\"btn btn-default btn-xs btn-danger\" onclick=\"deleteMenu(this)\">删除</button>" +
//                    "</td></tr>";
//            list1.add(tr);
//        }
//        jsonResult.put("data",list1);
//        jsonResult.put("count",list.size());
//        return jsonResult;
//    }
//
//
////    @RequestMapping(value = "toAddOrEdit")
////    public String toEdit(@RequestParam(required = false)String menuId, Model model)
////            throws JsonProcessingException {
////        String parentId = "";
////        ObjectMapper mapper = new ObjectMapper();       //jackson
////        SysMenu menu1 = new SysMenu();
////        menu1.setCategory("0");
////        List<SysMenu> list = sysMenuService.findBy(menu1);
////        if (StringUtils.isNotEmpty(menuId)) {   //编辑
////            List<SysMenu> byParent = sysMenuService.findByParent(menuId);
////            for (int i=0;i<list.size();i++) {           //编辑时不能把上级节点选择当前节点的子节点
////                for (int j=0;j<byParent.size();j++) {
////                    if (list.get(i).getMenuId().equals(byParent.get(j).getMenuId())) {
////                        list.remove(i);
////                    }
////                }
////            }
////            String value = mapper.writeValueAsString(list);
////            model.addAttribute("nodes",value);
////            SysMenu byMenuId = sysMenuService.findByMenuId(menuId);
////            model.addAttribute("sysMenu",byMenuId);
////            if (StringUtils.isNotEmpty(parentId = byMenuId.getParentId())) {
////                SysMenu parent = sysMenuService.findByMenuId(parentId);
////                model.addAttribute("parentMenu",parent);
////            }
////            return "sys/menu_form";
////        }else{      //新增
////            String value = mapper.writeValueAsString(list);
////            model.addAttribute("nodes",value);
////            return "sys/menu_form";
////        }
////    }
//
//    @RequestMapping(value = "/saveOrUpdate")
//    @RequiresPermissions(value = {"menu:add","menu:edit"},logical = Logical.OR)
//    public String saveOrUpdate(SysMenu sysMenu) {
//        if (StringUtils.isNotEmpty(sysMenu.getMenuId())) {
//            String parentId = "";
//            SysMenu byMenuId = sysMenuService.findByMenuId(sysMenu.getMenuId());
//            sysMenuService.updateMenu(sysMenu);
//            if (StringUtils.isNotEmpty(parentId = byMenuId.getParentId())) {
//                if (!parentId.equals(sysMenu.getParentId())){
//                    if (sysMenuService.findByParent(parentId).size()==0){
//                        sysMenuService.updateHasBranch(parentId,"false");
//                    }
//                    sysMenuService.updateHasBranch(sysMenu.getParentId(),"true");
//                }
//            }else{
//                sysMenuService.updateHasBranch(sysMenu.getParentId(),"true");
//            }
//        }else {
//            if (StringUtils.isNotEmpty(sysMenu.getParentId())) {
//                SysMenu parent = sysMenuService.findByMenuId(sysMenu.getParentId());
//                if (!"true".equals(sysMenuService.findByMenuId(parent.getHasBranch()))) {
//                    sysMenuService.updateHasBranch(parent.getMenuId(),"true");
//                }
//            }
//            sysMenuService.saveSysMenu(sysMenu);
//        }
//        return "forward:/menu/index";
//    }
//
//
//    @RequestMapping(value = "/deleteMenu")
//    @ResponseBody
//    @RequiresPermissions(value = "menu:delete")
//    public String deleteMenu(String menuId) {
//        String result = "error";
//        String parentId = sysMenuService.findByMenuId(menuId).getParentId();
//        List<SysMenu> children = sysMenuService.findByParent(menuId);
//        if (children.size() == 0) {
//            sysMenuService.deleteByPK(menuId);
//            result = "success";
//            if (sysMenuService.findByParent(parentId).size() == 0) {
//                sysMenuService.updateHasBranch(parentId,"false");
//            }
//        }
//        return result;
//    }
//
////
////    @RequestMapping(value = "/save",method = RequestMethod.POST)
////    public String save(SysMenu sysMenu) {
////        if (StringUtils.isNotEmpty(sysMenu.getParentId())) {
////            SysMenu parent = sysMenuService.findByMenuId(sysMenu.getParentId());
////            if (!"true".equals(sysMenuService.findByMenuId(parent.getHasBranch()))) {
////                sysMenuService.updateHasBranch(parent.getMenuId(),"true");
////            }
////        }
////        sysMenuService.saveSysMenu(sysMenu);
////        return "forward:/menu/index";
////    }
////
////    @RequestMapping(value = "/update",method = RequestMethod.POST)
////    public String update(SysMenu sysMenu) {
////        String parentId = "";
////        SysMenu byMenuId = sysMenuService.findByMenuId(sysMenu.getMenuId());
////        sysMenuService.updateMenu(sysMenu);
////        if (StringUtils.isNotEmpty(parentId = byMenuId.getParentId())) {
////            if (!parentId.equals(sysMenu.getParentId())){
////                if (sysMenuService.findByParent(parentId).size()==0){
////                    sysMenuService.updateHasBranch(parentId,"false");
////                }
////                sysMenuService.updateHasBranch(sysMenu.getParentId(),"true");
////            }
////        }else{
////            sysMenuService.updateHasBranch(sysMenu.getParentId(),"true");
////        }
////
////        return "forward:/menu/index";
////    }
//}