package com.ynrd.controller;


import com.ynrd.service.UserService;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.Random;

@Controller
@RequestMapping(value = "user")
public class UserController
{
    private static Logger logger = LogManager.getLogger(UserController.class);
    @Resource
    private UserService userService;

    @RequestMapping(value ="/login")
    public String login(String userName, String userPwd)
    {
        logger.info("--------用户登录------");
        if (userService.checkLogin(userName, userPwd))
        {
            return "index";
        }
        return "login";
    }

    @RequestMapping(value ="/register")
    public String register(String userName, String userPwd)
    {
        logger.info("--------用户注册------");
        //注册时要加密处理
        String salt = new Random().nextInt(90000) + 10000+"";
        Md5Hash md5Hash2 = new Md5Hash(userPwd, salt);
        logger.info("--加盐加密->> "+md5Hash2+"---盐： "+salt);
        return "login";
    }

    @RequestMapping(value ="/logout")
    public String logout()
    {
        Subject subject = SecurityUtils.getSubject();
        logger.info("-----用户：" + subject.getPrincipal()+" 退出登录---");
        subject.logout();
        return "login";
    }

}
