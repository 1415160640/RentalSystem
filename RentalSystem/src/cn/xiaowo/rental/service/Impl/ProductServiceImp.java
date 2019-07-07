package cn.xiaowo.rental.service.Impl;

import java.util.List;

import cn.xiaowo.rental.service.ProductService;
import cn.xiaowo.rental.utils.BeanFactory;
import cn.xiaowo.rental.dao.ProductDao;
import cn.xiaowo.rental.domain.Order;
import cn.xiaowo.rental.domain.PageModel;
import cn.xiaowo.rental.domain.Product;
import cn.xiaowo.rental.domain.lessor;
//房源信息业务层实现
public class ProductServiceImp implements ProductService {

	ProductDao ProductDao=(ProductDao)BeanFactory.createObject("ProductDao");
	
	@Override
	//分类查询房源
	public Product findProductByPid(String pid) throws Exception {
		return ProductDao.findProductByPid(pid);
		
	}

	@Override
	//查询热门房源
	public List<Product> findHots() throws Exception {
		return ProductDao.findHots();
	}

	@Override
	//查询推荐房源
	public List<Product> findNews() throws Exception {
		return ProductDao.findNews();
	}

	@Override
	//根据分类查询房源
	public PageModel findProductsByCidWithPage(String cid, int curNum) throws Exception {
		        //1_创建PageModel对象 目的:计算分页参数
				//统计当前分类下商品个数  select count(*) from product where cid=?
				int totalRecords=ProductDao.findTotalRecords(cid);
				PageModel pm=new PageModel(curNum,totalRecords,12);
				//2_关联集合 select * from product where cid =? limit ? ,?
				List list=ProductDao.findProductsByCidWithPage(cid,pm.getStartIndex(),pm.getPageSize());
				pm.setList(list);
				//3_关联url
				pm.setUrl("ProductServlet?method=findProductsByCidWithPage&cid="+cid);
				return pm;
	}

	@Override
	//查询可用房源
	public PageModel findAllProductsWithPage(int curNum) throws Exception {
		       //1_创建对象
				int totalRecords=ProductDao.findTotalRecords();
				PageModel pm=new PageModel(curNum,totalRecords,5);
				//2_关联集合 select * from product limit ? , ?
				List<Product> list=ProductDao.findAllProductsWithPage(pm.getStartIndex(),pm.getPageSize());
				pm.setList(list);
				//3_关联url
				pm.setUrl("AdminProductServlet?method=findAllProductsWithPage");
				return pm;
	}
	@Override
	//查询不可用房源
	public PageModel findNoProductsWithPage(int curNum) throws Exception {
	       //1_创建对象
			int totalRecords=ProductDao.findNoTotalRecords();
			PageModel pm=new PageModel(curNum,totalRecords,5);
			//2_关联集合 select * from product limit ? , ?
			List<Product> list=ProductDao.findNoProductsWithPage(pm.getStartIndex(),pm.getPageSize());
			pm.setList(list);
			//3_关联url
			pm.setUrl("AdminProductServlet?method=findNoProductsWithPage");
			return pm;
}

	@Override
	//保存房源信息
	public void saveProduct(Product product) throws Exception {
		 ProductDao.saveProduct(product);  
	}

	@Override
	//更新房源信息
	public void updateProduct(Product product) throws Exception {
		ProductDao.updateProduct(product);
	}

	@Override
	//编辑房源信息
	public void editProduct(Product product) throws Exception {
		ProductDao.updateProduct(product);	
	}

	@Override
	//删除房源信息
	public void deleteProduct(String pid) throws Exception {
		ProductDao.deleteProductDao(pid);
	}

	@Override
	//分页查询房源
	public PageModel findAllSearProductsWithPage(int curNum) throws Exception {
		       //1_创建对象
				int totalRecords=ProductDao.findSearTotalRecords();
				PageModel pm=new PageModel(curNum,totalRecords,5);
				//2_关联集合 select * from product limit ? , ?
				List<Product> list=ProductDao.findAllSearProductsWithPage(pm.getStartIndex(),pm.getPageSize());
				pm.setList(list);
				//3_关联url
				pm.setUrl("AdminProductServlet?method=findAllSearProductsWithPage");
				return pm;
	}

	@Override
	//模糊查询房源信息
	public List<Product> findSearProductsWithPage(String pdes, String cid, String aid) throws Exception{
		return ProductDao.findSearProductsWithPage(pdes,cid,aid);
	}

	@Override
	//查询出租人
	public lessor fingLessorByuid(String uid) throws Exception {
		return ProductDao.fingLessorByuid(uid);
	}

	@Override
	//插入订单
	public void insertOrder(Order order) throws Exception {
		  ProductDao.insertOrder(order);
	}




	
}
