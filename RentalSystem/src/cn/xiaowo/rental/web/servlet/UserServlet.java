package cn.xiaowo.rental.web.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.xiaowo.rental.domain.Money;
import cn.xiaowo.rental.domain.User;
import cn.xiaowo.rental.domain.lessor;
import cn.xiaowo.rental.service.OrderService;
import cn.xiaowo.rental.service.UserService;
import cn.xiaowo.rental.service.Impl.OrderServiceImp;
import cn.xiaowo.rental.service.Impl.UserServiceImp;
import cn.xiaowo.rental.utils.MyBeanUtils;
import cn.xiaowo.rental.utils.UUIDUtils;
import cn.xiaowo.rental.web.base.baseServlet;
//用户servlet
public class UserServlet extends baseServlet {
	        //跳转注册界面
			public String registUI(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				return "/jsp/register.jsp";
			}
			public String loginUI(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				return "/jsp/login.jsp";
			}
			public String LessorRegistUI(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				return "/lessor/register.jsp";
			}
			//实现注册功能
			public String userRegist(HttpServletRequest request, HttpServletResponse response) throws Exception {
				// 接收表单参数
						Map<String, String[]> map = request.getParameterMap();
						User user = new User();
						
						MyBeanUtils.populate(user, map);
						
						// 为用户的其他属性赋值
						user.setUid(UUIDUtils.getId());

						// 调用业务层注册功能
						UserService UserService = new UserServiceImp();
						try {
	    					UserService.userRegist(user);
	    					Money user0 = new Money();
					    	user0.setId(UUIDUtils.getCode());
					    	user0.setUserid(user.getUid());
					    	user0.setMoney(500);
					    	OrderService OrderService=new OrderServiceImp();
					    	OrderService.CreatMoney(user0);
							// 注册成功,向用户邮箱发送信息,跳转到提示页面
							request.setAttribute("msg", "用户注册成功,快去登入吧!");
						} catch (Exception e) {
							// 注册失败,跳转到提示页面
							request.setAttribute("msg", "用户注册失败,请重新注册!");

						}
						return "/jsp/info.jsp";
			    }
			    //实现注册功能
			    public String lessorRegist(HttpServletRequest request, HttpServletResponse response) throws Exception {
				// 接收表单参数
						Map<String, String[]> map = request.getParameterMap();
						lessor user = new lessor();
						
						MyBeanUtils.populate(user, map);
						
						// 为用户的其他属性赋值
						user.setUid(UUIDUtils.getId());
						user.setState(0);

						// 调用业务层注册功能
						UserService UserService = new UserServiceImp();
						try {
	    					UserService.lessorRegist(user);
							// 注册成功,向用户邮箱发送信息,跳转到提示页面
							request.setAttribute("msg", "用户注册成功,等待管理员审核! &nbsp; &nbsp; &nbsp; &nbsp;<a href=\"/RentalSystem/lessor/index.jsp\">登录链接</a>");
						} catch (Exception e) {
							// 注册失败,跳转到提示页面
							request.setAttribute("msg", "用户注册失败,请重新注册! &nbsp; &nbsp; &nbsp; &nbsp;<a href=\"/RentalSystem//lessor/register.jsp\">注册链接</a>");

						}
						return "/lessor/info.jsp";
			    }
			
				//登入
				public String userLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
			        //获取用户数据
					User user = new User();
					MyBeanUtils.populate(user, request.getParameterMap());
					//调用业务层功能
					UserService userservice = new UserServiceImp();
					OrderService OrderService=new OrderServiceImp();
					User user02 = null;
					Money mymoney=null;
					try {
						user02 = userservice.userLogin(user);//返回用户的所有信息
						mymoney = OrderService.findMoneyByOid(user02.getUid());
						//用户登入成功,将用户信息放入session
						request.getSession().setAttribute("mymoney", mymoney);
						request.getSession().setAttribute("loginUser", user02);
						response.sendRedirect("/RentalSystem/index.jsp");//重定向
						return null;
					} catch (Exception e) {
						//用户登入失败
						String msg = e.getMessage();
						//System.out.println(msg);
						request.setAttribute("msg", msg);
						return "/jsp/login.jsp";
					}
					
				}
				//用户退出
				public String logOut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
				  //清除session
				  request.getSession().invalidate();
				  //重新定向到首页
				  response.sendRedirect("/RentalSystem/index.jsp");
				  return null;
				}
				//CheckUserNameServlet  注册时异步检查用户名是否存在
				public void CheckUserNameServlet(HttpServletRequest request, HttpServletResponse response) throws Exception {
			        try {
						request.setCharacterEncoding("UTF-8");
						//1. 检测是否存在
						String username = request.getParameter("username");
						
						UserService userservice = new UserServiceImp();
						boolean isExist = userservice.checkUserName(username);
						
						//2.  通知页面，到底有还是没有。
						if(isExist){
							response.getWriter().println(1);  //存在用户名
						}else{
							response.getWriter().println(2); //不存在该用户名
						}
						
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
	

}
