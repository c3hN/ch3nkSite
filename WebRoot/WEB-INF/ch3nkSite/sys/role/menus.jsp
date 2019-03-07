<%--
  Created by IntelliJ IDEA.
  User: chenkai
  Time: 14:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="${basePath}/static/plugins/ztree/css/metroStyle/metroStyle.css">
    <link rel="stylesheet" href="${basePath}/static/plugins/bootstrap/css/bootstrap.min.css">
</head>
<body>
<div style="height: 280px;padding: 10px;margin-bottom: 10px;overflow-y: auto;">
    <ul class="ztree" id="menus"/>
</div>
<div style=" text-align:center;">
    <button class="btn btn-primary btn-sm" id="saveBtn">确定</button>
    <button class="btn btn btn-default btn-sm" id="close">取消</button>
</div>

<script src="${basePath}/static/js/jquery-3.2.1.js"></script>
<script src="${basePath}/static/plugins/bootstrap/js/bootstrap.min.js"></script>
<script src="${basePath}/static/plugins/ztree/js/jquery.ztree.core.min.js"></script>
<script src="${basePath}/static/plugins/ztree/js/jquery.ztree.excheck.min.js"></script>
<script src="${basePath}/static/plugins/layer/layer.js"></script>
<script>
    var setting = {
        data:{
            simpleData:{
                enable:true,
                idKey:"menuId",
                pIdKey:"parentId",
                rootPid:null
            },
            key:{
                name:"name",
                title:"name"
            }
        },
        check:{
            checkboxType:{"N":"ps","N":"ps"},
            enable:true
        }
    };
    var treeObj = $.fn.zTree.init($("#menus"), setting,  ${nodes});
    treeObj.expandAll(true);
    initChecked();





    $("#saveBtn").click(function () {
        var roleId = '${roleId}';
        var menuIds = new Array();
        var url = '${basePath}/role/info/addMenus.do';
        var checkedNodes = treeObj.getCheckedNodes();
        for(var i = 0,j = checkedNodes.length; i < j; i++){
            menuIds.push(checkedNodes[i].menuId);
        }
        $.ajax({
            url:url,
            type:'post',
            data:{'roleId':roleId,'menuIds':menuIds.join(',')},
            success:function(resp) {
                if(resp.status == '0') {
                    layer.alert(resp.msg,{icon:1},function () {
                        var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
                        parent.layer.close(index);
                    });
                }else {
                    layer.alert(resp.msg,{icon:2},function () {
                        var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
                        parent.layer.close(index);
                    });
                }

            },
            error:function (resp) {
                layer.alert(resp.msg);
            }
        });
    });

    //关闭弹窗
    $("#close").click(function () {
        var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
        parent.layer.close(index);
    });

    function initChecked() {
        checkedNodes = JSON.parse('${checked}');
        var treeNodes = treeObj.transformToArray(treeObj.getNodes());
        for(var i=0;i<checkedNodes.length;i++) {
            for (var j=0;j<treeNodes.length;j++) {
                if (treeNodes[j].menuId == checkedNodes[i]) {
                    treeObj.checkNode(treeNodes[j],true);
                    continue
                }
            }
        }
    }
</script>
</body>
</html>
