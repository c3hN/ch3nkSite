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
    <ul id="treeDemo" class="ztree"></ul>
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
            }
        },
        async:{
            enable:true,
            type:"post",
            url:"${basePath}/dic/init.do",
            dataType:"json",
            autoParam:["dicId","parentId"]
        },
        callback:{
            onAsyncSuccess:function (event, treeId, treeNode, msg) {
                console.log(event)
                console.log(treeId)
                console.log(treeNode)
                console.log(msg)
            }
        }
    };

   /* var zNodes =[
        { id:1, pId:0, name:"父节点1", open:true},
        { id:11, pId:1, name:"父节点11 - 折叠"},
        { id:111, pId:11, name:"叶子节点111"},
        { id:112, pId:11, name:"叶子节点112"},
        { id:113, pId:11, name:"叶子节点113"},
        { id:114, pId:11, name:"叶子节点114"},
        { id:12, pId:1, name:"父节点12 - 折叠"},
        { id:121, pId:12, name:"叶子节点121"},
        { id:122, pId:12, name:"叶子节点122"},
        { id:123, pId:12, name:"叶子节点123"},
        { id:124, pId:12, name:"叶子节点124"},
        { id:13, pId:1, name:"父节点13 - 没有子节点", isParent:true},
        { id:2, pId:0, name:"父节点2 - 折叠"},
        { id:21, pId:2, name:"父节点21 - 展开", open:true},
        { id:211, pId:21, name:"叶子节点211"},
        { id:212, pId:21, name:"叶子节点212"},
        { id:213, pId:21, name:"叶子节点213"},
        { id:214, pId:21, name:"叶子节点214"},
        { id:22, pId:2, name:"父节点22 - 折叠"},
        { id:221, pId:22, name:"叶子节点221"},
        { id:222, pId:22, name:"叶子节点222"},
        { id:223, pId:22, name:"叶子节点223"},
        { id:224, pId:22, name:"叶子节点224"},
        { id:23, pId:2, name:"父节点23 - 折叠"},
        { id:231, pId:23, name:"叶子节点231"},
        { id:232, pId:23, name:"叶子节点232"},
        { id:233, pId:23, name:"叶子节点233"},
        { id:234, pId:23, name:"叶子节点234"},
        { id:3, pId:0, name:"父节点3 - 没有子节点", isParent:true}
    ];*/

    $(document).ready(function(){
        $.fn.zTree.init($("#treeDemo"), setting, null);
    });
</script>
</body>
</html>
