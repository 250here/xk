<%--
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
        <li><a href="">添加课程</a> </li>
        <li><a href="">删除课程</a> </li>
        <li><a href="">添加上课学生</a></li>
        <li><a href="">开启选课</a></li>
    </ul>
</div>

<div class="col-sm-9">
    <table class="table table-striped">
        <caption>课程表</caption>
        <tr><thead>
        <th>课程ID</th>
        <th>课程名称</th>
        <th>任课教师</th>
        <th>上课地点</th>
        <th>上课教室</th>
        <th>上课时间</th>
        <th>考核方式</th>
        <th>学分</th>
        <th>选课人数上限</th>
        <th>操作</th>
        </thead>
        </tr>
        <tr>
            <th><input></th>
            <th><input></th>
            <th><input></th>
            <th><input></th>
            <th><input></th>
            <th>
                星期<input><p></p>
                第<input>节开始<p>至<p>第<input>节结束
            </th>
            <th><input></th>
            <th><input></th>
            <th><input></th>
            <th><button>提交</button></th>
        </tr>
    </table>
</div>
</body>
</html>