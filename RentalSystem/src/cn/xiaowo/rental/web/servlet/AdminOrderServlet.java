package cn.xiaowo.rental.web.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.xiaowo.rental.domain.Money;
import cn.xiaowo.rental.domain.Order;
import cn.xiaowo.rental.domain.PageModel;
import cn.xiaowo.rental.domain.Product;
import cn.xiaowo.rental.service.OrderService;
import cn.xiaowo.rental.service.ProductService;
import cn.xiaowo.rental.service.Impl.OrderServiceImp;
import cn.xiaowo.rental.service.Impl.ProductServiceImp;
import cn.xiaowo.rental.utils.UUIDUtils;
import cn.xiaowo.rental.web.base.baseServlet;
import net.sf.json.JSONArray;

/**
 * 财务信息管理servlet
 */
public class AdminOrderServlet extends baseServlet {
	 //查询处于预定房间阶段的订单
	 public String findOrders1(HttpServletRequest req, HttpServletResponse resp) throws Exception {
			//获取当前页
			int curNum=Integer.parseInt(req.getParameter("num"));
			OrderService OrderService=new OrderServiceImp();
			PageModel pm=null;
			  //获取到全部订单
			pm=OrderService.findAllOrders1(curNum);			
			req.setAttribute("page", pm);
			//转发 /admin/order/list.jsp
			return "/admin/order/list.jsp";
     }
	//异步查询订单的房源详情
	public String findOrderByOidWithAjax(HttpServletRequest req, HttpServletResponse resp) throws Exception {
			//服务端获取到订单ID,
			String pid=req.getParameter("pid");
			//查询这个订单下所有的订单项以及订单项对应的商品信息,返回集合
			OrderService OrderService=new OrderServiceImp();
			Product product=OrderService.findProductByOid(pid);
			//将返回的集合转换为JSON格式字符串,响应到客户端
			String jsonStr=JSONArray.fromObject(product).toString();
			//响应到客户端
			resp.setContentType("application/json;charset=utf-8");
			resp.getWriter().println(jsonStr);
			return null;
	}
	//根据具体状态查询订单
	public String findOrders(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		//获取当前页
		int curNum=Integer.parseInt(req.getParameter("num"));
		OrderService OrderService=new OrderServiceImp();
		PageModel pm=null;
		String st=req.getParameter("state");
		if(null==st||"".equals(st)){
			//获取到全部订单
			pm=OrderService.findAllOrders(curNum);			
		}else{
			pm=OrderService.findAllOrders(curNum,st);
		}
		//将PageModel放入request
		pm.setState(st);
		req.setAttribute("page", pm);
		//转发 /admin/order/list.jsp
		return "/admin/order/list.jsp";
	}

	
	//转出租金到出租人，同时扣除手续费
	public String OutMoney(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		//获取订单ID
		String itemid=req.getParameter("itemid");
		try {
		     //根据订单ID查询订单
		    OrderService OrderService=new OrderServiceImp();
		    Order order=OrderService.findOrderByOid(itemid);
		    Money user1=OrderService.findMoneyByOid("1000");
		    Money user2=OrderService.findMoneyByOid(order.getUid());
		    if(user2==null){
		    	Money user = new Money();
		    	user.setId(UUIDUtils.getCode());
		    	user.setUserid(order.getUid());
		    	user.setMoney(0);
		    	OrderService.CreatMoney(user);
		    	user2=OrderService.findMoneyByOid(order.getUid());
		    }
		    
		    if(user1.getMoney()>order.getTotal()*0.95) {
		      user1.setMoney(user1.getMoney()-order.getTotal()*0.95);
		      user2.setMoney(user2.getMoney()+order.getTotal()*0.95);
		    }else{
		    	int aaa=2/0;
		    }
		    //设置订单状态
		    order.setState(3);
		    //修改订单信息
		    OrderService.updateOrder(order,user1,user2);
		    Product product=OrderService.findProductByOid(order.getPid());
		    ProductService ProductService=new ProductServiceImp();
		    //设置订单状态
			product.setPflag(2);
			//修改订单信息
			ProductService.updateProduct(product);
		    req.setAttribute("msg", "操作成功!");
	     } catch (Exception e) {
		 // 注册失败,跳转到提示页面
		    req.setAttribute("msg", "操作失败，请重新选择!");
	    }
		return "/admin/info.jsp";
	}
	//退定金表示求租人取消订单
	public String OutMoney1(HttpServletRequest req, HttpServletResponse resp)  {
			//获取订单ID
			String itemid=req.getParameter("itemid");
			try {
				//根据订单ID查询订单
				OrderService OrderService=new OrderServiceImp();
				Order order=OrderService.findOrderByOid(itemid);
				Money user1=OrderService.findMoneyByOid("1000");
			    Money user2=OrderService.findMoneyByOid(order.getOid());
			    if(user2==null){
			    	Money user = new Money();
			    	user.setId(UUIDUtils.getCode());
			    	user.setUserid(order.getUid());
			    	user.setMoney(0);
			    	OrderService.CreatMoney(user);
			    	user2=OrderService.findMoneyByOid(order.getUid());
			    }
			    if(user1.getMoney()>100) {
			        user1.setMoney(user1.getMoney()-100);
			        user2.setMoney(user2.getMoney()+100);
			    }else {
			    	int a=2/0;
			    }
				//设置订单状态
				order.setState(5);
				//修改订单信息
				OrderService.updateOrder(order,user1,user2);
				Product product=OrderService.findProductByOid(order.getPid());
			    ProductService ProductService=new ProductServiceImp();
			    //设置房源状态
				product.setPflag(1);
				//修改订单信息
				ProductService.updateProduct(product);
				//重新定向到查询已发货订单
				req.setAttribute("msg", "操作成功!");
			} catch (Exception e) {
				// 注册失败,跳转到提示页面
				req.setAttribute("msg", "操作失败，请重新选择!");
			}
			return "/admin/info.jsp";
	}	
	//账户余额查询
    public String findMoney(HttpServletRequest req, HttpServletResponse resp) throws Exception {
				//获取当前页
				int curNum=Integer.parseInt(req.getParameter("num"));
				OrderService OrderService=new OrderServiceImp();
				PageModel pm=null;
				  //获取到全部订单
				pm=OrderService.findMoney(curNum);			
				req.setAttribute("page", pm);
				//转发 /admin/order/list.jsp
				return "/admin/money/list.jsp";
	 }

}
