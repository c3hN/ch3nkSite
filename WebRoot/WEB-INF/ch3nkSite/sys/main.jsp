<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<html>
    <head>
        <title>main</title>
        <link rel="stylesheet" href="${basePath}/static/plugins/bootstrap/css/bootstrap.min.css">
        <link rel="stylesheet" href="${basePath}/static/plugins/font-awesome-4.7.0/css/font-awesome.min.css">
        <link rel="stylesheet" href="${basePath}/static/css/main.css">
    </head>
    <body>
        <div class="left-wrap">
            <div class="left-top">
            </div>
            <div class="menus-wrap">
                <ul class="nav-menu">
                    <c:forEach var="menu" items="${menus}">
                        <c:if test="${menu.parentId == null}">
                            <%--<shiro:hasPermission name="${menu.permission}">--%>
                                <li>
                                    <a href="javascript:;" _href="${menu.href}">
                                        <%--<i class="${menu.menuIcon}"></i>--%>
                                        <span>${menu.name}</span>
                                    </a>
                                    <c:forEach var="child" items="${menus}">
                                        <c:if test="${child.parentId == menu.menuId}">
                                            <ul class="nav-sub-menu">
                                                <%--<shiro:hasPermission name="${child.permission}">--%>
                                                    <li>
                                                        <a href="javascript:;" _href="${child.href}">
                                                            <%--<i class="${child.menuIcon}"></i>--%>
                                                            <span>${child.name}</span>
                                                        </a>
                                                    </li>
                                                <%--</shiro:hasPermission>--%>
                                            </ul>
                                        </c:if>
                                    </c:forEach>
                                </li>
                            <%--</shiro:hasPermission>--%>
                        </c:if>
                    </c:forEach>
                </ul>
            </div>
        </div>
        <div class="main">
            <div class="main-top">
                <div class="left-open"></div>
                <div class="main-top-right">
                    <ul>
                        <li class="right-item_1">
                            <a class="principal-info">
                                <img src="${basePath}/static/img/user2-160x160.jpg" alt="user">
                                <span><shiro:principal property="nickName"/></span>
                            </a>
                            <ul class="principal-operate" style="display: none;">
                                <li>
                                    <a href="javascript:;">
                                        <i class="fa fa-user"></i>
                                        <span>个人中心</span>
                                    </a>
                                </li>
                                <li>
                                    <a href="${basePath}/logout">
                                        <i class="fa fa-sign-out"></i>
                                        <span>退出</span>
                                    </a>
                                </li>
                            </ul>
                        </li>
                        <li class="right-item_2">
                            <a class="notice">消息
                                <span class="num">11</span>
                            </a>
                        </li>
                    </ul>
                </div>
            </div>
                <div class="main-content">
                    <iframe src='' frameborder="0" scrolling="yes" class="x-iframe" style="overflow-y: hidden"></iframe>
                </div>
        </div>
    </body>
<script src="${basePath}/static/js/jquery-3.2.1.js"></script>
<script src="${basePath}/static/plugins/bootstrap/js/bootstrap.min.js"></script>
<script>
    $(".left-wrap .nav-menu li").click(function (event) {
       if($(this).children('.nav-sub-menu').length) {   //判断是否有二级菜单
            if($(this).hasClass('open')) {  //判断二级菜单是否展开
                $(this).removeClass('open');
                $(this).children('.nav-sub-menu').stop().slideUp();
                $(this).children('.nav-sub-menu').slideUp();
            }else {
                $(this).addClass('open');
                $(this).children('.nav-sub-menu').stop().slideDown();
                $(this).siblings().children('.nav-sub-menu').slideUp();
                $(this).siblings().removeClass('open');
            }
       }else {
           var url = "${basePath}" + $(this).children('a').attr('_href');
           $('.main-content').find('iframe').attr('src', url);
           event.stopPropagation();
       }
    });
    $(function () {
       var url = "${basePath}"+$(".left-wrap .nav-menu li ul li a").attr("_href");
       // if(url != undefined) {
       //      $('.main-content').find('iframe').attr('src', url);
       // }
       //  console.log(url)
    });
</script>
</html>
