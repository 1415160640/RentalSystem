package cn.xiaowo.rental.service;

import cn.xiaowo.rental.domain.Money;
import cn.xiaowo.rental.domain.Order;
import cn.xiaowo.rental.domain.PageModel;
import cn.xiaowo.rental.domain.Product;
import cn.xiaowo.rental.domain.User;
//财务管理业务层接口
public interface OrderService {

	PageModel findAllOrders1(int curNum) throws Exception;

	Product findProductByOid(String itemid) throws Exception;

	PageModel findAllOrders(int curNum) throws Exception;

	PageModel findAllOrders(int curNum, String st) throws Exception;

	Order findOrderByOid(String itemid) throws Exception;

	Money findMoneyByOid(String string) throws Exception;

	void updateOrder(Order order, Money user1, Money user2) throws Exception;

	void CreatMoney(Money user) throws Exception;

	PageModel findMoney(int curNum) throws Exception;

	PageModel findMyOrdersWithPage(User user, int curNum) throws Exception;

	void updateOrder(Order order) throws Exception;

}
