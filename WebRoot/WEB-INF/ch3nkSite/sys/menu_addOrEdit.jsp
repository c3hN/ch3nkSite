<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/7/30
  Time: 9:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link rel="stylesheet" href="${basePath}/static/plugins/layui/css/layui.css">
<div class="content" style="width: 450px;height: 200px; margin: 50px auto">

    <form action="${basePath}/menu/saveMenu.do" method="post" class="layui-form layui-form-pane" id="menuForm" lay-filter="menuForm">
        <div class="layui-form-item">
            <lable class="layui-form-label">菜单名称</lable>
            <div class="layui-input-block">
                <input type="text" name="name" required  lay-verify="required" placeholder="请输入菜单名称" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <lable class="layui-form-label">菜单编号</lable>
            <div class="layui-input-block">
                <input type="text" name="menuCode" required  lay-verify="required" placeholder="请输入菜单编号" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <lable class="layui-form-label">菜单类别</lable>
            <div class="layui-input-block">
                <input type="text" name="category" required  lay-verify="required" placeholder="请输入菜单类别" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <lable class="layui-form-label">资源路径</lable>
            <div class="layui-input-block">
                <input type="text"  name="href" required  lay-verify="required" placeholder="请输入资源路径" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <lable class="layui-form-label">图标</lable>
            <div class="layui-input-block">
                <input type="text" name="icon" required  lay-verify="required" placeholder="请输入图标" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <lable class="layui-form-label">权限代码</lable>
            <div class="layui-input-block">
                <input type="text" name="permission" required  lay-verify="required" placeholder="请输入权限代码" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <textarea name="remark" required lay-verify="required" placeholder="请输入备注" class="layui-textarea"></textarea>
        </div>
    </form>
</div>
<script src="${basePath}/static/plugins/layui/layui.js"></script>
<script src="${basePath}/static/js/jquery-3.2.1.js"></script>
<script>
    layui.use(['form'],function () {
        var form = layui.form;
    });



    function subForm() {
        $("#menuForm").submit();
    }
</script>