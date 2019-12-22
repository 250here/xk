<%@ page import="Beans.User" %>
<%@ page import="util.PermissionUtil" %>
<%@ page import="Service.AccountService" %><%--
  Created by IntelliJ IDEA.
  User: dell
  Date: 2019/12/7
  Time: 18:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
//  if(!PermissionUtil.checkLogin(request,response)){
//    return;
//  }
  AccountService accountService=new AccountService();
  User user=(User)request.getSession().getAttribute("user");
  String action=request.getParameter("action");
  if("logout".equals(action)){
      user=null;
      accountService.Logout(request);
  }
%>
<html>
  <head>
    <title>Index-Login</title>
  </head>
  <body>
  <%
    if("login".equals(action)){
      String id=request.getParameter("id");
      String pw=request.getParameter("password");
      if(accountService.Login(id,pw,request)){
        //user=(User)request.getSession().getAttribute("user");
        accountService.RedirectToCorrectPage(request,response);
        return;
      }else{
        out.println("学工号或密码错误");
      }
    }
    //if(PermissionUtil.checkLogin(request,response)){
    if(user!=null){
  %>
  hello,<%=user.name%>. click <a href="index.jsp?action=logout">here</a> to logout.
  <%
    }
  %>
<form action="index.jsp?action=login" method="post">
  <label>用户ID</label><input type="text" name="id">
  <label>密码</label><input type="password" name="password">
  <input type="submit" value="login">
</form>
  </body>
</html>
