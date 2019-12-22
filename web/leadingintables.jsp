<%@ page import="Beans.User" %>
<%@ page import="Service.LeadingInService" %>
<%@ page import="Service.TakeSectonService" %><%--
  Created by IntelliJ IDEA.
  User: dell
  Date: 2019/12/20
  Time: 21:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    boolean during = TakeSectonService.duringTakingSection;
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
<div class="col-sm-2">
    <ul class="nav nav-pills nav-stacked">
        <li><a href="leadingintables.jsp">导入</a></li>
        <li><a href="addSection.jsp">添加课程</a> </li>
        <li><a href="deleteSection.jsp">删除课程</a> </li>
        <li><a href="updateSection.jsp">修改课程信息</a></li>
        <li><%if(TakeSectonService.duringTakingSection ==false){

        %><a href="addSection.jsp?change=open">开启选课</a><%
        }else {
        %><a href="addSection.jsp?change=close">关闭选课</a>
            <%}%>
        </li>
        <li><a href="index.jsp?action=logout">登出</a></li>
    </ul>
</div
<div class="col-sm-9">
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
</div>
</body>
</html>
