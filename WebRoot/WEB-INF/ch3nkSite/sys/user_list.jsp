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
<script>
    layui.use('element', function(){
        var element = layui.element;
    });
    /**数据表格渲染*/
    layui.use(['layer','table','layer'],function () {
        var userTable = layui.table;
        var layer = layui.layer;
        userTable.render({
            elem:'#users'    //html容器ID
            ,height:470
            ,url: '${basePath}/user/list.do' //数据接口
            ,page: true //开启分页
            ,limit:10   //每页默认显示10条
            ,limits:[10,20,30]      //分页选项
            ,text: {
                none: '暂无相关数据' //默认：无数据。注：该属性为 layui 2.2.5 开始新增
            }
            ,even:true      //开启隔行背景
            ,cols: [[ //表头
                {type:'numbers',title:'序号'}
                ,{field: 'userCount', title: '登录账号', width:120, sort: true}
                ,{field: 'nickName', title: '用户昵称', width:120}
                ,{field: 'createTime', title: '注册时间', width:200, sort: true}
                ,{field: 'updateTime', title: '上次登录时间', width:200,sort:true}
                ,{title:'操作',width:250, align:'center', toolbar: '#operations'}
            ]]
        });
        //工具条监听
        userTable.on('tool(usersTableFilter)',function (obj) {    //表格工具条事件监听，返回obj
            if (obj.event == 'del') {
                layer.confirm('确定删除这条数据吗？',
                    {btn:['删除','取消'],
                        yes:function (index,layero) {      //按钮1回调
                            //
                            //  * 与后台交互
                            $.ajax({
                                type:'post',
                                url:'${basePath}/user/del.do?userId='+obj.data.userId,
                                error:function () {
                                    layer.alert('请求失败，请重试！');
                                },
                                success:function (data) {
                                   if (data == 'success') {
                                       obj.del(index);      //删除DOM
                                       layer.close(index);
                                       layer.msg("删除成功")
                                   }else {
                                       layer.msg('删除失败，请重试')
                                   }
                                }
                            })
                        },
                        btn2:function (index) {        //按钮2回调
                            layer.close(index);
                        }
                    }
                )
            }else if (obj.event == 'edit') {
                layer.open({
                    id:'userEdit',  //指定id
                    type:1,     //页面
                    title:'用户信息编辑',
                    closeBtn:0, //右上角关闭按钮不显示
                    area:['500px','600px'],
                    resize :false,
                    offset:['auto'],
                    content:'11111111111',
                    btn:['保存','取消'],
                    yes:function (index,layero) {       //保存按钮回调
                        layer.close(index);
                    },
                    btn2:function (index,layero) {      //取消按钮回调
                        layer.close(index);
                    }


                });
            }
        });

    });


</script>
<script type="text/html" id="operations">
    <a class="layui-btn layui-btn-xs" lay-event="detail">查看</a>
    <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
    <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
</script>
</body>
</html>
