<%@ page import="Beans.Section" %>
<%@ page import="DAO.SectionDAO" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="DAO.TeachesDAO" %>
<%@ page import="Beans.User" %>
<%@ page import="DAO.TimeSlotDAO" %>
<%@ page import="Beans.TimeSlot" %>
<%@ page import="DAO.TakesDAO" %>
<%@ page import="Service.TakeSectonService" %>
<%@ page import="util.Table2SQLTable.TableCheck" %><%--
  Created by IntelliJ IDEA.
  User: 1874442361
  Date: 2019/12/16
  Time: 19:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    boolean durng = TakeSectonService.duringTakingSection;
    User user = (User) request.getSession().getAttribute("user");
    String s="";
//    User user = new User("S001");
    if(request.getParameter("ac")!=null&&!request.getParameter("ac").equals("")){
    TakesDAO takesDAO = new TakesDAO();
    String courseid = request.getParameter("courseid");
    String sectionid = request.getParameter("sectionid");
    if(request.getParameter("ac").equals("take")){

        Section section =new Section(courseid,sectionid);
        if(!takesDAO.hadTakes(courseid,sectionid,user.id)){
        s = takesDAO.insertSectionToTakes(user.id,section);

        if(s.equals("选课成功")){
            s="";
            if(!TableCheck.checkStudentTime(user.id)){
                s+="上课时间冲突.";
            }
            if(!TableCheck.checkStudentExamTime(user.id)){
                s+="考试时间冲突.";
            }
            if(s.equals("")){
                s="选课成功";
            }else{
                takesDAO.deleteSectionFromTakes(user.id,section);
            }
        %>
<%
    }else {
s="选课失败";
    }
    }
    }
    if(request.getParameter("ac").equals("drop")){
        Section section =new Section(courseid,sectionid);
        if(takesDAO.hadTakes(courseid,sectionid,user.id)){
        takesDAO.deleteSectionFromTakes(user.id,section);
        %>
        <script type="text/javascript" language="javascript">
            alert("退课成功");
        </script>
<%
    }
    }
}
%>
<html>
<head>
    <link rel="stylesheet" href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://cdn.staticfile.org/jquery/2.1.1/jquery.min.js"></script>
    <script src="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <title>studentIndex</title>
</head>
<body>
<script type="text/javascript" language="javascript">
    alert(<%=s%>);
</script>

               <div class="col-sm-2">
                <ul class="nav nav-pills nav-stacked">
                    <li><a href="studentIndex.jsp">选课</a> </li>
                    <li><a href="schedule.jsp">查看课表</a> </li>
                    <li><a href="request.jsp">选课事务申请</a> </li>
                    <li><a href="index.jsp?action=logout">登出</a></li>
                </ul>
            </div>
            <div class="col-sm-6" >
                <form> 输入课程名：<input name = "search" type="text">
                    <button type="submit" value="搜索">搜索</button>
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
                        TakesDAO takesDAO = new TakesDAO();
                        ArrayList<Section> sections = sectionDAO.searchSection(partName);
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
                            ArrayList<Beans.TimeSlot> timeSlots = timeSlotDAO.getTimeSlot(section.getTimeSlotId());
                            for(TimeSlot timeSlot:timeSlots){
                                %>
                            周<%=timeSlot.getDay()%>,<%=timeSlot.getStartTime()%>到<%=timeSlot.getEndTime()%>节<p></p>
                            <%
                            }
                        %></td>
                        <td><%=section.getExamType()%></td>
                        <td><%=section.getCredits()%></td>
                        <td><%=section.getNumberOfStudent()%>/<%=section.getStudentNumberLimit()%></td>
                        <td>
                            <%
                                if(durng==true){
                                    //out.print(section);out.print(user);
                            if(takesDAO.hadTakes(section.getCourseId(),section.getSectionId(),user.id)){
                                %>
                            <a href="studentIndex.jsp?ac=drop&courseid=<%=section.getCourseId()%>&sectionid=<%=section.getSectionId()%>">退课</a>
                            <%
                            }else {
                                %>
                            <a href="studentIndex.jsp?ac=take&courseid=<%=section.getCourseId()%>&sectionid=<%=section.getSectionId()%>">选课</a>
                            <%
                            }
                                }
                            %>

                        </td> </tr>
                    <%
                        }
                    %>
                </table>
            </div>



</body>
</html>
