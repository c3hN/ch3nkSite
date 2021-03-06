<%--
  Created by IntelliJ IDEA.
  User: chenkai
  Date: 2018/12/14
  Time: 17:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<html>
<head>
    <title>用户新增编辑</title>
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
            <a href="javascript:;">菜单管理</a>
        </li>
        <li class="active">
            <a href="javascript:;">新增</a>
        </li>
    </ol>
</div>
<div class="content-body" id="content-body">
    <div class="content-wrap">
        <ul id="formTab" class="nav nav-tabs">
            <li class="active">
                <a href="#baseInfo" data-toggle="tab">基本信息</a>
            </li>
        </ul>
        <div class="form-wrap">
            <div class="container">
                <div class="row">
                    <form action="${basePath}/menu/info/saveOrUpdate.do" method="post" class="form-horizontal" id="menuForm">
                        <input type="text" name="parentId" style="display: none" value="${sysMenu.parentId}">
                        <input type="text" name="menuId" style="display: none" value="${sysMenu.menuId}">
                        <div class="tab-content">
                            <div class="tab-pane fade in active" id="baseInfo">
                                <div class="form-group">
                                    <label  class="col-sm-2 control-label">
                                        <span>上级菜单</span>
                                    </label>
                                    <div class="col-sm-3">
                                        <div class="input-group">
                                            <input type="text" class="form-control" id="parentName" value="${parent.name}" onfocus="this.blur()">
                                            <span class="input-group-btn">
                                                <button type="button" class="btn btn-default" data-toggle="modal" data-target="#parents_tree">&nbsp;<i class="fa fa-search"></i>&nbsp;</button>
                                            </span>
                                        </div>
                                    </div>
                                    <%--<label  class="col-sm-2 control-label">--%>
                                        <%--<span>类别</span>--%>
                                        <%--<span style="color: red;">*</span>--%>
                                    <%--</label>--%>
                                    <%--<div class="col-sm-5">--%>
                                        <%--<label class="radio-inline">--%>
                                            <%--<input type="radio" name="type" value="0" <c:if test="${sysMenu == null or sysMenu.category == '0'}">checked</c:if> >菜单--%>
                                        <%--</label>--%>
                                        <%--<label class="radio-inline">--%>
                                            <%--<input type="radio" name="type" value="1" <c:if test="${sysMenu.category == '1'}">checked</c:if> >操作--%>
                                        <%--</label>--%>
                                    <%--</div>--%>
                                </div>
                                <div class="form-group">
                                    <label for="name" class="col-sm-2 control-label">
                                        <span>菜单名称</span>
                                        <span style="color: red;">*</span>
                                    </label>
                                    <div class="col-sm-3">
                                        <input type="text" class="form-control" id="name" name="name" value="${sysMenu.name}">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="code" class="col-sm-2 control-label">
                                        <span>权限标识</span>
                                        <span style="color: red;">*</span>
                                    </label>
                                    <div class="col-sm-3">
                                        <input type="text" class="form-control" id="code" name="code" value="${sysMenu.code}">
                                    </div>
                                    <label for="href" class="col-sm-2 control-label">
                                        <span>资源路径</span>
                                        <span style="color: red;">*</span>
                                    </label>
                                    <div class="col-sm-3">
                                        <input type="text" class="form-control" id="href" name="href" value="${sysMenu.href}">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <%--<label for="menuIcon" class="col-sm-2 control-label">--%>
                                        <%--<span>图标</span>--%>
                                    <%--</label>--%>
                                    <%--<div class="col-sm-3">--%>
                                        <%--<div class="input-group">--%>
                                            <%--<span class="input-group-addon">&nbsp;<i></i>&nbsp;</span>--%>
                                            <%--<input type="text" class="form-control" id="menuIcon" name="menuIcon" value="${sysMenu.menuIcon}">--%>
                                            <%--<span class="input-group-btn">--%>
                                                <%--<button type="button" class="btn btn-default" id="iconBtn">&nbsp;<i class="fa fa-th"></i>&nbsp;</button>--%>
                                            <%--</span>--%>
                                        <%--</div>--%>
                                    <%--</div>--%>
                                    <%--<label for="seq" class="col-sm-2 control-label">--%>
                                        <%--<span>排序</span>--%>
                                    <%--</label>--%>
                                    <%--<div class="col-sm-3">--%>
                                        <%--<input type="text" class="form-control" id="seq" name="seq" value="${sysMenu.seq}">--%>
                                    <%--</div>--%>
                                </div>
                                <%--<div class="form-group">--%>
                                    <%--<label for="remark" class="col-sm-2 control-label">--%>
                                        <%--<span>备注</span>--%>
                                    <%--</label>--%>
                                    <%--<div class="col-sm-8">--%>
                                        <%--<textarea name="remark" id="remark" style="resize:none" cols="30" rows="3" class="form-control">${sysMenu.remark}</textarea>--%>
                                    <%--</div>--%>
                                <%--</div>--%>
                                <div class="col-sm-1 col-sm-offset-5">
                                    <button type="submit" class="btn btn-primary btn-block btn-content" id="saveOrEdit">保存</button>
                                </div>
                                <div class="col-sm-1">
                                    <button type="button" class="btn btn-primary btn-block btn-content" onclick="javascript:history.back(-1);">取消</button>
                                </div>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- 模态框（Modal） -->
<div class="modal fade" id="parents_tree" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-dialog-custom">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                    &times;
                </button>
                部门
            </div>
            <div class="modal-body">
                <ul id="menus" class="ztree"></ul>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" id="cleanParent">清空</button>
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
<script src="${basePath}/static/plugins/layer/layer.js"></script>
<script>
    <%--模态框树--%>
    var setting = {
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
        callback:{
            onClick:clickNode
        }
    };
    var treeObj = $.fn.zTree.init($("#menus"), setting,  ${nodes});
    var menus = treeObj.getNodes();
    $.each(menus,function (index,value) {//展开第一层
        treeObj.expandNode(menus[index]);
    });
    function clickNode(event, treeId, treeNode) {
        $("input[name='parentId']").attr("value",treeNode.menuId);
        $("input[id='parentName']").val(treeNode.name);
        $("#parents_tree").modal('hide');
    };

    $("#cleanParent").click(function () {
        $("input[name='parentId']").attr("value","");
        $("input[id='parentName']").val("");
        $("#parents_tree").modal('hide');
    });

    //
    // //    初始化表单验证
    // $("#roleForm").validator({
    //     fields: {
    //         'department.deptName':'required',
    //         'name':'required;remote(formCheck.do)',
    //         'eName':'required;remote(formCheck.do)'
    //     }
    // });
    // $("input[name='category']").eq(0).click(function () {
    //     $("input[name='menuIcon']").attr("disabled",false);
    // });
    // $("input[name='category']").eq(1).click(function () {
    //     $("input[name='menuIcon']").val("");
    //     $("input[name='menuIcon']").attr("disabled","disabled");
    // });
//选择图标
    <%--$("#iconBtn").click(function () {--%>
        <%--layer.open({--%>
            <%--type: 2,--%>
            <%--area:['1200px','800'],--%>
            <%--content: ['${basePath}/font-awesome-icons.jsp'], //这里content是一个URL，如果你不想让iframe出现滚动条，你还可以content:--%>
            <%--btn: ['确认', '取消'],--%>
            <%--yes: function(index, layero){--%>
                <%--console.log(layero);--%>
                <%--console.log("===========================");--%>
                <%--console.log(layero.find('div').eq(1))--%>
            <%--},--%>
            <%--btn2: function(index, layero){--%>
              <%--layer.close(index);--%>
            <%--},--%>
        <%--});--%>
    <%--});--%>
</script>
</body>
</html>
