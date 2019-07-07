package cn.xiaowo.rental.dao;

import java.util.List;

import cn.xiaowo.rental.domain.Order;
import cn.xiaowo.rental.domain.Product;
import cn.xiaowo.rental.domain.lessor;

public interface LessorDao {

	lessor LessorUserLogin(lessor user) throws Exception;

	int findTotalRecords(String uid) throws Exception;

	List<Product> findAllProductsWithPage(int startIndex, int pageSize,String uid) throws Exception;

	int findAllTotalRecords(String uid) throws Exception;

	List<Order> findAllProductsWithPage1(int startIndex, int pageSize,String uid) throws Exception;

	int findAllTotalRecords(String st,String uid) throws Exception;

	List<Order> findAllProductsWithPage1(int startIndex, int pageSize, String st,String uid) throws Exception;

}
