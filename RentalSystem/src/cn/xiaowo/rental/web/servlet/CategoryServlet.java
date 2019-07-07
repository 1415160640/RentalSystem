package cn.xiaowo.rental.web.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.xiaowo.rental.domain.Category;
import cn.xiaowo.rental.service.CategoryService;
import cn.xiaowo.rental.service.Impl.CategoryServiceImp;
import cn.xiaowo.rental.utils.JedisUtils;
import cn.xiaowo.rental.web.base.baseServlet;
import net.sf.json.JSONArray;
import redis.clients.jedis.Jedis;

/**
 * 	求租人界面分类管理
 */
public class CategoryServlet extends baseServlet {
	public void findAllCats(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//在redis中获取全部分类信息
		Jedis jedis = JedisUtils.getJedis();
		String jsonStr = jedis.get("allCats");
		if(null == jsonStr || "".equals(jsonStr)) {
			//调用业务层的功能
		   CategoryService category = new CategoryServiceImp();
		   List<Category> list = category.getAllCats();
		   //将全部分类装化成json格式响应回界面
		   jsonStr = JSONArray.fromObject(list).toString();
		    //json格式数据缓存到redis中
		   jedis.set("allCats", jsonStr);
		    //告诉浏览器本次响应的数据是JSON格式的字符串
		   response.setContentType("application/json;Charset=utf-8");
		   response.getWriter().print(jsonStr);
		}else {
			//System.out.println("缓存中有数据");
			  //告诉浏览器本次响应的数据是JSON格式的字符串
			response.setContentType("application/json;Charset=utf-8");
		    response.getWriter().print(jsonStr);
		}
		JedisUtils.closeJedis(jedis);
	}

}
