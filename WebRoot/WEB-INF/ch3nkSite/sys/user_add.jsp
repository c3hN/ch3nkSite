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
    <link rel="stylesheet" href="${basePath}/static/plugins/nice-validator/jquery.validator.css">
    <link rel="stylesheet" href="${basePath}/static/plugins/ztree/css/metroStyle/metroStyle.css">
    <link rel="stylesheet" href="${basePath}/static/plugins/font-awesome-4.7.0/css/font-awesome.min.css">
    <style>
        .content .form-content{
            width: 100%;
        }
        .title{
            border-bottom: 1px solid #dbdbdb;
            /*width: 100%;*/
            height: 30px;
            line-height: 30px;
            margin: 10px 20px 30px 20px;
            padding-left: 10px;
        }
        #deptName{
            padding: 0 46px 0 12px;
        }
        .modal-dialog-custom{
            width: 400px;
        }
        .position{
            border-bottom: 1px solid #dbdbdb;
            height: 50px;
            background-color: #FFFFFF;
            margin-bottom: 40px;
            line-height: 50px;
            padding: 0 0 0 20px;
        }
    </style>
</head>
<body>
<div class="position">
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
    <div class="form-content">
        <div class="container">
            <div class="row">
                <form action="${basePath}/user/saveOrUpdate.do" class="form-horizontal" id="userForm" method="post">
                    <div class="form-group">
                        <label for="account" class="col-sm-2 control-label">
                            <span>用户账号</span>
                            <span style="color: red;">*</span>
                        </label>
                        <div class="col-sm-3">
                            <input type="text" class="form-control" name="account" id="account" >
                        </div>
                        <label for="nickName" class="col-sm-2 control-label">
                            <span>用户昵称</span>
                            <span style="color: red;">*</span>
                        </label>
                        <div class="col-sm-3">
                            <input type="text" class="form-control" name="nickName" id="nickName">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="userPwd" class="col-sm-2 control-label">
                            <span>用户密码</span>
                            <span style="color: red;">*</span>
                        </label>
                        <div class="col-sm-3">
                            <input type="password" class="form-control" id="userPwd" name="userPwd">
                        </div>
                        <label class="col-sm-2 control-label">
                            <span>账号状态</span>
                        </label>
                        <div class="col-sm-5">
                            <label class="radio-inline">
                                <input type="radio" name="loginFlag" value="1" checked>启用
                            </label>
                            <label class="radio-inline">
                                <input type="radio" name="loginFlag" value="0">禁用
                            </label>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="checkPwd" class="col-sm-2 control-label">
                            <span>确认密码</span>
                            <span style="color: red;">*</span>
                        </label>
                        <div class="col-sm-3">
                            <input type="password" class="form-control" name="checkPwd" id="checkPwd">
                        </div>
                        <label  class="col-sm-2 control-label">
                            <span>角色列表</span>
                            <span style="color: red;">*</span>
                        </label>
                        <div class="col-sm-3">
                            <input type="text" class="form-control" name="department.deptName" id="deptName" >
                            <button type="button" class="btn btn-default" data-toggle="modal" data-target="#roles"><i class="fa fa-search"></i></button>
                        </div>
                    </div>
                    <div class="col-sm-1 col-sm-offset-5">
                        <button type="submit" class="btn btn-primary btn-block btn-content" id="saveOrEdit">保存</button>
                    </div>
                    <div class="col-sm-1">
                        <button type="button" class="btn btn-primary btn-block btn-content" onclick="javascript:history.back(-1);">取消</button>
                    </div>
                </form>

            </div>
        </div>
    </div>
</div>
<!-- 模态框（Modal） -->
<div class="modal fade" id="roles" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-dialog-custom">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                    &times;
                </button>
                角色列表
            </div>
            <div class="modal-body">
                <ul id="roles-tree" class="ztree"></ul>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
            </div>
        </div>
    </div>
</div>
<script src="${basePath}/static/js/jquery-3.2.1.js"></script>
<script src="${basePath}/static/plugins/bootstrap/js/bootstrap.min.js"></script>
<script src="${basePath}/static/plugins/nice-validator/jquery.validator.min.js"></script>
<script src="${basePath}/static/plugins/nice-validator/local/zh-CN.js"></script>
<script src="${basePath}/static/plugins/ztree/js/jquery.ztree.core.min.js"></script>
<script>
    //初始化表单验证
    var val = $("input[name='userId']").val();
    $("#userForm").validator({
        rules: {
            cusMobile:[/^1[3458]\d{9}$/, '请输入手机号'],
            cusMatch:function (element,param) {
                var comparedStr = element.value;
                var standStr = '';
                var tmp = param[0];
                if (param[0].substring(0,1) != '#') { //name
                    standStr = $("input[name="+param[0]+"]").val();
                }else{
                    standStr = $("input[id="+param[0].substring(1,param[0].length)+"]").val();
                }
                if (comparedStr == standStr) {
                    return true;
                }else{
                    return "两次输入不一致";
                }
            }
        },
        fields: {
            'account':'required;cusMobile;remote(formCheck.do)',
            'nickName':'required',
            'userPwd':'required',
            'checkPwd':'cusMatch(userPwd)'
        }
    });

</script>
</body>
</html>