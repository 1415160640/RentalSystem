package cn.xiaowo.rental.dao;

import java.util.List;

import cn.xiaowo.rental.domain.Area;
import cn.xiaowo.rental.domain.Category;


//租赁信息管理dao层接口
public interface CategoryDao {

	List<Category> getAllCats() throws Exception;
	void addCategory(Category c)throws Exception;
	int findTotalRecords() throws Exception;
	List<Category> findAllProductsWithPage(int startIndex, int pageSize) throws Exception ;
	void deleteCategory(String cid) throws Exception;
	Category getCategoryByCid(String cid) throws Exception;
	void updateCategory(Category c) throws Exception;
	List<Area> getAllArea() throws Exception;
	void addArea(Area c) throws Exception;
	void updateArea(Area c) throws Exception;
	Area getAreaByCid(String cid) throws Exception;
	void deleteArea(String cid) throws Exception;
	List<Area> findAllAreaWithPage(int startIndex, int pageSize) throws Exception;
	int findTotalRecordsArea() throws Exception;


}
