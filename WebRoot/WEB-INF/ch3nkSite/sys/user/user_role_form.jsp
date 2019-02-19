<%--
  Created by IntelliJ IDEA.
  User: chenkai
  Date: 2018/12/24
  Time: 14:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>用户分配角色</title>
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
            <a href="javascript:;">角色管理</a>
        </li>
        <li class="active">
            <a href="javascript:;">分配角色</a>
        </li>
    </ol>
</div>
<div class="content-body" id="content-body">
    <div class="content-wrap">
        <div class="form-horizontal">
            <div class="baseInfo">
                <ul id="formTab_1" class="nav nav-tabs">
                    <li class="active">
                        <a href="#baseInfo" data-toggle="tab">基本信息</a>
                    </li>
                </ul>
                <div class="tab-content">
                    <div class="tab-pane fade in active" id="baseInfo"  style="height: 50px;margin-bottom: 40px;padding-top: 20px;">
                        <div class="row">
                            <div class="col-md-4 col-md-offset-2">
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">
                                        <span>用户名称</span>
                                    </label>
                                    <div class="form-control-static">${user.nickName}</div>
                                </div>
                            </div>
                            <div class="col-md-4 col-md-offset-2">
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">
                                        <span>所属部门</span>
                                    </label>
                                    <div class="form-control-static">${user.department.deptName}</div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="menus">
                    <div class="baseInfo">
                        <ul id="formTab_2" class="nav nav-tabs">
                            <li class="active">
                                <a href="#roles" data-toggle="tab">角色列表</a>
                            </li>
                        </ul>
                        <div class="tab-content">
                            <div class="tab-pane fade in active" id="roles">
                               <div class="role_tree_wrap" style="background-color:#f2f2f2;height: 500px;overflow-y: auto">
                                   <ul id="role_tree" class="ztree"></ul>
                               </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="btns" style="width: 100%;margin-top: 15px;text-align:center;">
                    <button type="button" class="btn btn-primary" id="saveBtn">保存</button>
                    <button type="button" class="btn btn-primary" onclick="javascript:history.back(-1);">取消</button>
                </div>
            </div>
        </div>
    </div>
</div>


<script src="${basePath}/static/js/jquery-3.2.1.js"></script>
<script src="${basePath}/static/plugins/bootstrap/js/bootstrap.min.js"></script>
<script src="${basePath}/static/plugins/nice-validator/jquery.validator.min.js"></script>
<script src="${basePath}/static/plugins/nice-validator/local/zh-CN.js"></script>
<script src="${basePath}/static/plugins/ztree/js/jquery.ztree.core.min.js"></script>
<script src="${basePath}/static/plugins/ztree/js/jquery.ztree.excheck.min.js"></script>
<script>
    var setting1 = {
        data: {
            simpleData: {
                enable: true,
                idKey: "roleId",
                pIdKey: "parentId",
                rootPid: null
            },
            key: {
                name: "name",
                title: "name"
            }
        },
        check:{
            enable:true,
            chkStyle:"checkbox",
            chkboxType:{"Y":"p","N":"s"}
        }
    };
    var rolesObj = $.fn.zTree.init($("#role_tree"), setting1,  ${roles});
    var roles = rolesObj.getNodes();
    $("#saveBtn").click(function () {
        var userId = "${user.userId}";
        var checkRoles = rolesObj.getCheckedNodes();
        var roleIds = new Array();
        for (i = 0,len = checkRoles.length; i < len; i++) {
            roleIds[i] = checkRoles[i].roleId;
        }
        console.log(roleIds)
        $.ajax({
            type:'post',
            url:'${basePath}/user/saveUserRoles.do',
            dataType:'json',
            traditional:true,
            data:{
                "userId":userId,
                "roleIds":roleIds
            },
            success:function (data) {
                debugger
                console.log(data);
            }
        });
    });

    //ztree回显
    var haveChecked = ${checkedRoles}
    if (haveChecked.length != 0) {
        for(i = 0,len1 = haveChecked.length; i < len1; i++) {
            for (j = 0,len2 = roles.length; j < len2; j++) {
                if (haveChecked[i] == roles[j].roleId) {
                    rolesObj.checkNode(roles[j],true,true);
                }
            }
        }
    }
</script>
</body>
</html>
