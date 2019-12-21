<%@ page import="Beans.User" %>
<%@ page import="DAO.TakesDAO" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="Beans.Section" %>
<%@ page import="Beans.TimeSlot" %>
<%@ page import="DAO.TimeSlotDAO" %>
<%@ page import="Beans.Classroom" %>
<%@ page import="DAO.ClassroomDAO" %>
<%@ page import="DAO.ExamDAO" %>
<%@ page import="Service.TakeSectonService" %><%--
  Created by IntelliJ IDEA.
  User: 1874442361
  Date: 2019/12/16
  Time: 19:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    boolean during = TakeSectonService.duringTakingSection;
    User user = (User)session.getAttribute("user");
//    User user = new User("S001");
    TakesDAO takesDAO = new TakesDAO();
    TimeSlotDAO timeSlotDAO = new TimeSlotDAO();
    if(request.getParameter("courseid")!=null&&!request.getParameter("courseid").equals("")){
        Section section =new Section(request.getParameter("courseid"),request.getParameter("sectionid"));
        if(takesDAO.hadTakes(section.getCourseId(),section.getSectionId(),user.id)){
        takesDAO.deleteSectionFromTakes(user.id,section);
%>
        <script type="text/javascript" language="javascript">
            alert("退课成功");
        </script>
<%
    }
    }
%>
<html>
<head>
    <link rel="stylesheet" href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://cdn.staticfile.org/jquery/2.1.1/jquery.min.js"></script>
    <script src="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>

    <title>schedule</title>
</head>
<body>
<div class="col-sm-2">
    <ul class="nav nav-pills nav-stacked">
        <li><a href="studentIndex.jsp">选课</a> </li>
        <li><a href="schedule.jsp">查看课表</a> </li>
        <li><a href="request.jsp">选课事务申请</a> </li>
        <li><a href="index.jsp?action=logout">登出</a></li>
    </ul>
</div>
 <div class="col-sm-8" >
    <table class="table table-striped">
        <caption>课程表</caption>
        <tr> <thead>
        <th></th>
        <th>星期一</th>
        <th>星期二</th>
        <th>星期三</th>
        <th>星期四</th>
        <th>星期五</th>
        <th>星期六</th>
        <th>星期日</th>
        </thead>
        </tr>

         <%

             ArrayList<Section> sections = takesDAO.getSectionByStudentId(user.id);
            for(int i =1;i<=13;i++){
        %>
        <tr>
            <td> 第<%=i%>节</td>
            <%
                for(int j = 1;j<=7;j++ ){
            for(Section section:sections){
                ArrayList<TimeSlot> timeSlots = timeSlotDAO.getTimeSlot(section.getTimeSlotId());
                for (TimeSlot timeSlot:timeSlots){
                    if(timeSlot.getStartTime()==i&&timeSlot.getDay()==j){
                    int last = timeSlot.getEndTime()-timeSlot.getStartTime()+1;
                    %>
            <td rowspan=<%=last%>>
                <%=section.getSectionName()%>
                <p></p>
                <%=section.getBuilding()%>
                <%=section.getRoomNumber()%>
            </td>
            <%}
            }
                }
            }
                }
            %>
        </tr>



    </table>

     <table class="table table-striped">
         <caption>已选课程</caption>
          <thead>
         <th>课程ID</th>
         <th>课程名称</th>
         <th>上课地点</th>
         <th>选课人数</th>
         <th>考试方式</th>
         <th>考试时间</th>
         <th>考试地点</th>
         <th>学分</th>
         <th>成绩</th>
         <th>操作</th>
         </thead>
         <%
             ExamDAO examDAO = new ExamDAO();

             for(Section section:sections){
         %>
         <tr>

             <td><%=section.getCourseId()%>.<%=section.getSectionId()%></td>
             <td><%=section.getSectionName()%></td>
             <td><%=section.getBuilding()%> <%=section.getRoomNumber()%></td>
             <td><%=section.getNumberOfStudent()%>/<%=section.getStudentNumberLimit()%></td>
             <td><%=section.getExamType()%></td>
             <td><%
             TimeSlot t = examDAO.getExamTime(section.getCourseId(),section.getSectionId());
             Classroom classroom = examDAO.getExamRoom(section.getCourseId(),section.getSectionId());
             %>星期<%=t.getDay()%>,第<%=t.getStartTime()%>节到第<%=t.getEndTime()%>节</td>
             <td><%=classroom.getBuilding()%>,<%=classroom.getRoomnumber()%></td>
             <td><%=section.getCredits()%></td>
             <td><%=takesDAO.getGrade(section.getCourseId(),section.getSectionId(),user.id)%></td>
             <td><%if(during==true){%><a href="schedule.jsp?courseid=<%=section.getCourseId()%>&sectionid=<%=section.getSectionId()%>">退课</a><%}%></td>
         </tr> <%
                }
            %>

     </table>
</div>
</body>
</html>
