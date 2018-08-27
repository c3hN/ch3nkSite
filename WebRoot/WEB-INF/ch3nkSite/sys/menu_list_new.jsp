<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>菜单列表</title>
    <link rel="stylesheet" href="${basePath}/static/plugins/ztree/css/metroStyle/metroStyle.css">
    <script src="${basePath}/static/plugins/ztree/js/jquery.ztree.core.js"></script>
    <style>
        .form-item{
            width: 450px;
            height: 75px;
            border: 1px solid black;
            float: left;
            line-height: 50px;
        }
        .form-item label{
            display: block;
            float: left;
            padding: 9px 15px;
            width: 60px;
            line-height: 30px;
            font-weight: 400;
            text-align: center;
        }
        .input-block{
            display: inline-block;
            line-height: 50px;
        }
    </style>
</head>
<body style="background-color: #9F9F9F">
<div class="layui-fluid">
    <div class="layui-row layui-col-space10">
        <div class="layui-col-md2" style="overflow:auto;height: 600px;">
                <ul id="treeDemo" class="ztree"></ul>
        </div>
        <div class="layui-col-md10">
            <%--<form class="layui-form"> <!-- 提示：如果你不想用form，你可以换成div等任何一个普通元素 -->
                <div class="layui-form-item">
                    <label class="layui-form-label">输入框</label>
                    <div class="layui-input-block">
                        <input type="text" name="" placeholder="请输入" autocomplete="off" class="layui-input">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">下拉选择框</label>
                    <div class="layui-input-block">
                        <select name="interest" lay-filter="aihao">
                            <option value="0">写作</option>
                            <option value="1">阅读</option>
                        </select>
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">复选框</label>
                    <div class="layui-input-block">
                        <input type="checkbox" name="like[write]" title="写作">
                        <input type="checkbox" name="like[read]" title="阅读">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">开关关</label>
                    <div class="layui-input-block">
                        <input type="checkbox" lay-skin="switch">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">开关开</label>
                    <div class="layui-input-block">
                        <input type="checkbox" checked lay-skin="switch">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">单选框</label>
                    <div class="layui-input-block">
                        <input type="radio" name="sex" value="0" title="男">
                        <input type="radio" name="sex" value="1" title="女" checked>
                    </div>
                </div>
                <div class="layui-form-item layui-form-text">
                    <label class="layui-form-label">请填写描述</label>
                    <div class="layui-input-block">
                        <textarea placeholder="请输入内容" class="layui-textarea"></textarea>
                    </div>
                </div>
                <div class="layui-form-item">
                    <div class="layui-input-block">
                        <button class="layui-btn" lay-submit lay-filter="*">立即提交</button>
                        <button type="reset" class="layui-btn layui-btn-primary">重置</button>
                    </div>
                </div>
                <!-- 更多表单结构排版请移步文档左侧【页面元素-表单】一项阅览 -->
            </form>--%>
            <form action="">
                <div class="form-item">
                    <label for="">名称</label>
                    <div class="input-block">
                        <input type="text" name="name" class="layui-input">
                    </div>
                </div>
                <div class="form-item">
                    <label for="">名称</label>
                    <div class="input-block">
                        <input type="text" name="name" class="layui-input">
                    </div>
                </div>
                <div class="form-item">
                    <label for="">名称</label>
                    <div class="input-block">
                        <input type="text" name="name" class="layui-input">
                    </div>
                </div>
                <div class="form-item">
                    <label for="">名称</label>
                    <div class="input-block">
                        <input type="text" name="name" class="layui-input">
                    </div>
                </div>
                <div class="form-item">
                    <label for="">名称</label>
                    <div class="input-block">
                        <input type="text" name="name" class="layui-input">
                    </div>
                </div>
                <div class="form-item">
                    <label for="">名称</label>
                    <div class="input-block">
                        <input type="text" name="name" class="layui-input">
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>
<script>
    var zTreeObj;
    // zTree 的参数配置，深入使用请参考 API 文档（setting 配置详解）
    var setting = {};
    // zTree 的数据属性，深入使用请参考 API 文档（zTreeNode 节点数据详解）
    var zNodes = [
        {name:"test1", open:true, children:[
                {name:"test1_1"}, {name:"test1_2"}]},
        {name:"test1", open:true, children:[
                {name:"test1_1"}, {name:"test1_2"}]},
        {name:"test1", open:true, children:[
                {name:"test1_1"}, {name:"test1_2"}]},
        {name:"test1", open:true, children:[
                {name:"test1_1"}, {name:"test1_2"}]},
        {name:"test1", open:true, children:[
                {name:"test1_1"}, {name:"test1_2"}]},
        {name:"test1", open:true, children:[
                {name:"test1_1"}, {name:"test1_2"}]},
        {name:"test1", open:true, children:[
                {name:"test1_1"}, {name:"test1_2"}]},
        {name:"test1", open:true, children:[
                {name:"test1_1"}, {name:"test1_2"}]},
        {name:"test1", open:true, children:[
                {name:"test1_1"}, {name:"test1_2"}]},
        {name:"test1", open:true, children:[
                {name:"test1_1"}, {name:"test1_2"}]},
        {name:"test1", open:true, children:[
                {name:"test1_1"}, {name:"test1_2"}]},
        {name:"test1", open:true, children:[
                {name:"test1_1"}, {name:"test1_2"}]},
        {name:"test1", open:true, children:[
                {name:"test1_1"}, {name:"test1_2"}]},
        {name:"test1", open:true, children:[
                {name:"test1_1"}, {name:"test1_2"}]},
        {name:"test1", open:true, children:[
                {name:"test1_1"}, {name:"test1_2"}]},
        {name:"test1", open:true, children:[
                {name:"test1_1"}, {name:"test1_2"}]},
        {name:"test1", open:true, children:[
                {name:"test1_1"}, {name:"test1_2"}]},
        {name:"test1", open:true, children:[
                {name:"test1_1"}, {name:"test1_2"}]},
        {name:"test1", open:true, children:[
                {name:"test1_1"}, {name:"test1_2"}]},
        {name:"test1", open:true, children:[
                {name:"test1_1"}, {name:"test1_2"}]},
        {name:"test1", open:true, children:[
                {name:"test1_1"}, {name:"test1_2"}]},
        {name:"test1", open:true, children:[
                {name:"test1_1"}, {name:"test1_2"}]},
        {name:"test1", open:true, children:[
                {name:"test1_1"}, {name:"test1_2"}]},
        {name:"test1", open:true, children:[
                {name:"test1_1"}, {name:"test1_2"}]},
        {name:"test1", open:true, children:[
                {name:"test1_1"}, {name:"test1_2"}]},
        {name:"test1", open:true, children:[
                {name:"test1_1"}, {name:"test1_2"}]},
        {name:"test1", open:true, children:[
                {name:"test1_1"}, {name:"test1_2"}]},
        {name:"test1", open:true, children:[
                {name:"test1_1"}, {name:"test1_2"}]},
        {name:"test1", open:true, children:[
                {name:"test1_1"}, {name:"test1_2"}]},

        {name:"test2", open:true, children:[
                {name:"test2_1"}, {name:"test2_2"}]}
    ];
    $(document).ready(function(){
        zTreeObj = $.fn.zTree.init($("#treeDemo"), setting, zNodes);
    });

    layui.use('form', function(){
        var form = layui.form;

        //各种基于事件的操作，下面会有进一步介绍
    });
</script>
</body>
</html>

</body>
</html>
