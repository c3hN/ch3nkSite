<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<html>
<head>
    <title>角色列表</title>
    <link rel="stylesheet" href="${basePath}/static/plugins/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="${basePath}/static/plugins/bootstrap-table/bootstrap-table.min.css">
    <link rel="stylesheet" href="${basePath}/static/plugins/bootstrap-datetimepicker/bootstrap-datetimepicker.min.css">
    <link rel="stylesheet" href="${basePath}/static/plugins/font-awesome-4.7.0/css/font-awesome.min.css">
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
            <a href="javascript:;">公司管理</a>
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
                            <button type="button" class="btn btn-default btn-sm" id="addBtn">新增</button>
                        </div>
                        <div class="search_operations">
                            <div class="form-inline">
                                <input type="text" name="likeName" placeholder="公司名称" class="form-control">
                                <input type="text" name="likeCode" placeholder="公司代号" class="form-control">
                            </div>
                            <div>
                                <button type="button" class="btn btn-sm btn-default" id="tableSearchBtn">搜索</button>
                                <button type="button" class="btn btn-sm btn-primary" id="tableResetBtn">重置</button>
                            </div>
                        </div>
                    </div>
                    <%--<div class="table_wrap">--%>
                        <table id="company" class="table-hover general-table"></table>
                    <%--</div>--%>
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
<script src="${basePath}/static/plugins/layer/layer.js"></script>
<script>
    //新增
    $("#addBtn").click(function () {
        $(location).prop("href","${basePath}/company/view/add.do");
    });

    table = $("#company").bootstrapTable({
        url:'${basePath}/company/info/query.do',
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
            {field:'fullName',title:'公司名称',align:'center',width:'100'},
            {field:'shortName',title:'公司简称',align:'center',width:'100'},
            {field:'enName',title:'英文名称',align:'center',width:'100'},
            {field:'code',title:'公司代号',align:'center',width:'100'},
            {field:'type',title:'公司类型',align:'center',width:'100'},
            {field:'scale',title:'公司规模',align:'center',width:'100'},
            {field:'createDate',title:'成立时间',align:'center',width:'100'},
            {title:'操作',events:'operateEvents',formatter:'operateFormatter',align:'center',width:'150'}
        ]
    });
    //表格序号
    function numFormatter(value, row, index) {
        // return index+1;
        var pageSize = $('#company').bootstrapTable('getOptions').pageSize;     //通过table的#id 得到每页多少条
        var pageNumber = $('#company').bootstrapTable('getOptions').pageNumber; //通过table的#id 得到当前第几页
        return pageSize * (pageNumber - 1) + index + 1;    // 返回每条的序号： 每页条数 *（当前页 - 1 ）+ 序号
    }
    //表格操作
    function operateFormatter(value, row, index) {
        return [
            '<shiro:hasPermission name="user:detail"><button class="btn btn-default btn-xs" id="detail">查看</button> </shiro:hasPermission>',
            '<shiro:hasPermission name="user:edit"><button class="btn btn-default btn-xs" id="edit">编辑</button> </shiro:hasPermission>',
            '<shiro:hasPermission name="user:abandon"><button class="btn btn-default btn-xs btn-danger" id="abandon">删除</button></shiro:hasPermission>'
        ].join('');
    }
    window.operateEvents = {
        "click #detail":function (e,value,row,index) {
            $(location).prop('href', '${basePath}/company/view/detail.do?id='+row.id);
        },
        "click #edit":function (e,value,row,index) {
            $(location).prop('href', '${basePath}/company/view/edit.do?id='+row.id);
        },
        "click #abandon":function (e,value, row, index) {
            $(location).prop('href', '${basePath}/company/info/abandon.do?'+row.id);
        },
        "click #abandon":function (e,value, row, index) {
            layer.confirm('确定删除该公司?',{btn:['确定','取消'],icon:3},
                function (index,layero) {
                    $.post("${basePath}/company/info/abandon.do",{"id":row.id},function (data) {
                        if (data.status == "0") {
                            table.bootstrapTable('refresh',{url:'${basePath}/company/info/query.do'});//重载表格
                            layer.msg("已移入回收站！");
                        }else if (data.status == "1"){
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
    //表格字段格式化
    function stateFormatter(value, row, index) {
        if (value == '1') {
            return '是';
        }else if (value == '0') {
            return '否';
        }
    };
    //搜索
    $("#tableSearchBtn").click(function () {
        //模糊查询条件
        var likeName = $("input[name='likeName']").val();
        var likeCode = $("input[name='likeCode']").val();
        if (likeName != '' || likeCode != '') {
            table.bootstrapTable('refresh',{url:'${basePath}/company/info/query.do?likeFullName='+likeName+'&likeShortName'+likeName+'&likeCode='+likeCode});
        }else {
            layer.msg('请输入搜索条件！',{icon:2});
        }
    });
    //重置
    $("#tableResetBtn").click(function () {
        $("input[name='likeAccount']").val("");
        $("input[name='likeNickName']").val("");
        $("input[name='likeCreateTime']").val("");
        table.bootstrapTable('refresh',{url:'${basePath}/company/info/query.do'});
    });
    /**
     * 批量删除
     */
    $("#deleteBatchBtn").click(function () {
        layer.load(2);  //加载层
        var selected = $("#users").bootstrapTable('getSelections');
        if (selected.length != 0) {
            layer.confirm('确认删除所选择的用户？',{btn:['确认删除','取消'],icon:3},
            function (index,layero) {   //确认
                layer.close(index);
                var userIds = new Array();
                for (i = 0, len = selected.length; i < len; i++) {
                    userIds[i] = selected[i].userId;
                }
                $.ajax({
                    type:'post',
                    url:'${basePath}/user/userAbandonBatch.do',
                    dataType:'json',
                    traditional:true,
                    data:{'userIds':userIds},
                    success:function (data) {
                        //关闭加载层
                        layer.closeAll('loading');
                        if(data.msg == "success") {
                            table.bootstrapTable('refresh',{url:'${basePath}/user/list.do'});//重载表格
                            layer.msg("删除成功！用户已移入回收站!");
                        }else {
                            layer.closeAll('loading');
                            layer.alert("删除失败",{icon:2});
                        }
                    }
                });
            },
            function (index,layero) {

            })
        }else {
            layer.closeAll('loading');
            layer.msg('请选择需要删除的用户！');
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