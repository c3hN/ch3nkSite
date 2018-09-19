<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<html>
<head>
    <title>用户回收站</title>
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
    <div class="postion-content"><i class="fa fa-id-card-o"></i>&nbsp;用户回收站</div>
    <div class="operations">
        <button class="btn btn-default" id="goback"><i class="fa fa-reply"></i></button>
    </div>
</div>
<div class="content">
    <table id="users"></table>
</div>
<script src="${basePath}/static/js/jquery-3.2.1.js"></script>
<script src="${basePath}/static/plugins/bootstrap/js/bootstrap.min.js"></script>
<script src="${basePath}/static/plugins/bootstrap-table/bootstrap-table.min.js"></script>
<script src="${basePath}/static/plugins/bootstrap-table/locale/bootstrap-table-zh-CN.js"></script>
<script src="${basePath}/static/plugins/layer/layer.js"></script>
<script>
    table = $("#users").bootstrapTable({
        url:'${basePath}/user/listUsersDeleted.do',
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
            {field:'account',title:'登录账号',align:'center',width:'100'},
            {field:'nickName',title:'用户昵称',align:'center',width:'100'},
            {field:'createTime',title:'注册时间',align:'center',width:'100'},
            {field:'updateTime',title:'删除时间',align:'center',width:'100',formatter:'stateFormatter'},
            {title:'操作',events:'roleOperateEvents',formatter:'operateFormatter',align:'center',width:'100'}
        ]
    });
    //表格操作
    function operateFormatter(value, row, index) {
        return [
            '<button class="btn btn-default btn-xs" id="recoveUser">还原</button> ',
            '<button class="btn btn-default btn-xs btn-danger" id="deleteUser">删除</button> '
        ].join('');
    }
    window.roleOperateEvents = {
        "click #recoveUser":function (e,value, row, index) {
            console.log(row)
            layer.confirm("确认还原该用户？",{btn:['确定','取消'],icon:3},
            function (index,layero) {
                $.post("${basePath}/user/recoveUser.do",{"userId":row.userId},function (data) {
                    if (data.msg=="success"){
                       layer.alert("还原成功");
                       table.bootstrapTable('refresh',{silent: true,url:'${basePath}/user/listUsersDeleted.do'});//重载表格
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
        "click #deleteUser":function (e,value, row, index) {
            layer.confirm('删除后无法还原，确定删除该用户?',{btn:['确定','取消'],icon:3},
            function (index,layero) {
                $.post("${basePath}/user/delete.do",{"userId":row.userId},function (data) {
                    if (data.msg == "success") {
                        layer.alert("角色已被彻底删除");
                        table.bootstrapTable('refresh',{silent: true,url:'${basePath}/user/listUsersDeleted.do'});//重载表格
                    }else if (data.data == "error"){
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
    //表格状态字段格式化
    function stateFormatter(value, row, index) {
        if (value == '1') {
            return '启用';
        }else if (value == '0') {
            return '禁用';
        }
    }
    $("#goback").click(function () {
        $(location).prop("href","${basePath}/user/tolist.do");
    });


</script>
</body>
</html>
