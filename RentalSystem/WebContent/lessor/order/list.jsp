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
									<td align="center" width="13%">
										订单编号
									</td>
									<td align="center" width="3%">
										产生时间
									</td>
									<td align="center" width="3%">
										数量(天)
									</td>
									<td align="center" width="8%">
										总金额(元)
									</td>
									<td width="3%" align="center">
										状态
									</td>
									<td align="center" width="3%">
										求租人id
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
												${c.itemid}
											</td>
											<td style="CURSOR: hand; HEIGHT: 25px" align="center"
												width="3%">
												${c.date}
											</td>
											<td style="CURSOR: hand; HEIGHT: 25px" align="center"
												width="3%">
												${c.quantity}
											</td>
											<td style="CURSOR: hand; HEIGHT: 25px" align="center"
												width="8%">
												${c.total}
											</td>
											<td style="CURSOR: hand; HEIGHT: 25px" align="center"
												width="3%">
												<c:if test="${c.state ==0 }">
													已预定
												</c:if>
												<c:if test="${c.state ==1 }">
													待收房租
												</c:if>
												<c:if test="${c.state ==2 }">
													取消预定
												</c:if>
												<c:if test="${c.state ==3 }">
													<a href="/RentalSystem/LessorSevlet?method=OutMoney&itemid=${c.itemid}" style="color:blue; font-size:1.5em;">确认退房</a>
												</c:if>
												<c:if test="${c.state ==4 }">
													 完成
												</c:if>
												<c:if test="${c.state ==5 }">
													<span style="color:red">失败</span>
												</c:if>
											</td>
											<td style="CURSOR: hand; HEIGHT: 25px" align="center"
												width="3%">
												${c.oid}
											</td>
										</tr>
										<tr >
										     <td align="center" style="HEIGHT: 30px" colspan="8">
												<input type="button" value="房屋详情" id="${c.pid}" class="myClass"/>
												<table border="1" width="100%">
												</table>
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
<script>
$(function(){
	//页面加载完毕之后,获取样式名称为myClass一批元素,为期绑定点击事件
	$(".myClass").click(function(){
		//获取当前订单id
		var id=this.id;
		
		//获取当前按钮文字
		var txt=this.value;
		
		//PS:获取到当前元素的下一个对象table
		var $tb=$(this).next();
		
		if(txt=="房屋详情"){
			//向服务端发送Ajax请求,将当前的订单id传递到服务端
			var url="/RentalSystem/AdminOrderServlet";
			var obj={"method":"findOrderByOidWithAjax","pid":id};
			$.post(url,obj,function(data){
				//清除内容
				$tb.html("");
				var th="<tr><th>商品</th><th>面积</th><th>名称</th><th>单价(天)</th><th>描述</th></tr>";
				$tb.append(th);
				
				//利用JQUERY遍历响应到客户端的数据
				$.each(data,function(i,obj){
					var td="<tr><td><img src='/RentalSystem/"+obj.pimage+"' width='50px'/></td><td>"+obj.area+"</td><td>"+obj.pname+"</td><td>"+obj.price+"</td><td>"+obj.pdesc+"</td></tr>";
					$tb.append(td);				
				})
			},"json");
			this.value="关闭";
			//$(this).val("关闭");
			
		}else{
			this.value="房屋详情";
			//清空表格内容
			$tb.html("");
		}
	});
});
</script>	
</HTML>
