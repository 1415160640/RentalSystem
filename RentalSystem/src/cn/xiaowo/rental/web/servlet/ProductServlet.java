package cn.xiaowo.rental.web.servlet;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.xiaowo.rental.domain.Money;
import cn.xiaowo.rental.domain.Order;
import cn.xiaowo.rental.domain.PageModel;
import cn.xiaowo.rental.domain.Product;
import cn.xiaowo.rental.domain.User;
import cn.xiaowo.rental.domain.lessor;
import cn.xiaowo.rental.service.OrderService;
import cn.xiaowo.rental.service.ProductService;
import cn.xiaowo.rental.service.Impl.OrderServiceImp;
import cn.xiaowo.rental.service.Impl.ProductServiceImp;
import cn.xiaowo.rental.utils.CookieUtil;
import cn.xiaowo.rental.utils.UUIDUtils;
import cn.xiaowo.rental.web.base.baseServlet;


/**
 * 求租人显示房源信息
 */
public class ProductServlet extends baseServlet {
	public String indexUI(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		return "/jsp/index.jsp";
	}
	//查看商品详情
	public String findProductByPid(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String pid = request.getParameter("pid");
		ProductService ProductService = new ProductServiceImp();
		Product product = ProductService.findProductByPid(pid);
		lessor lessor = ProductService.fingLessorByuid(product.getUid());
		request.setAttribute("lessor", lessor);
		request.setAttribute("product", product);
		return "/jsp/product_info.jsp";
	}
    //分类分页查询房源
	public String findProductsByCidWithPage(HttpServletRequest request, HttpServletResponse response) throws Exception {	
		//获取cid,num
		String cid=request.getParameter("cid");
		int curNum=Integer.parseInt(request.getParameter("num"));
		//调用业务层功能:以分页形式查询当前类别下商品信息
		//返回PageModel对象(1_当前页商品信息2_分页3_url)
		ProductService ProductService=new ProductServiceImp();
		PageModel pm=ProductService.findProductsByCidWithPage(cid,curNum);
		//将PageModel对象放入request
		request.setAttribute("page", pm);
		request.setAttribute("cid", cid);
		//转发到/jsp/product_list.jsp
		return  "/jsp/product_list.jsp";
	}
	//添加订单
	public String addOrder(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		User user = (User) req.getSession().getAttribute("loginUser");
		if(user == null){
			req.setAttribute("msg", "请先登入再执行操作");
			return "/jsp/login.jsp";
		}
		try {
				//获取到商品id,数量
				String pid=req.getParameter("pid");
				String uid=req.getParameter("uid");
				int num=Integer.parseInt(req.getParameter("quantity"));
				double price=Double.parseDouble(req.getParameter("price"));
				//通过商品id查询都商品对象
				ProductService ProductService=new ProductServiceImp();
				Product product=ProductService.findProductByPid(pid);
				//获取到待购买的购物项
				Order order=new Order();
				order.setItemid(UUIDUtils.getCode());
				order.setQuantity(num);
				order.setTotal(num*price);
				order.setState(1);
				order.setPid(pid);
				order.setUid(uid);
				order.setOid(user.getUid());
				order.setDate(new Date());
				ProductService.insertOrder(order);
			    //设置订单状态
				product.setPflag(3);
				//修改订单信息
				ProductService.updateProduct(product);
				OrderService OrderService=new OrderServiceImp();
				Money user1=OrderService.findMoneyByOid("1000");
			    Money user2=OrderService.findMoneyByOid(order.getOid());
				if(user2==null){
				    	Money user0 = new Money();
				    	user0.setId(UUIDUtils.getCode());
				    	user0.setUserid(order.getUid());
				    	user0.setMoney(0);
				    	OrderService.CreatMoney(user0);
				    	user2=OrderService.findMoneyByOid(order.getOid());
				 }
				 if(user1.getMoney()>100) {
				        user1.setMoney(user1.getMoney()+100);
				        user2.setMoney(user2.getMoney()-100);
				 }else {
				    	int a=2/0;
				 }
				//设置订单状态
			    order.setState(0);
				//保存订单信息
			    OrderService.updateOrder(order,user1,user2);
			    req.setAttribute("msg", "操作成功!");
		} catch (Exception e) {
			// 注册失败,跳转到提示页面
			req.setAttribute("msg", "操作失败，请重新选择!");
		}
		return  "/jsp/info.jsp";
	}
	//查询求租人所有订单
	public String findMyOrdersWithPage(HttpServletRequest req, HttpServletResponse resp) throws Exception {
				//获取用户信息
				User user=(User)req.getSession().getAttribute("loginUser");
				//获取当前页
				int curNum=Integer.parseInt(req.getParameter("num"));
				//调用业务层功能:查询当前用户订单信息,返回PageModel
				OrderService OrderService=new OrderServiceImp();
				//SELECT * FROM orders WHERE uid=? limit ? , ? 
				//PageModel:1_分页参数 2_url  3_当前用户的当前页的订单(集合) ,每笔订单上对应的订单项,以及订单项对应的商品信息
				PageModel pm=OrderService.findMyOrdersWithPage(user,curNum);
				//将PageModel放入request
				req.setAttribute("page", pm);
				//转发到/jsp/order_list.jsp
				return "/jsp/order_list.jsp";
				
	}
    //求租人取消预定
	public String NoOrderByitemid(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		//获取到订单oid
		String oid=req.getParameter("itemid");
		//调用业务层功能:根据订单编号查询订单信息
		OrderService OrderService=new OrderServiceImp();
		Order order=OrderService.findOrderByOid(oid);
	    try {
		   order.setState(2);
		   OrderService.updateOrder(order);
		   req.setAttribute("msg", "操作成功!");
	    } catch (Exception e) {
		      //失败,跳转到提示页面
		   req.setAttribute("msg", "操作失败，请重新选择!");
	    }
	    return  "/jsp/info.jsp";	
	}
	//求租人确认订单
	public String YesOrderByitemid(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		//获取到订单oid
		String oid=req.getParameter("itemid");
		//调用业务层功能:根据订单编号查询订单信息
		OrderService OrderService=new OrderServiceImp();
		Order order=OrderService.findOrderByOid(oid);
		try {	
			ProductService ProductService=new ProductServiceImp();
		    Product product=ProductService.findProductByPid(order.getPid());
		    product.setPflag(2);//房源转态变为已出租
			//修改订单信息
			ProductService.updateProduct(product);
		    Money user1=OrderService.findMoneyByOid("1000");
	        Money user2=OrderService.findMoneyByOid(order.getOid());
		    if(user2==null){
		    	Money user0 = new Money();
		    	user0.setId(UUIDUtils.getCode());
		    	user0.setUserid(order.getUid());
		    	user0.setMoney(0);
		    	OrderService.CreatMoney(user0);
		    	user2=OrderService.findMoneyByOid(order.getOid());
		   }
		   if(user2.getMoney()>order.getTotal()) {
		        user1.setMoney(user1.getMoney()+order.getTotal());
		        user2.setMoney(user2.getMoney()-order.getTotal());
		   }else {
		    	int a=2/0;
		   }
		   order.setState(1);
		   OrderService.updateOrder(order, user1, user2);
	        req.setAttribute("msg", "操作成功!");
       } catch (Exception e) {
	      //失败,跳转到提示页面
	      req.setAttribute("msg", "操作失败，请重新选择!");
       }
       return  "/jsp/info.jsp";	
	}
	
}
