package com.yky.web.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class LoginInterceptor implements HandlerInterceptor {

	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub

	}

	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
		// TODO Auto-generated method stub

	}

	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
		
		         String uri = request.getRequestURI();
		      
		         if (uri.indexOf("/handle") >= 0) {
		            return true;
		        }
		       
		         HttpSession session = request.getSession();
		         Object name =  session.getAttribute("USER_SESSION");
		       
		         if (name != null) {
		             return true;
		         }
		      
		         request.setAttribute("msg", "请登录");
		         request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request, response);
		         return false;
	}

}
