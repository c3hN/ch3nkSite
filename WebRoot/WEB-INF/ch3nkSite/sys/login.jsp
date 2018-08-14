<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>后台管理系统</title>
    <link rel="stylesheet" href="${basePath}/static/plugins/bootstrap/css/bootstrap.css">
    <link rel="stylesheet" href="${basePath}/static/css/login.css">
    <script src="${basePath}/static/js/jquery-3.2.1.js"></script>
    <script src="${basePath}/static/plugins/bootstrap/js/bootstrap.js"></script>
</head>
<body>
<div class="header">

</div>
<div class="content">
    <div class="form-content">
        <%--<span class="form-title">用户登录</span>--%>
        <p>${errorMsg}</p>
        <form action="${basePath}/login" method="post">
            <div class="formIn">
                <input class="form-control" type="tel" name="userCount" placeholder="手机号" />
            </div>
            <div class="formIn">
                <input class="form-control" type="password" name="userPwd" placeholder="密码" />
            </div>
            <div class="formIn">
                <button class="btn btn-primary btn-block">登&nbsp;&nbsp;录</button>
            </div>
            <div class="formIn">
                <span><a href="javascript:void(0)">忘记密码?</a></span>
            </div>
        </form>
    </div>
    <div class="alert alert-danger" hidden="hidden">
        <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
        账号或密码错误！请重试！
    </div>
</div>
</div>
<script>
    $(function () {
        $("form input:eq(0)").focus();

    })
</script>
</body>
</html>