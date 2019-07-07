package cn.xiaowo.rental.web.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.xiaowo.rental.domain.Admin;
import cn.xiaowo.rental.domain.Money;
import cn.xiaowo.rental.domain.PageModel;
import cn.xiaowo.rental.domain.lessor;
import cn.xiaowo.rental.service.OrderService;
import cn.xiaowo.rental.service.UserService;
import cn.xiaowo.rental.service.Impl.OrderServiceImp;
import cn.xiaowo.rental.service.Impl.UserServiceImp;
import cn.xiaowo.rental.utils.MyBeanUtils;
import cn.xiaowo.rental.utils.UUIDUtils;
import cn.xiaowo.rental.web.base.baseServlet;

//用户管理
public class AdminUserServlert extends baseServlet{
	//登入
	public String userLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        //获取用户数据
		Admin user = new Admin();
		MyBeanUtils.populate(user, request.getParameterMap());
		//调用业务层功能
		UserService userservice = new UserServiceImp();
		OrderService OrderService=new OrderServiceImp();
		Money mymoney=null;
		Admin user02 = null;
		try {
			user02 = userservice.AdminUserLogin(user);//返回用户的所有信息
			mymoney = OrderService.findMoneyByOid(user02.getUid());
			if(mymoney==null) {
				Money user22 = new Money();
		    	user22.setId(UUIDUtils.getCode());
		    	user22.setUserid(user02.getUid());
		    	user22.setMoney(0);
		    	OrderService.CreatMoney(user22);
		    	mymoney=OrderService.findMoneyByOid(user02.getUid());
			}
			//用户登入成功,将用户信息放入session
			request.getSession().setAttribute("mymoney", mymoney);
			request.getSession().setAttribute("AdminloginUser", user02);
			response.sendRedirect("/RentalSystem/admin/home.jsp");//重定向
			return null;
		} catch (Exception e) {
			//用户登入失败
			String msg = e.getMessage();
			request.setAttribute("msg", msg);
			return "/admin/index.jsp";
		}
		
	}
	//退出管理用户
	public String logOut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
	  //清除session
	  request.getSession().invalidate();
	  return "/admin/index.jsp";
	}
	
	//查询待审核出租人信息
	public String findAllNoLessorWithPage(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		//获取当前页
		int curNum=Integer.parseInt(req.getParameter("num"));
		//调用业务层查全部商品信息返回PageModel
		UserService userservice = new UserServiceImp();
		PageModel pm=userservice.findAllNoLessorWithPage(curNum);
		//将PageModel放入request
		req.setAttribute("page", pm);
		//转发到/admin/category/list.jsp
		return "/admin/user/list2.jsp";
		
	}
	//查询出租人信息
	public String findAllLessorWithPage(HttpServletRequest req, HttpServletResponse resp) throws Exception {
			//获取当前页
			int curNum=Integer.parseInt(req.getParameter("num"));
			//调用业务层查全部商品信息返回PageModel
			UserService userservice = new UserServiceImp();
			PageModel pm=userservice.findAllLessorWithPage(curNum);
			//将PageModel放入request
			req.setAttribute("page", pm);
			//转发到/admin/category/list.jsp
			return "/admin/user/list3.jsp";
			
	}
	//查询求租人信息
	public String findAllUserWithPage(HttpServletRequest req, HttpServletResponse resp) throws Exception {
			//获取当前页
			int curNum=Integer.parseInt(req.getParameter("num"));
			//调用业务层查全部商品信息返回PageModel
			UserService userservice = new UserServiceImp();
			PageModel pm=userservice.findAllUserWithPage(curNum);
			//将PageModel放入request
			req.setAttribute("page", pm);
			//转发到/admin/category/list.jsp
			return "/admin/user/list.jsp";
			
	}
	//审核新出租人信息
	public String ManagerLessor(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		//获取ID
		String uid=req.getParameter("uid");
		//根据ID查询
		UserService userservice = new UserServiceImp();
		lessor user=userservice.findLessorByUid(uid);
		//设置状态
		user.setState(1);
		//修改信息
		userservice.updateLessor(user);
		//重新定向
		resp.sendRedirect("/RentalSystem/AdminUserServlert?method=findAllNoLessorWithPage&num=1");
		return null;
	}	
	//删除出租人信息
	public String deleteLessor(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		   //获取ID
		   String uid=req.getParameter("uid");
		   //根据ID查询
		   UserService userservice = new UserServiceImp();
			try {
				userservice.deleteLessor(uid);
				req.setAttribute("msg", "删除成功!");
			} catch (Exception e) {
				// 注册失败,跳转到提示页面
				req.setAttribute("msg", "删除失败，请重新选择!");
			}
			return "/admin/info.jsp";
	}
	//删除出租人信息
    public String deleteUser(HttpServletRequest req, HttpServletResponse resp) throws Exception {
			   //获取ID
			   String uid=req.getParameter("uid");
			   //根据ID查询
			   UserService userservice = new UserServiceImp();
				try {
					userservice.deleteUser(uid);
					req.setAttribute("msg", "删除成功!");
				} catch (Exception e) {
					// 注册失败,跳转到提示页面
					req.setAttribute("msg", "删除失败，请重新选择分类!");
				}
				return "/admin/info.jsp";
	}
	


}
