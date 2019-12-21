<%@ page import="Beans.User" %>
<%@ page import="Service.LeadingInService" %><%--
  Created by IntelliJ IDEA.
  User: dell
  Date: 2019/12/20
  Time: 21:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    User user=(User)session.getAttribute("user");
    if(user==null||!user.isRoot()){
        return;
    }
%>
<html>
<head>
    <title>Leading-in from csv</title>
</head>
<body>
<h2>导入</h2>
<p>
<%
    String action=request.getParameter("action");
    if("leadingin".equals(action)){
        String result=LeadingInService.leadingfin(request);
        out.println(result);
    }
%>
</p>
<p>&nbsp;&nbsp;你可以选择导入以下表格</p>
<form action="leadingintables.jsp?action=leadingin" method="post" enctype="multipart/form-data">
    <label>请选择导入表的类型：</label>
    <select id="type" name="type" required="required">
    <option value="classroom">classroom</option>
    <option value="course">course</option>
    <option value="timeslot">timeslot</option>
    <option value="section">section</option>
    <option value="teacher">teacher</option>
    <option value="teaches">teaches</option>
    <option value="student">student</option>
    <option value="exam">exam</option>
    </select>

    <label>上传文件（仅限csv）</label>
    <input type="file" required="required" name="csvfile" accept="text/csv">
    <input type="submit" value="确认提交">
</form>
</body>
</html>
