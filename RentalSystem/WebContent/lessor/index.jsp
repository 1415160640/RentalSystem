<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>出租人管理系统</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<%-- 

<link href="${pageContext.request.contextPath }/css/general.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath }/css/main.css" rel="stylesheet" type="text/css" />
--%>

<style type="text/css">
body {
  color: white;
}
</style>
</head>
<body style="background: #278296; background-image:url(${pageContext.request.contextPath}/img/lessor.jpg);">
<center> <div style="color:red;"><h3>${msg}</h3></div></center>
<form method="post" action="${pageContext.request.contextPath }/LessorSevlet?method=userLogin" target="_parent" name='theForm' onsubmit="return validate()">
  <table cellspacing="0" cellpadding="0" style="margin-top: 100px" align="center">
  <tr>
    <td style="padding-left: 50px">
      <table>
      <tr>
        <td>出租人账号：</td>
        <td><input type="text" name="username" /></td>
      </tr>
      <tr>
        <td>出租人密码：</td>
        <td><input type="password" name="password" /></td>
      </tr>
      <tr><td><input type="submit" value="进入管理中心" class="button" /></td><td><center><a href="${pageContext.request.contextPath}/UserServlet?method=LessorRegistUI" style="color:red;" >出租人注册</a><center></td></tr>
      </table>
    </td>
  </tr>
  </table>
</form>
<script language="JavaScript">

</script>
</body>