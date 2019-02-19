<%--
  Created by IntelliJ IDEA.
  User: chenkai
  Date: 2018/7/18
  Time: 20:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>用户信息</title>
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
            <a href="javascript:;">用户管理</a>
        </li>
        <li class="active">
            <a href="javascript:;">用户详情</a>
        </li>
    </ol>
</div>
<div class="content-body" id="content-body">
    <div class="content-wrap">
        <div class="baseInfo">
            <ul id="formTab_1" class="nav nav-tabs">
                <li class="active">
                    <a href="#baseInfo" data-toggle="tab">基本信息</a>
                </li>
            </ul>
            <div class="tab-content">
                <div class="tab-pane fade in active" id="baseInfo"  style="height: 50px;margin-bottom: 40px;padding-top: 20px;">
                    <div class="form-horizontal">
                        <div class="row">
                            <div class="col-md-4 col-md-offset-2">
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">
                                        <span>用户名称</span>
                                    </label>
                                    <div class="form-control-static">${role.name}</div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">
                                        <span>所属部门</span>
                                    </label>
                                    <div class="form-control-static">${role.department.deptName}</div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">
                                        <span>创建人</span>
                                    </label>
                                    <div class="form-control-static">${role.createBy}</div>
                                </div>
                            </div>
                            <div class="col-md-4 col-md-offset-2">
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">
                                        <span>角色编号</span>
                                    </label>
                                    <div class="form-control-static">${role.eName}</div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">
                                        <span>角色状态</span>
                                    </label>
                                    <c:if test="${role.useFlag == 1}">
                                        <div class="form-control-static" style="color:#3e8f3e">启用</div>
                                    </c:if>
                                    <c:if test="${role.useFlag == 0}">
                                        <div class="form-control-static" style="color: #9F9F9F;">禁用</div>
                                    </c:if>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">
                                        <span>创建时间</span>
                                    </label>
                                    <div class="form-control-static">
                                        <fmt:formatDate value="${role.createDate}" pattern="yyyy-MM-dd"/>
                                    </div>
                                </div>
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
        }
    };
    var menusObj = $.fn.zTree.init($("#menu_tree"), setting1,  ${menus});
    menusObj.expandAll(true);
</script>
</body>
</html>
