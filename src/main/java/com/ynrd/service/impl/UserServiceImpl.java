package com.ynrd.service.impl;

import com.ynrd.controller.UserController;
import com.ynrd.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl  implements UserService
{
    private static Logger logger = LogManager.getLogger(UserServiceImpl.class);

    public boolean checkLogin(String userName, String userPwd)
    {
        try
        {
            Subject subject = SecurityUtils.getSubject();
            UsernamePasswordToken token = new UsernamePasswordToken(userName, userPwd);
            subject.login(token);
            logger.info("--------登录成功------");
            return true;
        }
        catch (Exception e)
        {
            logger.info("--------登录失败------");
            return false;
        }

    }

}
