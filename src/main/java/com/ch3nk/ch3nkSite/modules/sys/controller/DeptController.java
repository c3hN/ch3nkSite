package com.ch3nk.ch3nkSite.modules.sys.controller;

import com.ch3nk.ch3nkSite.modules.sys.entity.SysDepartment;
import com.ch3nk.ch3nkSite.modules.sys.entity.SysRole;
import com.ch3nk.ch3nkSite.modules.sys.service.ISysDeptService;
import com.ch3nk.ch3nkSite.modules.sys.service.ISysRoleService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/dept")
public class DeptController {

    private Map<String,Object> jsonResult;

    @Qualifier("sysDeptServiceImpl")
    @Autowired
    private ISysDeptService sysDeptService;

    @Qualifier("sysRoleServiceImpl")
    @Autowired
    private ISysRoleService sysRoleService;

//    @RequestMapping("tolist")
//    public String toList(Model model) {
//        // 如果期望jquery treeTable按照正确顺序展示树形表格，例：有三个节点：A，B（节点A的子节点）和C（节点B的子节点）。
//        // 如果按照以下顺序在A - C - B中为HTML表中的这些节点创建行，则树将无法正确显示。必须确保该行是在为了A - B - C。
//        List<SysDepartment> list = new ArrayList<>();
//        List<SysDepartment> all = sysDeptService.findAll();
//        for (SysDepartment d : all) {
//            if (StringUtils.isEmpty(d.getParentId())) {
//                list.add(d);
//            }
//
//        }
//        model.addAttribute("sysDepts",list);
//        return "sys/dept_list_new";
//    }
    @RequestMapping("tolist")
    public String toList(Model model) {
        List<SysDepartment> list = sysDeptService.findAllParents();
        model.addAttribute("list",list);
        return "sys/dept_list";
    }

    @RequestMapping(value = "/toAddOrEdit")
    public String toAddOrEdit(@RequestParam(required = false)String deptId,Model model)
            throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        SysDepartment department = new SysDepartment();
        department.setState("1");
        List<SysDepartment> list = sysDeptService.findBy(department);
        if (StringUtils.isNotEmpty(deptId)) {       //编辑
            List<SysDepartment> byParentId = sysDeptService.findByParentId(deptId);
            for (int i=0;i<list.size();i++) {
                for (int j=0;j<byParentId.size();j++) {
                    if (list.get(i).getDeptId().equals(byParentId.get(j).getDeptId())) {
                        list.remove(i);
                    }
                }
            }
            String value = mapper.writeValueAsString(list);
            model.addAttribute("nodes",value);
            SysDepartment byDeptId = sysDeptService.findByDeptId(deptId);
            String parentId = sysDeptService.findByDeptId(deptId).getParentId();
            SysDepartment parent = sysDeptService.findByDeptId(parentId);
            model.addAttribute("parentDept",parent);
            model.addAttribute("sysDept",byDeptId);
            return "sys/dept_edit";
        }else{      //新增
            String value = mapper.writeValueAsString(list);
            model.addAttribute("nodes",value);
            return "sys/dept_add";
        }
    }

    @RequestMapping(value = "/loadTreeBranch")
    @ResponseBody
    public Map<String, Object> loadTreeBranch(String deptId) {
        jsonResult = new HashMap<String, Object>();
        List<String> list = new ArrayList<String>();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        SysDepartment department = new SysDepartment();
        department.setParentId(deptId);
        List<SysDepartment> departmentList = sysDeptService.findBy(department);
        String state = "启用";
        for (SysDepartment sysDepartment : departmentList) {
            String createDate = format.format(sysDepartment.getCreateDate());
            if (StringUtils.equals("0",sysDepartment.getState())) {
                state = "禁用";
            }
            String tr = "<tr data-tt-id=\""+sysDepartment.getDeptId()+"\" data-tt-parent-id=\""+sysDepartment.getParentId()+"\" data-tt-branch=\""+sysDepartment.getHasBranch()+"\">" +
                    "<td>"+sysDepartment.getDeptName()+"</td><td hidden=\"hidden\">"+sysDepartment.getDeptId()+"</td><td style=\"text-align:center;\">"+sysDepartment.getDeptNum()+"</td><td style=\"text-align:center;\">"+sysDepartment.getDeptAbbr()+"</td>" +
                    "<td style=\"text-align:center;\">"+createDate+"</td><td style=\"text-align:center;\">"+state+"</td>" +
                    "<td style=\"text-align:center;\">" +
                    "<button class=\"btn btn-default btn-xs\" onclick=\"detailDept(this)\">查看</button> \r" +
                    "<button class=\"btn btn-default btn-xs\" onclick=\"editDept(this)\">编辑</button> \r" +
                    "<button class=\"btn btn-default btn-xs btn-danger\" onclick=\"deleteDept(this)\">删除</button></td></tr>";
            list.add(tr);
        }
        jsonResult.put("data",list);
        return jsonResult;
    }

    @RequestMapping(value = "/save",method = RequestMethod.POST)
    public String save(SysDepartment sysDepartment) {
        String parentId = sysDepartment.getParentId();
        sysDeptService.saveSysDept(sysDepartment);
        if (StringUtils.isNotEmpty(parentId)) {
            sysDeptService.updateHasBranch(parentId,"true");
        }
        return "forward:/dept/tolist.do";
    }

    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public String update(SysDepartment sysDepartment) {
        String deptId = sysDepartment.getDeptId();
        String currParentId = sysDepartment.getParentId();
        String oldParentId = sysDeptService.findByDeptId(deptId).getParentId();
        sysDeptService.updateSysDept(sysDepartment);
        if (StringUtils.isNotEmpty(oldParentId)) {
            if (!oldParentId.equals(currParentId)) {
                if (sysDeptService.findByParentId(oldParentId).size() == 0) {
                    sysDeptService.updateHasBranch(oldParentId,"false");
                }
                sysDeptService.updateHasBranch(currParentId,"true");
            }
        }else{
            sysDeptService.updateHasBranch(currParentId,"true");

        }
        return "forward:/dept/tolist.do";
    }


    @RequestMapping(value = "/deleteDept")
    @ResponseBody
    public String deleteDept(String deptId) {
        String result = "";
        SysDepartment department = sysDeptService.findByDeptId(deptId);
        List<SysDepartment> byParentId = sysDeptService.findByParentId(deptId);
        List<SysRole> byDeptId = sysRoleService.findByDeptId(deptId);
        if (byParentId.size() == 0 && byDeptId.size() == 0) {
            sysDeptService.deleteByDeptId(deptId);
            result = "success";
        }
        if (sysDeptService.findByParentId(department.getParentId()).size() == 0) {
            sysDeptService.updateHasBranch(department.getParentId(),"flase");
        }
        return result;
    }


    @RequestMapping(value = "/formCheck")
    @ResponseBody
    public Map<String,Object> formCheck(SysDepartment sysDepartment){
        jsonResult = new HashMap<String, Object>();
        List<SysDepartment> list = sysDeptService.findBy(sysDepartment);
        if (list.size() > 0) {
            jsonResult.put("error","该名称已被使用");
        }else{
            jsonResult.put("ok","该名称可用");
        }

        return jsonResult;
    }


}