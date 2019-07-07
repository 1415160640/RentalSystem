package cn.xiaowo.rental.dao.Impl;

import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import cn.xiaowo.rental.dao.CategoryDao;
import cn.xiaowo.rental.domain.Area;
import cn.xiaowo.rental.domain.Category;
import cn.xiaowo.rental.utils.JDBCUtils;



//租赁信息dao实现层
public class CategoryDaoImp implements CategoryDao {

	@Override
	//查询房屋分类集合
	public List<Category> getAllCats() throws Exception {
		String sql = "select * from category";
		QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
		return qr.query(sql, new BeanListHandler<Category>(Category.class));
		
	}

	@Override
	//添加房屋分类信息
	public void addCategory(Category c) throws Exception {
		String sql="insert into category values (? ,?)";
		QueryRunner qr=new QueryRunner(JDBCUtils.getDataSource());
		qr.update(sql,c.getCid(),c.getCname());
	}

	@Override
	//统计房屋分类信息的记录数
	public int findTotalRecords() throws Exception {
		String sql="select count(*) from category";
		QueryRunner qr=new QueryRunner(JDBCUtils.getDataSource());
		Long num=(Long)qr.query(sql, new ScalarHandler());
		return num.intValue();
	}

	@Override
	//分页查询所有房源信息
	public List<Category> findAllProductsWithPage(int startIndex, int pageSize) throws Exception {
		String sql="select * from category limit  ? , ?";
		QueryRunner qr=new QueryRunner(JDBCUtils.getDataSource());
		return qr.query(sql, new BeanListHandler<Category>(Category.class),startIndex,pageSize);
	}

	@Override
	//删除房源信息
	public void deleteCategory(String cid) throws Exception {
		String sql="delete from category where cid=?";
		QueryRunner qr=new QueryRunner(JDBCUtils.getDataSource());
		qr.update(sql,cid); 
	}

	@Override
	//根据房源分类查询房源
	public Category getCategoryByCid(String cid) throws Exception {
		String sql="select * from category where cid=?";
		QueryRunner qr=new QueryRunner(JDBCUtils.getDataSource());
		return qr.query(sql, new BeanHandler<Category>(Category.class),cid);
	}

	@Override
	//更新房源信息
	public void updateCategory(Category c) throws Exception {
		String sql="update category set cname=? where cid=?";
		QueryRunner qr=new QueryRunner(JDBCUtils.getDataSource());
		Object[] params={c.getCname(),c.getCid()};
		qr.update(sql,params);
		
	}
	@Override
	//获取地区分类信息的集合
	public List<Area> getAllArea() throws Exception {
		String sql = "select * from area";
		QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
		return qr.query(sql, new BeanListHandler<Area>(Area.class));
		
	}

	@Override
	//添加地区分类
	public void addArea(Area c) throws Exception {
		String sql="insert into area values (? ,? ,? ,? )";
		QueryRunner qr=new QueryRunner(JDBCUtils.getDataSource());
		qr.update(sql,c.getAid(),c.getHouse_price(),c.getManger_price(),c.getAname());
	}

	@Override
	//统计地区分类信息的记录数
	public int findTotalRecordsArea() throws Exception {
		String sql="select count(*) from area";
		QueryRunner qr=new QueryRunner(JDBCUtils.getDataSource());
		Long num=(Long)qr.query(sql, new ScalarHandler());
		return num.intValue();
	}

	@Override
	//分页查询地区分类信息
	public List<Area> findAllAreaWithPage(int startIndex, int pageSize) throws Exception {
		String sql="select * from area limit  ? , ?";
		QueryRunner qr=new QueryRunner(JDBCUtils.getDataSource());
		return qr.query(sql, new BeanListHandler<Area>(Area.class),startIndex,pageSize);
	}

	@Override
	//删除地区分类信息
	public void deleteArea(String cid) throws Exception {
		String sql="delete from area where aid=?";
		QueryRunner qr=new QueryRunner(JDBCUtils.getDataSource());
		qr.update(sql,cid); 
	}

	@Override
	//通过id查询地区分类信息
	public Area getAreaByCid(String cid) throws Exception {
		String sql="select * from area where aid=?";
		QueryRunner qr=new QueryRunner(JDBCUtils.getDataSource());
		return qr.query(sql, new BeanHandler<Area>(Area.class),cid);
	}

	@Override
	//更新地区分类信息
	public void updateArea(Area c) throws Exception {
		String sql="update area set house_price=?,manger_price=?,aname=? where aid=?";
		QueryRunner qr=new QueryRunner(JDBCUtils.getDataSource());
		Object[] params={c.getHouse_price(),c.getManger_price(),c.getAname(),c.getAid()};
		qr.update(sql,params);
		
	}


}
