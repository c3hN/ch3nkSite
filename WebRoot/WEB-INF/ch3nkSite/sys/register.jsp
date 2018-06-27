<%--
  Created by IntelliJ IDEA.
  User: chenkai
  Date: 2018/5/29
  Time: 22:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
	<title>c3blog 登录</title>
	<link rel="stylesheet" href="${basePath}/static/plugins/bootstrap/css/bootstrap.css">
	<script src="${basePath}/static/js/jquery-3.2.1.js"></script>
	<script src="${basePath}/static/plugins/bootstrap/js/bootstrap.js"></script>
</head>
<style>
	*{
		margin: 0;
		padding: 0;
	}
	body{
		font-size: 12px;
	}
	body,html{
		height: 100%;
	}
	.header{
		width: 100%;
		height: 60px;
		background-color: #5C3838;
	}
	.content{
		background-image: url("${basePath}/static/img/wallhaven-93402.jpg");
		width: 100%;
		height: calc(100% - 100px);
	}
	.footer{
		width: 100%;
		height: 40px;
		font-size: 5px;
		background-color: #eff4fa;
		color: #b6b6b6;
		position: absolute;
	}
	.form-content{
		width: 350px;
		height: 325px;
		background-color: #775555;
		opacity: 0.7;
		border-radius: 7px;
		/*div居中方式1 */
		margin: auto;
		position: absolute;
		top: 0;
		left: 0;
		right: 0;
		bottom: 0;
		/*div居中方式2*/
		/* position: absolute;
        top: 50%;
        left: 50%;
        transform: translate(-50%,-50%); */
	}
	.form-title{
		display: inline-block;
		width: 100%;
		height: 35px;
		text-align: center;
		line-height: 35px;
		font-weight: bold;
		font-size: 25px;
		color: #000;
		padding: 5px 0;
		margin: 0 0 8px 0;
	}
	.formIn{
		padding: 0 25px;
		margin: 10px 0 30px 0;
	}
</style>
</head>
<body>
<div class="header">

</div>
<div class="content">
	<div class="form-content">
		<span class="form-title">用户注册</span>
		<form action="${basePath}/user/register.do" method="post">
			<div class="formIn">
				<input class="form-control" type="tel" name="userCount" placeholder="手机号" />
			</div>
			<div class="formIn">
				<input class="form-control" type="password" name="userPwd" placeholder="密码" />
			</div>
			<div class="formIn">
				<input class="form-control" type="password" name="" placeholder="确认密码" />
			</div>
			<div class="formIn">
				<button class="btn btn-primary btn-block">注&nbsp;&nbsp;册</button>
			</div>
		</form>

	</div>
</div>
</div>
<div class="footer">
</div>
<script>
    $(function () {
        $("form input:eq(0)").focus();
    })
</script>
</body>
</html>