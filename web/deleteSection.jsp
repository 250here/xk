<%@ page import="Beans.TimeSlot" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="Beans.User" %>
<%@ page import="DAO.SectionDAO" %>
<%@ page import="DAO.TeachesDAO" %>
<%@ page import="DAO.TimeSlotDAO" %>
<%@ page import="Beans.Section" %>
<%@ page import="Service.TakeSectonService" %><%--
  Created by IntelliJ IDEA.
  User: 1874442361
  Date: 2019/12/19
  Time: 19:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    User user = (User)session.getAttribute("user");
//if(!(user!=null&&user.isRoot())){
//    return;
//}
if(request.getParameter("courseid")!=null&&request.getParameter("sectionid")!=null){
    SectionDAO sectionDAO = new SectionDAO();
    TeachesDAO teachesDAO = new TeachesDAO();
    sectionDAO.deleteSection(request.getParameter("courseid"),request.getParameter("sectionid"));
    teachesDAO.delete(request.getParameter("courseid"),request.getParameter("sectionid"));
}
%>
<html>
<head>
    <link rel="stylesheet" href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://cdn.staticfile.org/jquery/2.1.1/jquery.min.js"></script>
    <script src="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <title>deletesection</title>
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
</div>


<div class="col-sm-6" >
    <form> 输入课程名：<input name = "search" type="text">
        <button id="searchSession" type="submit" value="搜索">搜索</button>
    </form>
    <table class="table table-striped">
        <caption>搜索结果:</caption>
        <thead>
        <th>课程ID</th>
        <th>课程名称</th>
        <th>任课老师</th>
        <th>上课时间</th>
        <th>考核方式</th>
        <th>学分</th>
        <th>选课人数</th>
        <th>操作</th>
        </thead>
        <%
               String partName = request.getParameter("search");

               SectionDAO sectionDAO = new SectionDAO();
                        TeachesDAO teachesDAO = new TeachesDAO();
                        TimeSlotDAO timeSlotDAO =new TimeSlotDAO();
                        ArrayList<Section> sections = sectionDAO.searchSection(partName);
//                        System.out.println(sections.size());
                        for(Section section:sections){
                            ArrayList<User> teachers = teachesDAO.getTeacherBySectionid(section.getCourseId(),section.getSectionId());
                            %>
        <tr>
            <td><%=section.getCourseId()%>.<%=section.getSectionId()%></td>
            <td><%=section.getSectionName()%></td>
            <td><%
                for(User teacher:teachers){
            %><%=teacher.name%>,
                <%
                    }%></td>
            <td><%
                ArrayList<TimeSlot> timeSlots = timeSlotDAO.getTimeSlot(section.getTimeSlotId());
                for(TimeSlot timeSlot:timeSlots){
            %>
                周<%=timeSlot.getDay()%>,<%=timeSlot.getStartTime()%>到<%=timeSlot.getEndTime()%>节<p></p>
                <%
                    }
                %></td>
            <td><%=section.getExamType()%></td>
            <td><%=section.getCredits()%></td>
            <td><%=section.getNumberOfStudent()%>/<%=section.getStudentNumberLimit()%></td>
            <td><a href="deleteSection.jsp?courseid=<%=section.getCourseId()%>&sectionid=<%=section.getSectionId()%>">删除课程</a></td>
        </tr>
            <%
                        }
        %>
</body>
</html>
