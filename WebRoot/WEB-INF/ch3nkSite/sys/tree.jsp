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
<script src="${basePath}/static/plugins/ztree/js/jquery.ztree.all.js"></script>
<script>

    var setting = {
        data: {
            simpleData: {
                enable: true,
                idKey:"dicId",
                pIdKey:"parentId",
                rootPId:null
            },
            key:{
                name:'dicName',
                title:'dicName',
                isParent:'isParent'
            }
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
                $.ajax({
                    url:'${basePath}/dic/loadTree',
                    type:'post',
                    data:{dicId:treeNode.dicId},
                    dataType:'json',
                    error:function () {
                        console.log("请求失败");
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
<%--
1. 初始化树，只显示第一层父节点
2. 默认每个父节点下都存在子节点，根据父节点在后台查询子节点时，如果存在，则返回，无数据则返回0，页面进行判断
3. 异步加载子节点重复问题

--%>