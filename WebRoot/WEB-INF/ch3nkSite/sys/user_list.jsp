<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>用户列表</title>
</head>
<body>

<div class="contents" style="margin: 10px 0 0 10px">
    <div class="positaion" style="background-color: #5e5e5e;margin-bottom: 30px" >
        <span class="layui-breadcrumb" lay-separator="-">
          <a href="">首页</a>
          <a href="">国际新闻</a>
          <a href="">亚太地区</a>
          <a><cite>正文</cite></a>
        </span>
    </div>
    <div class="list-operates">
        <div class="layui-btn-group">
            <button class="layui-btn layui-btn-sm" id="addUserBtn">新增</button>
            <button class="layui-btn layui-btn-sm" id="importUsersBtn">导入</button>
            <button class="layui-btn layui-btn-sm">统计报表</button>
        </div>
    </div>
    <div class="list">
        <table id="users" lay-filter="usersTableFilter"></table>
    </div>
</div>


<script>
    layui.use(['element','upload','layer','table'], function(){
        var element = layui.element;
        var upload = layui.upload;
        var layer = layui.layer;
        var table = layui.table;
        //创建一个上传组件
        upload.render({
            elem: '#importUsersBtn'
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
    layui.use(['layer','table','layer','laytpl'],function () {
        var userTable = layui.table;
        var layer = layui.layer;
        var laytpl = layui.laytpl;
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
                ,{field: 'loginFlag', title: '禁止登录', width:200,align:'center',sort:true,templet:'#loginFlag'}
                ,{title:'操作',width:250, align:'center', toolbar: '#operations'}
            ]]
        });
        //工具条监听
        userTable.on('tool(usersTableFilter)',function (obj) {    //表格工具条事件监听，返回obj
            if (obj.event == 'del') {
                layer.confirm('确定删除这条数据吗？',
                    {btn:['删除','取消'],
                        yes:function (index,layero) {      //按钮1回调
                            // 后台交互
                            $.ajax({
                                type:'post',
                                url:'${basePath}/user/delete.do?userId='+obj.data.userId,
                                error:function () {
                                    layer.alert('请求失败，请重试！');
                                },
                                success:function (data) {
                                   if (data == 'success') {
                                       obj.del(index);      //删除DOM
                                       layer.close(index);
                                       layer.msg("删除成功");
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
                    type:2,
                    title:'用户信息编辑',
                    closeBtn:1, //右上角关闭按钮显示
                    area:['700px','600px'],
                    resize :false,
                    move:false,
                    offset:['auto'],
                    content:['${basePath}/user/toAddOrEdit.do?userId='+obj.data.userId,'no'],
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
    });

    //新增弹窗
    layui.use(['layer','table'],function () {
        var layer = layui.layer;
        var table = layui.table;
        $("#addUserBtn").click(function () {
            layer.open({
                id:'userAdd',
                type:2,     //iframe
                title:'新增用户',
                closeBtn:'1', //右上角关闭按钮显示
                move:false, //禁止拖拽
                area:['700px','600px'],
                resize :false,
                offset:['auto'],
                move:false,
                content:['${basePath}/user/toAddOrEdit.do','no'],
                btn:['立即提交','取消'],
                yes:function (index,layero) {       //保存按钮回调
                    $("iframe")[0].contentWindow.subForm();//调用子页面提交按钮
                    layer.close(index);
                    layer.msg("添加成功")
                    table.reload('users',function () {
                        url: "${basePath},/user/list.do"
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
<script type="text/html" id="loginFlag">
    {{# if(d.loginFlag == "1") {            }}
    {{# return "否";                         }}
    {{# }else {                              }}
    {{# return "是";                          }}
    {{# }                                    }}
</script>
</body>
</html>
