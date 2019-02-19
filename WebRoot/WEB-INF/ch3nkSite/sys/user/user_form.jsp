<%--
  Created by IntelliJ IDEA.
  User: chenkai
  Date: 2018/12/14
  Time: 17:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<html>
<head>
    <title>用户新增编辑</title>
    <link rel="stylesheet" href="${basePath}/static/plugins/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="${basePath}/static/plugins/nice-validator/jquery.validator.css">
    <link rel="stylesheet" href="${basePath}/static/plugins/ztree/css/metroStyle/metroStyle.css">
    <link rel="stylesheet" href="${basePath}/static/plugins/font-awesome-4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="${basePath}/static/css/form.css">
</head>
<body>
<div class="content-head">
    <ol class="breadcrumb">
        <li class="active">
            <a href="javascript:;">组织权限</a>
        </li>
        <li class="active">
            <a href="javascript:;">用户管理</a>
        </li>
        <li class="active">
            <a href="javascript:;">新增</a>
        </li>
    </ol>
</div>
<div class="content-body" id="content-body">
    <div class="content-wrap">
        <ul id="formTab" class="nav nav-tabs">
            <li class="active">
                <a href="#baseInfo" data-toggle="tab">基本信息</a>
            </li>
            <li>
                <a href="#securityInfo" data-toggle="tab">安全信息</a>
            </li>
            <li>
                <a href="#otherInfo" data-toggle="tab">其他</a>
            </li>
        </ul>
        <div class="form-wrap">
            <div class="container">
                <div class="row">
                    <form action="${basePath}/user/saveOrUpdate.do" class="form-horizontal" id="userForm" method="post">
                        <input type="text" name="department.deptId" id="deptId" style="display: none" value="${dept.deptId}">
                        <input type="text" name="userId" style="display: none" value="${sysUser.userId}">
                        <div class="tab-content">
                            <div class="tab-pane fade in active" id="baseInfo">
                                <div class="form-group">
                                    <label for="deptName" class="col-sm-2 control-label">
                                        <span>所属部门</span>
                                        <span style="color: red;">*</span>
                                    </label>
                                    <div class="col-sm-3">
                                        <%--编辑时显示--%>
                                        <c:if test="${sysUser != null}">
                                            <div class="form-control-static">${sysUser.department.deptName}</div>
                                        </c:if>
                                        <%--新增时显示--%>
                                        <c:if test="${sysUser == null}">
                                            <div class="input-group">
                                                <input type="text" class="form-control" id="deptName" value="${dept.deptName}" onfocus="this.blur()">
                                                <span class="input-group-btn">
                                                    <button class="btn btn-default" type="button" data-toggle="modal" data-target="#depts_modal">&nbsp;<i class="fa fa-search"></i>&nbsp;</button>
                                                </span>
                                            </div>
                                        </c:if>
                                    </div>
                                    <label class="col-sm-2 control-label">
                                        <span>账号状态</span>
                                    </label>
                                    <div class="col-sm-5">
                                        <label class="radio-inline">
                                            <input type="radio" name="loginFlag" value="1" <c:if test="${sysUser.loginFlag == null or sysUser.loginFlag == '1'}">checked</c:if> >启用
                                        </label>
                                        <label class="radio-inline">
                                            <input type="radio" name="loginFlag" value="0" <c:if test="${sysUser.loginFlag == '0'}">checked</c:if> >禁用
                                        </label>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="account" class="col-sm-2 control-label">
                                        <span>用户账号</span>
                                        <span style="color: red;">*</span>
                                    </label>
                                    <div class="col-sm-3">
                                        <input type="text" class="form-control" name="account" id="account" value="${sysUser.account}">
                                    </div>
                                    <label for="nickName" class="col-sm-2 control-label">
                                        <span>用户昵称</span>
                                        <span style="color: red;">*</span>
                                    </label>
                                    <div class="col-sm-3">
                                        <input type="text" class="form-control" name="nickName" id="nickName" value="${sysUser.nickName}">
                                    </div>
                                </div>
                            </div>
                            <div class="tab-pane fade" id="securityInfo">
                                <div class="form-group">
                                    <label for="userPwd" class="col-sm-2 control-label">
                                        <span>用户密码</span>
                                        <span style="color: red;">*</span>
                                    </label>
                                    <div class="col-sm-3">
                                        <input type="password" class="form-control" id="userPwd" name="userPwd">
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
                                </div>
                            </div>
                            <div class="tab-pane fade" id="otherInfo"></div>
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
</div>
<div class="modal fade" id="depts_modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-dialog-custom">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                    &times;
                </button>
                部门
            </div>
            <div class="modal-body">
                <ul id="depts" class="ztree"></ul>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
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
    <%--模态框树--%>
    var setting1 = {
        data:{
            simpleData:{
                enable:true,
                idKey:"deptId",
                pIdKey:"parentId",
                rootPid:null
            },
            key:{
                name:"deptName",
                title:"deptName"
            }
        },
        callback:{
            onClick:clickNode
        }
    };
    var deptsObj = $.fn.zTree.init($("#depts"), setting1,  ${deptsNodes});
    var depts = deptsObj.getNodes();
    $.each(depts,function (index,value) {
        deptsObj.expandNode(depts[index]);
    });
    //点击数节点，表单填充部门id和部门
    function clickNode(event, treeId, treeNode) {
        $("input[id='deptId']").attr("value",treeNode.deptId);
        $("input[id='deptName']").attr("value",treeNode.deptName);
        $("#depts_modal").modal('hide');
    };
</script>
</body>
</html>
