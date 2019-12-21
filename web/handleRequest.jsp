<%@ page import="Beans.User" %>
<%@ page import="DAO.RequestDAO" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="Beans.Request" %>
<%@ page import="DAO.TakesDAO" %>
<%@ page import="Beans.Section" %><%--
  Created by IntelliJ IDEA.
  User: 1874442361
  Date: 2019/12/17
  Time: 20:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    if(request.getParameter("handle")!=null){
    RequestDAO requestDAO = new RequestDAO();
        TakesDAO takesDAO = new TakesDAO();
    Request request1 = new Request(request.getParameter("courseid"),request.getParameter("sectionid"),request.getParameter("studentid"));
    requestDAO.updateState(request1,request.getParameter("handle"));
    if(request.getParameter("handle").equals("accept")){
        Section section = new Section(request.getParameter("courseid"),request.getParameter("sectionid"));
        takesDAO.insertSectionToTakes(request.getParameter("studentid"),section);
    }
}%>
<html>
<head>
    <link rel="stylesheet" href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://cdn.staticfile.org/jquery/2.1.1/jquery.min.js"></script>
    <script src="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <title>handleRequset</title>
</head>
<body>
<div class="col-sm-2">
    <ul class="nav nav-pills nav-stacked">
        <li><a href="teacherSchedule.jsp">查看课程表</a> </li>
        <li><a href="roster.jsp">查看花名册</a> </li>
        <li><a href="handleRequest.jsp">处理选课事务申请</a> </li>
    </ul>
</div>
<div class="col-sm-8" >
    <table class="table table-striped">
        <caption>选课事务申请</caption>
        <thead>
        <th>申请课程</th>
        <th>学号</th>
        <th>申请理由</th>
        <th>状态</th>
        <th>操作</th>
        </thead>

       <%
           User user = (User)session.getAttribute("user");

           RequestDAO requestDAO = new RequestDAO();
           ArrayList<Request> requests = requestDAO.getRequestByteacherId(user.id);
       for (Request request1:requests){
        %><tr>
        <td><%=request1.getSectionName()%></td>
        <td><%=request1.getStudentId()%></td>
        <td><%=request1.getMessage()%></td>
        <td><%=request1.getState()%></td>

            <%
        if(request1.getState().equals("handling")){
        %>
        <td><a href="handleRequest.jsp?handle=accept&courseid=<%=request1.getCourseId()%>&sectionid=<%=request1.getSectionId()%>&studentid=<%=request1.getStudentId()%>">同意</a><p></p>
            <a href="handleRequest.jsp?handle=refuse&courseid=<%=request1.getCourseId()%>&sectionid=<%=request1.getSectionId()%>&studentid=<%=request1.getStudentId()%>">拒绝</a>
        </td>
   <%
        }else{
         %>
        <td>已处理</td>
        <%
        }
       }
    %> </tr>
    </table>
</div>
</body>
</html>
