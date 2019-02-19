<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>后台管理系统</title>
    <link rel="stylesheet" href="${basePath}/static/plugins/bootstrap/css/bootstrap.css">
    <link rel="stylesheet" href="${basePath}/static/plugins/nice-validator/jquery.validator.css">
    <script src="${basePath}/static/js/jquery-3.2.1.js"></script>
    <script src="${basePath}/static/plugins/bootstrap/js/bootstrap.js"></script>
    <script src="${basePath}/static/plugins/nice-validator/jquery.validator.min.js"></script>
    <script src="${basePath}/static/plugins/nice-validator/local/zh-CN.js"></script>
</head>
<body>
<div class="header">
</div>
<div class="content">
    <div class="form-content">
        <div class="container">
            <div class="row">
                <p>${errorMsg}</p>
                <form action="${basePath}/login" class="form-horizontal" role="form" method="post">
                    <div class="form-group">
                        <div class="col-md-4 col-md-offset-3">
                            <input type="tel" name="account" placeholder="手机号" class="form-control" data-rule="required;[/^((1[3,5,8][0-9])|(14[5,7])|(17[0,6,7,8])|(19[7]))\d{8}$/,'请输入正确格式']">
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-md-4 col-md-offset-3">
                            <input type="password" name="password" placeholder="密码" class="form-control" data-rule="required">
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-md-4 col-md-offset-3">
                            <input type="submit" value="登&nbsp;&nbsp;录" placeholder="手机号" class="form-control btn-primary">
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
</div>
<script>
    $(function () {
        $("form input:eq(0)").focus();
        $("input[name='account']").val("18913339867");
        $("input[name='password']").val("123");
    })
</script>
</body>
</html>