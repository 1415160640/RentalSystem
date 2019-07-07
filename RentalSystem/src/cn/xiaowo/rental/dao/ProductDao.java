package cn.xiaowo.rental.dao;

import java.util.List;

import cn.xiaowo.rental.domain.Order;
import cn.xiaowo.rental.domain.Product;
import cn.xiaowo.rental.domain.lessor;


//房源信息dao层接口
public interface ProductDao {

	List<Product> findHots()throws Exception;

	List<Product> findNews()throws Exception;

	Product findProductByPid(String pid)throws Exception;

	int findTotalRecords(String cid)throws Exception;

	List findProductsByCidWithPage(String cid, int startIndex, int pageSize)throws Exception;

	List<Product> findAllProductsWithPage(int startIndex, int pageSize)throws Exception;
	
	int findTotalRecords() throws Exception;

	void saveProduct(Product product) throws Exception;

	void updateProduct(Product product) throws Exception;

	void deleteProductDao(String pid) throws Exception;

	int findNoTotalRecords() throws Exception;

	List<Product> findNoProductsWithPage(int startIndex, int pageSize) throws Exception;

	List<Product> findAllSearProductsWithPage(int startIndex, int pageSize) throws Exception;

	int findSearTotalRecords() throws Exception;

	List<Product> findSearProductsWithPage(String pdes, String cid, String aid) throws Exception;

	lessor fingLessorByuid(String uid) throws Exception;

	void insertOrder(Order order) throws Exception;




}
