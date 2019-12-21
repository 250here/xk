<%@ page import="Service.InsertSectionService" %>
<%@ page import="Service.UpdateSectionService" %><%--
  Created by IntelliJ IDEA.
  User: 1874442361
  Date: 2019/12/21
  Time: 15:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://cdn.staticfile.org/jquery/2.1.1/jquery.min.js"></script>
    <script src="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <title>updateSection</title>
</head>
<body>
<div class="col-sm-2">
    <ul class="nav nav-pills nav-stacked">
        <li><a href="addSection.jsp">添加课程</a> </li>
        <li><a href="deleteSection.jsp">删除课程</a> </li>
        <li><a href="updateSection.jsp">修改课程信息</a></li>
        <li><a href="">开启选课</a></li>
        <li><a href="index.jsp?action=logout">登出</a></li>
    </ul>
</div>


<div class="col-sm-9">
    <form action="updateSection.jsp?action=submit" method="post">
        <table class="table table-striped">
            <caption>修改课程信息</caption>
            <thead>
            <th>课程ID</th>
            <th>上课地点</th>
            <th>上课教室</th>
            <th>课程人数上限</th>
            <th>操作</th>
            </thead>
            <tr>
                <td><input name="id",type="text"></td>
                <td><input name="building",type="text"></td>
                <td><input name="roomnumber",type="text"></td>
                <td><input name="numberlimit",type="text"></td>
                <td><button type="submit">确认</button> </td>
            </tr>

        </table>
    </form>
    <%
        if("submit".equals(request.getParameter("action"))) {
            String result = UpdateSectionService.updateSection(request);
            %>
    <script type="text/javascript" language="javascript">
        alert("<%=result%>");                                            // 弹出错误信息
    </script>
    <%
        }
    %>
</div>
</body>
</html>
