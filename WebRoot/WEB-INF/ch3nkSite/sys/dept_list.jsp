<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>部门列表</title>
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
        .position{
            height: 50px;
            background-color: #FFFFFF;
            margin-bottom: 40px;
            line-height: 50px;
            padding: 0 0 0 20px;
        }
    </style>
</head>
<body>
<div class="position">
    <div class="postion-content"><i class="fa fa-square-o"></i>&nbsp;部门列表</div>
</div>
<div class="content">
    <div class="operations">
        <div class="btn-group">
            <button class="btn btn-default btn-sm" onclick="window.location.reload(true)">新增</button>
            <button class="btn btn-default btn-sm" onclick="window.location.reload(true)">刷新</button>
        </div>
    </div>
    <div class="depts-list">
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
                            <button class="btn btn-default btn-xs" onclick="detailDept(this)">查看</button>
                            <button class="btn btn-default btn-xs" onclick="editDept(this)">编辑</button>
                            <button class="btn btn-default btn-xs btn-danger" onclick="deleteDept(this)">删除</button>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
</div>
<script src="${basePath}/static/js/jquery-3.2.1.js"></script>
<script src="${basePath}/static/plugins/jquery-treetable/jquery.treetable.js"></script>
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
   function editDept(obj) {
       var deptId = $(obj).parent().parent().parent().find("td").eq(1).text();
       $(location).attr('href', '${basePath}/dept/toAddOrEdit.do?deptId='+deptId);
   };
    function deleteDept(obj) {
        var deptId = $(obj).parent().parent().parent().find("td").eq(1).text();
        $(location).attr('href', '${basePath}/dept/deleteDept.do?deptId='+deptId);
    };
</script>
</body>
</html>