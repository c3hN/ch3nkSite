<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>后台首页</title>
    <link rel="stylesheet" href="${basePath}/static/plugins/layui/css/layui.css">
    <link rel="stylesheet" href="${basePath}/static/plugins/font-awesome-4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="${basePath}/static/css/index.css">
    <script src="${basePath}/static/js/jquery-3.2.1.js"></script>
    <script src="${basePath}/static/plugins/layui/layui.js"></script>
</head>
<body>
<!-- 顶部开始 -->
<div class="container">
    <div class="logo"><a href="./index.html">ch3nksite</a></div>
    <div class="left_open">
        <i title="展开左侧栏" class="fa fa-bars fa-2x fa-inverse"></i>
    </div>
    <ul class="layui-nav right" lay-filter="">
        <li class="layui-nav-item">
            <a href="javascript:;"><i class="fa fa-user-circle-o fa-2x"></i>
                <shiro:principal property="nickName"/>
            </a>
            <dl class="layui-nav-child">
                <dd><a href="${basePath}/logout">退出</a></dd>
            </dl>
        </li>
        <li class="layui-nav-item to-index"><a href="/">前台首页</a></li>
    </ul>
</div>
    <!-- 左侧菜单开始 -->
<div class="left-nav">
    <div id="side-nav">
        <ul id="nav">
            <li>
                <a href="javascript:;"><i class="fa fa-cog"></i>
                    <cite>系统设置</cite>
                </a>
                <ul class="sub-menu">
                    <li>
                        <a _href="${basePath}/user/tolist.do"><i class="fa fa-user"></i>
                            <cite>用户管理</cite>
                        </a>
                    </li >
                    <li>
                        <a _href="${basePath}/menu/tolist.do"><i class="fa fa-bars"></i>
                            <cite>菜单管理</cite>
                        </a>
                    </li >
                </ul>
            </li>
        </ul>
    </div>
</div>
<!-- 左侧菜单结束 -->
<!-- 右侧主体开始 -->
<div class="page-content">
    <div class="layui-tab tab" lay-filter="xbs_tab" lay-allowclose="false">
        <ul class="layui-tab-title">
            <li class="home"><i class="fa fa-home"></i>首页</li>
        </ul>
        <div class="layui-tab-content">
            <div class="layui-tab-item layui-show">
                <iframe src='${basePath}/user/tolist.do' frameborder="0" scrolling="yes" class="x-iframe"></iframe>
            </div>
        </div>
    </div>
</div>
<!-- 右侧主体结束 -->
<script>
    $(function () {
       layui.use(['element','layer'],function () {
           element = layui.element;
           layer = layui.layer;
       }) ;
    });

    //触发事件
    var tab = {
        tabAdd: function(title,url,id){
            //新增一个Tab项
            element.tabAdd('xbs_tab', {
                title: title
                ,content: '<iframe tab-id="'+id+'" frameborder="0" src="'+url+'" scrolling="yes" class="x-iframe"></iframe>'
                ,id: id
            })
        }
        ,tabDelete: function(othis){
            //删除指定Tab项
            element.tabDelete('xbs_tab', '44'); //删除：“商品管理”


            othis.addClass('layui-btn-disabled');
        }
        ,tabChange: function(id){
            //切换到指定Tab项
            element.tabChange('xbs_tab', id); //切换到：用户管理
        }
    };
    //左侧菜单效果
    $('.left-nav #nav li').click(function (event) {
        if($(this).children('.sub-menu').length){
            if($(this).hasClass('open')){
                $(this).removeClass('open');
                $(this).find('.nav_right').html('&#xe697;');
                $(this).children('.sub-menu').stop().slideUp();
                $(this).siblings().children('.sub-menu').slideUp();
            }else{
                $(this).addClass('open');
                $(this).children('a').find('.nav_right').html('&#xe6a6;');
                $(this).children('.sub-menu').stop().slideDown();
                $(this).siblings().children('.sub-menu').stop().slideUp();
                $(this).siblings().find('.nav_right').html('&#xe697;');
                $(this).siblings().removeClass('open');
            }
        }else{

            var url = $(this).children('a').attr('_href');
            var title = $(this).find('cite').html();
            var index  = $('.left-nav #nav li').index($(this));

            for (var i = 0; i <$('.x-iframe').length; i++) {
                if($('.x-iframe').eq(i).attr('tab-id')==index+1){
                    tab.tabChange(index+1);
                    event.stopPropagation();
                    return;
                }
            };
            tab.tabAdd(title,url,index+1);
            tab.tabChange(index+1);
        }
        event.stopPropagation();
    });
//   展开/收起侧边栏
    $('.container .left_open i').click(function(event) {
        if($('.left-nav').css('left')=='0px'){
            $('.left-nav').animate({left: '-221px'}, 100);
            $('.page-content').animate({left: '0px'}, 100);
            $('.page-content-bg').hide();
        }else{
            $('.left-nav').animate({left: '0px'}, 100);
            $('.page-content').animate({left: '221px'}, 100);
            if($(window).width()<768){
                $('.page-content-bg').show();
            }
        }
    });
</script>
</body>
</html>