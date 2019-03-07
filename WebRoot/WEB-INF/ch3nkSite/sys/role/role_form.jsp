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
                        <form action="${basePath}/role/info/saveOrUpdate.do" method="post" class="form-horizontal" id="roleForm">
                            <input type="text" name="company.id" style="display: none" id="companyId" value="${role.company.id}">
                            <input type="text" name="roleId" style="display: none" id="roleId" value="${role.roleId}">
                            <div class="form-group">
                                <label class="col-sm-2 control-label">
                                    <span>所属公司</span>
                                    <span style="color: red;">*</span>
                                </label>
                                <div class="col-sm-3">
                                    <div class="input-group">
                                        <input type="text" class="form-control" readonly  id="fullName" value="${role.company.fullName}">
                                        <span class="input-group-btn">
                                            <button type="button" class="btn btn-default" data-toggle="modal" data-target="#dept_tree">&nbsp;<i
                                                    class="fa fa-search"></i>&nbsp;</button>
                                        </span>
                                    </div>
                                </div>
                                <label for="name" class="col-sm-2 control-label">
                                    <span>角色名称</span>
                                    <span style="color: red;">*</span>
                                </label>
                                <div class="col-sm-3">
                                    <input type="text" class="form-control" name="name" id="name" value="${role.name}">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="eName" class="col-sm-2 control-label">
                                    <span>角色编号</span>
                                    <span style="color: red;">*</span>
                                </label>
                                <div class="col-sm-3">
                                    <input type="text" class="form-control" id="eName" name="code" value="${role.code}">
                                </div>
                                <label class="col-sm-2 control-label">
                                    <span>角色类型</span>
                                </label>
                                <div class="col-sm-5">
                                    <label class="radio-inline">
                                        <input type="radio" name="type" value="0" <c:if test="${role.type == null or role.type == '0'}">checked</c:if>>用户角色
                                    </label>
                                    <label class="radio-inline">
                                        <input type="radio" name="type" value="1" <c:if test="${role.type == '1'}">checked</c:if> >系统角色
                                    </label>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="description" class="col-sm-2 control-label">
                                    <span>备注</span>
                                </label>
                                <div class="col-sm-8">
                                    <textarea name="description" id="description" style="resize:none" cols="30" rows="3"
                                              class="form-control">${role.description}</textarea>
                                </div>
                            </div>
                            <div class="col-sm-1 col-sm-offset-5">
                                <button type="submit" class="btn btn-primary" id="saveOrEdit">保存</button>
                            </div>
                            <div class="col-sm-1">
                                <button type="button" class="btn btn-primary " onclick="javascript:history.back(-1);">
                                    取消
                                </button>
                            </div>
                        </form>
                    </div>
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
                公司列表
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
<script src="${basePath}/static/plugins/ztree/js/jquery.ztree.excheck.min.js"></script>
<script>
    <%--模态框树--%>
    var setting1 = {
        data:{
            simpleData:{
                enable:true,
                idKey:"id",
                pIdKey:null,
                rootPid:null
            },
            key:{
                name:"fullName",
                title:"fullName"
            }
        },
        callback:{
            onClick:clickNode
        }
    };
    var deptsObj = $.fn.zTree.init($("#depts"), setting1,  ${nodes});
    var depts = deptsObj.getNodes();
    $.each(depts,function (index,value) {
       deptsObj.expandNode(depts[index]);
    });
    function clickNode(event, treeId, treeNode) {
        $("input[id='companyId']").attr("value",treeNode.id);
        $("input[id='fullName']").attr("value",treeNode.fullName);
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
        check: {
            enable: true,
            chkStyle: "checkbox",
            chkboxType: {"N": "s"}    //取消勾选操作，只影响子级节点
        }
    };
    var menuTreeObj = $.fn.zTree.init($("#menus"),setting2,${nodes2});
    var menus = menuTreeObj.getNodes();
    $.each(menus,function (index,value) {   //循环展开第一级
        menuTreeObj.expandNode(menus[index]);
    });
















    //    初始化表单验证
    // $("#roleForm").validator({
    //     fields: {
    //         'department.deptName':'required',
    //         'name':'required;remote(formCheck.do)',
    //         'eName':'required;remote(formCheck.do)'
    //     }
    // });
</script>
</body>
</html>