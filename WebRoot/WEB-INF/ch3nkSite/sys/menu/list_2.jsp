<%--
  Created by IntelliJ IDEA.
  User: chenkai
  Date: 2018/12/17
  Time: 12:21
  To change this template use File | Settings | File Templates.
--%>
<%--<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">--%>
<%--<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<html>
<head>
    <title>部门列表</title>
    <link rel="stylesheet" href="${basePath}/static/plugins/bootstrap/css/bootstrap.min.css">
    <%--<link rel="stylesheet" href="${basePath}/static/plugins/jquery-treetable/css/jquery.treetable.theme.default.css">--%>
    <%--<link rel="stylesheet" href="${basePath}/static/plugins/jquery-treetable/css/jquery.treetable.css">--%>
    <%--<link rel="stylesheet" href="${basePath}/static/css/custom.css">--%>
    <link rel="stylesheet" href="${basePath}/static/plugins/jqGrid-4.7.0/css/cupertino/jquery-ui-1.8.16.custom.css">
    <%--<link rel="stylesheet" href="${basePath}/static/plugins/jqGrid-4.7.0/css/start/jquery-ui-1.8.20.custom.css">--%>
    <%--<link rel="stylesheet" href="${basePath}/static/plugins/jqGrid-4.7.0/css/flick/jquery-ui-1.8.16.custom.css">--%>
    <%--<link rel="stylesheet" href="${basePath}/static/plugins/jqGrid-4.7.0/css/ui-lightness/jquery-ui-1.8.16.custom.css">--%>
    <link rel="stylesheet" href="${basePath}/static/plugins/font-awesome-4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="${basePath}/static/css/common.css">
    <%--<link rel="stylesheet" href="${basePath}/static/css/table.css">--%>
</head>
<body>
<div class="container">
    <div class="row">
        <div class="col-md-12">
            <div class="table-content" style="position: relative">
                <div class="table_operations" id="table_operations">
                    <div class="btns_operations">
                        <shiro:hasPermission name="user:add">
                            <button type="button" class="btn btn-default btn-sm" id="addUserBtn">新增</button>
                        </shiro:hasPermission>
                        <shiro:hasPermission name="user:import">
                            <button type="button" class="btn btn-default btn-sm" id="importUsersBtn">导入</button>
                        </shiro:hasPermission>
                        <shiro:hasPermission name="user:export">
                            <button type="button" class="btn btn-default btn-sm" id="exportUsersBtn">导出</button>
                        </shiro:hasPermission>
                        <shiro:hasPermission name="user:abandonBatch">
                            <button type="button" class="btn btn-danger btn-sm" id="deleteBatchBtn">批量删除</button>
                        </shiro:hasPermission>
                        <shiro:hasPermission name="user:recover">
                            <a role="button" class="btn btn-info btn-sm"  href="${basePath}/user/operate?op=toRecover" role="button">回收站</a>
                        </shiro:hasPermission>
                    </div>
                    <shiro:hasPermission name="user:search">
                        <div class="search_operations">
                            <div class="form-inline">
                                <input type="text" name="likeAccount" placeholder="账号" class="form-control">
                                <input type="text" name="likeNickName" placeholder="昵称" class="form-control">
                                <input type="text" name="likeCreateTime" readonly  id="datetimepicker" placeholder="注册时间" class="form-control">
                            </div>
                            <div>
                                <button type="button" class="btn btn-sm btn-default" id="tableSearchBtn">搜索</button>
                                <button type="button" class="btn btn-sm btn-primary" id="tableResetBtn">重置</button>
                            </div>
                        </div>
                    </shiro:hasPermission>
                </div>
                    <div class="table_wrap">
                        <table id="menu_table"></table>
                    </div>
                <div id="pager"></div>
                <br>
            </div>
        </div>
    </div>
</div>
<script src="${basePath}/static/js/jquery-3.2.1.js"></script>
<script src="${basePath}/static/plugins/bootstrap/js/bootstrap.min.js"></script>
<script src="${basePath}/static/plugins/jquery-treetable/jquery.treetable.js"></script>
<script src="${basePath}/static/plugins/layer/layer.js"></script>
<script src="${basePath}/static/plugins/jqGrid-4.7.0/js/jquery.jqGrid.js"></script>
<script src="${basePath}/static/plugins/jqGrid-4.7.0/js/i18n/grid.locale-cn.js"></script>
</body>
</html>