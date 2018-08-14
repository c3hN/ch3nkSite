<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/7/19
  Time: 13:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>用户详情</title>
    <link rel="stylesheet" href="${basePath}/static/plugins/layui/css/layui.css">
</head>
<body>
<button class="layui-btn" onclick="javascript:history.back(-1);">返回</button>
    <div class="detail-content" style="margin: 5px;">
        <table class="layui-table">
            <colgroup>
                <col width="150">
                <col>
            </colgroup>
            <tbody>
            <tr>
                <td>账号</td>
                <td>${sysUser.account}</td>
            </tr>
            <tr>
                <td>昵称</td>
                <td>${sysUser.nickName}</td>
            </tr>
            <tr>
                <td>创建时间</td>
                <td>${sysUser.createTime}</td>
            </tr>
            <tr>
                <td>更新时间</td>
                <td>${sysUser.updateTime}</td>
            </tr>
            </tbody>
        </table>
    </div>
<script src="${basePath}/static/js/jquery-3.2.1.js"></script>
<script src="${basePath}/static/plugins/layui/layui.js"></script>
<script>
    layui.use(['element','form','table'], function(){
        var element = layui.element;
        var form = layui.form;
        var table = layui.table;
    });
    
    function formatDate(date) {
        var year = date.getFullYear();
        var month = date.getMonth()+1;
        var d = date.getDate();
        if (month < 10) {
            month = "0"+month;
        }
        if (d < 10) {
            d = "0" + d;
        }
        return year +"-"+month+"-"+d;
    }
    
</script>
</body>
</html>
