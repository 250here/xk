<%@ page import="Service.AccountService" %>
<%@ page import="Beans.User" %>
<%@ page import="DAO.TeachesDAO" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="sun.swing.SwingUtilities2" %>
<%@ page import="Beans.Section" %>
<%@ page import="DAO.TakesDAO" %>
<%@ page import="DAO.StudentDAO" %><%--
  Created by IntelliJ IDEA.
  User: 1874442361
  Date: 2019/12/17
  Time: 20:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://cdn.staticfile.org/jquery/2.1.1/jquery.min.js"></script>
    <script src="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <title>roster</title>
</head>
<body>
<div class="col-sm-4">
    <ul class="nav nav-pills nav-stacked">
        <li><a href="">查看课程表</a> </li>
        <li><a href="">查看花名册</a> </li>
        <li><a href="">处理选课事务申请</a> </li>
        <li><a href="index.jsp?action=logout">登出</a></li>
    </ul>
</div>
<div class="col-sm-6" >
    <table class="table table-striped">
        <caption>花名册</caption>
        <tr> <thead>
        <th>课程代码</th>
        <th>课程名称</th>
        <th>学号</th>
        <th>姓名</th>
        </thead>
        <%
        User user = (User)session.getAttribute("user");
                TeachesDAO teachesDAO = new TeachesDAO();
                ArrayList<Section> sections = teachesDAO.getSectionByTeacherid(user.id);
                TakesDAO takesDAO = new TakesDAO();
                StudentDAO studentDAO = new StudentDAO();

                for(Section section:sections){
                    ArrayList<User> students = takesDAO.getStudentsBySectionid(section.getCourseId(),section.getSectionId()); //返回的student只有id属性
                    for (User stu:students){
                        User student = studentDAO.getUserByStudenetID(stu.id);
                        %>
        <tr>
            <td><%=section.getSectionId()%></td>
            <td><%=section.getSectionName()%></td>
            <td><%=student.id%></td>
            <td><%=student.name%></td>
        </tr>
                <%
                    }
                }
                %>
        </table>
</div>
</body>
</html>
