<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>用户列表</title>
    <%--<link rel="stylesheet" href="${basePath}/static/plugins/layui/css/layui.css">--%>
</head>
<body>
<div class="">
        <span class="layui-breadcrumb">
            <a href="">首页</a>
            <a href="">用户列表</a>
            <a href="">亚太地区</a>
            <a><cite>正文</cite></a>
        </span>

    <div class="contents">
        <table id="users" lay-filter="usersTableFilter"></table>
    </div>

</div>
<%--<script src="${basePath}/static/js/jquery-3.2.1.js"></script>--%>
<script>
    layui.use('element', function(){
        var element = layui.element;
    });
    /**数据表格渲染*/
    layui.use(['layer','table','layer'],function () {
        var userTable = layui.table;
        var layer = layui.layer;
        userTable.render({
            elem:'#users'
            ,height:470
            ,url: '${basePath}/user/list.do' //数据接口
            ,page: true //开启分页
            ,limit:10   //每页默认显示10条
            ,limits:[10,20,30]
            ,cols: [[ //表头
                {type:'numbers',title:'序号'}
                ,{type:'checkbox'}
                ,{field: 'userCount', title: '登录账号', width:120, sort: true}
                ,{field: 'nickName', title: '用户昵称', width:120}
                ,{field: 'createTime', title: '注册时间', width:200, sort: true}
                ,{field: 'updateTime', title: '上次登录时间', width:200,sort:true}
                ,{title:'操作',width:250, align:'center', toolbar: '#operations'}
            ]]
        });
        <%--表格复选框事件监听--%>
        // 表格复选框事件监听
        userTable.on('checkbox(usersTableFilter)', function(obj){

            console.log(obj.data.userId)
        });
        // 表格工具条事件监听
        userTable.on('tool(usersTableFilter)',function (obj) {
            console.log(obj);
            if (obj.event == 'del') {
                alert('111111111');
            }
        })

    });


    layui,use('layer',function () {
        var $ = layui.jquery, layer = layui.layer; //独立版的layer无需执行这一句
        var active = {
            confirmTrans:function () {
                layer.msg('确认删除这条记录吗?', {
                    time: 20000, //20s后自动关闭
                    btn: ['确认', '取消']
                });
            }
        }
    });
</script>
<script type="text/html" id="operations">
    <a class="layui-btn layui-btn-xs" lay-event="detail">查看</a>
    <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
    <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
</script>
</body>
</html>
