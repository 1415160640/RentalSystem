package cn.xiaowo.rental.service;

import java.util.List;

import cn.xiaowo.rental.domain.Order;
import cn.xiaowo.rental.domain.PageModel;
import cn.xiaowo.rental.domain.Product;
import cn.xiaowo.rental.domain.lessor;


//房源信息业务层
public interface ProductService {

	List<Product> findHots()throws Exception;

	List<Product> findNews()throws Exception;

	Product findProductByPid(String pid)throws Exception;

	PageModel findProductsByCidWithPage(String cid, int curNum)throws Exception;

	PageModel findAllProductsWithPage(int curNum) throws Exception;

	void saveProduct(Product product)  throws Exception;


	void updateProduct(Product product) throws Exception;

	void editProduct(Product product) throws Exception;

	void deleteProduct(String pid) throws Exception;

	PageModel findNoProductsWithPage(int curNum) throws Exception;

	PageModel findAllSearProductsWithPage(int curNum) throws Exception;

	List<Product> findSearProductsWithPage(String pdes, String cid, String aid) throws Exception;

	lessor fingLessorByuid(String uid) throws Exception;

	void insertOrder(Order order) throws Exception; 

}
