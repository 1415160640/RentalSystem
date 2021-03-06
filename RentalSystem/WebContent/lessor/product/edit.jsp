<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<HTML>
	<HEAD>
		<meta http-equiv="Content-Language" content="zh-cn">
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<LINK href="${pageContext.request.contextPath}/css/Style1.css" type="text/css" rel="stylesheet">
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css" type="text/css" />
		<script src="${pageContext.request.contextPath}/js/jquery-1.11.3.min.js" type="text/javascript"></script>
		<script src="${pageContext.request.contextPath}/js/bootstrap.min.js" type="text/javascript"></script>
	</HEAD>
<script type="text/javascript">
	function SumPrice() {
		//1. 获取输入框的内容
		var aid = $("#aid").val();
		var area = $("#area").val();
		//2. 发送请求
		$.post("LessorSevlet?method=SumPrice" , {"aid":aid,"area":area} , function(data , status){
				$("#span01").html("<font color='red'>建议售价"+data+"(天)</font>");
		} );
    }
</script>
	
	
	<body>
		<!--  -->
		<form id="userAction_save_do" name="Form1" action="${pageContext.request.contextPath}/LessorSevlet?method=editProduct" method="post" enctype="multipart/form-data">
			&nbsp;
			<table cellSpacing="1" cellPadding="5" width="100%" align="center" bgColor="#eeeeee" style="border: 1px solid #8ba7e3" border="0">
				<tr>
					<td class="ta_01" align="center" bgColor="#afd1f3" colSpan="4"
						height="26">
						<STRONG>编辑商品</STRONG>
					</td>
				</tr>
				<tr>
					<td width="18%" align="center" bgColor="#f5fafe" class="ta_01">
						商品名称：
					</td>
					<td class="ta_01" bgColor="#ffffff">
					    <input type="hidden" name="pid" value="${product.pid}" />
					    <input type="hidden" name="pflag" value="${product.pflag}" />
					    <input type="hidden" name="pimage" value="${product.pimage}" />
						<input type="text" name="pname" value="${product.pname}" id="userAction_save_do_logonName" class="bg"/>
					</td>
					<td width="18%" align="center" bgColor="#f5fafe" class="ta_01">
						是否热门
					</td>
					<td class="ta_01" bgColor="#ffffff">
						<select name="is_hot">
							<option value="1" <c:if test="${product.is_hot == 1}">selected</c:if>>是</option>
							<option value="0" <c:if test="${product.is_hot == 0}">selected</c:if>>否</option>
						</select>
					</td>
				</tr>
				<tr>
					<td width="18%" align="center" bgColor="#f5fafe" class="ta_01">
						房源面积（平米）：
					</td>
					<td class="ta_01" bgColor="#ffffff">
						<input type="text" name="area" value="${product.area}"  id="area" class="bg"/>
					</td>
					<td width="18%" align="center" bgColor="#f5fafe" class="ta_01">
						房屋分类：
					</td>
					<td class="ta_01" bgColor="#ffffff" colspan="3">
						<select name="cid">
								<c:forEach items="${category}" var="c">
								   <option value="${c.cid}" <c:if test="${product.cid == c.cid}">selected</c:if>>${c.cname}</option>
							    </c:forEach>
						</select>
					</td>
				</tr>
				<tr>
					<td width="18%" align="center" bgColor="#f5fafe" class="ta_01">
						地区分类：
					</td>
					<td class="ta_01" bgColor="#ffffff" colspan="3">
						<select name="aid" id="aid" onblur="SumPrice()">
								<c:forEach items="${area}" var="c">
								   <option value="${c.aid}" <c:if test="${product.aid == c.aid}">selected</c:if>>${c.aname}</option>
							    </c:forEach>
						</select>
						<span id="span01"></span>
					</td>
				</tr>
				<tr>
					<td width="18%" align="center" bgColor="#f5fafe" class="ta_01">
						房租（元/天）：
					</td>
					<td class="ta_01" bgColor="#ffffff">
						<input type="text" name="price" value="${product.price}" id="userAction_save_do_logonName" class="bg" />
					</td>
				</tr>
				<tr>
					<td width="18%" align="center" bgColor="#f5fafe" class="ta_01">
						商品描述：
					</td>
					<td class="ta_01" bgColor="#ffffff" colspan="3">
						<textarea name="pdesc" rows="5" cols="30" >${product.pdesc}</textarea>
					</td>
				</tr>
				<tr>
				    <td style="CURSOR: hand; HEIGHT: 22px" align="center" width="10%">
						<img width="380" height="300" src="${ pageContext.request.contextPath }/${product.pimage}">
				    </td>
				</tr>
				<tr>
					<td width="18%" align="center" bgColor="#f5fafe" class="ta_01">
						商品图片：
					</td>
					<td class="ta_01" bgColor="#ffffff" colspan="3">
						<input type="file" name="pimages" id="pimages" value="" />
					</td>
				</tr>
				<tr>
					<td class="ta_01" style="WIDTH: 100%" align="center"
						bgColor="#f5fafe" colSpan="4">
						<button type="submit" id="userAction_save_do_submit" value="确定" class="button_ok">
							&#30830;&#23450;
						</button>

						<FONT face="宋体">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</FONT>
						<button type="reset" value="重置" class="button_cancel">&#37325;&#32622;</button>

						<FONT face="宋体">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</FONT>
						<INPUT class="button_ok" type="button" onclick="history.go(-1)" value="返回"/>
						<span id="Label1"></span>
					</td>
				</tr>
			</table>
		</form>
	</body>
</HTML>