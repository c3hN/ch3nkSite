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
    <link rel="stylesheet" href="${basePath}/static/plugins/jqGrid-4.7.0/css/ui.jqgrid.css">
    <link rel="stylesheet" href="${basePath}/static/plugins/jqGrid-4.7.0/css/flick/jquery-ui-1.8.16.custom.css">
    <link rel="stylesheet" href="${basePath}/static/plugins/font-awesome-4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="${basePath}/static/css/common.css">
</head>
<body>
<div class="content-head">
    <ol class="breadcrumb">
        <li class="active">
            <a href="javascript:;">系统设置</a>
        </li>
        <li class="active">
            <a href="javascript:;">菜单管理</a>
        </li>
    </ol>
</div>
<div class="content-body">
    <div class="container">
        <div class="row">
            <div class="col-md-12">
                <div class="table-content" style="position: relative">
                    <div class="btns_operations">
                        <shiro:hasPermission name="user:add">
                            <a role="button" type="button" class="btn btn-default btn-sm" href="${basePath}/menu/view/add.do">新增</a>
                        </shiro:hasPermission>
                        <shiro:hasPermission name="user:recover">
                            <a role="button" type="button" class="btn btn-info btn-sm" href="${basePath}/menu/view/recover.do">回收站</a>
                        </shiro:hasPermission>
                    </div>
                    <div class="table_wrap">
                        <table id="menu_table"></table>
                    </div>
                </div>
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
<script src="${basePath}/static/js/menu_index.js" charset="UTF-8"></script>
<script>
    var basePath = "${basePath}"
</script>
</body>
</html>