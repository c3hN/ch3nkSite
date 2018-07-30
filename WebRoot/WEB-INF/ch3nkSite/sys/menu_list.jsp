<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/7/26
  Time: 11:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>菜单列表</title>
</head>
<body>
<div class="" style="margin: 10px;">
    <div class="position" style="background-color: #dbdbdb;margin-bottom: 10px">
        <span class="layui-breadcrumb">
           <a href="">首页</a>
              <a href="">国际新闻</a>
              <a href="">亚太地区</a>
              <a><cite>正文</cite></a>
        </span>
    </div>
    <div class="contents">
        <div class="list-operates">
            <div class="layui-btn-group">
                <button class="layui-btn layui-btn-sm" id="addMenuBtn">新增</button>
                <button class="layui-btn layui-btn-sm" id="deleteMenusBtn" onclick="deleteBatch()">批量删除</button>
            </div>
        </div>
        <div class="menu-list">
            <table id="menus" lay-filter="menusFilter"></table>
        </div>
    </div>
</div>
<script>
    layui.use(['table','layer'],function () {
        var table = layui.table;
        var layer = layui.layer;
        table.render({
            elem:'#menus',
            height:470,
            url: '${basePath}/menu/list.do',
            page: true,
            limits:[10,20],
            text: {
                none: '暂无相关数据'
            }
            ,even:false
            ,cols: [[
                {type:'checkbox'}
                ,{type:'numbers',title:'序号'}
                ,{field: 'name', title: '名称',align:'center', width:115, sort: true}
                ,{field: 'menuCode', title: '菜单编号',align:'center',width:115,sort:true}
                ,{field: 'category', title: '菜单类别',align:'center',width:115,sort:true}
                ,{field: 'href', title: '资源路径',align:'center', width:140,sort:true}
                ,{field: 'icon', title: '图标',align:'center', width:110}
                ,{field: 'permission', title: '权限代码',align:'center', width:110}
                ,{field: 'createBy', title: '创建者',align:'center', width:110}
                ,{field: 'createDate', title: '创建时间',align:'center', width:110}
                ,{field: 'updateBy', title: '更新者',align:'center', width:110}
                ,{field: 'updateDate', title: '更新时间',align:'center', width:110}
                ,{field: 'deleteFlag', title: '是否启用',align:'center', width:110}
                ,{field: 'remark', title: '备注',align:'center', width:110}
                ,{title:'操作',width:250, fixed:'right', align:'center', toolbar: '#operations'}
            ]]
        });
        table.on('tool(menusFilter)',function (obj) {    //表格工具条事件监听，返回obj
            if (obj.event == 'del') {
                layer.confirm('确定删除这条数据吗？',
                    {btn:['删除','取消'],
                        yes:function (index,layero) {      //按钮1回调
                            $.ajax({
                                type:'get',
                                url:'${basePath}/menu/delete.do?userId='+obj.data.userId,
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
                    id:'menuEdit',  //指定id
                    type:2,     //iframe
                    title:'菜单信息编辑',
                    closeBtn:1, //右上角关闭按钮显示
                    area:['700px','600px'],
                    resize :false,
                    move:false,
                    offset:['auto'],
                    content:'${basePath}/menu/toAddOrEdit.do',
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
                    id:'menuDetail',  //指定id
                    type:2,     //iframe
                    title:'查看菜单信息',
                    closeBtn:1, //右上角关闭按钮显示
                    area:['700px','600px'],
                    resize :false,
                    move:false,
                    offset:['auto'],
                    content:'${basePath}/menu/toDetail.do?menuId='+obj.data.menuId,
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

        window.deleteBatch = function(){
            var data = table.checkStatus("menus").data;
            var arr = new Array();
            if (data.length > 0) {
                $.each(data,function (i,e) {
                    arr.push(data[i].menuId);
                });
                layer.confirm('确定删除这'+data.length+'条数据吗？',
                    {btn:['删除','取消'],
                        yes:function (index,layero) {      //按钮1回调
                            $.ajax({
                                type:'post',
                                data:JSON.stringify(arr),
                                url:'${basePath}/menu/deleteBatch.do',
                                contentType : "application/json",
                                dataType : "json",
                                error:function () {
                                    layer.alert('请求失败，请重试！');
                                },
                                success:function (data) {
                                    if (data.msg == 'success') {
                                        // obj.del(index);      //删除DOM
                                        console.log(index);
                                        console.log("11111111111")
                                        console.log(layero);
                                        layer.close(index);
                                        layer.msg("删除成功")
                                        table.reload('menus',function () {
                                            url: '${basePath}/menu/list.do'
                                        });
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
                );
            } else {
                layer.alert("请选择数据");
            }
        };


    });
    layui.use(['layer'],function () {
        var layer = layui.layer;
        $("#addMenuBtn").click(function () {
            layer.open({
                id:'menuAdd',  //指定id
                type:2,     //iframe
                title:'新增菜单',
                closeBtn:1, //右上角关闭按钮显示
                move:false,
                area:['800px','600px'],
                resize :false,
                offset:['auto'],
                content:['${basePath}/menu/toAddOrEdit.do','yes'],
                btn:['立即提交','取消'],
                yes:function (index,layero) {       //提交按钮回调
                    var result = $(layero).find("iframe")[0].contentWindow.subForm();
                    layer.close(index);
                    table.reload('menus',{
                        url: '${basePath}/menu/list.do',
                        page: true,
                        limit:10
                    });
                },
                btn2:function (index,layero) {      //取消按钮回调
                    layer.close(index);
                }
            });
        });
    })

</script>
<script type="text/html" id="operations">
    <button class="layui-btn layui-btn-xs" lay-event="detail">查看</button>
    <button class="layui-btn layui-btn-xs" lay-event="edit">编辑</button>
    <button class="layui-btn layui-btn-xs" lay-event="list" id="list_menu_children">列表</button>
    <button class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</button>
</script>
</body>
</html>
