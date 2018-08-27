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
        .form-group button{
            width: 34px;
            height: 34px;
            position: absolute;
            top: 0px;
            left: 251px;
            padding: 0;
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
        .btn-content{
            margin-top: 30px;
        }
    </style>
</head>
<body>
<div class="position" style="width: 100%; height: 50px; background-color: #FFFFFF; margin-bottom: 40px;line-height: 50px;padding: 0 0 0 20px;">
    <div class="postion-content"><i class="fa fa-bars"></i>&nbsp;菜单管理</div>
    <div class="operations" style="display: inline-block">
        <button class="btn btn-default" id="goBackBtn" onclick="javascript:history.back(0);"><i class="fa fa-reply" aria-hidden="true"></i></button>
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
                <form action="${basePath}/menu/saveOrUpdate.do" class="form-horizontal" id="menuForm">
                    <input type="text" name="roleId" style="display: none" value="${sysMenu.menuId}">
                    <div class="form-group">
                        <label  class="col-sm-2 control-label">
                            <span>上级菜单</span>
                        </label>
                        <div class="col-sm-3">
                            <input type="text" class="form-control" name="department.deptName" id="deptName" value="${sysDept.deptName}" >
                            <button type="button" class="btn btn-default" data-toggle="modal" data-target="#myModal"><i class="fa fa-search"></i></button>
                        </div>
                        <label  class="col-sm-2 control-label">
                            <span>类别</span>
                            <span style="color: red;">*</span>
                        </label>
                        <div class="col-sm-5">
                            <label class="radio-inline">
                                <input type="radio" name="category" value="1" <c:if test="${sysMenu.category == '0'}">checked</c:if>>菜单
                            </label>
                            <label class="radio-inline">
                                <input type="radio" name="category" value="0" <c:if test="${sysMenu.category == '1'}">checked</c:if>>操作
                            </label>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="eName" class="col-sm-2 control-label">
                            <span>菜单名称</span>
                            <span style="color: red;">*</span>
                        </label>
                        <div class="col-sm-3">
                            <input type="text" class="form-control" id="eName" name="eName">
                        </div>
                        <label for="eName" class="col-sm-2 control-label">
                            <span>资源路径</span>
                            <span style="color: red;">*</span>
                        </label>
                        <div class="col-sm-3">
                            <input type="text" class="form-control" id="eName" name="eName">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="eName" class="col-sm-2 control-label">
                            <span>权限标识</span>
                            <span style="color: red;">*</span>
                        </label>
                        <div class="col-sm-3">
                            <input type="text" class="form-control" id="eName" name="eName">
                        </div>
                        <label for="eName" class="col-sm-2 control-label">
                            <span>图标</span>
                            <span style="color: red;">*</span>
                        </label>
                        <div class="col-sm-3">
                            <input type="text" class="form-control" id="eName" name="eName">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="remark" class="col-sm-2 control-label">
                            <span>备注</span>
                        </label>
                        <div class="col-sm-8">
                            <textarea name="remark" id="remark" cols="30" rows="3" class="form-control"></textarea>
                        </div>
                    </div>
                    <div class="col-sm-1 col-sm-offset-5">
                        <button type="submit" class="btn btn-primary btn-block btn-content" id="saveOrEdit">保存</button>
                    </div>
                    <div class="col-sm-1">
                        <button type="button" class="btn btn-primary btn-block btn-content" onclick="javascript:history.back(-1);">取消</button>
                    </div>
                </form>

            </div>
        </div>
    </div>
</div>
<!-- 模态框（Modal） -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
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
<script src="${basePath}/static/js/jquery-3.2.1.js"></script>
<script src="${basePath}/static/plugins/bootstrap/js/bootstrap.min.js"></script>
<script src="${basePath}/static/plugins/nice-validator/jquery.validator.min.js"></script>
<script src="${basePath}/static/plugins/nice-validator/local/zh-CN.js"></script>
<script src="${basePath}/static/plugins/ztree/js/jquery.ztree.core.min.js"></script>
<script>
    <%--模态框树--%>
    var setting = {
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
    var detps = $.fn.zTree.init($("#depts"), setting,  ${nodes});
    detps.expandNode(detps.getNodes()[0]);    //展开第一层

    function clickNode(event, treeId, treeNode) {
        $("input[id='deptId']").attr("value",treeNode.deptId);
        $("input[id='deptName']").attr("value",treeNode.deptName);
        $("#myModal").modal('hide');
    };

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