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
            <a href="javascript:;">部门管理</a>
        </li>
    </ol>
</div>
<div class="content-body" id="content-body">
    <div class="content-wrap">
        <div class="table_operations" id="table_operations">
            <div class="btns_operations">
                <shiro:hasPermission name="dept:add">
                    <a type="button" class="btn btn-default btn-sm" href="${basePath}/dept/operate.do?op=add">新增</a>
                </shiro:hasPermission>
            </div>
        </div>
        <table id="depts">
            <thead>
            <tr>
                <th>部门名称</th><th>部门编号</th><th>部门简称</th><th>创建时间</th><th>状态</th><th>操作</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${list}" var="dept">
                <tr data-tt-id="${dept.deptId}" data-tt-parent-id="${dept.parentId}" data-tt-branch="${dept.hasBranch}">
                    <td>${dept.deptName}</td>
                    <td hidden="hidden">${dept.deptId}</td>
                    <td style="text-align: center">${dept.deptNum}</td>
                    <td style="text-align: center">${dept.deptAbbr}</td>
                    <td style="text-align: center"><fmt:formatDate value="${dept.createDate}" pattern="yyyy-MM-dd"/> </td>
                    <td style="text-align: center">
                        <c:if test="${dept.state == '1'}">启用</c:if>
                        <c:if test="${dept.state == '0'}">禁用</c:if>
                    </td>
                    <td style="text-align: center">
                        <shiro:hasPermission name="dept:detail">
                            <button class="btn btn-default btn-xs" onclick="detailDept(this)">查看</button>
                        </shiro:hasPermission>
                        <shiro:hasPermission name="dept:edit">
                            <button class="btn btn-default btn-xs" onclick="editDept(this)">编辑</button>
                        </shiro:hasPermission>
                        <shiro:hasPermission name="dept:delete">
                            <button class="btn btn-default btn-xs btn-danger" onclick="deleteDept(this)">删除</button>
                        </shiro:hasPermission>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
<script src="${basePath}/static/js/jquery-3.2.1.js"></script>
<script src="${basePath}/static/plugins/bootstrap/js/bootstrap.min.js"></script>
<script src="${basePath}/static/plugins/jquery-treetable/jquery.treetable.js"></script>
<script src="${basePath}/static/plugins/layer/layer.js"></script>
<script>
    var treetable=$("#depts").treetable({
        expandable: true,
        clickableNodeNames:true,
        stringCollapse: '收起',
        stringExpand: '展开',
        indent:19,
        onNodeExpand: function () {
            var node = this;        //判断当前节点是否已经拥有子节点
            var childSize = $("#treetable").find("[data-tt-parent-id='" + node.id + "']").length;
            $.post(
                '${basePath}/dept/loadTreeBranch.do',
                {'deptId':node.id},
                function(data,status,xhr){
                    $.each(data.data,function (index,obj) {
                        $("#depts").treetable("loadBranch", node, obj);// 插入子节点
                    });
                }
            );
        },
        onNodeCollapse: function() {
            var node = this;
            $("#depts").treetable("unloadBranch", node);
        }
    });
    //编辑
    function editDept(obj) {
        var deptId = $(obj).parent().parent().find("td").eq(1).text();
        $(location).attr('href', '${basePath}/dept/operate.do?op=edit&deptId='+deptId);
    };
    function deleteDept(obj) {
        var deptId = $(obj).parent().parent().find("td").eq(1).text();
        layer.confirm('确定删除该部门？',{btn:['确定','取消'],icon:3},
            function () {
                $.post("${basePath}/dept/deleteDept.do",{"deptId":deptId},function (data) {
                    if (data == 'success') {
                        // $(obj).parent().parent().remove();  //删除该行
                        $(obj).parent().parent().hide(700);  //删除该行
                        layer.msg("删除成功");
                    }else{
                        layer.alert("该部门下存在子子部门或角色，无法删除",{icon: 2});
                    }
                });
            },function (index,layero) {
                layer.close(index);
            });
    };
</script>
</body>
</html>