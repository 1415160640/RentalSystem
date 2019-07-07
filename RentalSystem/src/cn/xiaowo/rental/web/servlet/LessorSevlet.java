package cn.xiaowo.rental.web.servlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;

import cn.xiaowo.rental.domain.Area;
import cn.xiaowo.rental.domain.Category;
import cn.xiaowo.rental.domain.Money;
import cn.xiaowo.rental.domain.Order;
import cn.xiaowo.rental.domain.PageModel;
import cn.xiaowo.rental.domain.Product;
import cn.xiaowo.rental.domain.lessor;
import cn.xiaowo.rental.service.CategoryService;
import cn.xiaowo.rental.service.LessorService;
import cn.xiaowo.rental.service.OrderService;
import cn.xiaowo.rental.service.ProductService;
import cn.xiaowo.rental.service.UserService;
import cn.xiaowo.rental.service.Impl.CategoryServiceImp;
import cn.xiaowo.rental.service.Impl.LessorServiceImpl;
import cn.xiaowo.rental.service.Impl.OrderServiceImp;
import cn.xiaowo.rental.service.Impl.ProductServiceImp;
import cn.xiaowo.rental.service.Impl.UserServiceImp;
import cn.xiaowo.rental.utils.MyBeanUtils;
import cn.xiaowo.rental.utils.UUIDUtils;
import cn.xiaowo.rental.utils.UploadUtils;
import cn.xiaowo.rental.web.base.baseServlet;

/**
 * 出租人servlet
 */
public class LessorSevlet extends baseServlet {
	 //登入
	public String userLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
	        //获取用户数据
			lessor user = new lessor();
			MyBeanUtils.populate(user, request.getParameterMap());
			//调用业务层功能
			LessorService userservice = new LessorServiceImpl();
			OrderService OrderService=new OrderServiceImp();
			Money mymoney=null;
			lessor user02 = null;
			try {
				user02 = userservice.LessorUserLogin(user);//返回用户的所有信息
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
				request.getSession().setAttribute("lessorloginUser", user02);
				response.sendRedirect("/RentalSystem/lessor/home.jsp");//重定向
				return null;
			} catch (Exception e) {
				//用户登入失败
				String msg = e.getMessage();
				request.setAttribute("msg", msg);
				return "/lessor/index.jsp";
			}
			
	 }
	//出租人退出
	public String logOut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		  //清除session
		  request.getSession().invalidate();
		  return "/lessor/index.jsp";
	} 
	 //查询个人房源
	 public String findAllProductsWithPage(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		//获取当前页
		int curNum=Integer.parseInt(req.getParameter("num"));
		//调用业务层查全部商品信息返回PageModel
		LessorService userservice = new LessorServiceImpl();
		lessor user = (lessor) req.getSession().getAttribute("lessorloginUser");
		if(user == null){
			req.setAttribute("msg", "请先登入再执行操作");
			return "/lessor/info.jsp";
		}
		PageModel pm=userservice.findAllProductsWithPage(curNum,user.getUid());
		//将PageModel放入request
		req.setAttribute("page", pm);
		//转发到/admin/product/list.jsp
		return "/lessor/product/list.jsp";
	 }
	//设置不可用
	 public String pushDownProduct(HttpServletRequest req, HttpServletResponse resp) throws Exception {
	  			//获取订单ID
	  			String pid=req.getParameter("pid");
	  			//根据订单ID查询订单
	  			ProductService ProductService=new ProductServiceImp();
	  			Product product=ProductService.findProductByPid(pid);
	  			//设置订单状态
	  			product.setPflag(0);
	  			//修改订单信息
	  			ProductService.updateProduct(product);
	  			//重新定向到查询已发货订单
	  			resp.sendRedirect("/RentalSystem/LessorSevlet?method=findAllProductsWithPage&num=1");
	  			return null;
	  	}	
   //设置为已出租
  	public String pushDownProduct2(HttpServletRequest req, HttpServletResponse resp) throws Exception {
  			//获取订单ID
  			String pid=req.getParameter("pid");
  			//根据订单ID查询订单
  			ProductService ProductService=new ProductServiceImp();
  			Product product=ProductService.findProductByPid(pid);
  			//设置订单状态
  			product.setPflag(4);
  			//修改订单信息
  			ProductService.updateProduct(product);
  			//重新定向到查询已发货订单
  			resp.sendRedirect("/RentalSystem/LessorSevlet?method=findAllProductsWithPage&num=1");
  			return null;
  	}	
  	//设置为可用
  	public String pushUpProduct(HttpServletRequest req, HttpServletResponse resp) throws Exception {
  			//获取订单ID
  			String pid=req.getParameter("pid");
  			//根据订单ID查询订单
  			ProductService ProductService=new ProductServiceImp();
  			Product product=ProductService.findProductByPid(pid);
  			//设置房源信息状态
  			product.setPflag(1);
  			//修改订单信息
  			ProductService.updateProduct(product);
  			//重新定向到查询已发货订单
  			resp.sendRedirect("/RentalSystem/LessorSevlet?method=findAllProductsWithPage&num=1");
  			return null;
  		}	
  		//删除个人房源
  		public String deleteProduct(HttpServletRequest req, HttpServletResponse resp) throws Exception {
  			//获取订单ID
  			String pid=req.getParameter("pid");
  			//根据订单ID查询订单
  			ProductService ProductService=new ProductServiceImp();
  			ProductService.deleteProduct(pid);
  			//重新定向到查询已发货订单
  			resp.sendRedirect("/RentalSystem/LessorSevlet?method=findAllProductsWithPage&num=1");
  			return null;
  		}	
         //跳转到编辑房源信息界面
  		public String updateProduct(HttpServletRequest req, HttpServletResponse resp) throws Exception {
  			//获取订单ID
  			String pid=req.getParameter("pid");
  			//根据订单ID查询订单
  			ProductService ProductService=new ProductServiceImp();
  			Product product=ProductService.findProductByPid(pid);
  			//存数据
  			req.setAttribute("product", product);
  			CategoryService CategoryService=new CategoryServiceImp();
  			//获取全部分类信息
  			List<Category> list = CategoryService.getAllCats();
  			//将全部分类信息放入request
  			req.setAttribute("category", list);
  			List<Area> list2 = CategoryService.getAllArea();
  			//将全部分类信息放入request
  			req.setAttribute("area", list2);
  			//转发
  			return "/lessor/product/edit.jsp";
  		}
  		//编辑房源信息
  		public String editProduct(HttpServletRequest req, HttpServletResponse resp) throws Exception {
  			lessor user = (lessor) req.getSession().getAttribute("lessorloginUser");
  			if(user == null){
  				req.setAttribute("msg", "请先登入再执行操作");
  				return "/lessor/info.jsp";
  			}
  			//存储表单中数据
  			Map<String,String> map=new HashMap<String,String>();
  			//携带表单中的数据向servcie,dao
  			Product product=new Product();
  			try {
  				//利用req.getInputStream();获取到请求体中全部数据,进行拆分和封装
  				DiskFileItemFactory fac=new DiskFileItemFactory();
  				ServletFileUpload upload=new ServletFileUpload(fac);
  				List<FileItem> list=upload.parseRequest(req);
  				//4_遍历集合
  				for (FileItem item : list) {
  					if(item.isFormField()){
  						//5_如果当前的FileItem对象是普通项
  						//将普通项上name属性的值作为键,将获取到的内容作为值,放入MAP中
  						// {username<==>tom,password<==>1234}
  						map.put(item.getFieldName(), item.getString("utf-8"));
  					}else{
  						//6_如果当前的FileItem对象是上传项
  						//获取到原始的文件名称
  						String oldFileName=item.getName();
  						if(!oldFileName.isEmpty()) {
  						  //获取到要保存文件的名称   1222.doc  123421342143214.doc
  						 String newFileName=UploadUtils.getUUIDName(oldFileName);
  						 //通过FileItem获取到输入流对象,通过输入流可以获取到图片二进制数据
  						  InputStream is=item.getInputStream();
  						 //获取到当前项目下products/3下的真实路径
  						 //D:\tomcat\tomcat71_sz07\webapps\store_v5\products\3
  						 String realPath=getServletContext().getRealPath("/products/3/");
  						 String dir=UploadUtils.getDir(newFileName); // /f/e/d/c/4/9/8/4
  						 String path=realPath+dir; //D:\tomcat\tomcat71_sz07\webapps\store_v5\products\3/f/e/d/c/4/9/8/4
  						 //内存中声明一个目录
  						 File newDir=new File(path);
  						 if(!newDir.exists()){
  							 newDir.mkdirs();
  						 }
  						 //在服务端创建一个空文件(后缀必须和上传到服务端的文件名后缀一致)
  						 File finalFile=new File(newDir,newFileName);
  						 if(!finalFile.exists()){
  							finalFile.createNewFile();
  						 }
  						 //建立和空文件对应的输出流
  						 OutputStream os=new FileOutputStream(finalFile);
  						 //将输入流中的数据刷到输出流中 is-->os
  						 IOUtils.copy(is, os);
  						 //释放资源
  						 IOUtils.closeQuietly(is);
  						 IOUtils.closeQuietly(os);
  						 //向map中存入一个键值对的数据 userhead<===> /image/11.bmp
  						 // {username<==>tom,password<==>1234,userhead<===>image/11.bmp}
  						 map.put("pimage", "/products/3/"+dir+"/"+newFileName);
  						}
  					}
  				}
  				//7_利用BeanUtils将MAP中的数据填充到Product对象上
  				BeanUtils.populate(product, map);
  				product.setUid(user.getUid());
  				//8_调用servcie_dao将user上携带的数据存入数据仓库,重定向到查询全部商品信息路径
  				ProductService ProductService=new ProductServiceImp();
  				ProductService.editProduct(product);	
  				req.setAttribute("msg", "房源信息修改成功！");	
  			} catch (Exception e) {
  			    req.setAttribute("msg","房源信息修改失败！");	
  			}	
  			return "/lessor/info.jsp";
  		}
  		//异步计算租金和物业费
  		public void SumPrice(HttpServletRequest request, HttpServletResponse response) throws ServletException, Exception {
  	        try {
  				request.setCharacterEncoding("UTF-8");
  				//1. 检测是否存在
  				String aid = request.getParameter("aid");
  				double aaa=Double.parseDouble(request.getParameter("area"));
  				CategoryService CategoryService=new CategoryServiceImp();
  				Area area=CategoryService.getAreaByCid(aid);
  				double price=0;
  				if(area != null){
  					price=aaa*area.getHouse_price()*area.getManger_price();
  					response.getWriter().println(price); 
  				}else{
  					response.getWriter().println(price); 
  				}
  				
  			} catch (SQLException e) {
  				e.printStackTrace();
  			}
  		}
  		 //添加商品
  	    public String addProductUI(HttpServletRequest request, HttpServletResponse response) throws Exception {
  	  
  				CategoryService CategoryService=new CategoryServiceImp();
  				//获取全部分类信息
  				List<Category> list = CategoryService.getAllCats();
  				//将全部分类信息放入request
  				request.setAttribute("category", list);
  				List<Area> list2 = CategoryService.getAllArea();
  				//将全部分类信息放入request
  				request.setAttribute("area", list2);
  	            return "/lessor/product/add.jsp";
  	    }
  	    
     	//添加房源信息
  		public String addProduct(HttpServletRequest req, HttpServletResponse resp) throws Exception {
  			    lessor user = (lessor) req.getSession().getAttribute("lessorloginUser");
  			    if(user == null){
  				  req.setAttribute("msg", "请先登入再执行操作");
  				   return "/lessor/info.jsp";
  			    }
  				//存储表单中数据
  				Map<String,String> map=new HashMap<String,String>();
  				//携带表单中的数据向servcie,dao
  				Product product=new Product();
  				try {
  					//利用req.getInputStream();获取到请求体中全部数据,进行拆分和封装
  					DiskFileItemFactory fac=new DiskFileItemFactory();
  					ServletFileUpload upload=new ServletFileUpload(fac);
  					List<FileItem> list=upload.parseRequest(req);
  					//4_遍历集合
  					for (FileItem item : list) {
  						if(item.isFormField()){
  							//5_如果当前的FileItem对象是普通项
  							//将普通项上name属性的值作为键,将获取到的内容作为值,放入MAP中
  							// {username<==>tom,password<==>1234}
  							map.put(item.getFieldName(), item.getString("utf-8"));
  						}else{
  							//6_如果当前的FileItem对象是上传项
  							
  							//获取到原始的文件名称
  							String oldFileName=item.getName();
  							//获取到要保存文件的名称   1222.doc  123421342143214.doc
  							String newFileName=UploadUtils.getUUIDName(oldFileName);
  							
  							//通过FileItem获取到输入流对象,通过输入流可以获取到图片二进制数据
  							InputStream is=item.getInputStream();
  							//获取到当前项目下products/3下的真实路径
  							//D:\tomcat\tomcat71_sz07\webapps\store_v5\products\3
  							String realPath=getServletContext().getRealPath("/products/3/");
  							String dir=UploadUtils.getDir(newFileName); // /f/e/d/c/4/9/8/4
  							String path=realPath+dir; //D:\tomcat\tomcat71_sz07\webapps\store_v5\products\3/f/e/d/c/4/9/8/4
  							//内存中声明一个目录
  							File newDir=new File(path);
  							if(!newDir.exists()){
  								newDir.mkdirs();
  							}
  							//在服务端创建一个空文件(后缀必须和上传到服务端的文件名后缀一致)
  							File finalFile=new File(newDir,newFileName);
  							if(!finalFile.exists()){
  								finalFile.createNewFile();
  							}
  							//建立和空文件对应的输出流
  							OutputStream os=new FileOutputStream(finalFile);
  							//将输入流中的数据刷到输出流中 is-->os
  							IOUtils.copy(is, os);
  							//释放资源
  							IOUtils.closeQuietly(is);
  							IOUtils.closeQuietly(os);
  							//向map中存入一个键值对的数据 userhead<===> /image/11.bmp
  							// {username<==>tom,password<==>1234,userhead<===>image/11.bmp}
  							map.put("pimage", "/products/3/"+dir+"/"+newFileName);
  					}
  				}
  					//7_利用BeanUtils将MAP中的数据填充到Product对象上
  					BeanUtils.populate(product, map);
  					product.setPid(UUIDUtils.getId());
  					product.setPflag(1);
  					product.setUid(user.getUid());
  					
  					//8_调用servcie_dao将user上携带的数据存入数据仓库,重定向到查询全部商品信息路径
  					ProductService ProductService=new ProductServiceImp();
  					ProductService.saveProduct(product);
  					
  					req.setAttribute("msg", "房源信息插入成功！");	
  				} catch (Exception e) {
  					req.setAttribute("msg", "房源信息插入失败！");	  				}
  				
  				return "/lessor/info.jsp";
  			}
  	    //根据具体状态查询订单
  		public String findOrders(HttpServletRequest req, HttpServletResponse resp) throws Exception {
  			 lessor user = (lessor) req.getSession().getAttribute("lessorloginUser");
			 if(user == null){
				  req.setAttribute("msg", "请先登入再执行操作");
				   return "/lessor/info.jsp";
			 }
  			//获取当前页
  			int curNum=Integer.parseInt(req.getParameter("num"));
  			LessorService OrderService=new LessorServiceImpl();
  			PageModel pm=null;
  			String st=req.getParameter("state");
  			if(null==st||"".equals(st)){
  				//获取到全部订单
  				pm=OrderService.findAllOrders(curNum,user.getUid());			
  			}else{
  				pm=OrderService.findAllOrders(curNum,st,user.getUid());
  			}
  			//将PageModel放入request
  			pm.setState(st);
  			req.setAttribute("page", pm);
  			//转发 /admin/order/list.jsp
  			return "/lessor/order/list.jsp";
  		}

  		
  		//确认退房把押金退给求租人
  		public String OutMoney(HttpServletRequest req, HttpServletResponse resp) throws Exception {
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
				order.setState(4);
				//修改订单信息
				OrderService.updateOrder(order,user1,user2);
				Product product=OrderService.findProductByOid(order.getPid());
			    ProductService ProductService=new ProductServiceImp();
			    //设置订单状态
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
		
	
}
