package cn.xiaowo.rental.dao.Impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import cn.xiaowo.rental.dao.UserDao;
import cn.xiaowo.rental.domain.Admin;
import cn.xiaowo.rental.domain.PageModel;
import cn.xiaowo.rental.domain.User;
import cn.xiaowo.rental.domain.lessor;
import cn.xiaowo.rental.utils.JDBCUtils;

//管理员用户处理dao层实现
public class UserDaoImp implements UserDao{
	
	@Override
	//数据库查询管理员信息
	public Admin AdminUserLogin(Admin user) throws SQLException {
		String sql="select * from admin where username=?  and password= ?";
		QueryRunner qr=new QueryRunner(JDBCUtils.getDataSource());
		return qr.query(sql, new BeanHandler<Admin>(Admin.class),user.getUsername(),user.getPassword());
	}

	@Override
	//数据库插入求租人信息
	public void userRegist(User user) throws SQLException {
		String sql = "insert into user values(?,?,?,?,?,?,?,?,?)";
		QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
		Object[] params= {
				user.getUid(),
				user.getUsername(),
				user.getPassword(),
				user.getName(),
				user.getEmail(),
				user.getTelephone(),
				user.getBirthday(),
				user.getSex(),
				user.getAddress()};
		qr.update(sql,params);
	}
	@Override
	//数据库插入出租人信息
	public void lessorRegist(lessor user) throws SQLException {
			String sql = "insert into lessor values(?,?,?,?,?,?,?,?,?,?,?)";
			QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
			Object[] params= {
					user.getUid(),
					user.getUsername(),
					user.getPassword(),
					user.getName(),
					user.getEmail(),
					user.getTelephone(),
					user.getBirthday(),
					user.getSex(),
					user.getAddress(),
					user.getId(),
					user.getState()};
			qr.update(sql,params);
		}
	
	@Override
	//数据库求租人信息查询
	public User userLogin(User user) throws SQLException {
		String sql="select * from user where username=?  and password= ?";
		QueryRunner qr=new QueryRunner(JDBCUtils.getDataSource());
		return qr.query(sql, new BeanHandler<User>(User.class),user.getUsername(),user.getPassword());
	}

	@Override
	//数据库异步校验用户名是否被占用
	public boolean checkUserName(String username) throws SQLException {
        String sql = "select count(*) from user where username =?";
        QueryRunner qr=new QueryRunner(JDBCUtils.getDataSource());
		Long result = (Long) qr.query(sql, new  ScalarHandler(), username); 
		return result > 0 ;
	}

	@Override
	//查询待审核的出租人记录条数
	public int findTotalRecordsNoLessor() throws Exception {
		String sql="select count(*) from lessor where state=0";
		QueryRunner qr=new QueryRunner(JDBCUtils.getDataSource());
		Long num=(Long)qr.query(sql, new ScalarHandler());
		return num.intValue();
	}

	@Override
	//查询正常出租人记录条数
	public int findTotalRecordsLessor() throws Exception {
		String sql="select count(*) from lessor where state=1";
		QueryRunner qr=new QueryRunner(JDBCUtils.getDataSource());
		Long num=(Long)qr.query(sql, new ScalarHandler());
		return num.intValue();
	}

	@Override
	//查询普通用户记录条数
	public int findTotalRecordsUser() throws Exception {
		String sql="select count(*) from User";
		QueryRunner qr=new QueryRunner(JDBCUtils.getDataSource());
		Long num=(Long)qr.query(sql, new ScalarHandler());
		return num.intValue();
	}

	@Override
	//查询待审核的出租人对象集合
	public List<lessor> findAllNoLessorWithPage(int startIndex, int pageSize) throws Exception {
		String sql="select * from lessor where state=0 limit  ? , ?";
		QueryRunner qr=new QueryRunner(JDBCUtils.getDataSource());
		return qr.query(sql, new BeanListHandler<lessor>(lessor.class),startIndex,pageSize);
	}

	@Override
	//查询正常出租人对象集合
	public List<lessor> findAllLessorWithPage(int startIndex, int pageSize) throws Exception {
		String sql="select * from lessor where state=1 limit  ? , ?";
		QueryRunner qr=new QueryRunner(JDBCUtils.getDataSource());
		return qr.query(sql, new BeanListHandler<lessor>(lessor.class),startIndex,pageSize);
	}

	@Override
	//查询普通用户对象集合
	public List<User> findAllUserWithPage(int startIndex, int pageSize) throws Exception {
		String sql="select * from user limit  ? , ?";
		QueryRunner qr=new QueryRunner(JDBCUtils.getDataSource());
		return qr.query(sql, new BeanListHandler<User>(User.class),startIndex,pageSize);
	}

	@Override
	//根据uid查询lessor对象
	public lessor findLessorByUid(String uid) throws Exception {
		String sql="select * from lessor where uid=?";
		QueryRunner qr=new QueryRunner(JDBCUtils.getDataSource());
		return qr.query(sql, new BeanHandler<lessor>(lessor.class),uid);
	}

	@Override
	//更新lessor对象
	public void updateLessor(lessor user) throws Exception {
		String sql="update lessor set username=?,password=?,name=?,email=?,telephone=?,birthday=?,sex=?,address=?,id=?,state=? WHERE uid=?";
		QueryRunner qr=new QueryRunner(JDBCUtils.getDataSource());
		Object[] params={user.getUsername(),user.getPassword(),user.getName(),user.getEmail(),user.getTelephone(),user.getBirthday(),user.getSex(),user.getAddress(),user.getId(),user.getState(),user.getUid()};
		qr.update(sql,params);
	}

	@Override
	//删除出租人
	public void deleteLessor(String uid) throws Exception {
		String sql="delete from lessor where uid=?";
		QueryRunner qr=new QueryRunner(JDBCUtils.getDataSource());
		qr.update(sql,uid);
		
	}

	@Override
	//删除求租人
	public void deleteUser(String uid) throws Exception {
		String sql="delete from user where uid=?";
		QueryRunner qr=new QueryRunner(JDBCUtils.getDataSource());
		qr.update(sql,uid);
		
	}

	@Override
	//用过账号查询出租人
	public lessor findLessorByName(String username) throws Exception {
		String sql="select * from lessor where username=?";
		QueryRunner qr=new QueryRunner(JDBCUtils.getDataSource());
		return qr.query(sql, new BeanHandler<lessor>(lessor.class),username);
	}

	

}
