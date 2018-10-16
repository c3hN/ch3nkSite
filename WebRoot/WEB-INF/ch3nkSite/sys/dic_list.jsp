<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<html>
<head>
    <title>用户列表</title>
    <style>
        .type-list{
            border: 1px solid black;
        }
        .type-list a{
            display: block;
            text-decoration: none;
            color: #000;
            border: 1px solid #000;
        }
        .type-list a:hover{
            ;
        }
    </style>
</head>
<link rel="stylesheet" href="${basePath}/static/plugins/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="${basePath}/static/plugins/ztree/css/metroStyle/metroStyle.css">
<body>
<div class="contents" style="margin: 10px 0 0 10px">
    <div class="list-operates">
        <div class="layui-btn-group">
            <button class="layui-btn layui-btn-sm" id="addUserBtn">新增</button>
            <button class="layui-btn layui-btn-sm">导入</button>
            <button class="layui-btn layui-btn-sm">导出</button>
        </div>
    </div>
    <div class="container">
        <div class="row">
            <div class="col-md-2">
                <div class="content">
                    <ul class="ztree" id="dics"></ul>
                </div>
            </div>
            <div class="col-md-10">
                <div class="table-content">
                </div>
            </div>
        </div>
    </div>
</div>


<%--<script>--%>

<%--</script>--%>
<script src="${basePath}/static/js/jquery-3.2.1.js"></script>
<script src="${basePath}/static/plugins/bootstrap/js/bootstrap.min.js"></script>
<script src="${basePath}/static/plugins/ztree/js/jquery.ztree.core.min.js"></script>
<script>
    var setting = {
        data:{
            simpleData:{
                enable:true,
                idKey:"dicId",
                pIdKey:"parentId",
                rootPid:null
            },
            key:{
                name:"dicName",
                title:"dicName"
            }
        }/*,
        callback:{
            onClick:function (event, treeId, treeNode, clickFlag) {
                <%--table.bootstrapTable('refresh',{silent: true,url:'${basePath}/role/list?deptId='+treeNode.deptId});--%>
            }
        }*/
    };
    var dicTreeObj = $.fn.zTree.init($("#dics"), setting,  ${dicNodes});

</script>
</body>
</html>
