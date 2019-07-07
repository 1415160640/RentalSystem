<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>     
<HTML>
	<HEAD>
		<meta http-equiv="Content-Language" content="zh-cn">
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link href="${pageContext.request.contextPath}/css/Style1.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript"  src="${pageContext.request.contextPath}/js/public.js"></script>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css" type="text/css" />
		<script src="${pageContext.request.contextPath}/js/jquery-1.11.3.min.js" type="text/javascript"></script>
		<script src="${pageContext.request.contextPath}/js/bootstrap.min.js" type="text/javascript"></script>
	</HEAD>
	<body>
		<br>
			<table cellSpacing="1" cellPadding="0" width="100%" align="center" bgColor="#f5fafe" border="0">
				<TBODY>
					<c:if test="${empty page.list}">	
					<tr>			
						  <h1 align="center">暂无数据</h1>	  
					</tr>
					</c:if>
					<c:if test="${not empty page.list}">
					<tr>
						<td class="ta_01" align="center" bgColor="#f5fafe">
							<table cellspacing="0" cellpadding="1" rules="all"
								bordercolor="gray" border="1" id="DataGrid1"
								style="BORDER-RIGHT: gray 1px solid; BORDER-TOP: gray 1px solid; BORDER-LEFT: gray 1px solid; WIDTH: 100%; WORD-BREAK: break-all; BORDER-BOTTOM: gray 1px solid; BORDER-COLLAPSE: collapse; BACKGROUND-COLOR: #f5fafe; WORD-WRAP: break-word">
								<tr
									style="FONT-WEIGHT: bold; FONT-SIZE: 12pt; HEIGHT: 25px; BACKGROUND-COLOR: #afd1f3">

									<td align="center" width="3%">
										序号
									</td>
									<td align="center" width="10%">
										账户编号
									</td>
									<td align="center" width="10%">
										余额
									</td>
								</tr>
								<!--
									varStatus:代表循环过程中存储临时状态值
									 status.count:当前输出元素个数  
								 -->
								<c:forEach items="${page.list}" var="c" varStatus="status">
										<tr onmouseover="this.style.backgroundColor = 'white'"
											onmouseout="this.style.backgroundColor = '#F5FAFE';">
											<td style="CURSOR: hand; HEIGHT: 25px" align="center"
												width="3%">
												${status.count}
											</td>
											<td style="CURSOR: hand; HEIGHT: 25px" align="center"
												width="13%">
												${c.userid}
											</td>
											<td style="CURSOR: hand; HEIGHT: 25px" align="center"
												width="3%">
												${c.money}
											</td>
								</c:forEach>
							</table>
						</td>
					</tr>
					</c:if>
				</TBODY>
			</table>
			<c:if test="${not empty page.list}">
			<%@include file="/jsp/pageFile.jsp" %>
			</c:if>
	</body>	
</HTML>
