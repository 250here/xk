<%--
  Created by IntelliJ IDEA.
  User: 1874442361
  Date: 2019/12/16
  Time: 23:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
        <li><a href="">选课</a> </li>
        <li><a href="">查看课表</a> </li>
        <li><a href="">选课事务申请</a> </li>
    </ul>
</div>
<div class="col-sm-6" >
    <table class="table table-striped">
    <caption>已提交选课事务申请</caption>
    <tr> <thead>

    <th>申请课程</th>
    <th>申请理由</th>
    <th>状态</th>
    </thead>
        <tr></tr></table>

    <table class="table table-striped">
        <caption>填写选课事务申请</caption>
        <tr> <thead>
        <th>申请课程</th>
        <th>申请理由</th>
        <th>操作</th>
        </thead>
        <tr>
        <td><input name="msg" type="textarea"  ></td>
        <td><input name="msg" type="textarea"  ></td>
        <td><button>发送</button></td>
    </tr></table>

</div>
</body>
</html>
