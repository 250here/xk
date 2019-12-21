<%@ page import="Beans.User" %>
<%@ page import="Service.LeadingInService" %>
<%@ page import="java.util.List" %>
<%@ page import="Beans.Section" %>
<%@ page import="DAO.TeachesDAO" %><%--
  Created by IntelliJ IDEA.
  User: dell
  Date: 2019/12/21
  Time: 13:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    User user=(User)session.getAttribute("user");
//    if(user==null||!user.isTeacher()){
//        return;
//    }
    //String teacherid=user.id;
    String teacherid="T104";
%>
<html>
<head>
    <title>Leading-in Grades</title>
</head>
<body>
<h2>导入成绩</h2>

<p>
    <%
        String action=request.getParameter("action");
        if("leadingin".equals(action)){
            String result= LeadingInService.leadingfin(request);
            if(result!=null&&result.contains("succeed")){
                out.println("成绩导入成功");
            }else{
                out.println(result);
            }
        }
        List<Section> sections=new TeachesDAO().getSectionByTeacherid(teacherid);
        if(sections.size()==0){
            out.println("你没有课，不需要导入成绩");
            return;
        }
    %>
</p>
<p>请选择课程并选择表格</p>

<form action="leadingingrade.jsp?action=leadingin" method="post" enctype="multipart/form-data">
    <label>请选择课程id：</label>
    <select id="csid" name="csid" required="required">
        <%
            for(Section section:sections){
                String ids=section.getCourseId()+"."+section.getSectionId();
        %>
        <option value="<%=ids%>"><%=ids%></option>
        <%
            }
        %>
    </select>
    <select id="type" name="type" required="required">
        <option value="grade">成绩</option>
    </select>
    <label>上传文件（仅限csv）</label>
    <input type="file" required="required" name="csvfile" accept="text/csv">
    <input type="submit" value="确认提交">
</form>
</body>
</html>
