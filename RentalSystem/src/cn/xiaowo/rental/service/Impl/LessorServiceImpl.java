package cn.xiaowo.rental.service.Impl;

import java.util.List;

import cn.xiaowo.rental.dao.LessorDao;
import cn.xiaowo.rental.domain.Admin;
import cn.xiaowo.rental.domain.Order;
import cn.xiaowo.rental.domain.PageModel;
import cn.xiaowo.rental.domain.Product;
import cn.xiaowo.rental.domain.lessor;
import cn.xiaowo.rental.service.LessorService;
import cn.xiaowo.rental.utils.BeanFactory;

public class LessorServiceImpl implements LessorService {
	LessorDao lessorDao=(LessorDao)BeanFactory.createObject("LessorDao"); 
	
	@Override
	//出租人登入
	public lessor LessorUserLogin(lessor user) throws Exception {
		   lessor uu = lessorDao.LessorUserLogin(user);
			if(uu == null) {
				throw new RuntimeException("登入失败，密码错误或用户未激活！");
			}else {
				return uu;
			}
	}

	@Override
	//分页查询所有房源信息
	public PageModel findAllProductsWithPage(int curNum,String uid) throws Exception {
		 //1_创建对象
		int totalRecords=lessorDao.findTotalRecords(uid);
		PageModel pm=new PageModel(curNum,totalRecords,5);
		//2_关联集合 select * from product limit ? , ?
		List<Product> list=lessorDao.findAllProductsWithPage(pm.getStartIndex(),pm.getPageSize(),uid);
		pm.setList(list);
		//3_关联url
		pm.setUrl("LessorSevlet?method=findAllProductsWithPage");
		return pm;
	}

	@Override
	public PageModel findAllOrders(int curNum, String uid) throws Exception {
		 //1_创建对象
		int totalRecords=lessorDao.findAllTotalRecords(uid);
		PageModel pm=new PageModel(curNum,totalRecords,5);
		//2_关联集合 select * from product limit ? , ?
		List<Order> list=lessorDao.findAllProductsWithPage1(pm.getStartIndex(),pm.getPageSize(),uid);
		pm.setList(list);
		//3_关联url
		pm.setUrl("LessorSevlet?method=findOrders");
		return pm;
	}

	@Override
	public PageModel findAllOrders(int curNum, String st, String uid) throws Exception {
		        //1_创建对象
				int totalRecords=lessorDao.findAllTotalRecords(st,uid);
				PageModel pm=new PageModel(curNum,totalRecords,5);
				//2_关联集合 select * from product limit ? , ?
				List<Order> list=lessorDao.findAllProductsWithPage1(pm.getStartIndex(),pm.getPageSize(),st,uid);
				pm.setList(list);
				//3_关联url
				pm.setUrl("LessorSevlet?method=findOrders");
				return pm;
	}
	

}
