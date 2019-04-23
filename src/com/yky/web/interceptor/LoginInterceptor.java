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
		//��ȡ�����RUi:ȥ��http:localhost:8080�ⲿ��ʣ�µ�
		         String uri = request.getRequestURI();
		        //UTL:����login.jsp�ǿ��Թ������ʵģ�������URL���������ؿ���
		         if (uri.indexOf("/handle") >= 0) {
		            return true;
		        }
		         //��ȡsession
		         HttpSession session = request.getSession();
		         Object name =  session.getAttribute("USER_SESSION");
		         //�ж�session���Ƿ����û����ݣ�����У��򷵻�true����������ִ��
		         if (name != null) {
		             return true;
		         }
		         //�����������ĸ�����ʾ��Ϣ����ת������¼ҳ��
		         request.setAttribute("msg", "����û�е�¼�����ȵ�¼��");
		         request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request, response);
		         return false;
	}

}
