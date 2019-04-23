package com.yky.web.action;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class mvcController {

	@ResponseBody
    @RequestMapping("/handle")
    public Object login(String name,String pwd,HttpSession session){
    		 if(name!=null&&name.equals("admin")&&pwd!=null&&pwd.equals("admin")) {
    			 session.setAttribute("USER_SESSION", name);
    			 return true;
    		 }else {
    			 return false;
    		 }
    		
    	
    	
    	       
    }
    @RequestMapping("/login")
    public String toLogin() {
    	return "login";
    }
    
    @RequestMapping("/index")
    public String index() {
    	return "index";
    }
    
    @RequestMapping( "/logout")
         public String logout(HttpSession session){
             //���session
             session.invalidate();
             //�ض��򵽵�¼ҳ�����ת����
             return "redirect:login";
        }
}
