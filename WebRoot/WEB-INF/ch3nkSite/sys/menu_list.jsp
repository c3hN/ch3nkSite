<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>菜单列表</title>
    <link rel="stylesheet" href="${basePath}/static/plugins/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="${basePath}/static/plugins/jquery-treetable/css/jquery.treetable.theme.default.css">
    <link rel="stylesheet" href="${basePath}/static/plugins/jquery-treetable/css/jquery.treetable.css">
    <link rel="stylesheet" href="${basePath}/static/plugins/font-awesome-4.7.0/css/font-awesome.min.css">
    <style>
        table,th,td{
            border: 1px solid #dbdbdb;
        }
        table.treetable{
            width: 100%;
            margin: 0 10px 0 10px;
        }
        table.treetable thead tr{
            height: 38px;
            line-height: 38px;
        }
        table.treetable thead tr th{
            border: 1px solid #dbdbdb;
            background-color: #f2f2f2;
            text-align: center;
            font-weight: 400;
            font-size: 14px;
            color: #666666;
            padding: 0 15px;
        }
        table.treetable tbody tr:hover{
            background-color: #F2F2F2;
        }
        table.treetable tr.branch{
            background-color: #FFFFFF;
        }
        table.treetable tbody tr{
            background-color: #FFFFFF;
            font-size: 14px;
            font-weight: 400;
            color: #666666;
            text-align: left;
        }
        table.treetable tbody tr th{
            background-color: #ffffff;
            font-size: 14px;
            line-height: 38px;
        }
        .menu-list{
            margin: 0 25px 0 10px;
        }
       .operations{
           height: 50px;
           margin: 0 20px;
       }
    </style>
</head>
<body>
<div class="position" style="width: 100%; height: 50px; background-color: #FFFFFF; margin-bottom: 40px;line-height: 50px;padding: 0 0 0 20px;">
    <div class="postion-content"><i class="fa fa-bars"></i>&nbsp;菜单列表</div>
</div>
<div class="content">
    <div class="operations">
        <div class="btn-group">
            <button class="btn btn-default btn-sm" id="addMenuBtn">新增</button>
            <button class="btn btn-default btn-sm" onclick="window.location.reload(true)">刷新</button>
        </div>
    </div>
    <div class="menu-list">
        <table id="menus">
            <thead>
                <tr>
                    <th style="width: 95px;">菜单名称</th><th>类别</th><th>资源路径</th><th>权限标识</th><th>创建时间</th><th>状态</th><th>备注</th><th>操作</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${list}" var="menu">
                    <tr data-tt-id="${menu.menuId}" data-tt-parent-id="${menu.parentId}" data-tt-branch="${menu.hasBranch}">
                        <td>${menu.name}</td>
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
                            <button class="btn btn-default btn-xs" onclick="detailMenu(this)">查看</button>
                            <button class="btn btn-default btn-xs" onclick="editMenu(this)">编辑</button>
                            <button class="btn btn-default btn-xs btn-danger" onclick="deleteMenu(this)">删除</button>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
</div>
<script src="${basePath}/static/js/jquery-3.2.1.js"></script>
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
    $("#addMenuBtn").click(function () {
        $(location).prop("href","${basePath}/menu/toAddOrEdit.do");
    });
   function editMenu(obj) {
       var menuId = $(obj).parent().parent().find("td").eq(1).text();
       $(location).attr('href', '${basePath}/menu/toAddOrEdit.do?menuId='+menuId);
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
    }
</script>
</body>
</html>