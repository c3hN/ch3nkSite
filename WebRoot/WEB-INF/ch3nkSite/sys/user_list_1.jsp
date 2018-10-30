<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<html>
<head>
    <title>角色列表</title>
    <link rel="stylesheet" href="${basePath}/static/plugins/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="${basePath}/static/plugins/bootstrap-table/bootstrap-table.min.css">
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
        .content{
            width: 100%;
        }
        .costom-container{
            width: 100%;
        }
        .treeContent{
            height: 700px;
            overflow: auto;
        }
        .position{
            border-bottom: 1px solid #dbdbdb;
            height: 50px;
            background-color: #FFFFFF;
            margin-bottom: 40px;
            line-height: 50px;
            padding: 0 0 0 20px;
        }
        .fixed-table-container{
            border-radius: 0 !important;
        }
        .fixed-table-container thead th{
            font-weight: 400;
            background-color: #f2f2f2;
        }
    </style>
</head>
<body>
<div class="position">
    <div class="postion-content"><i class="fa fa-user"></i>&nbsp;用户列表</div>
</div>
<div class="content">
    <div class="container costom-container">
        <div class="row">
            <div class="col-md-2">
                <div class="treeContent">
                    <ul id="dept_tree" class="ztree"></ul>
                </div>
            </div>
            <div class="col-md-10">
                <div id="table_operations">
                    <div class="btn-group">
                        <button class="btn btn-default btn-sm" id="userAddBtn">新增</button>
                        <button class="btn btn-warning btn-sm" id="toRecoveBtn">回收站</button>
                    </div>
                </div>
                <div class="table-content">
                    <table id="users"></table>
                </div>
            </div>
        </div>
    </div>
</div>
<script src="${basePath}/static/js/jquery-3.2.1.js"></script>
<script src="${basePath}/static/plugins/bootstrap/js/bootstrap.min.js"></script>
<script src="${basePath}/static/plugins/bootstrap-table/bootstrap-table.min.js"></script>
<script src="${basePath}/static/plugins/bootstrap-table/locale/bootstrap-table-zh-CN.js"></script>
<script src="${basePath}/static/plugins/ztree/js/jquery.ztree.core.min.js"></script>
<script src="${basePath}/static/plugins/layer/layer.js"></script>
<script>
    table = $("#users").bootstrapTable({
        url:'${basePath}/user/list.do',
        height:549,
        undefinedText:'暂无',
        pagination:true,    //分页
        sidePagination:'server',
        pageNumber:1,
        pageSize:10,
        pageList:[10,20,30],
        paginationLoop:false,
        sortOrder:'desc',
        toolbar:'#table_operations',
        columns:[
            {title:'序号',formatter:'numFormatter', align:'center',width:'30'},
            {field:'account',title:'登录账号',align:'center',width:'100'},
            {field:'nickName',title:'用户昵称',align:'center',width:'100'},
            {field:'department.deptName',title:'所属部门',align:'center',width:'100'},
            {field:'createTime',title:'注册时间',align:'center',width:'100'},
            {field:'updateTime',title:'上次登录时间',align:'center',width:'100'},
            {field:'loginFlag',title:'允许登录',align:'center',width:'100',formatter:'stateFormatter'},
            {title:'操作',events:'userOperateEvents',formatter:'operateFormatter',align:'center',width:'150'}
        ]
    });
    function numFormatter(value, row, index) {
        return index+1;
    }
    //表格操作
    function operateFormatter(value, row, index) {
        return [
            '<button class="btn btn-default btn-xs" id="userDetail">查看</button> ',
            '<button class="btn btn-default btn-xs" id="editUser">编辑</button> ',
            '<button class="btn btn-default btn-xs btn-danger" id="logiDeleteUser">删除</button> '
        ].join('');
    }
    window.userOperateEvents = {
        "click #userDetail":function (e,value,row,index) {
            $(location).prop('href', '${basePath}/user/toAddMenus.do?userId='+row.userId);
        },
        "click #editUser":function (e,value, row, index) {
            $(location).prop('href', '${basePath}/user/toAddOrEdit.do?userId='+row.userId);
        },
        "click #logiDeleteUser":function (e,value, row, index) {
            layer.confirm('确定删除该用户?',{btn:['确定','取消'],icon:3},
                function (index,layero) {
                    $.post("${basePath}/user/logicalDel.do",{"userId":row.userId},function (data) {
                        if (data.msg == "success") {
                            table.bootstrapTable('refresh',{silent: true,url:'${basePath}/user/list?deptId='+row.department.deptId});//重载表格
                            layer.msg("用户已被移入回收站，可在回收站中还原");
                        }else if (data.msg == "error"){
                            layer.alert("删除失败",{icon:2});
                        }
                    });
                    layer.close(index);
                },
                function (index,layero) {
                    layer.close(index);
                });
        }
    }
    //表格字段格式化
    function stateFormatter(value, row, index) {
        if (value == '1') {
            return '是';
        }else if (value == '0') {
            return '否';
        }
    };
    var setting = {
        data:{
            simpleData:{
                enable:true,
                idKey:"deptId",
                pIdKey:"parentId",
                rootPid:null
            },
            key:{
                name:"deptName",
                title:"deptName"
            }
        },
        callback:{
            onClick:function (event, treeId, treeNode, clickFlag) {
                table.bootstrapTable('refresh',{silent: true,url:'${basePath}/user/list?deptId='+treeNode.deptId});
            }
        }
    };
    var deptTreeObj = $.fn.zTree.init($("#dept_tree"), setting,  ${deptsNodes});
    var depts = deptTreeObj.getNodes();
    $.each(depts,function (index,value) {
        deptTreeObj.expandNode(depts[index]);
    });

    $("#userAddBtn").click(function () {
        var nodes = deptTreeObj.getSelectedNodes();
        if (nodes.length > 0) {
            $(location).prop("href","${basePath}/user/toAddOrEdit.do?deptId="+nodes[0].deptId);
        }else{
            $(location).prop("href","${basePath}/user/toAddOrEdit.do");
        }
    });
    $("#toRecoveBtn").click(function () {
        $(location).prop("href","${basePath}/user/toRecove.do");
    });

</script>
</body>
</html>
