package cn.xiaowo.rental.dao.Impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import cn.xiaowo.rental.dao.LessorDao;
import cn.xiaowo.rental.domain.Admin;
import cn.xiaowo.rental.domain.Order;
import cn.xiaowo.rental.domain.Product;
import cn.xiaowo.rental.domain.lessor;
import cn.xiaowo.rental.utils.JDBCUtils;
//出租人dao层实现
public class LessorDaoImpl implements LessorDao{

	@Override
	//数据库查询管理员信息
	public lessor LessorUserLogin(lessor user) throws Exception {
		String sql="select * from lessor where state=1 and username=?  and password= ?";
		QueryRunner qr=new QueryRunner(JDBCUtils.getDataSource());
		return qr.query(sql, new BeanHandler<lessor>(lessor.class),user.getUsername(),user.getPassword());
	}

	@Override
	//查询房源信息数
	public int findTotalRecords(String uid) throws Exception {
		String sql="select count(*) from product where uid =?";
		QueryRunner qr=new QueryRunner(JDBCUtils.getDataSource());
		Long num=(Long)qr.query(sql, new ScalarHandler(),uid);
		return num.intValue();
	}

	@Override
	//查询房源集合
	public List<Product> findAllProductsWithPage(int startIndex, int pageSize,String uid) throws Exception {
		String sql="select * from product where uid=? limit ? , ?";
		QueryRunner qr=new QueryRunner(JDBCUtils.getDataSource());
		return qr.query(sql, new BeanListHandler<Product>(Product.class),uid,startIndex,pageSize);
	}

	@Override
	//查询订单记录数
	public int findAllTotalRecords(String uid) throws Exception {
		String sql="select count(*) from orderitem where uid=?";
		QueryRunner qr=new QueryRunner(JDBCUtils.getDataSource());
		Long num=(Long)qr.query(sql, new ScalarHandler(),uid);
		return num.intValue();
	}

	@Override
	//查询订单集合
	public List<Order> findAllProductsWithPage1(int startIndex, int pageSize,String uid) throws Exception {
		String sql="select * from orderitem where uid=? order by date desc limit  ? , ?";
		QueryRunner qr=new QueryRunner(JDBCUtils.getDataSource());
		return qr.query(sql, new BeanListHandler<Order>(Order.class),uid,startIndex,pageSize);
	}

	@Override
	//限定状态下查询订单数
	public int findAllTotalRecords(String st,String uid) throws Exception {
		String sql="select count(*) from orderitem where state=? and uid=?";
		QueryRunner qr=new QueryRunner(JDBCUtils.getDataSource());
		Long num=(Long)qr.query(sql, new ScalarHandler(),st,uid);
		return num.intValue();
	}

	@Override
	//限定状态下的订单集合
	public List<Order> findAllProductsWithPage1(int startIndex, int pageSize, String st,String uid) throws Exception {
		String sql="select * from orderitem where state=? and uid=? order by date desc limit  ? , ?";
		QueryRunner qr=new QueryRunner(JDBCUtils.getDataSource());
		return qr.query(sql, new BeanListHandler<Order>(Order.class),st,uid,startIndex,pageSize);
	}

}
