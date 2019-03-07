<%--
  Created by IntelliJ IDEA.
  User: chenkai
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="${basePath}/static/plugins/bootstrap/css/bootstrap.min.css">
</head>
<body>
<div id="addOperate" style="width: 100%;height: 100%;padding: 15px;">
    <div class="container" style="width: 100%;">
        <div class="row">
            <form action="" class="form-horizontal" id="operate">
                <input type="text" style="display: none;" name="menu.menuId" value="${menuId}">
                <div class="form-group">
                    <label for="name" class="col-sm-3 control-label">
                        <span>权限名称</span>
                        <span style="color: red;">*</span>
                    </label>
                    <div class="col-sm-9">
                        <input type="text" class="form-control" id="name" name="name">
                    </div>
                </div>
                <div class="form-group">
                    <label for="name" class="col-sm-3 control-label">
                        <span>权限代码</span>
                        <span style="color: red;">*</span>
                    </label>
                    <div class="col-sm-9">
                        <input type="text" class="form-control" id="code" name="code">
                    </div>
                </div>
                <div style=" text-align:center;">
                    <button type="button" class="btn btn-primary btn-sm" id="saveBtn">确定</button>
                    <button type="button" class="btn btn btn-default btn-sm" id="close">取消</button>
                </div>
            </form>
        </div>
    </div>
</div>
<script src="${basePath}/static/js/jquery-3.2.1.js"></script>
<script src="${basePath}/static/plugins/bootstrap/js/bootstrap.min.js"></script>
<script src="${basePath}/static/plugins/layer/layer.js"></script>
<script>
    $('#saveBtn').click(function () {
        $.ajax({
            url:'${basePath}/operate/info/saveOperate.do',
            data:$('#operate').serialize(),
            success:function (resp) {
                if (resp.status == '0') {
                    layer.alert(resp.msg,{'icon':1},function () {
                        var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
                        parent.layer.close(index);
                    })
                }else {
                    layer.alert(resp.msg,{'icon':2},function () {
                        var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
                        parent.layer.close(index);
                    })
                }
            },
            error:function (resp) {
                layer.alert(resp.msg,{'icon':2},function () {
                    close();
                })
            }
        });
    });
    $("#close").click(function () {
        close()
    });
    function close() {
        var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
        parent.layer.close(index);
    }
</script>
</body>
</html>
