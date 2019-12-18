<%--
  Created by IntelliJ IDEA.
  User: 1874442361
  Date: 2019/12/17
  Time: 20:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
        <li><a href="">查看课程表</a> </li>
        <li><a href="">查看花名册</a> </li>
        <li><a href="">处理选课事务申请</a> </li>
    </ul>
</div>
<div class="col-sm-8" >
    <table class="table table-striped">
        <caption>选课事务申请</caption>
        <tr> <thead>
        <th>申请课程</th>
        <th>学号</th>
        <th>申请理由</th>
        <th>状态</th>
        <th>操作</th>
        </thead>

        <tr>
        <th></th>
        <th></th>
        <th></th>
        <th></th>
        <th>
            <button>同意</button><p></p>
            <button>拒绝</button>
        </th>
    </tr>
    </table>
</div>
</body>
</html>
