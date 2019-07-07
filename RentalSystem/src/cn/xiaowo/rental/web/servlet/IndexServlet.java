package cn.xiaowo.rental.web.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.xiaowo.rental.domain.Product;
import cn.xiaowo.rental.service.ProductService;
import cn.xiaowo.rental.service.Impl.ProductServiceImp;
import cn.xiaowo.rental.web.base.baseServlet;

/**
 * 初始化求租人首页
 */
public class IndexServlet extends baseServlet {
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
	        //调用业务层查询最新商品,查询最热商品,返回2个集合
			ProductService ProductService=new ProductServiceImp();
			List<Product> list01=ProductService.findHots();
			List<Product> list02=ProductService.findNews();
			//将2个集合放入到request
			request.setAttribute("hots", list01);
			request.setAttribute("news", list02);
			//转发到真实页面
			return "/jsp/index.jsp";
			
		}

}
