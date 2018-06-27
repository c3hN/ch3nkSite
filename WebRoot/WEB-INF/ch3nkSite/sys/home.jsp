<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>管理后台首页</title>
    <link rel="stylesheet" href="${basePath}/static/css/home.css">
    <link rel="stylesheet" href="${basePath}/static/plugins/font-awesome-4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="${basePath}/static/plugins/waves-0.7.6/waves.min.css">
</head>
<body>
<div class="header">
    <div class="header-left">
			<span class="logo">
				<a href="#"><i class="fa fa-angellist"></i></a>
			</span>
        <span class="logo-name">c3blog管理后台</span>
    </div>
    <div class="header-right">
        <span class="user-info"><a href="#"><i class="fa fa-user"></i>用户</a></span>
        <span class="user-remind"><a href=""><i class="fa fa-bell"></i></a></span>
    </div>
</div>
<div class="content">
    <div class="menus-content">
        <ul>
            <li><a class="waves-effect" href="#"><i class="fa fa-home"></i>首页</a></li>
            <li><a class="waves-effect" href="#"><i class="fa fa-bars"></i>资源管理</a></li>
            <li><a class="waves-effect" href="#"><i class="fa fa-user-o"></i>角色管理</a></li>
            <li><a class="waves-effect" href="#"><i class="fa fa-users"></i>用户管理</a></li>
            <li><a class="waves-effect" href="#"><i class="fa fa-user-plus"></i>角色分配</a></li>
        </ul>
    </div>
</div>
<!-- <div class="footer">
    <div class="footer-content">&copy;c3blog</div>
</div> -->
<script src="${basePath}/static/plugins/waves-0.7.6/waves.min.js"></script>
<script src="${basePath}/static/js/jquery-3.2.1.js"></script>
<script src="${basePath}/static/js/home.js"></script>
<script>


</script>
</body>
</html>