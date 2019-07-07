<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>     
<HTML>
	<HEAD>
		<meta http-equiv="Content-Language" content="zh-cn">
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link href="${pageContext.request.contextPath}/css/Style1.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript"  src="${pageContext.request.contextPath}/js/public.js"></script>
		<script type="text/javascript">
			function addCategory(){
				window.location.href = "${pageContext.request.contextPath}/AdminCategoryServlet?method=addCategoryUI";
			}
		</script>
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
									<td align="center" width="13%">
										用户id
									</td>
									<td align="center" width="3%">
										姓名
									</td>
									<td align="center" width="8%">
										联系电话
									</td>
									<td align="center" width="3%">
										性别
									</td>
									<td align="center" width="10%">
										出生日期
									</td>
									<td align="center" width="13%">
										身份证号
									</td>
									<td align="center" width="13%">
										单位/住址
									</td>
									<td width="10%" align="center">
										信息审核
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
												${c.username}
											</td>
											<td style="CURSOR: hand; HEIGHT: 25px" align="center"
												width="3%">
												${c.name}
											</td>
											<td style="CURSOR: hand; HEIGHT: 25px" align="center"
												width="8%">
												${c.telephone}
											</td>
											<td style="CURSOR: hand; HEIGHT: 25px" align="center"
												width="3%">
												${c.sex}
											</td>
											<td style="CURSOR: hand; HEIGHT: 25px" align="center"
												width="10%">
												${c.birthday}
											</td>
											<td style="CURSOR: hand; HEIGHT: 25px" align="center"
												width="13%">
												${c.id}
											</td>
											<td style="CURSOR: hand; HEIGHT: 25px" align="center"
												width="13%">
												${c.address}
											</td>
											<td align="center" style="HEIGHT: 25px">
												<a href="${pageContext.request.contextPath}/AdminUserServlert?method=ManagerLessor&uid=${c.uid}">
													<img src="${pageContext.request.contextPath}/img/admin/i_ok.gif" width="16" height="16" border="0" style="CURSOR: hand">
												</a>
											</td>
										</tr>
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
