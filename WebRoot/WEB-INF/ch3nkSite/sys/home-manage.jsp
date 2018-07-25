<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<html>
<head>
    <title>ch3nksite 管理后台</title>
    <link rel="stylesheet" href="${basePath}/static/plugins/layui/css/layui.css">
</head>
<body class="layui-layout-body">
<div class="layui-layout layui-layout-admin">
    <div class="layui-header">
        <div class="layui-logo">ch3nksite 管理后台</div>
        <!-- 头部区域（可配合layui已有的水平导航） -->
        <ul class="layui-nav layui-layout-left">
            <li class="layui-nav-item layui-hide-xs" lay-unselect>
                <a href="#" target="_blank" title="前台">
                    <i class="layui-icon layui-icon-website"></i>
                </a>
            </li>
            <li class="layui-nav-item layui-hide-xs" lay-unselect>
                <input type="text" placeholder="搜索..." autocomplete="off" class="layui-input layui-input-search" layadmin-event="serach" lay-action="#">
            </li>

        </ul>
        <ul class="layui-nav layui-layout-right">
            <li class="layui-nav-item">
                <a href="javascript:;">
                    <i class="layui-icon">&#xe66f;</i>
                    <shiro:principal property="nickName"/>
                </a>
                <dl class="layui-nav-child">
                    <dd><a href="">基本资料</a></dd>
                    <dd><a href="">安全设置</a></dd>
                    <dd><a href="${basePath}/logout">登出</a></dd>
                </dl>
            </li>
        </ul>
    </div>

    <div class="layui-side layui-bg-black">
        <div class="layui-side-scroll">
            <!-- 左侧导航区域（可配合layui已有的垂直导航） -->
            <ul class="layui-nav layui-nav-tree"  lay-filter="test">
                <li class="layui-nav-item">
                    <a href="">
                        <i class="layui-icon layui-icon-home"></i>
                        首页
                    </a>
                </li>
                <li class="layui-nav-item">
                    <a class="" href="javascript:;"><i class="layui-icon layui-icon-set"></i>
                        设置</a>
                    <dl class="layui-nav-child">
                        <dd><a href="javascript:;">菜单管理</a></dd>
                        <dd><a href="javascript:;" url="${basePath}/user/tolist.do">用户管理</a></dd>
                        <dd><a href="javascript:;">角色管理</a></dd>
                        <dd><a href="javascript:;" url="${basePath}/depart/tolist.do">机构管理</a></dd>
                        <dd><a href="javascript:;">资源分配</a></dd>
                        <dd><a href="javascript:;"url="${basePath}/dic">数据字典</a></dd>
                        <dd><a href="javascript:;">系统参数</a></dd>
                    </dl>
                </li>
            </ul>
        </div>
    </div>

    <div class="layui-body" style="bottom: 0">
        <!-- 内容主体区域 -->
    </div>

</div>
<script src="${basePath}/static/plugins/layui/layui.js"></script>
<script src="${basePath}/static/js/jquery-3.2.1.js"></script>
<script>
    //JavaScript代码区域
    layui.use('element', function(){
        var element = layui.element;

    });

    $(".layui-nav-item a").click(function () {
        var url = $(this).attr("url");
        if (url != null) {
            $(".layui-body").load(url);
        }
    })
</script>
</body>
</html>

