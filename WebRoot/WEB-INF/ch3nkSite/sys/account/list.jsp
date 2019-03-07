<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<html>
<head>
    <title>账号列表</title>
    <link rel="stylesheet" href="${basePath}/static/plugins/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="${basePath}/static/plugins/bootstrap-table/bootstrap-table.min.css">
    <link rel="stylesheet" href="${basePath}/static/plugins/bootstrap-datetimepicker/bootstrap-datetimepicker.min.css">
    <link rel="stylesheet" href="${basePath}/static/plugins/font-awesome-4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="${basePath}/static/plugins/ztree/css/metroStyle/metroStyle.css">
    <link rel="stylesheet" href="${basePath}/static/css/common.css">
    <link rel="stylesheet" href="${basePath}/static/css/table.css">
</head>
<body>
<div class="content-head">
    <ol class="breadcrumb">
        <li class="active">
            <a href="javascript:;">组织权限</a>
        </li>
        <li class="active">
            <a href="javascript:;">账号管理</a>
        </li>
    </ol>
</div>
<div class="content-body" id="content-body">
    <div class="container">
        <div class="row">
            <div class="col-md-12">
                <div class="table-content" style="position: relative">
                    <div class="table_operations" id="table_operations">
                        <div class="btns_operations">
                            <shiro:hasPermission name="user:add">
                                <button type="button" class="btn btn-default btn-sm" id="addBtn">新增</button>
                            </shiro:hasPermission>
                        </div>
                        <shiro:hasPermission name="user:search">
                            <div class="search_operations">
                                <div class="form-inline">
                                    <input type="text" name="likeAccount" placeholder="账号" class="form-control">
                                    <input type="text" name="likeNickName" placeholder="昵称" class="form-control">
                                    <input type="text" name="likeCreateTime" readonly  id="datetimepicker" placeholder="注册时间" class="form-control">
                                </div>
                                <div>
                                    <button type="button" class="btn btn-sm btn-default" id="tableSearchBtn">搜索</button>
                                    <button type="button" class="btn btn-sm btn-primary" id="tableResetBtn">重置</button>
                                </div>
                            </div>
                        </shiro:hasPermission>
                    </div>
                    <table id="table" class="table-hover general-table">
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
                name:"fullName",
                title:"fullName"
            }
        },
        callback:{
            onClick:function (event, treeId, treeNode, clickFlag) {
                table.bootstrapTable('refresh',{url:'${basePath}/account/info/query.do'});
            }
        }
    };
    var treeObj = $.fn.zTree.init($("#dept_tree"), setting,  ${deptsNodes});
    var companies = treeObj.getNodes();
    $.each(companies,function (index,value) {
        treeObj.expandNode(companies[index]);
    });
    //新增
    $("#addBtn").click(function () {
        $(location).prop("href","${basePath}/account/view/add.do");
    });

    table = $("#table").bootstrapTable({
        url:'${basePath}/account/info/query.do',
        height:750,
        striped:true,
        undefinedText:'暂无',
        checkbox:true,
        pagination:true,    //分页
        sidePagination:'server',
        pageNumber:1,
        pageSize:20,
        pageList:[20,30,40,50],
        paginationLoop:false,
        sortOrder:'desc',
        toolbar:'#table_operations',
        columns:[
            {title:'序号',formatter:'numFormatter', align:'center',width:'30'},
            {field:'account',title:'账号',align:'center',width:'100'},
            {field:'nickName',title:'昵称',align:'center',width:'100'},
            {field:'isAuth',title:'认证状态',align:'center',width:'100',formatter:'authFormatter',},
            {field:'type',title:'账号类型',align:'center',width:'100',formatter:'typeFormatter'},
            {field:'createDate',title:'创建时间',align:'center',width:'100'},
            {field:'modifyDate',title:'修改时间',align:'center',width:'100'},
            {title:'操作',events:'operateEvents',formatter:'operateFormatter',align:'center',width:'150'}
        ]
    });
    //表格序号
    function numFormatter(value, row, index) {
        // return index+1;
        var pageSize = $('#table').bootstrapTable('getOptions').pageSize;     //通过table的#id 得到每页多少条
        var pageNumber = $('#table').bootstrapTable('getOptions').pageNumber; //通过table的#id 得到当前第几页
        return pageSize * (pageNumber - 1) + index + 1;    // 返回每条的序号： 每页条数 *（当前页 - 1 ）+ 序号
    };
    function authFormatter(value, row, index) {
        if (value == '0') {
            return '未认证';
        }else if (value == '1') {
            return '已认证';
        }
    };
    function typeFormatter(value, row, index) {
        if (value == '0') {
            return '普通用户';
        }else if (value == '1') {
            return '系统用户';
        }
    };
    //表格操作
    function operateFormatter(value, row, index) {
        return [
            '<shiro:hasPermission name="user:detail"><button class="btn btn-default btn-xs" id="addRoles">添加角色</button> </shiro:hasPermission>',
            '<shiro:hasPermission name="user:edit"><button class="btn btn-default btn-xs" id="edit">编辑</button> </shiro:hasPermission>',
            '<shiro:hasPermission name="user:abandon"><button class="btn btn-default btn-xs btn-danger" id="abandon">删除</button></shiro:hasPermission>'
        ].join('');
    }
    window.operateEvents = {
        "click #addRoles":function (e,value,row,index) {
            <%--$(location).prop('href', '${basePath}/account/view/addRole.do?acId='+row.acId);--%>
            layer.open({
                type: 2,
                title: '选择角色',
                shadeClose: true,
                // shade: 0.8,
                area: ['380px', '380px'],
                content: '${basePath}/account/view/addRole.do?acId='+row.acId //iframe的url
            });
        },
        "click #edit":function (e,value, row, index) {
            $(location).prop('href', '${basePath}/account/view/edit.do?acId='+row.acId);
        },
        "click #abandon":function (e,value, row, index) {
            layer.confirm('确定删除该账号?',{btn:['确定','取消'],icon:3},
                function (index,layero) {
                    $.post(
                        "${basePath}/account/info/abandon.do",
                        {"acId":row.acId},
                        function (data) {
                        if (data.status == "0") {
                            table.bootstrapTable('refresh',{url:'${basePath}/account/info/query.do?acId='+row.acId});//重载表格
                            layer.msg("账号已移入回收站！");
                        }else{
                            layer.alert(data.msg,{icon:2});
                        }
                    });
                    layer.close(index);
                },
                function (index,layero) {
                    layer.close(index);
                });
        }
    }
    //搜索
    $("#tableSearchBtn").click(function () {
        //模糊查询条件
        var likeAccount = $("input[name='likeAccount']").val();
        var likeNickName = $("input[name='likeNickName']").val();
        var likeCreateTime = $("input[name='likeCreateTime']").val();
        if (likeAccount != '' || likeNickName != '' || likeCreateTime != '') {
            table.bootstrapTable('refresh',{url:'${basePath}/user/list.do?likeAccount='+likeAccount+'&likeNickName='+likeNickName+'&likeCreateTime='+likeCreateTime});
        }else {
            layer.alert('请输入搜索条件！',{icon:2});
        }
    });
    //时间
    $('#datetimepicker').datetimepicker({
        format: 'yyyy-mm-dd',
        language:'zh-CN',
        autoclose:true,
        todayHighlight:true,
        minView:'2'
    });
</script>
</body>
</html>