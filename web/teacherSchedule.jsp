<%@ page import="Beans.User" %>
<%@ page import="DAO.TeachesDAO" %>
<%@ page import="DAO.SectionDAO" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="Beans.Section" %>
<%@ page import="Beans.TimeSlot" %>
<%@ page import="DAO.TimeSlotDAO" %><%--
  Created by IntelliJ IDEA.
  User: 1874442361
  Date: 2019/12/17
  Time: 20:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>teacherSchedule</title>
    <link rel="stylesheet" href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://cdn.staticfile.org/jquery/2.1.1/jquery.min.js"></script>
    <script src="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
<div class="col-sm-4">
    <ul class="nav nav-pills nav-stacked">
        <li><a href="teacherSchedule.jsp">查看课程表</a> </li>
        <li><a href="roster.jsp">查看花名册</a> </li>
        <li><a href="handleRequest.jsp">处理选课事务申请</a> </li>
        <li><a href="index.jsp?action=logout">登出</a></li>
    </ul>
</div>
<div class="col-sm-6" >

    <table class="table table-striped">
        <caption>课程表</caption>
        <tr> <thead>
        <th>课程ID</th>
        <th>课程名称</th>
        <th>上课时间</th>
        <th>上课地点</th>
        <th>学分</th>
        <th>选课人数</th>
        <th>考核方式</th>
        </thead>
        </tr>

        <%
            User user = (User)session.getAttribute("user");
            TeachesDAO teachesDAO = new TeachesDAO();
            SectionDAO sectionDAO = new SectionDAO();
            TimeSlotDAO timeSlotDAO = new TimeSlotDAO();

            ArrayList<Section> sections = teachesDAO.getSectionByTeacherid(user.id);
            for(Section section:sections){
        %>
       <tr>
           <td><%=section.getCourseId()+"."+section.getSectionId()%></td>
           <td><%=section.getSectionName()%></td>
           <td><%
               ArrayList<TimeSlot> timeSlots = timeSlotDAO.getTimeSlot(section.getTimeSlotId());
           for(TimeSlot timeSlot:timeSlots){
           %>
           周<%=timeSlot.getDay()%>第<%=timeSlot.getStartTime()%>节到第<%=timeSlot.getEndTime()%>节<p></p>
           <%
           }
           %></td>
           <td><%=section.getBuilding()%><%=section.getRoomNumber()%></td>
           <td><%=section.getCredits()%></td>
           <td><%=section.getNumberOfStudent()%>/<%=section.getStudentNumberLimit()%></td>
           <td><%=section.getExamType()%></td>
       </tr>
        <%
            }
        %>


    </table>
</div>
</body>
</html>
