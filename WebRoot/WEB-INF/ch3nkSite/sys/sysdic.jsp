<%--
  Created by IntelliJ IDEA.
  User: chenkai
  Date: 2018/7/2
  Time: 20:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>系统数据字典</title>
    <link rel="stylesheet" href="${basePath}/static/plugins/ztree/css/metroStyle/metroStyle.css">
</head>
<body>
    <div>
        <ul id="sysDic"></ul>
    </div>

<script src="${basePath}/static/plugins/ztree/js/jquery.ztree.core.js"></script>
<script>

    var setting = {
        async:{
            enable:true,
            url:'${basePath}/dic/list',
            type:'post',
            autoParam:['dicId']
        },
        simpleDate:{
            enable:true,
            idKey:'dicId',
            pIdKey:'parentId',
        }

    }
</script>
</body>
</html>
