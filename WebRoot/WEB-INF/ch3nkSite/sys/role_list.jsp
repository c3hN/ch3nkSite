<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<html>
<head>
    <title>角色列表</title>
    <link rel="stylesheet" href="${basePath}/static/plugins/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="${basePath}/static/plugins/bootstrap-table/dist/bootstrap-table.min.css">
    <link rel="stylesheet" href="${basePath}/static/plugins/ztree/css/metroStyle/metroStyle.css">
    <link rel="stylesheet" href="${basePath}/static/plugins/font-awesome-4.7.0/css/font-awesome.min.css">
    <style>
        body,html{
            margin: 0;
            padding: 0;
        }
        .position{
            height: 50px;
            background-color: #FFFFFF;
            margin-bottom: 40px;
            line-height: 50px;
            padding: 0 0 0 20px;
        }
        .left-list,.right-table{
            display: inline-block;
        }

        .left-list{
            width: 30%;
            height: 400px;
            overflow: auto;
        }
        .right-table{
            width: 65%;
            height: 400px;
        }
        .right-table:after{
            content: '';
            clear: both;
        }
        a{
            display: block;
            width: 300px;
            height: 300px;
            background-color: #9F9F9F;
        }
    </style>
</head>
<body>
<div class="position">
    <div class="postion-content"><i class="fa fa-id-card-o"></i>&nbsp;角色列表</div>
</div>
<div class="content">
    <div class="container">
        <div class="row">
            <div class="col-md-2"><a href="">a</a></div>
            <div class="col-md-10"><a href="">a</a></div>
        </div>
    </div>
</div>
<%--<div class="left-list">--%>
    <%--<ul id="treeDemo" class="ztree"></ul>--%>
<%--</div>--%>
<%--<div class="right-table">--%>
    <%--<div class="btn-group" id="toolbar">--%>
        <%--<button class="btn btn-default"><i class="fa fa fa-plus"></i>新增</button>--%>
        <%--<button class="btn btn-default"><i class="fa fa-refresh"></i>刷新</button>--%>
    <%--</div>--%>
    <%--<table id="roles"></table>--%>
<%--</div>--%>
<script src="${basePath}/static/js/jquery-3.2.1.js"></script>
<script src="${basePath}/static/plugins/bootstrap/js/bootstrap.min.js"></script>
<script src="${basePath}/static/plugins/bootstrap-table/dist/bootstrap-table.min.js"></script>
<script src="${basePath}/static/plugins/bootstrap-table/dist/locale/bootstrap-table-zh-CN.js"></script>
<script src="${basePath}/static/plugins/ztree/js/jquery.ztree.core.min.js"></script>
<script src="${basePath}/static/js/role_table.js"></script>
<script>
    var setting = {	};

    var zNodes =[
        { name:"父节点1 - 展开", open:true,
            children: [
                { name:"父节点11 - 折叠",
                    children: [
                        { name:"叶子节点111"},
                        { name:"叶子节点112"},
                        { name:"叶子节点113"},
                        { name:"叶子节点114"}
                    ]},
                { name:"父节点12 - 折叠",
                    children: [
                        { name:"叶子节点121"},
                        { name:"叶子节点122"},
                        { name:"叶子节点123"},
                        { name:"叶子节点124"}
                    ]},
                { name:"父节点13 - 没有子节点", isParent:true}
            ]},
        { name:"父节点2 - 折叠",
            children: [
                { name:"父节点21 - 展开", open:true,
                    children: [
                        { name:"叶子节点211"},
                        { name:"叶子节点212"},
                        { name:"叶子节点213"},
                        { name:"叶子节点214"}
                    ]},
                { name:"父节点22 - 折叠",
                    children: [
                        { name:"叶子节点221"},
                        { name:"叶子节点222"},
                        { name:"叶子节点223"},
                        { name:"叶子节点224"}
                    ]},
                { name:"父节点23 - 折叠",
                    children: [
                        { name:"叶子节点231"},
                        { name:"叶子节点232"},
                        { name:"叶子节点233"},
                        { name:"叶子节点234"}
                    ]}
            ]},
        { name:"父节点3 - 没有子节点", isParent:true}

    ];

    $(document).ready(function(){
        $.fn.zTree.init($("#treeDemo"), setting, zNodes);
    });
</script>
</body>
</html>
