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
            border-bottom: 1px solid #dbdbdb;
            /*width: 100%;*/
            height: 30px;
            line-height: 30px;
            margin: 10px 20px 30px 20px;
            padding-left: 10px;
        }
        #deptName{
            padding: 0 46px 0 12px;
        }
        .modal-dialog-custom{
            width: 400px;
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
    <div class="title">
        基本信息
    </div>
    <div class="form-content">
        <div class="container">
            <div class="row">
                <form action="${basePath}/role/saveOrUpdate.do" method="post" class="form-horizontal" id="roleForm">
                    <input type="text" name="department.deptId" style="display: none" id="deptId" value="${sysDept.deptId}">
                    <div class="form-group">
                        <label  class="col-sm-2 control-label">
                            <span>所属部门</span>
                            <span style="color: red;">*</span>
                        </label>
                        <div class="col-sm-3">
                            <div class="input-group">
                                <input type="text" class="form-control" name="department.deptName" id="deptName" value="${sysDept.deptName}">
                                <span class="input-group-btn">
                                    <button type="button" class="btn btn-default" data-toggle="modal" data-target="#dept_tree">&nbsp;<i class="fa fa-search"></i>&nbsp;</button>
                                </span>
                            </div>
                        </div>
                        <label for="name" class="col-sm-2 control-label">
                            <span>角色名称</span>
                            <span style="color: red;">*</span>
                        </label>
                        <div class="col-sm-3">
                            <input type="text" class="form-control" name="name" id="name" >
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="eName" class="col-sm-2 control-label">
                            <span>角色编号</span>
                            <span style="color: red;">*</span>
                        </label>
                        <div class="col-sm-3">
                            <input type="text" class="form-control" id="eName" name="eName">
                        </div>
                        <label class="col-sm-2 control-label">
                            <span>状态</span>
                        </label>
                        <div class="col-sm-5">
                            <label class="radio-inline">
                                <input type="radio" name="useFlag" value="1"checked>启用
                            </label>
                            <label class="radio-inline">
                                <input type="radio" name="useFlag" value="0">禁用
                            </label>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="remark" class="col-sm-2 control-label">
                            <span>资源配置</span>
                        </label>
                        <div class="col-sm-8">
                            <div class="input-group">
                                <input type="text" class="form-control">
                                <span class="input-group-btn">
                                    <button class="btn btn-default" type="button" data-toggle="modal" data-target="#menu_tree">&nbsp;<i class="fa fa-bars"></i>&nbsp;</button>
                                </span>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="remark" class="col-sm-2 control-label">
                            <span>备注</span>
                        </label>
                        <div class="col-sm-8">
                            <textarea name="remark" id="remark" style="resize:none" cols="30" rows="3" class="form-control"></textarea>
                        </div>
                    </div>
                    <div class="col-sm-1 col-sm-offset-5">
                        <button type="submit" class="btn btn-primary btn-block btn-content" id="saveOrEdit">保存</button>
                    </div>
                    <div class="col-sm-1">
                        <button type="button" class="btn btn-primary btn-block btn-content" onclick="javascript:history.back(-1);">取消</button>
                        <button type="button" class="btn btn-primary btn-block btn-content" id="check">sss</button>
                    </div>
                </form>

            </div>
        </div>
    </div>
</div>
<!-- 模态框（Modal） -->
<div class="modal fade" id="dept_tree" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-dialog-custom">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                    &times;
                </button>
                部门
            </div>
            <div class="modal-body">
                <ul id="depts" class="ztree"></ul>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
            </div>
        </div>
    </div>
</div>
<div class="modal fade" id="menu_tree" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-dialog-custom">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                    &times;
                </button>
                菜单
            </div>
            <div class="modal-body">
                <ul id="menus" class="ztree"></ul>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
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
    <%--模态框树--%>
    var setting1 = {
        data:{
            simpleData:{
                enable:true,
                idKey:"deptId",
                pIdKey:"parentId",
                rootPid:null
            },
            key:{
                name:"deptName",
                title:"deptName"
            }
        },
        callback:{
            onClick:clickNode
        }
    };
    var deptsObj = $.fn.zTree.init($("#depts"), setting1,  ${nodes1});
    var depts = deptsObj.getNodes();
    $.each(depts,function (index,value) {
       deptsObj.expandNode(depts[index]);
    });
    function clickNode(event, treeId, treeNode) {
        $("input[id='deptId']").attr("value",treeNode.deptId);
        $("input[id='deptName']").attr("value",treeNode.deptName);
        $("#dept_tree").modal('hide');
    };

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
        check:{
            enable:true,
            chkStyle:"checkbox",
            chkboxType:{  "N": "s" }    //取消勾选操作，只影响子级节点
        },
        callback:{
            onCheck:function (event,treeId,treeNode) {
              console.log(event);
              console.log(treeId);
              console.log(treeNode);
            },
        }
    };
    var menuTreeObj = $.fn.zTree.init($("#menus"),setting2,${nodes2});
    var menus = menuTreeObj.getNodes();
    $.each(menus,function (index,value) {   //循环展开第一级
        menuTreeObj.expandNode(menus[index]);
    });




$("#check").click(function () {
    console.log(menuTreeObj.getCheckedNodes(true));
});
















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