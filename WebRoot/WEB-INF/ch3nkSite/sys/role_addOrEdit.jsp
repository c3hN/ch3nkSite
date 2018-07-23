<%--
  Created by IntelliJ IDEA.
  User: chenkai
  Date: 2018/7/17
  Time: 21:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link rel="stylesheet" href="${basePath}/static/plugins/layui/css/layui.css">
<div style="width: 400px; height: 500px;" >
    <form class="layui-form" action="${basePath}/user/addUser.do" lay-filter = "add-user-form">
        <div class="layui-form-item">
            <label class="layui-form-label">账号</label>
            <div class="layui-input-block">
                <input type="text" name="account"  lay-verify="required" placeholder="请输入账号" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">选择框</label>
            <div class="layui-input-block">
                <select name="city" lay-verify="required">
                    <option value=""></option>
                    <option value="0">北京</option>
                    <option value="1">上海</option>
                    <option value="2">广州</option>
                    <option value="3">深圳</option>
                    <option value="4">杭州</option>
                </select>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">角色</label>
            <div class="layui-input-block">
                <input type="checkbox" name="like[write]" title="部门经理">
                <input type="checkbox" name="like[read]" title="开发主管" checked>
                <input type="checkbox" name="like[dai]" title="测试">
            </div>
        </div>
    </form>
</div>


<script src="${basePath}/static/js/jquery-3.2.1.js"></script>
<script src="${basePath}/static/plugins/layui/layui.js"></script>
<script>
    layui.use(['element','form'],function () {
        var element = layui.element;
        var form = layui.form;

    });
    function subForm() {
        $("form").submit();
    }
</script>