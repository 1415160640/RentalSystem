<%@ page language="java" pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>菜单</title>
<link href="${pageContext.request.contextPath}/css/left.css" rel="stylesheet" type="text/css"/>
<link rel="StyleSheet" href="${pageContext.request.contextPath}/css/dtree.css" type="text/css" />
</head>
<body>
<table width="100" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td height="12"></td>
  </tr>
</table>
<table width="100%" border="0">
  <tr>
    <td>
<div class="dtree">

	<a href="javascript: d.openAll();">展开所有</a> | <a href="javascript: d.closeAll();">关闭所有</a>
	
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/dtree.js"></script>
	<script type="text/javascript">
	  <!--   
		d = new dTree('d');
		d.add('01',-1,'系统管理');
		
		d.add('0102','01','房源管理','','','mainFrame');
		d.add('010201','0102','我的房源','${pageContext.request.contextPath}/LessorSevlet?method=findAllProductsWithPage&num=1','','mainFrame');
		d.add('010202','0102','上传房源','${pageContext.request.contextPath}/LessorSevlet?method=addProductUI','','mainFrame');

		d.add('0103','01','订单处理');
		d.add('010301','0103','预定房源','${pageContext.request.contextPath}/LessorSevlet?method=findOrders&num=1&state=0','','mainFrame');
		d.add('010302','0103','退房处理','${pageContext.request.contextPath}/LessorSevlet?method=findOrders&num=1&state=3','','mainFrame');
		d.add('010303','0103','我的订单','${pageContext.request.contextPath}/LessorSevlet?method=findOrders&num=1','','mainFrame');
		
		document.write(d);
	   -->
	</script>
</div>	</td>
  </tr>
</table>
</body>
</html>
