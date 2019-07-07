package cn.xiaowo.rental.service;

import cn.xiaowo.rental.domain.PageModel;
import cn.xiaowo.rental.domain.lessor;

public interface LessorService {

	lessor LessorUserLogin(lessor user) throws Exception;

	PageModel findAllProductsWithPage(int curNum, String uid) throws Exception;

	PageModel findAllOrders(int curNum, String uid) throws Exception;

	PageModel findAllOrders(int curNum, String st, String uid) throws Exception;

}
