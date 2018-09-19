<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<html>
<head>
    <title>角色列表</title>
    <link rel="stylesheet" href="${basePath}/static/plugins/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="${basePath}/static/plugins/bootstrap-table/bootstrap-table.min.css">
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
    </style>
</head>
<body>
<div class="position">
    <div class="postion-content"><i class="fa fa-id-card-o"></i>&nbsp;角色列表</div>
    <div class="operations">
        <button class="btn btn-default" onclick="javascript:history.back(-1);"><i class="fa fa-reply"></i></button>
    </div>
</div>
<div class="content">
    <table id="roles"></table>
</div>
<script src="${basePath}/static/js/jquery-3.2.1.js"></script>
<script src="${basePath}/static/plugins/bootstrap/js/bootstrap.min.js"></script>
<script src="${basePath}/static/plugins/bootstrap-table/bootstrap-table.min.js"></script>
<script src="${basePath}/static/plugins/bootstrap-table/locale/bootstrap-table-zh-CN.js"></script>
<script src="${basePath}/static/plugins/layer/layer.js"></script>
<script>
    table = $("#roles").bootstrapTable({
        url:'${basePath}/role/listRolesDeleted.do',
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
            {field:'eName',title:'编号',align:'center',width:'100'},
            {field:'department.deptName',title:'所属部门',align:'center',width:'100'},
            {field:'useFlag',title:'状态',align:'center',width:'100',formatter:'stateFormatter'},
            {field:'createDate',title:'创建时间',align:'center',width:'100'},
            {field:'remark',title:'备注',align:'center',width:'200'},
            {title:'操作',events:'roleOperateEvents',formatter:'operateFormatter',align:'center',width:'100'}
        ]
    });
    //表格操作
    function operateFormatter(value, row, index) {
        return [
            '<button class="btn btn-default btn-xs" id="recoveRole">还原</button> ',
            '<button class="btn btn-default btn-xs btn-danger" id="deleteRole">删除</button> '
        ].join('');
    }
    window.roleOperateEvents = {
        "click #recoveRole":function (e,value, row, index) {
            console.log(row)
            layer.confirm("确认还原该角色？",{btn:['确定','取消'],icon:3},
            function (index,layero) {
                $.post("${basePath}/role/recoveRole.do",{"roleId":row.roleId},function (data) {
                    if (data.msg=="success"){
                       layer.alert("该角色已被还原至部门 "+row.department.deptName+" 下");
                        table.bootstrapTable('refresh',{silent: true,url:'${basePath}/role/listRolesDeleted.do'});//重载表格
                    }else{
                        layer.alert("还原失败，请重试");
                    }
                });
                layer.close(index);
            },
            function (index,layero) {
                layer.close(index);
            })
        },
        "click #deleteRole":function (e,value, row, index) {
            layer.confirm('删除后无法还原，确定删除该角色?',{btn:['确定','取消'],icon:3},
            function (index,layero) {
                $.post("${basePath}/role/delete.do",{"roleId":row.roleId},function (data) {
                    if (data.msg == "success") {
                        layer.alert("角色已被彻底删除");
                        table.bootstrapTable('refresh',{silent: true,url:'${basePath}/role/listRolesDeleted.do'});//重载表格
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
    // deptTree.expandNode(deptTree.getNodes()[0]);    //展开第一层
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

</script>
</body>
</html>
