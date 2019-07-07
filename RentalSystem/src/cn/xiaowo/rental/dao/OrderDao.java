package cn.xiaowo.rental.dao;



import java.sql.Connection;
import java.util.List;

import cn.xiaowo.rental.domain.Money;
import cn.xiaowo.rental.domain.Order;
import cn.xiaowo.rental.domain.Product;
import cn.xiaowo.rental.domain.User;

//财务管理dao层实现
public interface OrderDao {



	int findTotalRecords1() throws Exception;

	List<cn.xiaowo.rental.domain.Order> findAllProductsWithPage1(int startIndex, int pageSize) throws Exception;

	Product findProductByOid(String pid) throws Exception;

	int findTotalRecords() throws Exception;

	List<Order> findAllProductsWithPage(int startIndex, int pageSize) throws Exception;

	int findTotalRecords(String st) throws Exception;

	List<Order> findAllProductsWithPage(int startIndex, int pageSize, String st) throws Exception;

	Order findOrderByOid(String itemid) throws Exception;

	Money findMoneyByOid(String string) throws Exception;

	void updateOrder(Connection conn, Order order) throws Exception;

	void updateMoney(Connection conn, Money user2) throws Exception;

	void CreatMoney(Money user) throws Exception;

	int findTotalMoney() throws Exception;

	List<Money> findAllMoneyWithPage1(int startIndex, int pageSize) throws Exception;

	int getTotalRecords(User user) throws Exception;

	List findMyOrdersWithPage(User user, int startIndex, int pageSize) throws Exception;

}
