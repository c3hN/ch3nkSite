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
    <div class="postion-content"><i class="fa fa-id-card-o"></i>&nbsp;角色列表</div>
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
                        <button class="btn btn-default btn-sm" id="roleAddBtn">新增</button>
                        <button class="btn btn-warning btn-sm" id="toRecoveBtn">回收站</button>
                    </div>
                </div>
                <div class="table-content">
                    <table id="roles"></table>
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
    table = $("#roles").bootstrapTable({
        url:'${basePath}/role/list.do',
        height:549,
        undefinedText:'暂无',
        pagination:true,    //分页
        sidePagination:'server',
        pageNumber:1,
        pageSize:10,
        pageList:[10,20],
        paginationLoop:false,
        sortOrder:'desc',
        toolbar:'#table_operations',
        columns:[
            {title:'序号',formatter:'numFormatter', align:'center',width:'30'},
            {field:'name',title:'名称',align:'center',width:'100'},
            {field:'eName',title:'编号',align:'center',width:'100'},
            {field:'department.deptName',title:'所属部门',align:'center',width:'100'},
            {field:'useFlag',title:'状态',align:'center',width:'100',formatter:'stateFormatter'},
            {field:'createDate',title:'创建时间',align:'center',width:'100'},
            {field:'remark',title:'备注',align:'center',width:'100'},
            {title:'操作',events:'roleOperateEvents',formatter:'operateFormatter',align:'center',width:'150'}
        ]
    });
    function numFormatter(value, row, index) {
        // return index+1;
        var pageSize = $('#roles').bootstrapTable('getOptions').pageSize;     //通过table的#id 得到每页多少条
        var pageNumber = $('#roles').bootstrapTable('getOptions').pageNumber; //通过table的#id 得到当前第几页
        return pageSize * (pageNumber - 1) + index + 1;    // 返回每条的序号： 每页条数 *（当前页 - 1 ）+ 序号
    }
    //表格操作
    function operateFormatter(value, row, index) {
        return [
            '<button class="btn btn-default btn-xs" id="addMenus">权限分配</button> ',
            '<button class="btn btn-default btn-xs" id="editRole">编辑</button> ',
            '<button class="btn btn-default btn-xs btn-danger" id="logiDeleteRole">删除</button> '
        ].join('');
    }
    window.roleOperateEvents = {
        "click #addMenus":function (e,value,row,index) {
            $(location).prop('href', '${basePath}/role/toAddMenus.do?roleId='+row.roleId);
        },
        "click #editRole":function (e,value, row, index) {
            $(location).prop('href', '${basePath}/role/toAddOrEdit.do?roleId='+row.roleId);
        },
        "click #logiDeleteRole":function (e,value, row, index) {
            layer.confirm('确定删除该角色?',{btn:['确定','取消'],icon:3},
            function (index,layero) {
                $.post("${basePath}/role/logicalDelete.do",{"roleId":row.roleId},function (data) {
                    if (data.data == "success") {
                        layer.msg("角色已被移入回收站，可在回收站中还原");
                        table.bootstrapTable('refresh',{silent: true,url:'${basePath}/role/list?deptId='+row.department.deptId});//重载表格

                    }else if (data.data == "error"){
                        layer.alert("该角色被使用，删除失败",{icon:2});
                    }
                });
                layer.close(index);
            },
            function (index,layero) {
                layer.close(index);
            });
        }
    }
    //表格状态字段格式化
    function stateFormatter(value, row, index) {
        if (value == '1') {
            return '启用';
        }else if (value == '0') {
            return '禁用';
        }
    }
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
                table.bootstrapTable('refresh',{silent: true,url:'${basePath}/role/list?deptId='+treeNode.deptId});
            }
        }
    };
    var deptTreeObj = $.fn.zTree.init($("#dept_tree"), setting,  ${nodes});
    var depts = deptTreeObj.getNodes();
    $.each(depts,function (index,value) {
       deptTreeObj.expandNode(depts[index]);
    });

    $("#roleAddBtn").click(function () {
        var nodes = deptTreeObj.getSelectedNodes();
        if (nodes.length > 0) {
            $(location).prop("href","${basePath}/role/toAddOrEdit.do?deptId="+nodes[0].deptId);
        }else{
            $(location).prop("href","${basePath}/role/toAddOrEdit.do");
        }
    });
    $("#toRecoveBtn").click(function () {
        $(location).prop("href","${basePath}/role/toRecove.do");
    });

</script>
</body>
</html>
