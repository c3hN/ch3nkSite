<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    final String url = request.getContextPath()+"/main.do";
    response.sendRedirect(response.encodeURL(url));
%>