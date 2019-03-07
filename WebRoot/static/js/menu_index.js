$(function () {
    initTable();
})
function initTable() {
    jQuery("#menu_table").jqGrid( {
        url :basePath+'/menu/info/query.do',//组件创建完成之后请求数据的url
        datatype : "json",//请求数据返回的类型。可选json,xml,txt
        treeGrid:true,
        // treeGridModel: 'adjacency',
        ExpandColumn:'name',
        ExpandColClick:true,
        // treeGridModel:'adjacency',
        height:'auto',
        autowidth:true,
        pager:false,
        hoverrows:false,
        sortable:false,
        // pagerpos:'center',
        // viewrecords:true,
        prmNames:{nd:null,search:null},
        // colNames:["menuId","名称", "类型","代码", "路径","创建人","创建日期","修改人","修改日期","修改次数"],
        colModel:[
            {label:'menuId',name:'menuId',index:'menuId', width:1,hidden:true,key:true},
            {label:'名称',name:'name',index:'name', width:180},
            {label:'类型',name:'type',index:'type', width:80, align:"center"},
            {label:'代码',name:'code',index:'code', width:80, align:"center"},
            {label:'路径',name:'href',index:'href', width:80, align:"center"},
            {label:'创建人',name:'createBy.account',index:'createBy', width:80,align:"center"},
            {label:'创建日期',name:'createDate',index:'createDate', width:80,align:"center"},
            {label:'修改人',name:'modifyBy.account',index:'modifyBy', width:80,align:"center"},
            {label:'修改日期',name:'modifyDate',index:'modifyDate', width:80,align:"center"},
            {label:'修改次数',name:'modifyNum',index:'modifyNum', width:80,align:"center"},
            {label:'操作',name:'',index:'operate', width:200,align:"center",
                formatter:function(value, grid, rows, state) {
                    return '<button class="btn btn-primary btn-xs" onclick="detail(\''+ rows.menuId+ '\');">详情</button> '+
                    '<button class="btn btn-primary btn-xs" onclick="addOperate(\''+ rows.menuId+ '\');">添加操作</button> '+
                    '<button class="btn btn-primary btn-xs" onclick="edit(\''+ rows.menuId+ '\');">编辑</button> '+
                    '<button class="btn btn-danger btn-xs" onclick="abandon(\''+ rows.menuId+'\');">删除</button>';
                }
            }
        ],
        treeReader:{
            level_field: "level",
            parent_id_field: "parentId",
            leaf_field: "isLeaf",
            expanded_field: "expanded"
        },
        rowNum : 10,//一页显示多少条
        rowList : [ 10, 20, 30 ],//可供用户选择一页显示多少条
        pager : '#pager',//表格页脚的占位符(一般是div)的id
        sortname : 'menuId',//初始化的时候排序的字段
        sortorder : "desc",//排序方式,可选desc,asc
        mtype : "post",//向后台请求数据的ajax的类型。可选post,get
        viewrecords : true,
        // caption : "系统菜单"//表格的标题名字
    });
    /*创建jqGrid的操作按钮容器*/
    /*可以控制界面上增删改查的按钮是否显示*/
    // jQuery("#menu_table").jqGrid('navGrid', '#pager', {edit : false,add : false,del : false,search:false,reflash:false});

    window.detail = function (menuId) {
        $.post(
            basePath+'/menu/info/checkOperate.do',
            {'menuId':menuId},
            function (resp) {
                if (resp.status == '0') {
                    layer.open({
                        type: 2,
                        title: '查看权限',
                        shadeClose: true,
                        // shade: 0.8,
                        area: ['380px', '380px'],
                        content: basePath+'/menu/info/queryOperations.do?menuId='+menuId //iframe的url
                    });
                } else {
                    layer.alert(resp.msg,{'icon':2})
                }
            })
    };
    window.edit =function (menuId) {
        $(location).prop('href',basePath+'/menu/view/edit.do?menuId='+menuId);
    };
    window.add = function() {
        $(location).prop('href', basePath+'/menu/view/add.do');
    }
    window.abandon = function(menuId) {
        layer.confirm('确认删除？',{btn:['确定','取消']},
            function (index, layero) {
                $.post(
                    basePath+'/menu/info/delete.do?',
                    {'menuId':menuId},
                    function (resp) {
                        if (resp.status == '0') {
                            $(location).prop('href',basePath+'/menu/view/index.do')
                        }else {
                            layer.alert(resp.msg,{'icon':2},function () {
                                layer.closeAll();
                            })
                        }
                    });
            },
            function (index) {
                layer.close(index);
            });
    }
    window.addOperate = function (menuId) {
        layer.open({
            type: 2,
            skin: 'layui-layer-rim', //加上边框
            area: ['420px', '280px'], //宽高
            content: basePath+'/menu/view/addOperate.do?menuId='+menuId
        });
    }
    
};