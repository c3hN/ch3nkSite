<%--
  Created by IntelliJ IDEA.
  User: chenkai
  Date: 2018/12/17
  Time: 12:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<html>
<head>
    <title>用户回收站</title>
    <link rel="stylesheet" href="${basePath}/static/plugins/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="${basePath}/static/plugins/bootstrap-table/bootstrap-table.min.css">
    <link rel="stylesheet" href="${basePath}/static/plugins/bootstrap-datetimepicker/bootstrap-datetimepicker.min.css">
    <link rel="stylesheet" href="${basePath}/static/plugins/font-awesome-4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="${basePath}/static/css/form.css">
    <link rel="stylesheet" href="${basePath}/static/css/table.css">
</head>
<body>
<div class="content-head">
    <ol class="breadcrumb">
        <li class="active">
            <a href="javascript:;">组织权限</a>
        </li>
        <li class="active">
            <a href="javascript:;">用户管理</a>
        </li>
        <li class="active">
            <a href="javascript:;">回收站</a>
        </li>
    </ol>
</div>
<div class="content-body" id="content-body">
    <div class="content-wrap">
        <div class="table_operations" id="table_operations">
            <div class="btns_operations">
                <button type="button" class="btn btn-default btn-sm" id="recoverBatchBtn">批量还原</button>
                <button type="button" class="btn btn-danger btn-sm" id="deleteBatchBtn">批量删除</button>
            </div>
            <div class="search_operations">
                <div class="form-inline">
                    <input type="text" name="likeAccount" placeholder="账号" class="form-control">
                    <input type="text" name="likeNickName" placeholder="昵称" class="form-control">
                    <input type="text" name="likeCreateTime" readonly  id="datetimepicker" placeholder="注册时间" class="form-control">
                </div>
                <div>
                    <button type="button" class="btn btn-sm btn-default" id="tableSearchBtn">搜索</button>
                    <button type="button" class="btn btn-sm btn-primary" id="tableResetBtn">重置</button>
                </div>
            </div>
        </div>
        <table id="usersDeleted" class="table-hover general-table"></table>
    </div>
</div>
<script src="${basePath}/static/js/jquery-3.2.1.js"></script>
<script src="${basePath}/static/plugins/bootstrap/js/bootstrap.min.js"></script>
<script src="${basePath}/static/plugins/bootstrap-table/bootstrap-table.min.js"></script>
<script src="${basePath}/static/plugins/bootstrap-table/locale/bootstrap-table-zh-CN.js"></script>
<script src="${basePath}/static/plugins/bootstrap-datetimepicker/bootstrap-datetimepicker.min.js"></script>
<script src="${basePath}/static/plugins/bootstrap-datetimepicker/bootstrap-datetimepicker.zh-CN.js"></script>
<script src="${basePath}/static/plugins/layer/layer.js"></script>
<script>
    table = $("#usersDeleted").bootstrapTable({
        url:'${basePath}/user/listUsersDeleted.do',
        height:750,
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
            {checkbox:true},
            {title:'序号',formatter:'numFormatter', align:'center',width:'30'},
            {field:'account',title:'登录账号',align:'center',width:'100'},
            {field:'nickName',title:'用户昵称',align:'center',width:'100'},
            {field:'department.deptName',title:'所属部门',align:'center',width:'100'},
            {field:'createTime',title:'注册时间',align:'center',width:'100'},
            {field:'updateTime',title:'删除时间',align:'center',width:'100'},
            {title:'操作',events:'roleOperateEvents',formatter:'operateFormatter',align:'center',width:'100'}
        ]
    });
    //表格序号
    function numFormatter(value, row, index) {
        var pageSize = $('#usersDeleted').bootstrapTable('getOptions').pageSize;     //通过table的#id 得到每页多少条
        var pageNumber = $('#usersDeleted').bootstrapTable('getOptions').pageNumber; //通过table的#id 得到当前第几页
        return pageSize * (pageNumber - 1) + index + 1;    // 返回每条的序号： 每页条数 *（当前页 - 1 ）+ 序号
    }
    //表格操作
    function operateFormatter(value, row, index) {
        return [
            '<button class="btn btn-default btn-xs" id="recoveUser">还原</button> ',
            '<button class="btn btn-default btn-xs btn-danger" id="deleteUser">删除</button> '
        ].join('');
    }
    window.roleOperateEvents = {
        "click #recoveUser":function (e,value, row, index) {
            layer.confirm("确认还原该用户？",{btn:['确定','取消'],icon:3},
                function (index,layero) {
                    $.post("${basePath}/user/userRecover.do",{"userId":row.userId},function (data) {
                        if (data.msg=="success"){
                            layer.alert("还原成功,用户已还原至 "+row.department.deptName+" 下",{icon:1});
                            table.bootstrapTable('refresh',{url:'${basePath}/user/listUsersDeleted.do'});//重载表格
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
                    $.post("${basePath}/user/deleteUser.do",{"userId":row.userId},function (data) {
                        if (data.msg == "success") {
                            layer.alert("用户已被彻底删除",{icon:1});
                            table.bootstrapTable('refresh',{url:'${basePath}/user/listUsersDeleted.do'});//重载表格
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
    //搜索
    $("#tableSearchBtn").click(function () {
        //模糊查询条件
        var likeAccount = $("input[name='likeAccount']").val();
        var likeNickName = $("input[name='likeNickName']").val();
        var likeCreateTime = $("input[name='likeCreateTime']").val();
        if (likeAccount != '' || likeNickName != '' || likeCreateTime != '') {
                table.bootstrapTable('refresh',{url:'${basePath}/user/listUsersDeleted.do?likeAccount='+likeAccount+'&likeNickName='+likeNickName+'&likeCreateTime='+likeCreateTime});
        }else {
            layer.alert('请输入搜索条件！',{icon:2});
        }
    });
    //重置
    $("#tableResetBtn").click(function () {
        $("input[name='likeAccount']").val("");
        $("input[name='likeNickName']").val("");
        $("input[name='likeCreateTime']").val("");
        table.bootstrapTable('refresh',{url:'${basePath}/user/listUsersDeleted.do'});
    });
//    批量还原
    $("#recoverBatchBtn").click(function () {
        layer.load(2);    //加载层
        var selected = table.bootstrapTable('getSelections');
        if (selected.length != 0) {
            layer.confirm('确认还原所选择的用户？',{btn:['确认还原','取消'],icon:3},
            function (index,layero) {//确认
                layer.close(index);
                var userIds = new Array();
                for(i = 0 , len = selected.length; i < len; i++) {
                    userIds[i] = selected[i].userId;
                }
                $.ajax({
                    type:'post',
                    url:'${basePath}/user/userRecoverBatch.do',
                    dataType:'json',
                    traditional:true,
                    data:{'userIds':userIds},
                    success:function (data) {
                        layer.closeAll('loading');
                        if(data.msg == "success") {
                            table.bootstrapTable('refresh',{url:'${basePath}/user/listUsersDeleted.do'});//重载表格
                            layer.msg("已成功还原"+data.total+"个用户！");
                        }else {
                            layer.closeAll();
                            layer.alert("还原失败",{icon:2});
                        }
                    }
                });
            },
            function () {
                layer.closeAll();
            })
        }else {
            layer.closeAll('loading');
            layer.msg("请选择需要还原的用户！");
        }
    });
//    批量删除
    $("#deleteBatchBtn").click(function () {
        layer.load(2);//加载层
        var selected = table.bootstrapTable('getSelections');
        if (selected.length != 0) {
            layer.confirm('确认彻底删除所选择的用户？',{btn:['确认删除','取消'],icon:3},
            function (index,layero) {//确认
                layer.close(index);
                var userIds = new Array();
                for (i = 0 , len = selected.length; i < len; i++) {
                    userIds[i] = selected[i].userId;
                }
                $.ajax({
                    type:'post',
                    url:'${basePath}/user/deleteUserBatch.do',
                    dataType:'json',
                    traditional:true,
                    data:{'userIds':userIds},
                    success:function (data) {
                        layer.closeAll('loading');
                        if(data.msg == "success") {
                            table.bootstrapTable('refresh',{url:'${basePath}/user/listUsersDeleted.do'});//重载表格
                            layer.msg("已彻底删除！");
                        }else {
                            layer.closeAll();
                            layer.alert("删除失败",{icon:2});
                        }
                    }
                });
            },
            function (index,layero) {
                layer.closeAll();
            });
        }else {
            layer.closeAll('loading');
            layer.msg("请选择需要彻底删除的用户！");
        }
    });
    //时间
    $('#datetimepicker').datetimepicker({
        format: 'yyyy-mm-dd',
        language:'zh-CN',
        autoclose:true,
        todayHighlight:true,
        minView:'2'
    });
</script>
</body>
</html>