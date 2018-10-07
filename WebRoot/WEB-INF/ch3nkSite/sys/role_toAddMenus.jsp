<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/8/8
  Time: 14:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<html>
<head>
    <title>新增编辑页面</title>
    <link rel="stylesheet" href="${basePath}/static/plugins/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="${basePath}/static/plugins/nice-validator/jquery.validator.css">
    <link rel="stylesheet" href="${basePath}/static/plugins/ztree/css/metroStyle/metroStyle.css">
    <link rel="stylesheet" href="${basePath}/static/plugins/font-awesome-4.7.0/css/font-awesome.min.css">
    <style>
        .content .form-content{
            width: 100%;
        }
        .title{
            height: 30px;
            line-height: 30px;
            margin: 10px 20px 30px 20px;
            padding-left: 10px;
        }
        .position{
            border-bottom: 1px solid #dbdbdb;
            height: 50px;
            background-color: #FFFFFF;
            margin-bottom: 40px;
            line-height: 50px;
            padding: 0 0 0 20px;
        }
    </style>
</head>
<body>
<div class="position">
    <div class="postion-content"><i class="fa fa-id-card-o"></i>&nbsp;角色管理</div>
    <div class="operations" style="display: inline-block">
        <button class="btn btn-default" id="goBackBtn" onclick="javascript:history.back(-1);"><i class="fa fa-reply" aria-hidden="true"></i></button>
        <button class="btn btn-default"id="refreshBtn" onclick="window.location.reload(true)"><i id="refreshIcon" class="fa fa-refresh"></i> </button>
    </div>
</div>
<div class="content">
    <div class="container">
        <div class="row">
            <div class="col-md-6">
                <div>
                    <span>角色信息</span>
                    <input type="text" hidden="hidden" name="roleId" value>
                </div>
            </div>
            <button type="button" id="sub">保存</button>
            <div class="col-md-6">
                <div>
                    <span>功能菜单</span>
                    <ul id="menus" class="ztree"></ul>
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
    var setting2 = {
        data:{
            simpleData:{
                enable:true,
                idKey:"menuId",
                pIdKey:"parentId",
                rootPid:null
            },
            key:{
                name:"name",
                title:"name"
            }
        },
        check: {
            enable: true,
            chkStyle: "checkbox",
            chkboxType: {"N": "s"}    //取消勾选操作，只影响子级节点
        }
    };
    var menuTreeObj = $.fn.zTree.init($("#menus"),setting2,${menus});
    var menus = menuTreeObj.getNodes();
    $.each(menus,function (index,value) {   //循环展开第一级
        menuTreeObj.expandNode(menus[index]);
    });

    $("#sub").click(function () {
        var roleId = "${role.roleId}";
        var ids = new Array();
        var nodes = menuTreeObj.getCheckedNodes(true);
        for(var i = 0;i<nodes.length;i++) {
            ids.push(nodes[i].menuId);
        }
        console.log(roleId);
        $.post("${basePath}/role/saveRoleMenus.do",{"roleId":roleId,"ids":ids},function (data) {
            console.log(data);
        });
    });


    console.log(checkedMenus);





    //    初始化表单验证
    $("#roleForm").validator({
        fields: {
            'department.deptName':'required',
            'name':'required;remote(formCheck.do)',
            'eName':'required;remote(formCheck.do)'
        }
    });
</script>
</body>
</html>