<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>用户列表</title>
    <link rel="stylesheet" href="${basePath}/static/plugins/layui/css/layui.css">
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
        <table id="users"></table>
    </div>

</div>
<script src="${basePath}/static/js/jquery-3.2.1.js"></script>
<script>
    layui.use('element', function(){
        var element = layui.element;

    });
    /**数据表格渲染*/
    layui.use(['layer','table','util','laytpl'],function () {
        var userTable = layui.table;
        var util = layui.util;
        userTable.render({
            elem:'#users'
            ,height:470
            ,url: '${basePath}/user/list.do' //数据接口
            ,page: true //开启分页
            ,limit:10   //每页默认显示10条
            ,cols: [[ //表头
                {field: 'userCount', title: '登录账号', width:120, sort: true, fixed: 'left'}
                ,{field: 'nickName', title: '用户昵称', width:120}
                ,{field: 'createTime', title: '注册时间', width:120, sort: true,template:function (d) {
                        return d.userList.userCount;
                    }}
                ,{field: 'updateTime', title: '上次登录时间', width:120,sort:true}
            ]]
        });
    });


</script>
<%--时间戳处理--%>
<%--<script type="text/html" >--%>
<%--$(button).click(function () {--%>
<%--alert(Date.parse(d.createTime));--%>
<%--})--%>
<%--</script>--%>
</body>
</html>
