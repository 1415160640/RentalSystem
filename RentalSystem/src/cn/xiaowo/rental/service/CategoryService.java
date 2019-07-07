package cn.xiaowo.rental.service;

import java.util.List;

import cn.xiaowo.rental.domain.Area;
import cn.xiaowo.rental.domain.Category;
import cn.xiaowo.rental.domain.PageModel;


//租赁信息管理业务层接口
public interface CategoryService {

	List<Category> getAllCats () throws Exception;
	
	List<Area> getAllArea () throws Exception;
	
	void addCategory(Category c) throws Exception;

	PageModel getAllCats(int curNum) throws Exception;

	void deleteCategory(String cid) throws Exception;

	Category getCategoryByCid(String cid) throws Exception;

	void updateCategory(Category c) throws Exception;

	PageModel getAllArea(int curNum) throws Exception;

	void addArea(Area c) throws Exception;

	Area getAreaByCid(String aid) throws Exception;

	void updateArea(Area c) throws Exception;

	void deleteArea(String aid) throws Exception;


}
