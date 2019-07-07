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
import cn.xiaowo.rental.domain.PageModel;
import cn.xiaowo.rental.domain.Product;
import cn.xiaowo.rental.domain.lessor;
import cn.xiaowo.rental.service.CategoryService;
import cn.xiaowo.rental.service.ProductService;
import cn.xiaowo.rental.service.UserService;
import cn.xiaowo.rental.service.Impl.CategoryServiceImp;
import cn.xiaowo.rental.service.Impl.ProductServiceImp;
import cn.xiaowo.rental.service.Impl.UserServiceImp;
import cn.xiaowo.rental.utils.UUIDUtils;
import cn.xiaowo.rental.utils.UploadUtils;
import cn.xiaowo.rental.web.base.baseServlet;

//房源servlet
public class AdminProductServlet extends baseServlet {
	    //查询可用房源
		public String findAllProductsWithPage(HttpServletRequest req, HttpServletResponse resp) throws Exception {
			//获取当前页
			int curNum=Integer.parseInt(req.getParameter("num"));
			//调用业务层查全部商品信息返回PageModel
			ProductService ProductService = new ProductServiceImp();
			PageModel pm=ProductService.findAllProductsWithPage(curNum);
			//将PageModel放入request
			req.setAttribute("page", pm);
			//转发到/admin/product/list.jsp
			return "/admin/product/list.jsp";
		}
		//查询不可用房源
		public String findNoProductsWithPage(HttpServletRequest req, HttpServletResponse resp) throws Exception {
					//获取当前页
					int curNum=Integer.parseInt(req.getParameter("num"));
					//调用业务层查全部商品信息返回PageModel
					ProductService ProductService = new ProductServiceImp();
					PageModel pm=ProductService.findNoProductsWithPage(curNum);
					//将PageModel放入request
					req.setAttribute("page", pm);
					//转发到/admin/product/list.jsp
					return "/admin/product/list2.jsp";
	}
		//条件查询所有房源信息
		public String findAllSearProductsWithPage(HttpServletRequest req, HttpServletResponse resp) throws Exception {
			//获取当前页
		    int curNum=Integer.parseInt(req.getParameter("num"));
			//调用业务层查全部商品信息返回PageModel
		    ProductService ProductService=new ProductServiceImp();
		    PageModel pm=ProductService.findAllSearProductsWithPage(curNum);
		    //将PageModel放入request
		    req.setAttribute("page", pm);
		    CategoryService CategoryService=new CategoryServiceImp();
			//获取全部分类信息
			List<Category> list = CategoryService.getAllCats();
			//将全部分类信息放入request
			req.setAttribute("category", list);
			//获取全部地区分类信息
			List<Area> list2 = CategoryService.getAllArea();
			//将全部分类信息放入request
			req.setAttribute("area", list2);
		    //转发到/admin/product/list3.jsp
		    return "/admin/product/list3.jsp";
		}
		//条件查询房源信息
		public String findSearProductsWithPage(HttpServletRequest req, HttpServletResponse resp) throws Exception {
			String pdes =  req.getParameter("pdes");
			String cid =  req.getParameter("cid");
			String aid =  req.getParameter("aid");
			ProductService ProductService=new ProductServiceImp();
			List<Product> products = ProductService.findSearProductsWithPage(pdes,cid,aid); 
			req.setAttribute("products", products);
			CategoryService CategoryService=new CategoryServiceImp();
			//获取全部分类信息
			List<Category> list = CategoryService.getAllCats();
			//将全部分类信息放入request
			req.setAttribute("category", list);
			//获取全部地区分类信息
			List<Area> list2 = CategoryService.getAllArea();
			//将全部分类信息放入request
			req.setAttribute("area", list2);
			return "/admin/product/list4.jsp";	
		}	
		//设置为已出租
		public String pushDownProduct2(HttpServletRequest req, HttpServletResponse resp) throws Exception {
			//获取订单ID
			String pid=req.getParameter("pid");
			//根据订单ID查询订单
			ProductService ProductService=new ProductServiceImp();
			Product product=ProductService.findProductByPid(pid);
			//设置订单状态
			product.setPflag(2);
			//修改订单信息
			ProductService.updateProduct(product);
			//重新定向到查询已发货订单
			resp.sendRedirect("/RentalSystem/AdminProductServlet?method=findAllProductsWithPage&num=1");
			return null;
		}	
		
		//设置为可用
		public String pushUpProduct(HttpServletRequest req, HttpServletResponse resp) throws Exception {
			//获取订单ID
			String pid=req.getParameter("pid");
			//根据订单ID查询订单
			ProductService ProductService=new ProductServiceImp();
			Product product=ProductService.findProductByPid(pid);
			//设置订单状态
			product.setPflag(1);
			//修改订单信息
			ProductService.updateProduct(product);
			//重新定向到查询已发货订单
			resp.sendRedirect("/RentalSystem/AdminProductServlet?method=findNoProductsWithPage&num=1");
			return null;
		}	
		//deleteProduct
		public String deleteProduct(HttpServletRequest req, HttpServletResponse resp) throws Exception {
			//获取订单ID
			String pid=req.getParameter("pid");
			//根据订单ID查询订单
			ProductService ProductService=new ProductServiceImp();
			ProductService.deleteProduct(pid);
			//重新定向到查询已发货订单
			resp.sendRedirect("/RentalSystem/AdminProductServlet?method=findNoProductsWithPage&num=1");
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
			return "/admin/product/edit.jsp";
		}
		//编辑房源信息
		public String editProduct(HttpServletRequest req, HttpServletResponse resp) throws Exception {
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
				//8_调用servcie_dao将user上携带的数据存入数据仓库,重定向到查询全部商品信息路径
				ProductService ProductService=new ProductServiceImp();
				ProductService.editProduct(product);	
				req.setAttribute("msg", "房源信息修改成功！");	
			} catch (Exception e) {
			    req.setAttribute("msg","房源信息修改失败！");	
			}	
			return "/admin/info.jsp";
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
		
}
