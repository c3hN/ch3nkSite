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
                <a href="javascript:;">账号管理</a>
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
                        <form action="${basePath}/account/info/saveOrUpdate.do" method="post" class="form-horizontal" id="roleForm">
                            <input type="text" name="acId" style="display: none" id="acId" value="${account.acId}">
                            <div class="form-group">
                                <c:if test="${account.acId == null}">
                                    <label class="col-sm-2 control-label">
                                        <span>账号</span>
                                        <span style="color: red;">*</span>
                                    </label>
                                    <div class="col-sm-3">
                                        <input type="text" class="form-control"  id="account" name="account" value="${account.account}">
                                    </div>
                                </c:if>
                                <c:if test="${account.acId != null}">
                                    <label class="col-sm-2 control-label">
                                        <span>账号</span>
                                        <span style="color: red;">*</span>
                                    </label>
                                    <div class="col-sm-3">
                                        <div class="form-control-static">${account.account}</div>
                                    </div>
                                </c:if>
                                <label for="nickName" class="col-sm-2 control-label">
                                    <span>昵称</span>
                                    <span style="color: red;">*</span>
                                </label>
                                <div class="col-sm-3">
                                    <input type="text" class="form-control" name="nickName" id="nickName" value="${account.nickName}">
                                </div>
                            </div>
                            <c:if test="${account.acId == null}">
                                <div class="form-group">
                                    <label for="password" class="col-sm-2 control-label">
                                        <span>密码</span>
                                        <span style="color: red;">*</span>
                                    </label>
                                    <div class="col-sm-3">
                                        <input type="password" class="form-control" id="password" name="password" value="${account.password}">
                                    </div>
                                </div>
                            </c:if>
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
<script src="${basePath}/static/js/jquery-3.2.1.js"></script>
<script src="${basePath}/static/plugins/bootstrap/js/bootstrap.min.js"></script>
<script src="${basePath}/static/plugins/nice-validator/jquery.validator.min.js"></script>
<script src="${basePath}/static/plugins/nice-validator/local/zh-CN.js"></script>
<script src="${basePath}/static/plugins/ztree/js/jquery.ztree.core.min.js"></script>
<script src="${basePath}/static/plugins/ztree/js/jquery.ztree.excheck.min.js"></script>
<script>
</script>
</body>
</html>