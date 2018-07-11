<%--
  Created by IntelliJ IDEA.
  User: chenkai
  Date: 2018/7/3
  Time: 22:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>tree</title>
    <link rel="stylesheet" href="${basePath}/static/plugins/ztree/css/metroStyle/metroStyle.css" type="text/css">
</head>
<body>
<div>
    <ul id="dicTree" class="ztree"></ul>
</div>

<script src="${basePath}/static/js/jquery-3.2.1.js"></script>
<script src="${basePath}/static/plugins/ztree/js/jquery.ztree.core.js"></script>
<script>

    var setting = {
        data: {
            simpleData: {
                enable: true,
                idKey:"dicId",
                pIdKey:"parentId",
                rootPId:"000"
            },
            key:{
                name:'dicName',
                title:'dicName'
            }
        },
        view:{
            showLine:true,
            showIcon:true
        },
        async:{
            enable:true,
            type:"post",
            url:"${basePath}/dic/loadTree",
            dataType:"json",
            autoParam:["dicId"],
        },
        callback:{
            onClick:function (event, treeId, treeNode, clickFlag) {
                alert(treeNode.level);
                $.ajax({
                    url:'${basePath}/dic/loadTree',
                    type:'post',
                    data:{dicId:treeNode.dicId},
                    dataType:'json',
                    error:function () {
                        alert("请求失败,请重试")
                    },
                    success:function (data) {
                        var treeObj = $.fn.zTree.getZTreeObj("dicTree");

                        treeObj.addNodes(treeNode,data);
                        treeObj.reAsyncChildNodes(treeNode,'refresh');

                    }
                })
            }
        }
    };

    $(document).ready(function(){
        $.fn.zTree.init($("#dicTree"), setting, null);     //初始化字典树

    });

</script>
</body>
</html>