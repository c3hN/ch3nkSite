<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/8/8
  Time: 14:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<html>
<head>
    <title>新增编辑页面</title>
    <link rel="stylesheet" href="${basePath}/static/plugins/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="${basePath}/static/plugins/font-awesome-4.7.0/css/font-awesome.min.css">
    <style>
        .input-set{
            width: 200px;
            display: inline-block;
            padding-right: 34px;
            padding: 0 35px 0 10px;
        }
        .form-group label{
            width: 100px;
            text-align: center;
        }
        .form-group button{
            width: 34px;
            height: 34px;
            position: relative;
            top: -1px;
            left: -38px;
            padding: 0px;
        }
        .title{
            border-bottom: 1px solid #dbdbdb;
            width: 100%;
            height: 30px;
            line-height: 30px;
            margin: 10px 20px 30px 20px;
            padding-left: 10px;
        }
    </style>
</head>
<body>
<div class="position" style="width: 100%; height: 50px; background-color: #FFFFFF; margin-bottom: 40px;line-height: 50px;padding: 0 0 0 20px;">
    <div class="postion-content"><i class="fa fa-user"></i>&nbsp;用户管理</div>
    <div class="operations" style="display: inline-block">
        <button class="btn btn-default" id="goBackBtn" onclick="javascript:history.back(-1);"><i class="fa fa-reply" aria-hidden="true"></i></button>
        <button class="btn btn-default"id="refreshBtn" onclick="window.location.reload(true)"><i id="refreshIcon" class="fa fa-refresh"></i> </button>
    </div>
</div>
<div class="content">
    <div class="title">
        基本信息
    </div>
    <div class="container">
        <div class="form-content">
            <form action="${basePath}/user/save.do" method="post">
                <div class="row">
                    <div class="col-md-6">
                        <div class="form-group">
                            <label>登录账号</label>
                            <input class="form-control input-set" type="text" name="account">
                        </div>
                        <div class="form-group">
                            <label>用户昵称</label>
                            <input class="form-control input-set" type="text" name="nickName">
                        </div>
                        <div>
                            <button class="btn btn-default">保存</button>
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="form-group">
                            <label>密码</label>
                            <input class="form-control input-set" type="password" name="userPwd">
                        </div>
                        <div class="form-group">
                            <label>角色</label>
                            <input class="form-control input-set" readonly="readonly">
                            <button class="btn btn-default"><i class="fa fa-search"></i></button>
                        </div>
                        <div>
                            <button class="btn btn-default">重置</button>
                        </div>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>


<script src="${basePath}/static/js/jquery-3.2.1.js"></script>
<script src="${basePath}/static/plugins/bootstrap/js/bootstrap.min.js"></script>
<script>
    $("#refreshBtn").mouseover(function () {
        $("#refreshIcon").addClass("fa-spin");
    });
    $("#refreshBtn").mouseleave(function () {
        $("#refreshIcon").removeClass("fa-spin");
    });
</script>
</body>
</html>
