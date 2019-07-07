package cn.xiaowo.rental.dao.Impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import cn.xiaowo.rental.dao.ProductDao;
import cn.xiaowo.rental.domain.Order;
import cn.xiaowo.rental.domain.Product;
import cn.xiaowo.rental.domain.User;
import cn.xiaowo.rental.domain.lessor;
import cn.xiaowo.rental.utils.JDBCUtils;
import cn.xiaowo.rental.utils.TextUtils;


//房源信息dao层实现
public class ProductDaoImp implements ProductDao {

	@Override
	//限定分类下的房源信息
	public Product findProductByPid(String pid) throws Exception {
		String sql="select * from product where pid=?";
		QueryRunner qr=new QueryRunner(JDBCUtils.getDataSource());
		return qr.query(sql, new BeanHandler<Product>(Product.class),pid);
	}

	@Override
	//查询热门房源
	public List<Product> findHots() throws Exception {
		String sql="SELECT * FROM product WHERE pflag=1 AND is_hot=1  LIMIT 0 ,9 ";
		QueryRunner qr=new QueryRunner(JDBCUtils.getDataSource());
		return qr.query(sql, new BeanListHandler<Product>(Product.class));
	}

	@Override
      //产生推荐房源
	public List<Product> findNews() throws Exception {
		String sql="SELECT * FROM product WHERE pflag=1  LIMIT 0 , 9 ";
		QueryRunner qr=new QueryRunner(JDBCUtils.getDataSource());
		return qr.query(sql, new BeanListHandler<Product>(Product.class));
		
	}

	@Override
	//限定分类下的房源信息数
	public int findTotalRecords(String cid) throws Exception {
		String sql="select count(*) from product where pflag=1 and cid =?";
		QueryRunner qr=new QueryRunner(JDBCUtils.getDataSource());
		Long num=(Long)qr.query(sql, new ScalarHandler(),cid);
		return num.intValue();
	}

	@Override
	//限定分类下的房源信息集合
	public List findProductsByCidWithPage(String cid, int startIndex, int pageSize) throws Exception {
		String sql="select * from product where pflag=1 and cid=? limit ? , ?";
		QueryRunner qr=new QueryRunner(JDBCUtils.getDataSource());
		return qr.query(sql, new BeanListHandler<Product>(Product.class),cid,startIndex,pageSize);
	}

	@Override
	//查询所有可用房源数集合
	public List<Product> findAllProductsWithPage(int startIndex, int pageSize) throws Exception{
		String sql="select * from product where pflag=1  limit  ? , ?";
		QueryRunner qr=new QueryRunner(JDBCUtils.getDataSource());
		return qr.query(sql, new BeanListHandler<Product>(Product.class),startIndex,pageSize);
	}
	@Override
	//查询所有不可用房源数的集合
	public List<Product> findNoProductsWithPage(int startIndex, int pageSize) throws Exception{
		String sql="select * from product where pflag!=1  limit  ? , ?";
		QueryRunner qr=new QueryRunner(JDBCUtils.getDataSource());
		return qr.query(sql, new BeanListHandler<Product>(Product.class),startIndex,pageSize);
	}

	@Override
	//查询所有可用房源记录数
	public int findTotalRecords() throws Exception {
		String sql="select count(*) from product where pflag=1";
		QueryRunner qr=new QueryRunner(JDBCUtils.getDataSource());
		Long num=(Long)qr.query(sql, new ScalarHandler());
		return num.intValue();
	}
	@Override
	//查询所有不可用房源记录数
	public int findNoTotalRecords() throws Exception {
		String sql="select count(*) from product where pflag!=1";
		QueryRunner qr=new QueryRunner(JDBCUtils.getDataSource());
		Long num=(Long)qr.query(sql, new ScalarHandler());
		return num.intValue();
	}

	@Override
	//插入房源信息
	public void saveProduct(Product product) throws Exception {
		String sql="INSERT INTO product VALUES(?,?,?,?,?,?,?,?,?,?,?)";
		QueryRunner qr=new QueryRunner(JDBCUtils.getDataSource());
		Object[] params={product.getPid(),product.getPname(),product.getArea(),product.getPrice(),product.getPimage(),product.getIs_hot(),product.getPdesc(),product.getPflag(),product.getAid(),product.getCid(),product.getUid()};
		qr.update(sql,params);
	}


	@Override
	//更新房源信息
	public void updateProduct(Product product) throws Exception {
		String sql="update product set pname=?,area=?,price=?,pimage=?,is_hot=?,pdesc=?,pflag=?,aid=?,cid=?,uid=? WHERE pid=?";
		QueryRunner qr=new QueryRunner(JDBCUtils.getDataSource());
		Object[] params={product.getPname(),product.getArea(),product.getPrice(),product.getPimage(),product.getIs_hot(),product.getPdesc(),product.getPflag(),product.getAid(),product.getCid(),product.getUid(),product.getPid()};
		qr.update(sql,params);
	}

	@Override
	//删除房源信息
	public void deleteProductDao(String pid) throws Exception {
		String sql="delete from product where pid=?";
		QueryRunner qr=new QueryRunner(JDBCUtils.getDataSource());
		qr.update(sql,pid);
	}

	@Override
	//查询所有房源信息总和
	public List<Product> findAllSearProductsWithPage(int startIndex, int pageSize)
			throws Exception {
		String sql="select * from product  limit  ? , ?";
		QueryRunner qr=new QueryRunner(JDBCUtils.getDataSource());
		return qr.query(sql, new BeanListHandler<Product>(Product.class),startIndex,pageSize);
		
	}

	@Override
	//查询所有房源信息数
	public int findSearTotalRecords() throws Exception {
		String sql="select count(*) from product";
		QueryRunner qr=new QueryRunner(JDBCUtils.getDataSource());
		Long num=(Long)qr.query(sql, new ScalarHandler());
		return num.intValue();
	}

	@Override
	//模糊查询房源信息
	public List<Product> findSearProductsWithPage(String pdes, String cid, String aid) throws Exception{
		String sql = "select * from product where 1=1 ";
		List<String> list = new ArrayList<String> ();
				
		//判断有没有姓名， 如果有，就组拼到sql语句里面
		if(!TextUtils.isEmpty(pdes)){
			sql = sql + "  and pdesc like ? ";
			list.add("%"+pdes+"%");
		}
		if(!TextUtils.isEmpty(aid)){
			sql = sql + " and aid=?";
			list.add(aid);
		}
		if(!TextUtils.isEmpty(cid)){
			sql = sql + "  and cid=?";
			list.add(cid);
		}
		QueryRunner qr=new QueryRunner(JDBCUtils.getDataSource());
		return qr.query(sql, new BeanListHandler<Product>(Product.class),list.toArray());
	}

	@Override
	//查询出租人信息
	public lessor fingLessorByuid(String uid) throws Exception {
		String sql="select * from lessor where uid=?";
		QueryRunner qr=new QueryRunner(JDBCUtils.getDataSource());
		return qr.query(sql, new BeanHandler<lessor>(lessor.class),uid);
	}

	@Override
	//插入订单
	public void insertOrder(Order order) throws Exception {
		String sql="INSERT INTO orderitem VALUES(?,?,?,?,?,?,?,?)";
		QueryRunner qr=new QueryRunner(JDBCUtils.getDataSource());
		Object[] params={order.getItemid(),order.getQuantity(),order.getTotal(),order.getState(),order.getPid(),order.getUid(),order.getOid(),order.getDate()};
		qr.update(sql,params);
		
	}


}
