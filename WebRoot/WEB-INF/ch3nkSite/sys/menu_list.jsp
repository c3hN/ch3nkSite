<%--
  Created by IntelliJ IDEA.
  User: chenkai
  Date: 2018/7/26
  Time: 21:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>菜单列表</title>
</head>
<body>
<div class="contents" style="margin: 10px 0 0 10px">
    <div class="current-positaion" style="background-color: #5e5e5e;margin-bottom: 30px" >
        <span class="layui-breadcrumb">
          <a href="">首页</a>
          <a><cite>菜单列表</cite></a>
        </span>
    </div>
    <div class="layui-tab layui-tab-card" lay-filter="menusTab">
        <ul class="layui-tab-title">
            <li class="layui-this">后台菜单</li>
            <li>前台菜单</li>
        </ul>
        <div class="layui-tab-content">
            <div class="layui-tab-item layui-show">
                <div class="list-operates">
                    <div class="layui-btn-group">
                        <button class="layui-btn layui-btn-sm" id="addBackstageMenuBtn">新增</button>
                        <button class="layui-btn layui-btn-sm" id="deleteBackstageMenuBtn">批量删除</button>
                        <button class="layui-btn layui-btn-sm" id="editBackstageMenuBtn">编辑</button>
                    </div>
                </div>
                <div class="list">
                    <table id="backstageMenus" lay-filter="backstageMenusFilter"></table>
                </div>
            </div>
            <div class="layui-tab-item">
                <div class="list-operates">
                    <div class="layui-btn-group">
                        <button class="layui-btn layui-btn-sm" id="addReceptionMenuBtn">新增</button>
                        <button class="layui-btn layui-btn-sm" id="deleteReceptionMenuBtn">批量删除</button>
                        <button class="layui-btn layui-btn-sm" id="editReceptionMenuBtn">编辑</button>
                    </div>
                </div>
                <div class="list">
                    <table id="receptionMenus" lay-filter="receptionMenusFilter"></table>
                </div>
            </div>
        </div>
    </div>

</div>

<script>
    layui.use(['element','table'],function () {
        var element = layui.element;
        var table = layui.table;
        element.on('tab(menusTab)',function (data) {
            if (data.index = 1) {
                renderMenusTable(table,"receptionMenus","1");
            }
        })
    })

    function renderMenusTable(table,id,category) {
        table.render({
            elem:'#'+id
            ,height:470
            ,url: '${basePath}/menu/list.do?category='+category
            ,page: true
            ,limit:10
            ,limits:[10,20]
            ,text: {
                none: '暂无相关数据'
            }
            ,even:true
            ,cols: [[
                {type:'checkbox'}
                ,{type:'numbers',title:'序号'}
                ,{field: 'name', title: '名称', width:120,align:'center', sort: true}
                ,{field: 'menuCode', title: '代码', width:120,align:'center'}
                ,{field: 'href', title: '资源路径', width:200, align:'center',sort: true}
                ,{field: 'icon', title: '图标', width:200,align:'center',sort:true}
                ,{field: 'createBy', title: '创建者', width:200,align:'center',sort:true}
                ,{field: 'createDate', title: '创建时间', width:200,align:'center',sort:true}
                ,{field: 'updateBy', title: '更新者', width:200,align:'center',sort:true}
                ,{field: 'updateDate', title: '更新时间', width:200,align:'center',sort:true}
                ,{field: 'deleteFlag', title: '是否启用', width:200,align:'center',sort:true}
                ,{field: 'permission', title: '权限代码', width:200,align:'center',sort:true}
                ,{field: 'remark', title: '备注', width:200}
                ,{title:'操作',width:250, align:'center',fixed:'right', toolbar: '#operations'}
            ]]
        });
    }
    
    $(function () {
        layui.use(['element','table'],function () {
            var table = layui.table;
            renderMenusTable(table,"backstageMenus","0");
        });
    })

</script>
<script type="text/html" id="operations">
    <a class="layui-btn layui-btn-xs" lay-event="detail">查看</a>
    <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
    <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
</script>
<script type="text/html" id="loginFlag">
    {{# if(d.deleteFlag == "1") {       }}
    {{#     return "启用";               }}
    {{# }else{                          }}
    {{#      return "禁用";              }}
    {{# }                               }}
</script>

</body>
</html>
