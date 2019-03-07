package com.ch3nk.ch3nkSite.modules.sys.controller;

import com.ch3nk.ch3nkSite.common.base.baseController.BaseController;
import com.ch3nk.ch3nkSite.common.exception.AffectedRowIsZeroException;
import com.ch3nk.ch3nkSite.common.response.AjaxRespBean;
import com.ch3nk.ch3nkSite.modules.sys.entity.SysOperate;
import com.ch3nk.ch3nkSite.modules.sys.service.OperateService;
import com.ch3nk.ch3nkSite.modules.sys.service.SysMenuService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * com.ch3nk.ch3nkSite.modules.sys.controller
 * chenkai
 */
@Controller
@RequestMapping("operate")
public class OperateController extends BaseController {

    @Autowired
    private OperateService operateService;
    @Autowired
    private SysMenuService sysMenuService;


    @RequestMapping("/info/saveOperate")
    @ResponseBody
    public AjaxRespBean saveOperate(SysOperate operate) {
        AjaxRespBean ajaxRespBean = null;
        String parentId = sysMenuService.findByPKey(operate.getMenu().getMenuId()).getParentId();   //菜单根节点不需要操作权限
        if (StringUtils.isAnyBlank(operate.getMenu().getMenuId(),operate.getName(),operate.getCode())) {
            ajaxRespBean = AjaxRespBean.failResponse("权限名称或权限代码不能为空");
            return ajaxRespBean;
        }else if (StringUtils.isBlank(parentId)){
            ajaxRespBean = AjaxRespBean.failResponse("菜单根节点不需要操作权限");
            return ajaxRespBean;
        }
        try {
            operate.beforeInsert();
            operateService.insert(operate);
            ajaxRespBean = AjaxRespBean.successResponse("保存成功");
        } catch (AffectedRowIsZeroException e) {
            ajaxRespBean = AjaxRespBean.failResponse("保存失败");
        }
        return ajaxRespBean;
    }
}
