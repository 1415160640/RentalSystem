package cn.xiaowo.rental.service.Impl;

import java.sql.SQLException;
import java.util.List;


import cn.xiaowo.rental.dao.UserDao;
import cn.xiaowo.rental.domain.Admin;
import cn.xiaowo.rental.domain.PageModel;
import cn.xiaowo.rental.domain.User;
import cn.xiaowo.rental.domain.lessor;
import cn.xiaowo.rental.service.UserService;
import cn.xiaowo.rental.utils.BeanFactory;



public class UserServiceImp implements UserService{
    
	UserDao userdao=(UserDao)BeanFactory.createObject("UserDao");
    
	@Override
	//实现系统管理员登录
	public Admin AdminUserLogin(Admin user) throws Exception {
		Admin uu = userdao.AdminUserLogin(user);
		if(uu == null) {
			throw new RuntimeException("登入失败，密码错误或用户不存在！");
		}else {
			return uu;
		}
	}
	
	
	@Override
	//实现求租人注册功能
	public void userRegist(User user) throws Exception {
	    userdao.userRegist(user);
	}
	@Override
	//实现出租人注册功能
	public void lessorRegist(lessor user) throws Exception {
	    userdao.lessorRegist(user);
	}

	@Override
	//实现求租人登入
	public User userLogin(User user) throws Exception {
		User uu = userdao.userLogin(user);
		if(uu == null) {
			throw new RuntimeException("密码有误！");
		}else {
			return uu;
		}
	}
	
	@Override
	//异步校验用户名是否被占用
	public boolean checkUserName(String username) throws Exception {
		return userdao.checkUserName(username);
	}


	@Override
	//查询待审核出租人信息
	public PageModel findAllNoLessorWithPage(int curNum) throws Exception {
		//1_创建对象
		int totalRecords=userdao.findTotalRecordsNoLessor();
		PageModel pm=new PageModel(curNum,totalRecords,5);
		//2_关联集合 select * from product limit ? , ?
		List<lessor> list=userdao.findAllNoLessorWithPage(pm.getStartIndex(),pm.getPageSize());
		pm.setList(list);
		//3_关联url
		pm.setUrl("AdminUserServlert?method=findAllNoLessorWithPage");
		return pm;
	}


	@Override
	//查询出租人信息
	public PageModel findAllLessorWithPage(int curNum) throws Exception {
		       //1_创建对象
		       int totalRecords=userdao.findTotalRecordsLessor();
				PageModel pm=new PageModel(curNum,totalRecords,5);
				//2_关联集合 select * from product limit ? , ?
				List<lessor> list=userdao.findAllLessorWithPage(pm.getStartIndex(),pm.getPageSize());
				pm.setList(list);
				//3_关联url
				pm.setUrl("AdminUserServlert?method=findAllLessorWithPage");
				return pm;
	}


	@Override
	//查询求租人信息
	public PageModel findAllUserWithPage(int curNum) throws Exception {
		       //1_创建对象
				int totalRecords=userdao.findTotalRecordsUser();
				PageModel pm=new PageModel(curNum,totalRecords,5);
				//2_关联集合 select * from product limit ? , ?
				List<User> list=userdao.findAllUserWithPage(pm.getStartIndex(),pm.getPageSize());
				pm.setList(list);
				//3_关联url
				pm.setUrl("AdminUserServlert?method=findAllUserWithPage");
				return pm;
	}


	@Override
	public lessor findLessorByUid(String uid) throws Exception{
		return userdao.findLessorByUid(uid);
	}


	@Override
	public void updateLessor(lessor user) throws Exception {
		userdao.updateLessor(user);
	}


	@Override
	public void deleteLessor(String uid) throws Exception {
		userdao.deleteLessor(uid);
		
	}


	@Override
	public void deleteUser(String uid) throws Exception {
		userdao.deleteUser(uid);
		
	}


	@Override
	public lessor findLessorByName(String username) throws Exception {
		return userdao.findLessorByName(username);
	}

}
