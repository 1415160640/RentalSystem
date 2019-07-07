<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!doctype html>
<html>
<head>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>用户登录</title>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css" type="text/css" />
		<script src="${pageContext.request.contextPath}/js/jquery-1.11.3.min.js" type="text/javascript"></script>
		<script src="${pageContext.request.contextPath}/js/bootstrap.min.js" type="text/javascript"></script>
		<!-- 引入自定义css文件 style.css -->
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" type="text/css"/>

<style>
  body{
   margin-top:20px;
   margin:0 auto;
 }
 .carousel-inner .item img{
	 width:100%;
	 height:300px;
 }
 .container .row div{ 
	 /* position:relative;
	 float:left; */
 }
 
font {
    color: #666;
    font-size: 22px;
    font-weight: normal;
    padding-right:17px;
}
 </style>
</head>
<body>
	
	
	 <%@include file="/jsp/header.jsp" %>
	
	
	
	
	
<div class="container"  style="width:100%;height:800px;;background: url('${pageContext.request.contextPath}/img/loginbg.jpg') ;">
<div class="row"> 
	<div class="col-md-5">
	</div>
	
	<div class="col-md-5" >
		<div style="width:440px;border:1px solid #E7E7E7;padding:20px 0 20px 30px;border-radius:5px;margin-top:60px;background:#fff;">
				<font>登录</font>
				<div>${msg}</div>
         <form class="form-horizontal" action="${pageContext.request.contextPath}/UserServlet?method=userLogin" method="post">
               <div class="form-group">
                       <label for="username" class="col-sm-2 control-label">用户名</label>
                      <div class="col-sm-6">
                          <input type="text" name="username" class="form-control" id="username" placeholder="请输入用户名">
                      </div>
             </div>
              <div class="form-group">
                    <label for="inputPassword3" class="col-sm-2 control-label">密码</label>
                    <div class="col-sm-6">
                          <input type="password" name="password" class="form-control" id="inputPassword3" placeholder="请输入密码">
                    </div>
              </div>
              <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10">
                 </div>
             </div>
             <div class="form-group">
                 <div class="col-sm-offset-2 col-sm-10">
                 <input type="submit"  width="100" value="登录" name="submit" 
                       style="background-color:#7F97D3;
                        height:35px;width:100px;color:white;">
                 </div>
            </div>
         </form>
         </div>			
	</div>
</div>
</div>	
</body>
</html>