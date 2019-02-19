<%--
  Created by IntelliJ IDEA.
  User: chenkai
  Date: 2018/12/17
  Time: 12:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<html>
<head>
    <title>部门列表</title>
    <link rel="stylesheet" href="${basePath}/static/plugins/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="${basePath}/static/plugins/jquery-treetable/css/jquery.treetable.theme.default.css">
    <link rel="stylesheet" href="${basePath}/static/plugins/jquery-treetable/css/jquery.treetable.css">
    <link rel="stylesheet" href="${basePath}/static/plugins/font-awesome-4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="${basePath}/static/css/form.css">
    <link rel="stylesheet" href="${basePath}/static/css/treetable.css">
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
    </ol>
</div>
<div class="content-body" id="content-body">
    <div class="content-wrap">
        <div class="table_operations" id="table_operations">
            <div class="btns_operations">
                <shiro:hasPermission name="menu:add">
                    <a type="button" class="btn btn-default btn-sm" href="${basePath}/menu/operate?op=add">新增</a>
                </shiro:hasPermission>
            </div>
        </div>
        <table id="menus">
            <thead>
            <tr>
                <th width="200">菜单名称</th><th width="40">类别</th><th width="180">资源路径</th><th width="180">权限标识</th><th width="100">创建时间</th><th width="40">状态</th><th width="100">备注</th><th width="160">操作</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${list}" var="menu">
                <tr data-tt-id="${menu.menuId}" data-tt-parent-id="${menu.parentId}" data-tt-branch="${menu.hasBranch}">
                    <td style="text-align: left;">${menu.name}</td>
                    <td hidden="hidden">${menu.menuId}</td>
                    <td>
                        <c:if test="${menu.category == '0'}">菜单</c:if>
                        <c:if test="${menu.category == '1'}">操作</c:if>
                    </td>
                    <td>${menu.href}</td>
                    <td>${menu.permission}</td>
                    <td><fmt:formatDate value="${menu.createDate}" pattern="yyyy-MM-dd"/> </td>
                    <td>
                        <c:if test="${menu.deleteFlag == '1'}">启用</c:if>
                        <c:if test="${menu.deleteFlag == '0'}">禁用</c:if>
                    </td>
                    <td>${menu.remark}</td>
                    <td style="text-align: center">
                        <shiro:hasPermission name="menu:detail">
                            <button class="btn btn-default btn-xs" onclick="detailMenu(this)">查看</button>
                        </shiro:hasPermission>
                        <shiro:hasPermission name="menu:edit">
                            <button class="btn btn-default btn-xs" onclick="editMenu(this)">编辑</button>
                        </shiro:hasPermission>
                        <shiro:hasPermission name="menu:delete">
                            <button class="btn btn-default btn-xs btn-danger" onclick="deleteMenu(this)">删除</button>
                        </shiro:hasPermission>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <%--<table id="usersDeleted" class="table-hover general-table"></table>--%>
    </div>
</div>
<script src="${basePath}/static/js/jquery-3.2.1.js"></script>
<script src="${basePath}/static/plugins/bootstrap/js/bootstrap.min.js"></script>
<script src="${basePath}/static/plugins/jquery-treetable/jquery.treetable.js"></script>
<script src="${basePath}/static/plugins/layer/layer.js"></script>
<script>
    $("#menus").treetable({
        expandable: true,
        clickableNodeNames:true,
        stringCollapse: '收起',
        stringExpand: '展开',
        indent:19,
        onNodeExpand: function () {
            var node = this;        //判断当前节点是否已经拥有子节点
            var childSize = $("#treetable").find("[data-tt-parent-id='" + node.id + "']").length;
            $.post(
                '${basePath}/menu/loadTreeBranch.do',
                {'menuId':node.id},
                function(data,status,xhr){
                    $.each(data.data,function (index,obj) {
                        $("#menus").treetable("loadBranch", node, obj);// 插入子节点
                    });
                }
            );
        },
        onNodeCollapse: function() {
            var node = this;
            $("#menus").treetable("unloadBranch", node);
        }
    });
    //编辑
    function editMenu(obj) {
        var menuId = $(obj).parent().parent().find("td").eq(1).text();
        $(location).attr('href', '${basePath}/menu/operate.do?op=edit&menuId='+menuId);
    };
    function disableMenu(obj) {
        var menuId = $(obj).parent().parent().find("td").eq(1).text();
        $(location).attr('href', '${basePath}/menu/disableMenu.do?menuId='+menuId);
    };
    function deleteMenu(obj) {
        var menuId = $(obj).parent().parent().find("td").eq(1).text();
        layer.confirm('确定删除该菜单？',{btn:['确定','取消'],icon:3},
            function () {
                $.post("${basePath}/menu/deleteMenu.do",{"menuId":menuId},function (data) {
                    if (data == 'success') {
                        // $(obj).parent().parent().remove();  //删除该行
                        $(obj).parent().parent().hide(700);  //隐藏该行
                        layer.msg("删除成功");
                    }else{
                        layer.alert("该菜单下存在子节点，删除失败",{icon: 2});
                    }
                });
            },
            function (index,layero) {
                layer.close(index);
            })
    };
</script>
</body>
</html>