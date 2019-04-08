package com.yky.web.action;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/mvc")
public class mvcController {

    @RequestMapping("/user")
    public String hello(){        
        return "user";
    }
}
