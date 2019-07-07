package cn.xiaowo.rental.service.Impl;

import java.util.List;

import cn.xiaowo.rental.domain.Area;
import cn.xiaowo.rental.domain.Category;
import cn.xiaowo.rental.domain.PageModel;
import cn.xiaowo.rental.service.CategoryService;
import cn.xiaowo.rental.utils.BeanFactory;
import cn.xiaowo.rental.utils.JedisUtils;
import redis.clients.jedis.Jedis;
import cn.xiaowo.rental.dao.CategoryDao;

//租赁信息管理业务层
public class CategoryServiceImp implements CategoryService {
	
	CategoryDao CategoryDao=(CategoryDao)BeanFactory.createObject("CategoryDao");
	@Override
	//不分页查询所有分类信息
	public List<Category> getAllCats() throws Exception {
		return CategoryDao.getAllCats();
	}
	
	@Override
	//增加分类信息方法
	public void addCategory(Category c) throws Exception {
		        //本质是向MYSQL插入一条数据
				CategoryDao.addCategory(c);
				//更新redis缓存
				Jedis jedis = JedisUtils.getJedis();
				jedis.del("allCats");
				JedisUtils.closeJedis(jedis);
		
	}

	@Override
	//分页查询所有分类信息
	public PageModel getAllCats(int curNum) throws Exception {
		//1_创建对象
		int totalRecords=CategoryDao.findTotalRecords();
		PageModel pm=new PageModel(curNum,totalRecords,5);
		//2_关联集合 select * from product limit ? , ?
		List<Category> list=CategoryDao.findAllProductsWithPage(pm.getStartIndex(),pm.getPageSize());
		pm.setList(list);
		//3_关联url
		pm.setUrl("AdminCategoryServlet?method=findAllCats");
		return pm;
	}

	@Override
	//删除分类
	public void deleteCategory(String cid) throws Exception {
		CategoryDao.deleteCategory(cid);
		//更新redis缓存
		Jedis jedis = JedisUtils.getJedis();
		jedis.del("allCats");
		JedisUtils.closeJedis(jedis);
	}

	@Override
	//通过cid查询分类
	public Category getCategoryByCid(String cid) throws Exception {
		return CategoryDao.getCategoryByCid(cid);
	}

	@Override
	//把跟新后的分类信息存储到数据库中
	public void updateCategory(Category c) throws Exception {
		CategoryDao.updateCategory(c);
		//更新redis缓存
		Jedis jedis = JedisUtils.getJedis();
		jedis.del("allCats");
		JedisUtils.closeJedis(jedis);
		
	}
	
	
	
	  //不分页查询所有地区分类信息用于客户端显示
		public List<Area> getAllArea() throws Exception {
			return CategoryDao.getAllArea();
		}
		@Override
		//增加地区分类信息方法
		public void addArea(Area c) throws Exception {
			        //本质是向MYSQL插入一条数据
					CategoryDao.addArea(c);
					//更新redis缓存
					Jedis jedis = JedisUtils.getJedis();
					jedis.del("allArea");
					JedisUtils.closeJedis(jedis);
			
		}

		@Override
		//分页查询所有地区分类信息
		public PageModel getAllArea(int curNum) throws Exception {
			//1_创建对象
			int totalRecords=CategoryDao.findTotalRecordsArea();
			PageModel pm=new PageModel(curNum,totalRecords,5);
			//2_关联集合 select * from product limit ? , ?
			List<Area> list=CategoryDao.findAllAreaWithPage(pm.getStartIndex(),pm.getPageSize());
			pm.setList(list);
			//3_关联url
			pm.setUrl("AdminCategoryServlet?method=findAllArea");
			return pm;
		}

		@Override
		//删除地区分类
		public void deleteArea(String cid) throws Exception {
			CategoryDao.deleteArea(cid);
			//更新redis缓存
			Jedis jedis = JedisUtils.getJedis();
			jedis.del("allArea");
			JedisUtils.closeJedis(jedis);
		}

		@Override
		//通过cid查询地区分类
		public Area getAreaByCid(String cid) throws Exception {
			return CategoryDao.getAreaByCid(cid);
		}

		@Override
		//把跟新后的地区分类信息存储到数据库中
		public void updateArea(Area c) throws Exception {
			CategoryDao.updateArea(c);
			//更新redis缓存
			Jedis jedis = JedisUtils.getJedis();
			jedis.del("allCats");
			JedisUtils.closeJedis(jedis);
			
		}
	

}
