package cn.xiaowo.rental.dao;

import java.sql.SQLException;
import java.util.List;


import cn.xiaowo.rental.domain.Admin;
import cn.xiaowo.rental.domain.PageModel;
import cn.xiaowo.rental.domain.User;
import cn.xiaowo.rental.domain.lessor;



public interface UserDao {

	Admin AdminUserLogin(Admin user) throws Exception;

	void userRegist(User user) throws Exception;

	User userLogin(User user) throws Exception;

	boolean checkUserName(String username) throws Exception;

	int findTotalRecordsNoLessor() throws Exception ;

	int findTotalRecordsLessor() throws Exception;

	int findTotalRecordsUser() throws Exception;

	List<lessor> findAllNoLessorWithPage(int startIndex, int pageSize) throws Exception;

	List<lessor> findAllLessorWithPage(int startIndex, int pageSize) throws Exception;

	List<User> findAllUserWithPage(int startIndex, int pageSize) throws Exception;

	lessor findLessorByUid(String uid) throws Exception;

	void updateLessor(lessor user) throws Exception;

	void deleteLessor(String uid) throws Exception;

	void deleteUser(String uid) throws Exception;

	lessor findLessorByName(String username) throws Exception;

	void lessorRegist(lessor user) throws Exception;



	

}
