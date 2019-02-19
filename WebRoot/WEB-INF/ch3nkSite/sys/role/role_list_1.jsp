<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<html>
<head>
    <title>角色管理</title>
    <link rel="stylesheet" href="${basePath}/static/plugins/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="${basePath}/static/plugins/bootstrap-table/bootstrap-table.min.css">
    <link rel="stylesheet" href="${basePath}/static/plugins/bootstrap-datetimepicker/bootstrap-datetimepicker.min.css">
    <link rel="stylesheet" href="${basePath}/static/plugins/font-awesome-4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="${basePath}/static/plugins/ztree/css/metroStyle/metroStyle.css">
    <link rel="stylesheet" href="${basePath}/static/css/common.css">
    <link rel="stylesheet" href="${basePath}/static/css/table.css">
    <style>

    </style>
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
    </ol>
</div>
<div class="content-body" id="content-body">
    <%--<div class="body-left">--%>
        <%--<div class="tree-content">--%>
            <%--<ul id="dept_tree" class="ztree"></ul>--%>
        <%--</div>--%>
    <%--</div>--%>
    <%--<div class="body-right">--%>
        <%--<div class="table-content">--%>
            <%--<div class="table_operations" id="table_operations">--%>
                <%--<div class="btns_operations">--%>
                    <%--<button type="button" class="btn btn-default btn-sm">新增</button>--%>
                    <%--<button type="button" class="btn btn-danger btn-sm">批量删除</button>--%>
                    <%--<button type="button" class="btn btn-info btn-sm">回收站</button>--%>
                <%--</div>--%>
                <%--<div class="search_operations">--%>
                    <%--<div class="form-inline">--%>
                        <%--<input type="text" name="likeAccount" placeholder="名称" class="form-control">--%>
                        <%--<input type="text" name="likeNickName" placeholder="编号" class="form-control">--%>
                        <%--<input type="text" name="likeCreateTime" placeholder="所属部门" id="createTime" class="form-control">--%>
                    <%--</div>--%>
                    <%--<div>--%>
                        <%--<button type="button" class="btn btn-sm btn-default">搜索</button>--%>
                        <%--<button type="button" class="btn btn-sm btn-primary">重置</button>--%>
                    <%--</div>--%>
                <%--</div>--%>
            <%--</div>--%>
            <%--<table id="roles" class="table-hover general-table">--%>
            <%--</table>--%>
        <%--</div>--%>
    <%--</div>--%>
    <div class="container">
        <div class="row">
            <div class="col-md-2">
                <div class="tree-content">
                    <ul id="dept_tree" class="ztree"></ul>
                </div>
            </div>
            <div class="col-md-10">
                <div class="table-content">
                    <div class="table_operations" id="table_operations">
                        <div class="btns_operations">
                            <shiro:hasPermission name="role:add">
                                <button type="button" class="btn btn-default btn-sm" id="addRoleBtn">新增</button>
                            </shiro:hasPermission>
                            <shiro:hasPermission name="role:abandonBatch">
                                <button type="button" class="btn btn-danger btn-sm" id="deleteBatchBtn">批量删除</button>
                            </shiro:hasPermission>
                            <shiro:hasPermission name="role:recover">
                                <a role="button" class="btn btn-info btn-sm" href="${basePath}/role/operate.do?op=toRecover">回收站</a>
                            </shiro:hasPermission>
                        </div>
                        <shiro:hasPermission name="role:search">
                            <div class="search_operations">
                                <div class="form-inline">
                                    <input type="text" name="likeName" placeholder="名称" class="form-control">
                                    <input type="text" name="likeEName" placeholder="编号" class="form-control">
                                    <select name="likeUseFlag" class="form-control">
                                        <option value="1">启用</option>
                                        <option value="0">禁用</option>
                                    </select>
                                </div>
                                <div>
                                    <button type="button" class="btn btn-sm btn-default" id="tableSearchBtn">搜索</button>
                                    <button type="button" class="btn btn-sm btn-primary" id="tableResetBtn">重置</button>
                                </div>
                            </div>
                        </shiro:hasPermission>
                    </div>
                    <table id="roles" class="table-hover general-table">
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>
<script src="${basePath}/static/js/jquery-3.2.1.js"></script>
<script src="${basePath}/static/plugins/bootstrap/js/bootstrap.min.js"></script>
<script src="${basePath}/static/plugins/bootstrap-table/bootstrap-table.min.js"></script>
<script src="${basePath}/static/plugins/bootstrap-table/locale/bootstrap-table-zh-CN.js"></script>
<script src="${basePath}/static/plugins/bootstrap-datetimepicker/bootstrap-datetimepicker.min.js"></script>
<script src="${basePath}/static/plugins/bootstrap-datetimepicker/bootstrap-datetimepicker.zh-CN.js"></script>
<script src="${basePath}/static/plugins/ztree/js/jquery.ztree.core.min.js"></script>
<script src="${basePath}/static/plugins/layer/layer.js"></script>
<script>
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
            onClick:function (event, treeId, treeNode, clickFlag) {
                table.bootstrapTable('refresh',{silent: true,url:'${basePath}/role/list?deptId='+treeNode.deptId});
            }
        }
    };
    var deptTreeObj = $.fn.zTree.init($("#dept_tree"), setting,  ${nodes});
    var depts = deptTreeObj.getNodes();
    $.each(depts,function (index,value) {
        deptTreeObj.expandNode(depts[index]);
    });

    table = $("#roles").bootstrapTable({
        url:'${basePath}/role/list.do?deptId='+depts[0].deptId,
        height:750,
        striped:true,
        undefinedText:'暂无',
        pagination:true,    //分页
        sidePagination:'server',
        pageNumber:1,
        pageSize:20,
        pageList:[20,30,40,50],
        paginationLoop:false,
        sortOrder:'desc',
        toolbar:'#table_operations',
        columns:[
            {checkbox:true},
            {title:'序号',formatter:'numFormatter', align:'center',width:'30'},
            {field:'name',title:'名称',align:'center',width:'100'},
            {field:'eName',title:'编号',align:'center',width:'100'},
            {field:'department.deptName',title:'所属部门',align:'center',width:'100'},
            {field:'useFlag',title:'状态',align:'center',width:'100',formatter:'stateFormatter'},
            {field:'createDate',title:'创建时间',align:'center',width:'100'},
            {title:'操作',events:'roleOperateEvents',formatter:'operateFormatter',align:'center',width:'150'}
        ]
    });
    function numFormatter(value, row, index) {
        var pageSize = $('#roles').bootstrapTable('getOptions').pageSize;     //通过table的#id 得到每页多少条
        var pageNumber = $('#roles').bootstrapTable('getOptions').pageNumber; //通过table的#id 得到当前第几页
        return pageSize * (pageNumber - 1) + index + 1;    // 返回每条的序号： 每页条数 *（当前页 - 1 ）+ 序号
    }
    //表格操作
    function operateFormatter(value, row, index) {
        return [
            '<shiro:hasPermission name="role:recover"><button class="btn btn-default btn-xs" id="addMenus">授权菜单</button> </shiro:hasPermission>',
            '<shiro:hasPermission name="role:detail"><button class="btn btn-default btn-xs" id="menuDetail">查看</button> </shiro:hasPermission>',
            '<shiro:hasPermission name="role:edit"><button class="btn btn-default btn-xs" id="editRole">编辑</button> </shiro:hasPermission>',
            '<shiro:hasPermission name="role:abandon"><button class="btn btn-default btn-xs btn-danger" id="abandonRole">删除</button></shiro:hasPermission>',
        ].join('');
    }
    window.roleOperateEvents = {
        "click #addMenus":function (e,value,row,index) {
            $(location).prop('href', '${basePath}/role/operate.do?op=roleAddMenu&roleId='+row.roleId);
        },
        "click #menuDetail":function (e,value,row,index) {
            $(location).prop('href', '${basePath}/role/operate.do?op=detail&roleId='+row.roleId);
        },
        "click #editRole":function (e,value, row, index) {
            $(location).prop('href', '${basePath}/role/operate.do?op=edit&roleId='+row.roleId);
        },
        "click #abandonRole":function (e,value, row, index) {
            layer.confirm('确定删除该角色?',{btn:['确定','取消'],icon:3},
                function (index,layero) {
                    $.post("${basePath}/role/roleAbandon.do",{"roleId":row.roleId},function (data) {
                        if (data.data == "success") {
                            layer.msg("角色已被移入回收站，可在回收站中还原");
                            table.bootstrapTable('refresh',{silent: true,url:'${basePath}/role/list?deptId='+row.department.deptId});//重载表格

                        }else if (data.data == "error"){
                            layer.alert("该角色被使用，删除失败",{icon:2});
                        }
                    });
                    layer.close(index);
                },
                function (index,layero) {
                    layer.close(index);
                });
        }
    }
    //表格状态字段格式化
    function stateFormatter(value, row, index) {
        if (value == '1') {
            return '<span style="color: #3e8f3e">启用</span>';
        }else if (value == '0') {
            return '<span style="color: #9F9F9F;">禁用</span>';
        }
    }
    //搜索
    $("#tableSearchBtn").click(function () {
        //模糊查询条件
        var likeName = $("input[name='likeName']").val();
        var likeEName = $("input[name='likeEName']").val();
        var likeUseFlag = $("select[name='likeUseFlag']").val();
        if (likeName != '' || likeEName != '' || likeUseFlag != '') {
            table.bootstrapTable('refresh',{url:'${basePath}/role/list.do?likeName='+likeName+'&likeEName='+likeEName+'&likeUseFlag='+likeUseFlag});
        }else {
            layer.alert('请输入搜索条件！',{icon:2});
        }
    });
    //重置
    $("#tableResetBtn").click(function () {
        $("input[name='likeName']").val("");
        $("input[name='likeEName']").val("");
        $("select[name='likeUseFlag']").val("");
        table.bootstrapTable('refresh',{url:'${basePath}/role/list.do'});
    });


    $("#roleAddBtn").click(function () {
        var nodes = deptTreeObj.getSelectedNodes();
        if (nodes.length > 0) {
            $(location).prop("href","${basePath}/role/toAddOrEdit.do?deptId="+nodes[0].deptId);
        }else{
            $(location).prop("href","${basePath}/role/toAddOrEdit.do");
        }
    });
    $("#toRecoveBtn").click(function () {
        $(location).prop("href","${basePath}/role/toRecove.do");
    });
    //新增
    $("#addRoleBtn").click(function () {
        var nodes = deptTreeObj.getSelectedNodes();
        if (nodes.length > 0) {
            $(location).prop("href","${basePath}/role/operate.do?op=add&deptId="+nodes[0].deptId);
        }else{
            $(location).prop("href","${basePath}/role/operate.do?op=add");
        }
    });
    //批量删除
    $("#deleteBatchBtn").click(function () {
        layer.load(2);
        var selected = table.bootstrapTable('getSelections');
        var len = selected.length;
        if (len != 0) {
            layer.confirm('确认删除所选择的角色？',{btn:['确认删除','取消'],icon:3},
            function (index,layero) {
                layer.close(index);
                var roleIds = new Array();
                for(i = 0 ; i < len; i++) {
                    roleIds[i] = selected[i].roleId;
                }
                <%--$.ajax({--%>
                    <%--type:'post',--%>
                    <%--url:'${basePath}/role/roleAbandonBatch.do',--%>
                    <%--dataType:'json',--%>
                    <%--traditional:true,--%>
                    <%--data:{'roleIds':roleIds},--%>
                    <%--success:function (data) {--%>
                        <%--//关闭加载层--%>
                        <%--layer.closeAll('loading');--%>
                        <%--if(data.msg == "success") {--%>
                            <%--table.bootstrapTable('refresh',{url:'${basePath}/role/list.do'});//重载表格--%>
                            <%--layer.msg("成功删除"+data.total+"个角色!");--%>
                        <%--}else {--%>
                            <%--layer.closeAll('loading');--%>
                            <%--layer.alert("删除失败",{icon:2});--%>
                        <%--}--%>
                    <%--}--%>
                <%--});--%>
            },
            function (index,layero) {
                layer.closeAll();
            });
        }else {
            layer.closeAll('loading');
            layer.msg('请选择需要删除的角色！');
        }
    });
</script>
</body>
</html>