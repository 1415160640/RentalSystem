<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!doctype html>
<html>

	<head>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>订单管理</title>
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

				<div style="margin:0 auto; margin-top:10px;width:950px;background-color:#FFCD42;">
					<strong>我的订单</strong>
					<table class="table table-bordered">
					<c:if test="${empty page.list}">
					      <div class="row" style="width:1210px;margin:0 auto;">
        	                   <div class="col-md-12">
        			                    <h1>暂无订单信息</h1>
        		              </div>
        	               </div>	
					</c:if>
					<c:forEach items="${page.list}" var="o">
						<tbody>
							<tr class="success">
								<th colspan="5">
									订单编号:${o.itemid}
									总金额:¥${o.total}元
									<div align="right">
									<c:if test="${o.state==0}">
										<a href="${pageContext.request.contextPath}/ProductServlet?method=YesOrderByitemid&itemid=${o.itemid}">确认订房</a>
										<a href="${pageContext.request.contextPath}/ProductServlet?method=NoOrderByitemid&itemid=${o.itemid}">取消预定</a>
									</c:if>	 
									<c:if test="${o.state==1}">已确认订房</c:if>
									<c:if test="${o.state==2}">取消预定中</c:if>	 
									<c:if test="${o.state==3}">等待退房</c:if>
									<c:if test="${o.state==4}">完成的订单</c:if>	 
									<c:if test="${o.state==5}">取消的订单</c:if>	
									</div>	 
								</th>
							</tr>
							<tr class="warning">
								<th>商品id</th>
								<th>数量</th>
								<th>单价</th>
								<th>日期</th>
							</tr>
 							<tr class="active"> 
 							    <td width="20%">
									${o.pid}
 								</td>
 								<td width="10%"> 
									${o.quantity}
 								</td> 
								<td width="20%">
									￥${o.total/o.quantity}
 								</td> 
 								<td width="15%"> 
									<span class="subtotal">${o.date}</span>
								</td> 
							    </tr> 
						</tbody>
					</c:forEach>
					</table>
					<%@ include file="/jsp/pageFile.jsp" %>
				</div>
			</div>
		</div>

	</body>

</html>