<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>用户列表</title>
    <link rel="stylesheet" href="${basePath}/static/plugins/layui/css/layui.css">
    <link rel="stylesheet" href="${basePath}/static/plugins/font-awesome-4.7.0/css/font-awesome.min.css">
    <style>
        .layui-btn-group , .table-search{
            display: inline-block;
        }
    </style>
</head>
<body>
<div class="" style="">
    <div class="position" style="width: 100%; height: 50px; background-color: #dbdbdb; margin-bottom: 40px;line-height: 50px;padding: 0 0 0 20px;">
        <div class="postion-content"><i class="fa fa-user"></i> 用户管理</div>
    </div>
    <div class="contents" style="margin: 40px 0 0 10px">
        <div class="layui-btn-group">
            <button class="layui-btn layui-btn-sm" id="addUserBtn">新增</button>
            <button class="layui-btn layui-btn-sm" id="userImportBtn">导入</button>
            <button class="layui-btn layui-btn-sm" id="userExprotBtn">统计报表</button>
        </div>
        <%--表格搜索--%>
        <div class="table-search" style="width: 158px; height: 30px;margin-left: 400px;">
                <input type="text" name="account" placeholder="账号" class="layui-input" style="display: inline-block;">
                <input type="text" name="nickName" placeholder="昵称" class="layui-input" style="display: inline-block;">
                <input type="text" name="createTime" placeholder="创建时间" class="layui-input" id="createTime">
            <%--<button class="layui-btn layui-btn-sm" id="userImportBtn1">搜索</button>--%>
            <%--<button class="layui-btn layui-btn-sm" id="userExprotBtn1">重置</button>--%>
        </div>
        <div class="user-list">
            <table id="users" lay-filter="usersTableFilter"></table>
        </div>
    </div>
    <div style="display: none;">
        <form action="${basePath}/user/editUser.do" method="post" id="loginFlagEditForm">
            <input type="text" name="userId" value=""/>
            <input type="text" name="loginFlag" value=""/>
        </form>
    </div>
</div>
<script src="${basePath}/static/js/jquery-3.2.1.js"></script>
<script src="${basePath}/static/plugins/layui/layui.js"></script>
<script>
    layui.use(['element','upload','layer','table','laytpl'], function(){
        var element = layui.element;
        var upload = layui.upload;
        var layer = layui.layer;
        var table = layui.table;
        var laytpl = layui.laytpl;
        //创建一个上传组件
        upload.render({
            elem: '#userImportBtn'
            ,url: '${basePath}/user/importUsers.do'
            ,accept: 'file' //允许上传的文件类型
            ,done: function(res, index, upload){ //上传后的回调
                table.reload('users',{
                    url: '${basePath}/user/list.do',
                    page: true, //开启分页
                    limit:10   //每页默认显示10条
                });
                layer.msg("导入成功："+res.successCount+"条;</br>导入失败:"+res.failureCount+"条;</br>信息："+res.failureInfo);
            }
        })
    });
    /**数据表格渲染*/
    layui.use(['layer','table','layer','laytpl','form'],function () {
        var userTable = layui.table;
        var layer = layui.layer;
        var laytpl = layui.laytpl;
        form = layui.form;
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
            ,even:false      //开启隔行背景
            ,cols: [[ //表头
                {type:'numbers',title:'序号'}
                ,{field: 'account', title: '登录账号',align:'center', width:115, sort: true}
                ,{field: 'nickName', title: '用户昵称',align:'center',width:115,sort:true}
                ,{field: 'createTime', title: '注册时间', align:'center',width:115, sort: true}
                ,{field: 'updateTime', title: '上次登录时间',align:'center', width:140,sort:true}
                ,{field: 'loginFlag', title: '允许登录',align:'center', width:110,templet:'#loginFlagTpl'}
                ,{title:'操作',width:250, align:'center', toolbar: '#operations'}
            ]]
        });
        //工具条监听
        userTable.on('tool(usersTableFilter)',function (obj) {    //表格工具条事件监听，返回obj
            if (obj.event == 'del') {
                layer.confirm('确定删除这条数据吗？',
                    {btn:['删除','取消'],
                        yes:function (index,layero) {      //按钮1回调
                            $.ajax({
                                type:'get',
                                url:'${basePath}/user/delete.do?userId='+obj.data.userId,
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
                            });
                        },
                        btn2:function (index) {        //按钮2回调
                            layer.close(index);
                        }
                    }
                )
            }else if (obj.event == 'edit') {
                layer.open({
                    id:'userEdit',  //指定id
                    type:2,     //iframe
                    title:'用户信息编辑',
                    closeBtn:1, //右上角关闭按钮显示
                    area:['700px','600px'],
                    resize :false,
                    move:false,
                    offset:['auto'],
                    content:'${basePath}/user/toAddOrEdit.do?userId='+obj.data.userId,
                    btn:['保存','取消'],
                    yes:function (index,layero) {       //保存按钮回调
                        layer.close(index);
                    },
                    btn2:function (index,layero) {      //取消按钮回调
                        layer.close(index);
                    }
                });
            }else if (obj.event == 'detail') {
                layer.open({
                    id:'userEdit',  //指定id
                    type:2,     //iframe
                    title:'查看用户信息',
                    closeBtn:1, //右上角关闭按钮显示
                    area:['700px','600px'],
                    resize :false,
                    move:false,
                    offset:['auto'],
                    content:'${basePath}/user/toDetail.do?userId='+obj.data.userId,
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

        form.on('switch(loginFlagFilter)',function (obj) {
            var userId = this.value;
            var loginFlag = obj.elem.checked ? "1" : "0";
            $.ajax({
                url:"${basePath}/user/switchLoginFlag.do?userId="+userId+"&loginFlag="+loginFlag,
                dataType : "json",
                error:function () {
                    layer.msg("请求失败，请重试");
                },
                success:function (data) {
                    if (data.msg == 'success') {
                        layer.msg('修改成功');
                    }else {
                        layer.msg('修改成功');
                    }
                }
            });
        });
    });
    layui.use('laydate',function () {
       var laydate = layui.laydate;
        laydate.render({
            elem:'#createTime',
            theme: 'grid'
        });
    });
    layui.use(['layer','table'],function () {
        var layer = layui.layer;
        var table = layui.table;
        $("#addUserBtn").click(function () {
            layer.open({
                id:'userAdd',  //指定id
                type:2,     //iframe
                title:'新增用户',
                closeBtn:1, //右上角关闭按钮显示
                move:false,
                area:['600px','500px'],
                resize :false,
                offset:['auto'],
                content:['${basePath}/user/toAddOrEdit.do','no'],
                btn:['立即提交','取消'],
                yes:function (index,layero) {       //提交按钮回调
                    var result = $(layero).find("iframe")[0].contentWindow.subForm();
                    layer.close(index);
                    table.reload('users',{
                        url: '${basePath}/user/list.do',
                        page: true, //开启分页
                        limit:10   //每页默认显示10条
                    });
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
<script type="text/html" id="loginFlagTpl">
    <input type="checkbox" name="loginFlag" value="{{d.userId}}" lay-skin="switch" lay-text="允许|拒绝" lay-filter="loginFlagFilter" {{ d.loginFlag == '1' ? 'checked' : '' }}>
</script>
</body>
</html>
