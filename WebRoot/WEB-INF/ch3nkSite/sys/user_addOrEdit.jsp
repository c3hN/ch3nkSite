<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/7/18
  Time: 9:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link rel="stylesheet" href="${basePath}/static/plugins/layui/css/layui.css">
<div class="content" style="width: 450px;height: 200px; margin: 50px 100px 150px 50px;">

    <form action="${basePath}/user/register.do" method="post" class="layui-form layui-form-pane" id="userForm" lay-filter="userForm">
        <div class="layui-form-item">
            <lable class="layui-form-label">账号</lable>
            <div class="layui-input-block">
                <input type="text" name="account" required  lay-verify="required|phone" placeholder="请输入手机号" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <lable class="layui-form-label">用户名</lable>
            <div class="layui-input-block">
                <input type="text" name="nickName" required  lay-verify="required" placeholder="请输入用户名" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <lable class="layui-form-label">密码</lable>
            <div class="layui-input-block">
                <input type="password" name="userPwd" required  lay-verify="required" placeholder="请输入密码" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <lable class="layui-form-label">确认密码</lable>
            <div class="layui-input-block">
                <input type="password"  required  lay-verify="required" placeholder="请输入密码" autocomplete="off" class="layui-input">
            </div>
        </div>
    </form>
</div>
<script src="${basePath}/static/plugins/layui/layui.js"></script>
<script src="${basePath}/static/js/jquery-3.2.1.js"></script>
<script>
    layui.use(['form'],function () {
        var form = layui.form;
    });

    $(function () {
        var inputs = $("#userForm input");
        console.log(inputs);
        console.log("===========");
        jquery.each(inputs)

    })


    function subForm() {
        $("form").submit();
        // alert ("done");
    }
</script>