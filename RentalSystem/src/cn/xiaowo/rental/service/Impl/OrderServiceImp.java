package cn.xiaowo.rental.service.Impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import cn.xiaowo.rental.dao.OrderDao;
import cn.xiaowo.rental.domain.Money;
import cn.xiaowo.rental.domain.Order;
import cn.xiaowo.rental.domain.PageModel;
import cn.xiaowo.rental.domain.Product;
import cn.xiaowo.rental.domain.User;
import cn.xiaowo.rental.service.OrderService;
import cn.xiaowo.rental.utils.BeanFactory;
import cn.xiaowo.rental.utils.JDBCUtils;


//财务管理业务层实现
public class OrderServiceImp implements OrderService {
	
	OrderDao orderDao=(OrderDao)BeanFactory.createObject("OrderDao");
	
	@Override
	//查询处于预定房间阶段的订单
	public PageModel findAllOrders1(int curNum) throws Exception {
		 //1_创建对象
		int totalRecords=orderDao.findTotalRecords1();
		PageModel pm=new PageModel(curNum,totalRecords,5);
		//2_关联集合 select * from product limit ? , ?
		List<Order> list=orderDao.findAllProductsWithPage1(pm.getStartIndex(),pm.getPageSize());
		pm.setList(list);
		//3_关联url
		pm.setUrl("AdminOrderServlet?method=findOrders");
		return pm;
	}
	@Override
	//异步查找订单详情
	public Product findProductByOid(String pid) throws Exception {
		return orderDao.findProductByOid(pid);
		
	}
	@Override
	public PageModel findAllOrders(int curNum) throws Exception {
		 //1_创建对象
		int totalRecords=orderDao.findTotalRecords();
		PageModel pm=new PageModel(curNum,totalRecords,5);
		//2_关联集合 select * from product limit ? , ?
		List<Order> list=orderDao.findAllProductsWithPage(pm.getStartIndex(),pm.getPageSize());
		pm.setList(list);
		//3_关联url
		pm.setUrl("AdminOrderServlet?method=findOrders");
		return pm;
	
	}

	@Override
	//根据状态分页查询所有订单
	public PageModel findAllOrders(int curNum, String st) throws Exception {
		//1_创建对象
		int totalRecords=orderDao.findTotalRecords(st);
		PageModel pm=new PageModel(curNum,totalRecords,5);
		//2_关联集合 select * from product limit ? , ?
		List<Order> list=orderDao.findAllProductsWithPage(pm.getStartIndex(),pm.getPageSize(),st);
		pm.setList(list);
		//3_关联url
		pm.setUrl("AdminOrderServlet?method=findOrders");
		return pm;
	}
	
	@Override
	//根据编号查询订单
	public Order findOrderByOid(String itemid) throws Exception {
		return orderDao.findOrderByOid(itemid);
	}
	@Override
	//更新订单状态 开启事务绑定连接
	public void updateOrder(Order order, Money user1, Money user2) throws Exception {
		Connection conn=null;
		try {
			//获取连接
			conn=JDBCUtils.getConnection();
			//开启事务
			conn.setAutoCommit(false);
			//保存订单
			orderDao.updateOrder(conn,order);
			//保存资金
			orderDao.updateMoney(conn,user1);
			orderDao.updateMoney(conn,user2);
			//提交
			conn.commit();
		} catch (Exception e) {
			//回滚
			conn.rollback();
		}
		
	}
	//更新订单状态 开启事务绑定连接
	public void updateOrder(Order order) throws Exception {
			Connection conn=null;
			conn=JDBCUtils.getConnection();
			//保存订单
			orderDao.updateOrder(conn,order);
			
			
	}
	@Override
	//查询求租人财务账户
	public Money findMoneyByOid(String string) throws Exception {
		return orderDao.findMoneyByOid(string);
	}
	@Override
	//创建财务账户
	public void CreatMoney(Money user) throws Exception {
		orderDao.CreatMoney(user);
	}
	@Override
	public PageModel findMoney(int curNum) throws Exception {
		       //1_创建对象
				int totalRecords=orderDao.findTotalMoney();
				PageModel pm=new PageModel(curNum,totalRecords,5);
				//2_关联集合 select * from product limit ? , ?
				List<Money> list=orderDao.findAllMoneyWithPage1(pm.getStartIndex(),pm.getPageSize());
				pm.setList(list);
				//3_关联url
				pm.setUrl("AdminOrderServlet?method=findOrders");
				return pm;
	}
	@Override
	//查找求租人id
	public PageModel findMyOrdersWithPage(User user, int curNum) throws Exception {
		        //1_创建PageModel对象,目的:计算并且携带分页参数
				//select count(*) from orders where uid=?
				int totalRecords=orderDao.getTotalRecords(user);
				PageModel pm=new PageModel(curNum, totalRecords, 5);
				//2_关联集合  select * from orders where uid=? limit ? ,?
				List list=orderDao.findMyOrdersWithPage(user,pm.getStartIndex(),pm.getPageSize());
				pm.setList(list);
				//3_关联url
				pm.setUrl("ProductServlet?method=findMyOrdersWithPage");
				return pm;
	}

}