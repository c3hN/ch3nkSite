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
            <a href="javascript:;">部门管理</a>
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
        </ul>
        <div class="form-wrap">
            <div class="container">
                <div class="row">
                    <form action="${basePath}/company/info/saveOrUpdate.do" method="post" class="form-horizontal" id="deptForm">
                        <input type="text" name="parentId" value="" style="display: none">
                        <input type="text" name="id" value="${company.id}" style="display: none">
                        <div class="tab-content">
                            <div class="tab-pane fade in active" id="baseInfo">
                                <div class="form-group">
                                    <label for="fullName" class="col-sm-2 control-label">
                                        <span style="color: red;">*</span>
                                        <span>公司全称</span>
                                    </label>
                                    <div class="col-sm-3">
                                        <input type="text" class="form-control" name="fullName" id="fullName" value="${company.fullName}">
                                    </div>
                                    <label for="shortName" class="col-sm-2 control-label">
                                        <span style="color: red;">*</span>
                                        <span>公司简称</span>
                                    </label>
                                    <div class="col-sm-3">
                                        <input type="text" class="form-control" id="shortName" name="shortName" value="${company.shortName}">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="enName" class="col-sm-2 control-label">
                                        <span>英文名称</span>
                                    </label>
                                    <div class="col-sm-3">
                                        <input type="text" class="form-control" id="enName" name="enName" value="${company.enName}">
                                    </div>
                                    <label for="code" class="col-sm-2 control-label">
                                        <span>公司代号</span>
                                    </label>
                                    <div class="col-sm-3">
                                        <input type="text" class="form-control" id="code" name="code" value="${company.code}">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="type" class="col-sm-2 control-label">
                                        <span>公司类型</span>
                                    </label>
                                    <div class="col-sm-3">
                                        <input type="text" class="form-control" id="type" name="type" value="${company.type}">
                                    </div>
                                    <label for="scale" class="col-sm-2 control-label">
                                        <span>公司规模</span>
                                    </label>
                                    <div class="col-sm-3">
                                        <input type="text" class="form-control" id="scale" name="scale" value="${company.scale}">
                                    </div>
                                </div>
                                <div class="col-sm-1 col-sm-offset-5">
                                    <button type="submit" class="btn btn-primary btn-block btn-content" id="saveOrEdit">保存</button>
                                </div>
                                <div class="col-sm-1">
                                    <button type="button" class="btn btn-primary btn-block btn-content" onclick="javascript:history.back(-1);">取消</button>
                                </div>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- 模态框（Modal） -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
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
                <button type="button" class="btn btn-primary" id="cleanParent">清空</button>
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
    var setting = {
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
    var detpsObj = $.fn.zTree.init($("#depts"), setting,  ${depts});
    // detpsObj.expandNode(detpsObj.getNodes()[0]);    //展开第一层
    var depts = detpsObj.getNodes();
    $.each(depts,function (index,value) {
        detpsObj.expandNode(depts[index]);
    });

    function clickNode(event, treeId, treeNode) {
        $("input[name='parentId']").attr('value',treeNode.deptId);
        // $("input[id='parentName']").attr('value',treeNode.deptName);
        $("input[id='parentName']").val(treeNode.deptName);
        $("#myModal").modal('hide');
    };

    $("#cleanParent").click(function () {
        $("input[name='parentId']").attr("value","");
        $("input[id='parentName']").val("");
        $("#myModal").modal('hide');
    });

    //    初始化表单验证
    // $("#deptForm").validator({
    //     fields: {
    //         'deptName':'required;remote(formCheck.do)',
    //     }
    // });
</script>
</body>
</html>
