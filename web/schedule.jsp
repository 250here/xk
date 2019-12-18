<%--
  Created by IntelliJ IDEA.
  User: 1874442361
  Date: 2019/12/16
  Time: 19:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://cdn.staticfile.org/jquery/2.1.1/jquery.min.js"></script>
    <script src="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>

    <title>schedule</title>
</head>
<body>
<div class="col-sm-4">
    <ul class="nav nav-pills nav-stacked">
        <li><a href="">选课</a> </li>
        <li><a href="">查看课表</a> </li>
        <li><a href="">选课事务申请</a> </li>
    </ul>
</div>
 <div class="col-sm-6" >

    <table class="table table-striped">
        <caption>课程表</caption>
        <tr> <thead>
        <th></th>
        <th>星期日</th>
        <th>星期一</th>
        <th>星期二</th>
        <th>星期三</th>
        <th>星期四</th>
        <th>星期五</th>
        <th>星期六</th>
        </thead>
        </tr>

         <%
            for(int i =1;i<=13;i++){
        %>
        <tr><th> 第<%=i%>节</th></tr>
            <%
                }
            %>


    </table>
</div>
</body>
</html>
