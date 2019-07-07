<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>     
<HTML>
	<HEAD>
		<meta http-equiv="Content-Language" content="zh-cn">
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link href="${pageContext.request.contextPath}/css/Style1.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/public.js"></script>
	</HEAD>
	<body>
		<br>
		<form id="Form1" name="Form1" action="${pageContext.request.contextPath}/AdminProductServlet?method=findSearProductsWithPage" method="post">
			<table cellSpacing="1" cellPadding="0" width="100%" align="center" bgColor="#f5fafe" border="0">
				<TBODY>
					<tr>
						<td class="ta_01" align="center" bgColor="#afd1f3">
							<strong>条件查询房源信息列表</strong>
						</TD>
					</tr>	
		        	<tr >
				          <td class="ta_01" align="center" bgColor="#f5fafe">
					          按出租人查询: &nbsp;<input type="text" name="pdes"/>
					       &nbsp; &nbsp; &nbsp;
					          房源类型:&nbsp;
					          <select name="cid">
					             <option value="">请选择</option>
								 <c:forEach items="${category}" var="c">
								   <option value="${c.cid}">${c.cname}</option>
							     </c:forEach>
						       </select>
					          &nbsp; &nbsp; &nbsp;
					        房源地区: &nbsp;
					         <select name="aid">
					            <option value="">请选择</option>
								<c:forEach items="${area}" var="c">
								   <option value="${c.aid}">${c.aname}</option>
							    </c:forEach>
						     </select>
					          &nbsp;&nbsp; &nbsp;
					         <input type="submit" value="查询">
				        </td>
			        </tr>
					<tr>
						<td class="ta_01" align="center" bgColor="#f5fafe">
							<table cellspacing="0" cellpadding="1" rules="all"
								bordercolor="gray" border="1" id="DataGrid1"
								style="BORDER-RIGHT: gray 1px solid; BORDER-TOP: gray 1px solid; BORDER-LEFT: gray 1px solid; WIDTH: 100%; WORD-BREAK: break-all; BORDER-BOTTOM: gray 1px solid; BORDER-COLLAPSE: collapse; BACKGROUND-COLOR: #f5fafe; WORD-WRAP: break-word">
								<tr
									style="FONT-WEIGHT: bold; FONT-SIZE: 12pt; HEIGHT: 25px; BACKGROUND-COLOR: #afd1f3">

									<td align="center" width="5%">
										序号
									</td>
									<td align="center" width="10%">
										房源图片
									</td>
									<td align="center" width="5%">
										房源名称
									</td>
									<td align="center" width="5%">
										房源面积（平米）
									</td>
									<td align="center" width="5%">
										房租（天）
									</td>
									<td align="center" width="17%">
										房源描述
									</td>
									<td align="center" width="3%">
										是否热门
									</td>
									<td align="center" width="3%">
										状态
									</td>
									<td width="5%" align="center">
										编辑房源
									</td>
									<td width="5%" align="center">
										设为可用
									</td>
									<td width="5%" align="center">
										设为不可用
									</td>
								</tr>
								<c:forEach items="${products}" var="p" varStatus="status">
										<tr onmouseover="this.style.backgroundColor = 'white'"
											onmouseout="this.style.backgroundColor = '#F5FAFE';">
											<td style="CURSOR: hand; HEIGHT: 22px" align="center"
												width="5%">
												${ status.count }
											</td>
											<td style="CURSOR: hand; HEIGHT: 22px" align="center"
												width="10%">
												<img width="150" height="90" src="${ pageContext.request.contextPath }/${p.pimage}">
											</td>
											<td style="CURSOR: hand; HEIGHT: 22px" align="center"
												width="5%">
												${ p.pname }
											</td>
											<td style="CURSOR: hand; HEIGHT: 22px" align="center"
												width="5%">
												${ p.area }
											</td>
											<td style="CURSOR: hand; HEIGHT: 22px" align="center"
												width="5%">
												${ p.price }
											</td>
											<td style="CURSOR: hand; HEIGHT: 22px" align="center"
												width="17%">
												${ p.pdesc }
											</td>
											<td style="CURSOR: hand; HEIGHT: 22px" align="center"
												width="3%">
												<c:if test="${p.is_hot !=0 }">
													是
												</c:if>
												<c:if test="${p.is_hot !=1 }">
													否
												</c:if>
											</td>
											<td style="CURSOR: hand; HEIGHT: 22px" align="center" width="3%">
												<c:if test="${p.pflag ==0 }">
													不可用
												</c:if>
												<c:if test="${p.pflag ==1 }">
													可用
												</c:if>
												<c:if test="${p.pflag ==2 }">
													已出租
												</c:if>
												<c:if test="${p.pflag ==3 }">
													已预定
												</c:if>
												<c:if test="${p.pflag ==4 }">
													线下已出租
												</c:if>
											</td>
											<td align="center" style="HEIGHT: 22px">
												<a href="${pageContext.request.contextPath}/AdminProductServlet?method=updateProduct&pid=${p.pid}">
													<img src="${pageContext.request.contextPath}/img/admin/i_edit.gif" border="0" style="CURSOR: hand">
												</a>
											</td>
											<td align="center" style="HEIGHT: 22px">
											    <c:if test="${p.pflag ==0 }">
												<a href="${pageContext.request.contextPath}/AdminProductServlet?method=pushUpProduct&pid=${p.pid}">
													<img src="${pageContext.request.contextPath}/img/admin/i_ok.gif" width="16" height="16" border="0" style="CURSOR: hand">
												</a>
												</c:if>
											</td>
											<td align="center" style="HEIGHT: 22px">
											    <c:if test="${p.pflag ==1 }">
												<a href="${pageContext.request.contextPath}/AdminProductServlet?method=pushDownProduct&pid=${p.pid}">
													<img src="${pageContext.request.contextPath}/img/admin/i_del.gif" width="16" height="16" border="0" style="CURSOR: hand">
												</a>
												</c:if>
											</td>
										</tr>
									</c:forEach>
							</table>
						</td>
					</tr>
				</TBODY>
			</table>
		</form>
	</body>
</HTML>

