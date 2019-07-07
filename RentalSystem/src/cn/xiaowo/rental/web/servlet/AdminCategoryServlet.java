package cn.xiaowo.rental.web.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.xiaowo.rental.domain.Area;
import cn.xiaowo.rental.domain.Category;
import cn.xiaowo.rental.domain.PageModel;
import cn.xiaowo.rental.service.CategoryService;
import cn.xiaowo.rental.service.Impl.CategoryServiceImp;
import cn.xiaowo.rental.utils.UUIDUtils;
import cn.xiaowo.rental.web.base.baseServlet;

/**
 * 租赁信息管理servlet
 */
public class AdminCategoryServlet extends baseServlet{
	
	 //查询所有分类信息
	public String findAllCats(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		//获取当前页
		int curNum=Integer.parseInt(req.getParameter("num"));
		//调用业务层查全部商品信息返回PageModel
		CategoryService CategoryService=new CategoryServiceImp();
		PageModel pm=CategoryService.getAllCats(curNum);
		//将PageModel放入request
		req.setAttribute("page", pm);
		//转发到/admin/category/list.jsp
		return "/admin/category/list.jsp";
		
	}
	
	//跳转到增加分类界面
	public String addCategoryUI(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		return "/admin/category/add.jsp";
	}

	//增加分类方法
	public String addCategory(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		//获取分类名称
		String cname=req.getParameter("cname");
		//创建分类ID
		String id=UUIDUtils.getId();
		Category c=new Category();
		c.setCid(id);
		c.setCname(cname);
		//调用业务层添加分类功能
		CategoryService CategoryService=new CategoryServiceImp();
		CategoryService.addCategory(c);
		//重定向到查询全部分类信息
		resp.sendRedirect("/RentalSystem/AdminCategoryServlet?method=findAllCats&num=1");
		return null;
	}
	//删除分类方法
	public String deleteCategory(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		//获取分类名称
		String cid=req.getParameter("cid");
		//调用业务层添加分类功能
		CategoryService CategoryService=new CategoryServiceImp();
		try {
			CategoryService.deleteCategory(cid);
			req.setAttribute("msg", "删除成功!");
		} catch (Exception e) {
			// 注册失败,跳转到提示页面
			req.setAttribute("msg", "删除失败，请重新选择分类!");
		}
		//重定向到查询全部分类信息
		//resp.sendRedirect("/store/AdminCategoryServlet?method=findAllCats&num=1");
		return "/admin/info.jsp";
	}
	//跳转到编辑分类信息界面
	public String editCategoryUI(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		//获取分类名称
		String cid=req.getParameter("cid");
		//调用业务层添加分类功能
		CategoryService CategoryService=new CategoryServiceImp();
		Category category=CategoryService.getCategoryByCid(cid);
		req.setAttribute("category", category);
		return "/admin/category/edit.jsp";
	}
	//编辑更新分类信息方法
	public String editCategory(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		//获取分类名称
		String cid=req.getParameter("cid");
		String cname=req.getParameter("cname");
		Category c=new Category();
		c.setCid(cid);
		c.setCname(cname);
		//调用业务层添加分类功能
		CategoryService CategoryService=new CategoryServiceImp();
		try {
			CategoryService.updateCategory(c);
			req.setAttribute("msg", "修改成功!");
		} catch (Exception e) {
			// 注册失败,跳转到提示页面
			req.setAttribute("msg", "修改失败，请重新选择分类!");
		}
		//重定向到查询全部分类信息
		//resp.sendRedirect("/store/AdminCategoryServlet?method=findAllCats&num=1");
		return "/admin/info.jsp";
	}
	
	
	
	
	  //查询所有地区分类信息
	 public String findAllArea(HttpServletRequest req, HttpServletResponse resp) throws Exception {
			//获取当前页
			int curNum=Integer.parseInt(req.getParameter("num"));
			//调用业务层查全部商品信息返回PageModel
			CategoryService CategoryService=new CategoryServiceImp();
			PageModel pm=CategoryService.getAllArea(curNum);
			//将PageModel放入request
			req.setAttribute("page", pm);
			//转发到/admin/category/list.jsp
			return "/admin/category/list2.jsp";
			
		}
		
		//跳转到增加地区分类界面
		public String addAreaUI(HttpServletRequest req, HttpServletResponse resp) throws Exception {
			return "/admin/category/add2.jsp";
		}

		//增加地区分类方法
		public String addArea(HttpServletRequest req, HttpServletResponse resp) throws Exception {
			//获取分类名称
			String aname=req.getParameter("aname");
			double house_price=Double.parseDouble(req.getParameter("house_price"));
			double manger_price=Double.parseDouble(req.getParameter("manger_price"));
			//创建分类ID
			String aid=UUIDUtils.getId();
			Area c=new Area();
			c.setAid(aid);
			c.setHouse_price(house_price);
			c.setManger_price(manger_price);
			c.setAname(aname);
			//调用业务层添加分类功能
			CategoryService CategoryService=new CategoryServiceImp();
			CategoryService.addArea(c);
			//重定向到查询全部分类信息
			resp.sendRedirect("/RentalSystem/AdminCategoryServlet?method=findAllArea&num=1");
			return null;
		}
		//删除分类方法
		public String deleteArea(HttpServletRequest req, HttpServletResponse resp) throws Exception {
			//获取分类名称
			String aid=req.getParameter("aid");
			//调用业务层添加分类功能
			CategoryService CategoryService=new CategoryServiceImp();
			try {
				CategoryService.deleteArea(aid);
				req.setAttribute("msg", "删除成功!");
			} catch (Exception e) {
				// 注册失败,跳转到提示页面
				req.setAttribute("msg", "删除失败，请重新选择分类!");
			}
			return "/admin/info.jsp";
		}
		//跳转到编辑地区分类信息界面
		public String editAreaUI(HttpServletRequest req, HttpServletResponse resp) throws Exception {
			//获取分类名称
			String aid=req.getParameter("aid");
			//调用业务层添加分类功能
			CategoryService CategoryService=new CategoryServiceImp();
			Area area=CategoryService.getAreaByCid(aid);
			req.setAttribute("area", area);
			return "/admin/category/edit2.jsp";
		}
		//编辑更新地区分类信息方法
		public String editArea(HttpServletRequest req, HttpServletResponse resp) throws Exception {
			//获取分类名称
			String aid=req.getParameter("aid");
			String aname=req.getParameter("aname");
			double house_price=Double.parseDouble(req.getParameter("house_price"));
			double manger_price=Double.parseDouble(req.getParameter("manger_price"));
			Area c=new Area();
			c.setAid(aid);
			c.setHouse_price(house_price);
			c.setManger_price(manger_price);
			c.setAname(aname);
			//调用业务层添加分类功能
			CategoryService CategoryService=new CategoryServiceImp();
			try {
				CategoryService.updateArea(c);
				req.setAttribute("msg", "修改成功!");
			} catch (Exception e) {
				// 注册失败,跳转到提示页面
				req.setAttribute("msg", "修改失败，请重新选择地区!");
			}
			return "/admin/info.jsp";
		}
	
	
	

}
