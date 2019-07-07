package cn.xiaowo.rental.service;

import java.sql.SQLException;

import cn.xiaowo.rental.domain.Admin;
import cn.xiaowo.rental.domain.PageModel;
import cn.xiaowo.rental.domain.User;
import cn.xiaowo.rental.domain.lessor;


public interface UserService {

	Admin AdminUserLogin(Admin user) throws Exception;

	void userRegist(User user) throws  Exception;

	User userLogin(User user) throws Exception;

	boolean checkUserName(String username) throws Exception;

	PageModel findAllNoLessorWithPage(int curNum) throws Exception;

	PageModel findAllLessorWithPage(int curNum) throws Exception;

	PageModel findAllUserWithPage(int curNum) throws Exception;

	lessor findLessorByUid(String uid) throws Exception;

	void updateLessor(lessor user) throws Exception;

	void deleteLessor(String uid) throws Exception;

	void deleteUser(String uid) throws Exception;

	lessor findLessorByName(String username) throws Exception;

	void lessorRegist(lessor user) throws Exception;


}
