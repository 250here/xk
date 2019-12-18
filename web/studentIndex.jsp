<%--
  Created by IntelliJ IDEA.
  User: 1874442361
  Date: 2019/12/16
  Time: 19:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://cdn.staticfile.org/jquery/2.1.1/jquery.min.js"></script>
    <script src="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <title>studentIndex</title>
</head>
<body>

               <div class="col-sm-6">
                <ul class="nav nav-pills nav-stacked">
                    <li><a href="">选课</a> </li>
                    <li><a href="">查看课表</a> </li>
                    <li><a href="">选课事务申请</a> </li>
                </ul>
            </div>
            <div class="col-sm-6" >
                <form> 输入课程名：<input id="" type="text">
                    <button id="searchSession" type="submit" value="搜索">搜索</button>
                </form>
                <table class="table table-striped">
                    <caption>搜索结果:</caption>
                    <thead>
                    <th>课程名称</th>
                    <th>任课老师</th>
                    <th>上课时间</th>
                    <th>考核方式</th>
                    <th>学分</th>
                    <th>选课人数</th>
                    <th>操作</th>
                    </thead>
<%--                                        <%--%>
<%--                                            for(int i =0; i<requests.size();i++){--%>
<%--                                                User requester=requests.get(i);--%>
<%--                                        %>--%>
                    <%--                    <tr>--%>
                    <%--                        <td><%=requester.getUsername()%></td>--%>
                    <%--                        <%--%>
                    <%--                            if(true){--%>
                    <%--                        %>--%>
                    <%--                    <%--%>
                    <%--                            }--%>
                    <%--                        }--%>
                    <%--                    %>--%>
                </table>
            </div>



</body>
</html>
