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
		//获取请求的RUi:去除http:localhost:8080这部分剩下的
		         String uri = request.getRequestURI();
		        //UTL:除了login.jsp是可以公开访问的，其他的URL都进行拦截控制
		         if (uri.indexOf("/handle") >= 0) {
		            return true;
		        }
		         //获取session
		         HttpSession session = request.getSession();
		         Object name =  session.getAttribute("USER_SESSION");
		         //判断session中是否有用户数据，如果有，则返回true，继续向下执行
		         if (name != null) {
		             return true;
		         }
		         //不符合条件的给出提示信息，并转发到登录页面
		         request.setAttribute("msg", "您还没有登录，请先登录！");
		         request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request, response);
		         return false;
	}

}
