<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!doctype html>
<html>

	<head>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>房源详情信息</title>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css" type="text/css" />
		<script src="${pageContext.request.contextPath}/js/jquery-1.11.3.min.js" type="text/javascript"></script>
		<script src="${pageContext.request.contextPath}/js/bootstrap.min.js" type="text/javascript"></script>
		<!-- 引入自定义css文件 style.css -->
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" type="text/css"/>

		<style>
			body {
				margin-top: 20px;
				margin: 0 auto;
			}
			
			.carousel-inner .item img {
				width: 100%;
				height: 300px;
			}
		</style>
	</head>

	<body>

		 <%@include file="/jsp/header.jsp" %>

		<div class="container">
			<div class="row">
				<div style="margin:0 auto;width:950px;">
					<div class="col-md-6">
						<img style="opacity: 1;width:400px;height:350px;" title="" class="medium" src="${pageContext.request.contextPath}/${product.pimage}">
					</div>
					
                    <form id="myForm" method="post" action="${pageContext.request.contextPath}/ProductServlet?method=addOrder">
					<div class="col-md-6">
						<div><strong>${product.pname}</strong></div>
                        <div style="margin:10px 0 10px 0;">面积:<strong style="color:#ef0101;">：${product.area}平米</strong></div>
						<div style="margin:10px 0 10px 0;">单价:<strong style="color:#ef0101;">￥：${product.price}元/天</strong></div> 
						<div style="margin:10px 0 10px 0;">出租人:<strong style="color:#ef0101;">${lessor.name}</strong> </div>
						<div style="margin:10px 0 10px 0;">联系电话:<strong style="color:#ef0101;">${lessor.telephone}</strong> </div>
						<div style="margin:10px 0 10px 0;">email:<strong style="color:#ef0101;">${lessor.email}</strong> </div>
						<div style="margin:10px 0 10px 0;">单位:<strong style="color:#ef0101;">${lessor.address}</strong> </div>
						<div style="padding:10px;border:1px solid #e7dbb1;width:330px;margin:15px 0 10px 0;;background-color: #fffee6;">

							<div style="border-bottom: 1px solid #faeac7;margin-top:20px;padding-left: 10px;">&nbsp;&nbsp;&nbsp;&nbsp;预定天数:
								<!-- 向服务端发送 购买数量-->
								<input id="quantity" name="quantity" value="1" maxlength="4" size="10" type="text"> </div>
								<!-- 向服务端发送 商品pid-->
								<input type="hidden" name="pid" value="${product.pid}"/>
								<input type="hidden" name="uid" value="${product.uid}"/>
								<input type="hidden" name="price" value="${product.price}"/>

							<div style="margin:20px 0 10px 0;;text-align: center;">
								<a href="javascript:void(0)">
									<input id="btnId" style="color:blue;" value="预定房间" type="button">
								</a>&nbsp; &nbsp; &nbsp; &nbsp;&nbsp;
							</div>
						</div>
					</div>
					</form>
				</div>
				<div class="clear"></div>
				<div style="width:950px;margin:0 auto;">
					<div style="background-color:#d3d3d3;width:930px;padding:10px 10px;margin:10px 0 10px 0;">
						<strong>商品介绍</strong>
						<h3>${product.pdesc}</h3>
					</div>

					
				</div>

			</div>
		</div>
	</body>
<script>
$(function(){
	$("#btnId").click(function(){
		var formObj=document.getElementById("myForm");
		//formObj.action="/store/CartServlet";
		//formObj.method="get";
		formObj.submit();
	});
});
</script>
</html>