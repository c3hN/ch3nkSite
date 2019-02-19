<%--
  Created by IntelliJ IDEA.
  User: chenkai
  Date: 2019/1/4
  Time: 15:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<html>
<head>
    <title>jqDemo</title>
    <%--<link rel="stylesheet" href="../../../static/plugins/jqGrid-4.7.0/css/ui.jqgrid.css">--%>
    <link rel="stylesheet" href="<c:url value="/static/plugins/jqGrid-4.7.0/css/ui.jqgrid.css"/>">
</head>
<body>
<a href="<c:url value="/menu/index.do"/>">123</a>
<form id="demo-form">
    姓名<input type="text" name="account">
    年龄<input type="text" name="nickName">
    性别<input type="text" name="userPhoto">
</form>
<button id="btn">按钮</button>
<script src="<c:url value="/static/js/jquery-3.2.1.js"/>"></script>
<script>
    $("#btn").click(function () {
        $(location).attr('href','${basePath}/demo/jqgrid.do?'+$("#demo-form").serialize());
    });
</script>
</body>
</html>
