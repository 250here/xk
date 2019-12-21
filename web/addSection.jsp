<%@ page import="Beans.Section" %>
<%@ page import="Beans.User" %>
<%@ page import="Beans.TimeSlot" %>
<%@ page import="DAO.*" %>
<%@ page import="Service.InsertSectionService" %><%--
  Created by IntelliJ IDEA.
  User: 1874442361
  Date: 2019/12/17
  Time: 20:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://cdn.staticfile.org/jquery/2.1.1/jquery.min.js"></script>
    <script src="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <title>addSection</title>
</head>
<body>
<div class="col-sm-2">
    <ul class="nav nav-pills nav-stacked">
        <li><a href="addSection.jsp">添加课程</a> </li>
        <li><a href="deleteSection.jsp">删除课程</a> </li>
        <li><a href="">添加上课学生</a></li>
        <li><a href="">开启选课</a></li>
    </ul>
</div>

<div class="col-sm-9">
    <form action="addSection.jsp?action=submit" method="post">
    <table class="table table-striped">
        <caption>添加课程</caption>
        <tr><thead>
        <th>课程ID</th>
        <th>课程名称</th>
        <th>任课教师工号</th>
        <th>上课地点</th>
        <th>上课教室</th>
        <th>上课时间</th>
        <th>考核方式</th>
        <th>考试地点</th>
        <th>考试教室</th>
        <th>考试时间</th>
        <th>选课人数上限</th>
        <th>操作</th>
        </thead>
        </tr>
        <tr>
            <td><input name = "id" type="text"></td>
            <td><input name = "coursename" type="text"></td>
            <td><input name = "teacherid" type="text"></td>
            <td><input name = "building" type="text"></td>
            <td><input name = "classroom" type="text"></td>
            <td>
                星期<input name="day1" type="text">第<input name="start1" type="text">节开始至第<input name="end1" type="text">节结束<p></p>
                星期<input name="day2" type="text">第<input name="start2" type="text">节开始至第<input name="end2" type="text">节结束<p></p>
                星期<input name="day3" type="text">第<input name="start3" type="text">节开始至第<input name="end3" type="text">节结束<p></p>
            </td>
            <td><input  name = "examtype" type="text"></td>
            <td> <input name = "exambuilding" type="text"></td>
            <td> <input name = "examroomnumber" type="text"></td>
            <td>星期<input name="examday" type="text">第<input name="examstart" type="text">节开始至第<input name="examend" type="text">节结束<p></p></td>
            <td><input  name = "studentnumberlimit" type="text"></td>
            <td><button type="submit" value="添加">添加</button></td>
        </tr>
    </table>
    </form>
    <%

        if("submit".equals(request.getParameter("action"))) {
            String result = InsertSectionService.insertSection(request);

            if(result!=null){
                %>
    <script type="text/javascript" language="javascript">
        alert("导入失败<%=result%>");                                            // 弹出错误信息

//        window.location='userlogin.html' ;                            // 跳转到登录界面
    </script>
    <%
            }else {
                %>
    <script type="text/javascript" language="javascript">
    alert("导入成功");
    </script>
    <%
            }
        }
    %>
</div>
</body>
</html>
