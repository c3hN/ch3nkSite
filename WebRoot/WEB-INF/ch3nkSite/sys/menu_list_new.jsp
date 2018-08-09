<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<html>
<head>
    <title>菜单列表</title>
    <link rel="stylesheet" href="${basePath}/static/plugins/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="${basePath}/static/plugins/jquery-treetable/css/jquery.treetable.theme.default.css">
    <link rel="stylesheet" href="${basePath}/static/plugins/jquery-treetable/css/jquery.treetable.css">
    <style>
        #menus{
            width: 1060px;
        }
        table.treetable thead tr th{
            height: 39px;
            border: 1px solid #e6e6e6;
            font-size: 14px;
            text-align: center;
            padding: 5px 0 5px 0;
        }
        table.treetable thead{
            height: 39px;
            border: 1px solid #e6e6e6;
            background-color: #f2f2f2;
            text-align: center;
            padding: 5px 15px 5px 15px;
        }
        table.treetable tbody tr:hover{
            background-color: #f2f2f2;
        }
        table.treetable tbody tr td{
            height: 39px;
            border: 1px solid #e6e6e6;
            text-align: center;
            font-size: 14px;
            padding: 5px 15px 5px 15px;
        }
    </style>
</head>
<body>
<div class="content">
    <div class="operations">
        <div class="btn-group">
            <button class="btn btn-default" onclick="window.location.reload(true)">新增</button>
            <button class="btn btn-default" onclick="window.location.reload(true)">删除</button>
            <button class="btn btn-default" onclick="window.location.reload(true)">刷新</button>
        </div>
    </div>
    <div class="menu-list">
        <table id="menus">
            <thead>
                <tr>
                    <th style="width: 200px !important;">菜单名称</th>
                    <th style="width: 90px;">类别</th>
                    <th style="width: 100px;">资源路径</th>
                    <th style="width: 80px;">权限标识</th>
                    <th style="width: 70px;">创建时间</th>
                    <th style="width: 70px;">状态</th>
                    <th style="width: 200px;">备注</th>
                    <th style="width: 250px;">操作</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${list}" var="menu">
                    <tr data-tt-id="${menu.menuId}" data-tt-parent-id="${menu.parentId}">
                        <td style="text-align: left">${menu.name}</td>
                        <td hidden="hidden">${menu.menuId}</td>
                        <td>
                            <c:if test="${menu.category == '0'}">菜单</c:if>
                            <c:if test="${menu.category == '1'}">操作权限</c:if>
                        </td>
                        <td>${menu.href}</td>
                        <td>${menu.permission}</td>
                        <td>${menu.createDate}</td>
                        <td>
                            <c:if test="${menu.deleteFlag == '0'}">禁用</c:if>
                            <c:if test="${menu.deleteFlag == '1'}">启用</c:if>
                        </td>
                        <td>${menu.remark}</td>
                        <td><button onclick="editMenu(this)">编辑</button><button onclick="disableMenu(this)">禁用</button></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
</div>
<script src="${basePath}/static/js/jquery-3.2.1.js"></script>
<script src="${basePath}/static/plugins/jquery-treetable/jquery.treetable.js"></script>
<script>
    $("#menus").treetable({
        expandable: true,
        clickableNodeNames:true,
        indent:19
    });
    function editMenu(obj) {
        var menuId = $(obj).parent().parent().find("td").eq(1).text();
        $(location).attr('href', '${basePath}/menu/toAddOrEdit.do?menuId='+menuId);
    };
    function disableMenu(obj) {
        var menuId = $(obj).parent().parent().find("td").eq(1).text();
        $(location).attr('href', '${basePath}/menu/disableMenu.do?menuId='+menuId);
    }
</script>
</body>
</html>