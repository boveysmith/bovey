package com.ynrd.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController
{

   @RequestMapping({"/","/login"})
   public String login()
   {
        return "login";
   }

    @RequestMapping(value ="/register")
    public String register()
    {
        return "register";
    }

    @RequestMapping(value ="/index")
    public String index()
    {
        return "index";
    }

    @RequestMapping(value ="/lesspermission")
    public String lessPermission()
    {
        return "lesspermission";
    }

    @RequestMapping(value ="/c_add")
    public String c_add()
    {
        return "c_add";
    }
}
