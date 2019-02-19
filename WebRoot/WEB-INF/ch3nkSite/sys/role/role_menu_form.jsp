<%--
  Created by IntelliJ IDEA.
  User: chenkai
  Date: 2018/12/24
  Time: 14:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>角色分配菜单</title>
    <link rel="stylesheet" href="${basePath}/static/plugins/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="${basePath}/static/plugins/nice-validator/jquery.validator.css">
    <link rel="stylesheet" href="${basePath}/static/plugins/ztree/css/metroStyle/metroStyle.css">
    <link rel="stylesheet" href="${basePath}/static/plugins/font-awesome-4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="${basePath}/static/css/form.css">
</head>
<body>
<div class="content-head">
    <ol class="breadcrumb">
        <li class="active">
            <a href="javascript:;">组织权限</a>
        </li>
        <li class="active">
            <a href="javascript:;">角色管理</a>
        </li>
        <li class="active">
            <a href="javascript:;">授权功能菜单</a>
        </li>
    </ol>
</div>
<div class="content-body" id="content-body">
    <div class="content-wrap">
        <div class="form-horizontal">
            <div class="baseInfo">
                <ul id="formTab_1" class="nav nav-tabs">
                    <li class="active">
                        <a href="#baseInfo" data-toggle="tab">基本信息</a>
                    </li>
                </ul>
                <div class="tab-content">
                    <div class="tab-pane fade in active" id="baseInfo"  style="height: 50px;margin-bottom: 40px;padding-top: 20px;">
                        <div class="row">
                            <div class="col-md-4 col-md-offset-2">
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">
                                        <span>角色名称</span>
                                    </label>
                                    <div class="form-control-static">${role.name}</div>
                                </div>
                            </div>
                            <div class="col-md-4 col-md-offset-2">
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">
                                        <span>角色编号</span>
                                    </label>
                                    <div class="form-control-static">${role.eName}</div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="menus">
                    <div class="baseInfo">
                        <ul id="formTab_2" class="nav nav-tabs">
                            <li class="active">
                                <a href="#menus" data-toggle="tab">授权功能菜单</a>
                            </li>
                        </ul>
                        <div class="tab-content">
                            <div class="tab-pane fade in active" id="menus">
                               <div class="menu_tree_wrap" style="background-color:#f2f2f2;height: 500px;overflow-y: auto">
                                   <ul id="menu_tree" class="ztree"></ul>
                               </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="btns" style="width: 100%;margin-top: 15px;text-align:center;">
                    <button type="button" class="btn btn-primary" id="saveBtn">保存</button>
                    <button type="button" class="btn btn-primary" onclick="javascript:history.back(-1);">取消</button>
                </div>
            </div>
        </div>
    </div>
</div>


<script src="${basePath}/static/js/jquery-3.2.1.js"></script>
<script src="${basePath}/static/plugins/bootstrap/js/bootstrap.min.js"></script>
<script src="${basePath}/static/plugins/nice-validator/jquery.validator.min.js"></script>
<script src="${basePath}/static/plugins/nice-validator/local/zh-CN.js"></script>
<script src="${basePath}/static/plugins/ztree/js/jquery.ztree.core.min.js"></script>
<script src="${basePath}/static/plugins/ztree/js/jquery.ztree.excheck.min.js"></script>
<script>
    var setting1 = {
        data: {
            simpleData: {
                enable: true,
                idKey: "menuId",
                pIdKey: "parentId",
                rootPid: null
            },
            key: {
                name: "name",
                title: "name"
            }
        },
        check:{
            enable:true,
            chkStyle:"checkbox",
            chkboxType:{"Y":"p","N":"s"}
        }
    };
    var menusObj = $.fn.zTree.init($("#menu_tree"), setting1,  ${menuNodes});
    var menus = menusObj.getNodes();
    $.each(menus,function (index,value) {
        menusObj.expandNode(menus[index]);
    });
    $("#saveBtn").click(function () {
        var roleId = "${role.roleId}";
        var checkedMenus = menusObj.getCheckedNodes();
        var menuIds = new Array();
        for (i = 0,len = checkedMenus.length; i < len; i++) {
            menuIds[i] = checkedMenus[i].menuId;
        }
        $.ajax({
            type:'post',
            url:'${basePath}/role/saveRoleMenus.do',
            dataType:'json',
            traditional:true,
            data:{
                "roleId":roleId,
                "menuIds":menuIds
            },
            success:function (data) {
                alert("1111111111")
            }
        });
    });
    //ztree回显
    var haveChecked = ${checkedMenus}
    var allNodes = menusObj.transformToArray(menus);
    if (haveChecked.length != 0) {
        for(i = 0,len1 = haveChecked.length; i < len1; i++) {
            for (j = 0,len2 = allNodes.length; j < len2; j++) {
                if (haveChecked[i] == allNodes[j].menuId) {
                    menusObj.checkNode(allNodes[j],true,true);
                }
            }
        }
    }
</script>
</body>
</html>
