package com.ch3nk.ch3nkSite.modules.sys.controller;

import com.ch3nk.ch3nkSite.modules.sys.entity.SysDepartment;
import com.ch3nk.ch3nkSite.modules.sys.service.ISysDeptService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
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
        SysDepartment sysDepartment1 = new SysDepartment();
        List<SysDepartment> departmentList = sysDeptService.findBy(sysDepartment1);
        String value = mapper.writeValueAsString(departmentList);
        model.addAttribute("nodes",value);
        if (StringUtils.isNotEmpty(deptId)) {
            SysDepartment sysDepartment = new SysDepartment();
            sysDepartment.setDeptId(deptId);
            SysDepartment department = sysDeptService.findBy(sysDepartment).get(0);
            model.addAttribute("sysDept",department);
            return "sys/dept_edit";
        }
        return "sys/dept_add";
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

    @RequestMapping(value = "/saveOrUpdate")
    public String saveOrUpdate(SysDepartment sysDepartment){


        return null;
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
