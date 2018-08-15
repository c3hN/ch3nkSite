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
        .bootstrap-table-set{
            min-width: 300px;
        }
    </style>
</head>
<body>

<div class="contents" style="margin: 10px 0 0 10px">
    <div class="list-operates">
        <div class="layui-btn-group">
            <button class="layui-btn layui-btn-sm" id="addUserBtn">新增</button>
            <button class="layui-btn layui-btn-sm">导入</button>
            <button class="layui-btn layui-btn-sm">导出</button>
        </div>
    </div>
    <div class="list">
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
                ,{field: 'account', title: '登录账号', width:120,align:'center', sort: true}
                ,{field: 'nickName', title: '用户昵称', width:120,align:'center'}
                ,{field: 'createTime', title: '注册时间', width:200, align:'center',sort: true}
                ,{field: 'updateTime', title: '上次登录时间', width:200,align:'center',sort:true}
                ,{field: 'loginFlag', title: '禁止登录', width:200,align:'center',sort:true}
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
            }else if (obj.event == "detail") {
                layer.open({
                    id:'userDetail',  //指定id
                    type:2,     //iframe
                    title:'用户信息',
                    closeBtn:1, //右上角关闭按钮不显示
                    area:['700px','600px'],
                    resize :false,
                    move:false,
                    offset:['auto'],
                    content:'${basePath}/user/detail.do',
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

    layui.use(['layer'],function () {
        var layer = layui.layer;
        $("#addUserBtn").click(function () {
            layer.open({
                id:'userAdd',  //指定id
                type:2,     //iframe
                title:'新增用户',
                closeBtn:'1', //右上角关闭按钮显示
                move:false, //禁止拖拽
                area:['700px','600px'],
                resize :false,
                offset:['auto'],
                move:false,
                content:['${basePath}/user/toAdd.do','no'],
                btn:['立即提交','取消'],
                yes:function (index,layero) {       //保存按钮回调
                    $("iframe")[0].contentWindow.subForm();//调用子页面提交按钮
                    layer.close(index);
                    layer.msg("添加成功")
                },
                btn2:function (index,layero) {      //取消按钮回调
                    layer.close(index);
                }
            });
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
