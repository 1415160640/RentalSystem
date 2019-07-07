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
		
		d.add('0102','01','租赁信息管理','','','mainFrame');
		d.add('010201','0102','房屋分类信息','${pageContext.request.contextPath}/AdminCategoryServlet?method=findAllCats&num=1','','mainFrame');
		d.add('010202','0102','地区分类信息','${pageContext.request.contextPath}/AdminCategoryServlet?method=findAllArea&num=1','','mainFrame');

		d.add('0103','01','房源信息管理');
		d.add('010301','0103','可用房源','${pageContext.request.contextPath}/AdminProductServlet?method=findAllProductsWithPage&num=1','','mainFrame');
		d.add('010302','0103','不可用房源','${pageContext.request.contextPath}/AdminProductServlet?method=findNoProductsWithPage&num=1','','mainFrame');
		d.add('010303','0103','模糊查询房源','${pageContext.request.contextPath}/AdminProductServlet?method=findAllSearProductsWithPage&num=1','','mainFrame');
		
		d.add('0104','01','用户信息管理');
		d.add('010401','0104','待审核出租人信息','${pageContext.request.contextPath}/AdminUserServlert?method=findAllNoLessorWithPage&num=1','','mainFrame'); 
		d.add('010402','0104','出租人员信息管理','${pageContext.request.contextPath}/AdminUserServlert?method=findAllLessorWithPage&num=1','','mainFrame');
		d.add('010403','0104','求租人员信息管理','${pageContext.request.contextPath}/AdminUserServlert?method=findAllUserWithPage&num=1','','mainFrame');
		
		d.add('0105','01','财务信息管理');
		d.add('010502','0105','定金查询','${pageContext.request.contextPath}/AdminOrderServlet?method=findOrders&num=1&state=0','','mainFrame'); 
		d.add('010503','0105','租房确认','${pageContext.request.contextPath}/AdminOrderServlet?method=findOrders1&num=1','','mainFrame'); 
		d.add('010504','0105','所有订单管理','${pageContext.request.contextPath}/AdminOrderServlet?method=findOrders&num=1','','mainFrame');
		d.add('010505','0105','账户余额查询','${pageContext.request.contextPath}/AdminOrderServlet?method=findMoney&num=1','','mainFrame');
		
		
		document.write(d);
	   -->
	</script>
</div>	</td>
  </tr>
</table>
</body>
</html>
