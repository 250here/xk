<%@ page import="Beans.User" %>
<%@ page import="DAO.RequestDAO" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="Beans.Request" %>
<%@ page import="Beans.Section" %>
<%@ page import="DAO.SectionDAO" %>
<%@ page import="util.Encode" %>
<%@ page import="Service.TakeSectonService" %>
<%@ page import="DAO.ClassroomDAO" %><%--
  Created by IntelliJ IDEA.
  User: 1874442361
  Date: 2019/12/16
  Time: 23:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
boolean during = TakeSectonService.duringTakingSection;
%>
<html>
<head>
    <link rel="stylesheet" href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://cdn.staticfile.org/jquery/2.1.1/jquery.min.js"></script>
    <script src="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>

    <title>request</title>
</head>
<body>
<div class="col-sm-4">
    <ul class="nav nav-pills nav-stacked">
        <li><a href="studentIndex.jsp">选课</a> </li>
        <li><a href="schedule.jsp">查看课表</a> </li>
        <li><a href="request.jsp">选课事务申请</a> </li>
        <li><a href="index.jsp?action=logout">登出</a></li>
    </ul>
</div>
<div class="col-sm-6" >
    <table class="table table-striped">
    <caption>已提交选课事务申请</caption>
    <tr> <thead>
    <th>课程ID</th>
    <th>申请课程</th>
    <th>申请理由</th>
    <th>状态</th>
    </thead>
        <%
        User user = (User)session.getAttribute("user");
        RequestDAO requestDAO = new RequestDAO();
        ArrayList<Request> requests = requestDAO.getRequestByStudentId(user.id);
            for(Request request1:requests){
        %><tr>
                <td><%=request1.getCourseId()%>.<%=request1.getSectionId()%></td>
                <td><%=request1.getSectionName()%></td>
                <td><%=request1.getMessage()%></td>
                <td><%=request1.getState()%></td>
        </tr><%
            }
            %>
    </table>

    <table class="table table-striped">
        <form action="request.jsp?action=submit" method="post">
        <caption>填写选课事务申请</caption>
         <thead>
        <th>申请课程ID</th>
        <th>申请课程名称</th>
        <th>申请理由</th>
        <th>操作</th>
        </thead>
        <tr>
        <td><input name="id" type="text"></td>
        <td><input name="courseName" type="text"></td>
        <td><input name="message" type="text"></td>
        <td><%if(during==true){%><button type="submit" value="提交">提交</button><%}%></td>
    </tr>
    </form>
    </table>
    <%
        if("submit".equals(request.getParameter("action"))){
        RequestDAO requestDAO1 = new RequestDAO();
        SectionDAO sectionDAO = new SectionDAO();
        ClassroomDAO classroomDAO = new ClassroomDAO();

        String courseName = Encode.parseToUTF8(request.getParameter("courseName"));
        String courseid = "";
        String sectionid = "";

        if(request.getParameter("id").contains(".")){
            String[] id = request.getParameter("id").split("\\.");
            courseid = id[0];
            sectionid = id[1];
 }
        Section section = new Section(courseid,sectionid);
        section.setSectionName(courseName);
    String message = Encode.parseToUTF8(request.getParameter("message"));
    User student = (User)session.getAttribute("user");

    Request request1 = new Request(courseid,sectionid,student.id);
    request1.setState("handling");
    request1.setMessage(message);

    if((sectionDAO.haveSection(section)) && sectionDAO.getSectionByCourseAndSectionid(courseid,sectionid)!=null && sectionDAO.getSectionByCourseAndSectionid(courseid,sectionid).getSectionName().equals(courseName)){
    Section section1 = new Section(courseid,sectionid);

    if(requestDAO.haveRequest(request1)){
        %>
    <script type="text/javascript" language="javascript">
        alert("课程已申请");
    </script>
    <%

    }else {
        requestDAO.insertRequest(request1);
        %>
    <script type="text/javascript" language="javascript">
        alert("申请成功");
    </script>
    <%
    }
    }else{
    %>
    <script type="text/javascript" language="javascript">
        alert("课程不存在");
    </script>
    <%
    }
        }
    %>
</div>
</body>
</html>
