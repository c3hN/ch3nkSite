<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<html>
<head>
    <title>用户列表</title>
    <link rel="stylesheet" href="${basePath}/static/plugins/layui/css/layui.css">
    <link rel="stylesheet" href="${basePath}/static/plugins/layui/css/layui.css">
    <link rel="stylesheet" href="${basePath}/static/plugins/font-awesome-4.7.0/css/font-awesome.min.css">
    <style>
        .contents .table-operations .layui-btn-group , .table-search,.inputs{
            margin: 0;
            padding: 0;
            display: inline-block;
        }
        .table-search{
            margin-left: 200px;
        }
        .inputs{
            margin: 0 10px 0 0;
        }
        .table-search-input{
            width: 100px;
            height: 30px;
            margin: 0;
            padding-left: 10px;
            line-height: 30px;
            border: 1px solid #e6e6e6;
            background-color: #fff;
            border-radius: 2px;
        }
        .position{
            height: 50px;
            background-color: #FFFFFF;
            margin-bottom: 40px;
            line-height: 50px;
            padding: 0 0 0 20px;
        }
    </style>
</head>
<body>
<div class="position">
    <div class="postion-content"><i class="fa fa-user"></i>&nbsp;用户列表</div>
</div>
<div class="container">
    <div class="contents" style="margin: 30px 0 0 15px;">
        <div class="table-operations">
            <div class="layui-btn-group">
                <shiro:hasPermission name="sysUser:add">
                    <button class="layui-btn layui-btn-sm" id="addUserBtn">新增</button>
                </shiro:hasPermission>
                <shiro:hasPermission name="sysUser:import">
                    <button class="layui-btn layui-btn-sm" id="userImportBtn">导入</button>
                </shiro:hasPermission>
                <shiro:hasPermission name="sysUser:reportForm">
                    <button class="layui-btn layui-btn-sm" id="userExprotBtn">统计报表</button>
                </shiro:hasPermission>
            </div>
            <div class="table-search">
                <shiro:hasPermission name="sysUser:tableSearch">
                    <div class="inputs">
                        <input type="text" name="likeAccount" placeholder="账号" class="table-search-input">
                        <input type="text" name="likeNickName" placeholder="昵称" class="table-search-input">
                        <input type="text" name="likeCreateTime" placeholder="创建时间" id="createTime" class="table-search-input">
                    </div>
                    <div class="layui-btn-group">
                        <button class="layui-btn layui-btn-sm" id="searchBtn">搜索</button>
                        <button class="layui-btn layui-btn-sm" id="recoverBtn" onclick="window.location.reload(true)">重置</button>
                    </div>
                </shiro:hasPermission>
            </div>
        </div>
        <div class="user-list">
            <table id="users" lay-filter="usersTableFilter"></table>
        </div>
        <div>
            <button class="layui-btn">回收站</button>
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
        var table = layui.table;
        form = layui.form;
        userTable.render({
            elem:'#users'    //html容器ID
            ,height:472
            ,width:893
            ,url: '${basePath}/user/list.do' //数据接口
            ,page: true //开启分页
            ,limit:10   //每页默认显示10条
            ,limits:[10,20,30]      //分页选项
            ,text: {
                none: '暂无相关数据'
            }
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
                                url:'${basePath}/user/logicalDel.do?userId='+obj.data.userId,
                                error:function () {
                                    layer.alert('请求失败，请重试！');
                                },
                                success:function (data) {
                                    if (data.msg == 'success') {
                                        // obj.del(index);      //删除DOM
                                        // layer.close(index);
                                        table.reload('users',{
                                            url: '${basePath}/user/list.do',
                                            page: true, //开启分页
                                            limit:10   //每页默认显示10条
                                        });
                                        layer.msg("删除成功");
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
                $(location).attr('href', '${basePath}/user/toAddOrEdit.do?userId='+obj.data.userId);
            }else if (obj.event == 'detail') {
                $(location).attr('href', '${basePath}/user/toDetail.do?userId='+obj.data.userId);
            }
        });
        //监听表格开关
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
    $("#addUserBtn").click(function () {
        $(location).attr('href', '${basePath}/user/toAddOrEdit.do');
    });
    //表格搜索
    layui.use(['laydate','table'],function () {
        var laydate = layui.laydate;
        var table = layui.table;
        laydate.render({
            elem:'#createTime',
            theme:'grid'
        });
        $("#searchBtn").click(function () {
            //模糊查询条件
            var likeAccount = $("input[name='likeAccount']").val();
            var likeNickName = $("input[name='likeNickName']").val();
            var likeCreateTime = $("input[name='likeCreateTime']").val();
            table.reload("users",{
                where:{
                    "likeAccount":likeAccount,
                    "likeNickName":likeNickName,
                    "likeCreateTime":likeCreateTime
                }
            });
        });

    });

    
</script>
<script type="text/html" id="operations">
    <shiro:hasPermission name="sysUser:detail">
        <a class="layui-btn layui-btn-xs" lay-event="detail">查看</a>
    </shiro:hasPermission>
    <shiro:hasPermission name="sysUser:edit">
        <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
    </shiro:hasPermission>
    <shiro:hasPermission name="sysUser:logicalDel">
        <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
    </shiro:hasPermission>
</script>
<script type="text/html" id="loginFlagTpl">
    <input type="checkbox" name="loginFlag" value="{{d.userId}}" lay-skin="switch" lay-text="允许|拒绝" lay-filter="loginFlagFilter" {{ d.loginFlag == '1' ? 'checked' : '' }}>
</script>
</body>
</html>
