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
            height: 100%;
        }
        .costom-container{
            width: 100%;
        }
        .treeContent{
            height: 700px;
            overflow: auto;
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
                    <button class="btn btn-default btn-sm">新增</button>
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
<script>
    table = $("#roles").bootstrapTable({
        url:'${basePath}/role/list.do',
        height:549,
        undefinedText:'暂无',
        pagination:true,    //分页
        pageNumber:1,
        pageSize:10,
        pageList:[10,20],
        paginationLoop:false,
        sortOrder:'desc',
        toolbar:'#table_operations',
        columns:[
            {field:'name',title:'名称',align:'center',width:'100'},
            // {field:'roleId',title:'roleId',align:'center',width:'170'},
            {field:'eName',title:'编号',align:'center',width:'100'},
            {field:'department.deptName',title:'部门名称',align:'center',width:'100'},
            {field:'useFlag',title:'状态',align:'center',width:'100',formatter:'stateFormatter'},
            {field:'createDate',title:'创建时间',align:'center',width:'100'},
            {field:'remark',title:'备注',align:'center',width:'200'},
            {title:'操作',events:'roleOperateEvents',formatter:'operateFormatter',align:'center',width:'150'}
        ]
    });
    //表格操作
    function operateFormatter(value, row, index) {
        var url = "${basePath}/role/toDetail?roleId="+row.roleId;
        return [
            '<button class="btn btn-default btn-xs" id="detailRole">查看</button> ',
            '<button class="btn btn-default btn-xs" id="editRole">编辑</button> ',
            '<button class="btn btn-default btn-xs btn-danger" id="deleteRole">删除</button> '
        ].join('');
    }
    //表格状态字段格式化
    function stateFormatter(value, row, index) {
        if (value == '1') {
            return '启用';
        }else if (value == '0') {
            return '未启用';
        }
    }
    window.roleOperateEvents = {
        "click #detailRole":function (e,value, row, index) {
            console.log(index);
            $(location).attr('href', '${basePath}/role/toDetail.do?roleId='+row.roleId);
        },
        "click #editRole":function (e,value, row, index) {
            $(location).attr('href', '${basePath}/role/toAddOrEdit.do?roleId='+row.roleId);
        },
        "click #deleteRole":function (e,value, row, index) {
            $.post("${basePath}/role/logicalDelete.do",{"roleId":row.roleId},function (data) {
                if (data.data == "success") {
                    alert("删除成功")
                }else{
                    alert("失败")
                }
            });
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
    var deptTree = $.fn.zTree.init($("#dept_tree"), setting,  ${nodes});
    deptTree.expandNode(deptTree.getNodes()[0]);    //展开第一层

</script>
</body>
</html>
