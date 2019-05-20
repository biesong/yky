package com.yky.web.action;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yky.web.entity.Report;
import com.yky.web.service.BaseService;
import com.yky.web.service.EmailService;

@Controller
public class UserController {
	@Autowired
	BaseService service;
	@Autowired
	EmailService emaiService;
	@RequestMapping("/user")
	public String toUser() {
		return "user";
	}
	@ResponseBody
	@RequestMapping("/getuser")
	public Report getUser() {
		return service.getData("select count(t2.UserID)y,CONVERT ( VARCHAR ( 100 ), RegistTime, 111 )x from T_UserInfo t2 where DateDiff(dd,RegistTime,getdate())<=30 and CONVERT ( VARCHAR ( 100 ), RegistTime, 111 )<CONVERT ( VARCHAR ( 100 ), GETDATE(), 111 ) group by CONVERT ( VARCHAR ( 100 ), RegistTime, 111 ) order by CONVERT ( VARCHAR ( 100 ), RegistTime, 111 )");
	}
	
	@ResponseBody
	@RequestMapping("/gevxtuser")
	public Report getvxUser() {
		return service.getData("select count(t.UserID)y, CONVERT(varchar(100), t.AddTime, 111) x from T_WXRelationInfo  t where DateDiff(dd,t.AddTime,getdate())<=30" 
	                         + " and CONVERT(varchar(100), t.AddTime, 111)<CONVERT(varchar(100), GETDATE(), 111)"
				             + " and t.UserID  not in(select t2.UserID from T_UserInfo t2 where  CONVERT ( VARCHAR ( 100 ), RegistTime, 111 )<  CONVERT ( VARCHAR ( 100 ),t.addtime, 111 ) )"
	                         + " group by CONVERT(varchar(100), t.AddTime, 111) order by CONVERT(varchar(100), t.AddTime, 111)");
	}
	@ResponseBody
	@RequestMapping("/getvcode")
	public Report getvcode() {
		return service.getData("select CONVERT(varchar(100), addTime, 111)x,count(verifycode)y from T_MemberSendCode where DateDiff(dd,addTime,getdate())<=30 and CONVERT(varchar(100), addTime, 111)<CONVERT(varchar(100), GETDATE(), 111)" 
	                        + "  group by CONVERT(varchar(100), addTime, 111) order by CONVERT(varchar(100), addTime, 111)");
	}
	@ResponseBody
	@RequestMapping("/email")
	public Object sendEmail() {
		emaiService.sendMailSimple("bs_632237845@126.com", "", "");
		return "xxx";
	}
}
