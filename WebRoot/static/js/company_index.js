$(function () {

    table = $("#company").bootstrapTable({
        url:basePath+'/company/info/query.do',
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
            {checkbox:true},
            {title:'序号',formatter:'numFormatter', align:'center',width:'30'},
            {field:'fullName',title:'公司名称',align:'center',width:'100'},
            {field:'shortName',title:'公司简称',align:'center',width:'100'},
            {field:'enName',title:'英文名称',align:'center',width:'100'},
            {field:'code',title:'公司代号',align:'center',width:'100'},
            {field:'type',title:'公司类型',align:'center',width:'100'},
            {field:'scale',title:'公司规模',align:'center',width:'100'},
            {field:'createDate',title:'成立时间',align:'center',width:'100'},
            {title:'操作',events:'userOperateEvents',formatter:'operateFormatter',align:'center',width:'150'}
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
            '<shiro:hasPermission name="user:detail"><button class="btn btn-default btn-xs" id="userDetail">查看</button> </shiro:hasPermission>',
            '<shiro:hasPermission name="user:addRoles"><button class="btn btn-default btn-xs" id="userAddRoles">分配角色</button> </shiro:hasPermission>',
            '<shiro:hasPermission name="user:edit"><button class="btn btn-default btn-xs" id="editUser">编辑</button> </shiro:hasPermission>',
            '<shiro:hasPermission name="user:abandon"><button class="btn btn-default btn-xs btn-danger" id="abandonUser">删除</button></shiro:hasPermission>'
        ].join('');
    }
    window.userOperateEvents = {
        "click #userDetail":function (e,value,row,index) {
            $(location).prop('href', basePath+'/user/operate.do?op=detail&userId='+row.userId);
        },
        "click #userAddRoles":function (e,value,row,index) {
            $(location).prop('href', basePath+'/user/operate.do?op=addRoles&userId='+row.userId);
        },
        "click #editUser":function (e,value, row, index) {
            $(location).prop('href', basePath+'/user/operate.do?op=edit&userId='+row.userId);
        },
        "click #abandonUser":function (e,value, row, index) {
            layer.confirm('确定删除该用户?',{btn:['确定','取消'],icon:3},
                function (index,layero) {
                    $.post(basePath+"/user/userAbandon.do",{"userId":row.userId},function (data) {
                        if (data.msg == "success") {
                            table.bootstrapTable('refresh',{url:basePath+'/user/list.do?deptId='+row.department.deptId});//重载表格
                            layer.msg("用户已移入回收站！");
                        }else if (data.msg == "error"){
                            layer.alert("删除失败",{icon:2});
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
        var likeAccount = $("input[name='likeAccount']").val();
        var likeNickName = $("input[name='likeNickName']").val();
        var likeCreateTime = $("input[name='likeCreateTime']").val();
        if (likeAccount != '' || likeNickName != '' || likeCreateTime != '') {
            table.bootstrapTable('refresh',{url:basePath+'/user/list.do?likeAccount='+likeAccount+'&likeNickName='+likeNickName+'&likeCreateTime='+likeCreateTime});
        }else {
            layer.alert('请输入搜索条件！',{icon:2});
        }
    });
    //重置
    $("#tableResetBtn").click(function () {
        $("input[name='likeAccount']").val("");
        $("input[name='likeNickName']").val("");
        $("input[name='likeCreateTime']").val("");
        table.bootstrapTable('refresh',{url:basePath+'/user/list.do'});
    });
    //新增
    $("#addBtn").click(function () {
        $(location).prop("href",basePath+"/company/view/add.do");
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
                        url:basePath+'/user/userAbandonBatch.do',
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
    //时间控件
    $('#datetimepicker').datetimepicker({
        format: 'yyyy-mm-dd',
        language:'zh-CN',
        autoclose:true,
        todayHighlight:true,
        minView:'2'
    });
});