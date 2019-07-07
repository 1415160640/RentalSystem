package cn.xiaowo.rental.utils;

import javax.servlet.http.Cookie;
//从一个cookie数组中找到需要的对象
public class CookieUtil{
	    public static Cookie findCookie(Cookie[] cookies,String name) {
			if(cookies !=null) {
				 for(Cookie cookie : cookies) {
					 if(name.equals(cookie.getName())) {
						 return cookie;
					 }
				 }
			}
	    	return null;   	
	    }

}
