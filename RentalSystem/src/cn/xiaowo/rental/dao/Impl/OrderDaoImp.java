package cn.xiaowo.rental.dao.Impl;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.DateConverter;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import cn.xiaowo.rental.dao.OrderDao;
import cn.xiaowo.rental.domain.Money;
import cn.xiaowo.rental.domain.Order;
import cn.xiaowo.rental.domain.Product;
import cn.xiaowo.rental.domain.User;
import cn.xiaowo.rental.utils.JDBCUtils;

//资金管理dao层实现类
public class OrderDaoImp implements OrderDao {

	@Override
	//限定转态下查询订单信息数
	public int findTotalRecords1() throws Exception {
		String sql="select count(*) from orderitem where state=1 or state=2";
		QueryRunner qr=new QueryRunner(JDBCUtils.getDataSource());
		Long num=(Long)qr.query(sql, new ScalarHandler());
		return num.intValue();
	}

	@Override
	//限定转态下查询订单信息集合
	public List<Order> findAllProductsWithPage1(int startIndex, int pageSize) throws Exception {
		String sql="select * from orderitem where state=1 or state=2 ORDER BY date DESC limit ? , ?";
		QueryRunner qr=new QueryRunner(JDBCUtils.getDataSource());
		return qr.query(sql, new BeanListHandler<Order>(Order.class),startIndex,pageSize);
		
	}
	@Override
	//根据用户查询房源记录
	public Product findProductByOid(String pid) throws Exception {
		String sql="select * from product where pid= ?";
		QueryRunner qr=new QueryRunner(JDBCUtils.getDataSource());
		return qr.query(sql, new BeanHandler<Product>(Product.class),pid);
	}
	@Override
	//查询订单数
	public int findTotalRecords() throws Exception {
		String sql="select count(*) from orderitem";
		QueryRunner qr=new QueryRunner(JDBCUtils.getDataSource());
		Long num=(Long)qr.query(sql, new ScalarHandler());
		return num.intValue();
	}

	@Override
	//查询订单集合
	public List<Order> findAllProductsWithPage(int startIndex, int pageSize) throws Exception {
		String sql="select * from orderitem order by date desc limit  ? , ?";
		QueryRunner qr=new QueryRunner(JDBCUtils.getDataSource());
		return qr.query(sql, new BeanListHandler<Order>(Order.class),startIndex,pageSize);
	}

	@Override
	//限定状态下的订单记录数
	public int findTotalRecords(String st) throws Exception {
		String sql="select count(*) from orderitem where state=?";
		QueryRunner qr=new QueryRunner(JDBCUtils.getDataSource());
		Long num=(Long)qr.query(sql, new ScalarHandler(),st);
		return num.intValue();
	}

	@Override
	//限定状态下的订单记录集合
	public List<Order> findAllProductsWithPage(int startIndex, int pageSize, String st) throws Exception {
		String sql="select * from orderitem where state=? order by date desc limit  ? , ?";
		QueryRunner qr=new QueryRunner(JDBCUtils.getDataSource());
		return qr.query(sql, new BeanListHandler<Order>(Order.class),st,startIndex,pageSize);
	}

	@Override
	//根据订单编号查询订单
	public Order findOrderByOid(String itemid) throws Exception {
		String sql="select * from orderitem where itemid= ?";
		QueryRunner qr=new QueryRunner(JDBCUtils.getDataSource());
		return qr.query(sql, new BeanHandler<Order>(Order.class),itemid);
	}

	@Override
	//更新订单状态
	public void updateOrder(Connection conn,Order order) throws Exception {
		String sql="UPDATE orderitem SET quantity=? ,total=? ,state= ?, pid=?,uid=?, oid =? ,date=? WHERE itemid=?";
		QueryRunner qr=new QueryRunner();
		Object[] params={order.getQuantity(),order.getTotal(),order.getState(),order.getPid(),order.getUid(),order.getOid(),order.getDate(),order.getItemid()};
		qr.update(conn,sql,params);
		
	}
	//更新用户资金数
	public void updateMoney(Connection conn,Money user) throws Exception {
			String sql="UPDATE money SET userid=? ,money=?  WHERE id=?";
			QueryRunner qr=new QueryRunner();
			Object[] params={user.getUserid(),user.getMoney(),user.getId()};
			qr.update(conn,sql,params);
			
	}

	@Override
	//用户资金查询
	public Money findMoneyByOid(String string) throws Exception {
		String sql="select * from money where userid=?";
		QueryRunner qr=new QueryRunner(JDBCUtils.getDataSource());
		return qr.query(sql, new BeanHandler<Money>(Money.class),string);
	}

	@Override
	//创建财务账户
	public void CreatMoney(Money user) throws Exception {
		String sql="INSERT INTO money VALUES(?,?,?)";
		QueryRunner qr=new QueryRunner(JDBCUtils.getDataSource());
		Object[] params={user.getId(),user.getUserid(),user.getMoney()};
		qr.update(sql,params);
	}

	@Override
	//统计财务表记录数量
	public int findTotalMoney() throws Exception {
		String sql="select count(*) from money";
		QueryRunner qr=new QueryRunner(JDBCUtils.getDataSource());
		Long num=(Long)qr.query(sql, new ScalarHandler());
		return num.intValue();
	}

	@Override
	//返回财务表集合
	public List<Money> findAllMoneyWithPage1(int startIndex, int pageSize) throws Exception {
		String sql="select * from money order by money desc limit  ? , ?";
		QueryRunner qr=new QueryRunner(JDBCUtils.getDataSource());
		return qr.query(sql, new BeanListHandler<Money>(Money.class),startIndex,pageSize);
	}

	@Override
	//根据用户查询订单数
	public int getTotalRecords(User user) throws Exception {
		String sql="select count(*) from orderitem where oid=?";
		QueryRunner qr=new QueryRunner(JDBCUtils.getDataSource());
		Long num=(Long)qr.query(sql, new ScalarHandler(),user.getUid());
		return num.intValue();
	}

	@Override
	//根据用户查询订单
	public List findMyOrdersWithPage(User user, int startIndex, int pageSize) throws Exception {
		String sql="select * from orderitem where oid=? order by date desc limit ? , ?";
		QueryRunner qr=new QueryRunner(JDBCUtils.getDataSource());
		return qr.query(sql, new BeanListHandler<Order>(Order.class),user.getUid(),startIndex,pageSize);
	}

	
	


}
