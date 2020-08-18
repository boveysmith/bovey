package com.ynrd.config;


import com.jagregory.shiro.freemarker.ShiroTags;
import freemarker.template.Configuration;
import freemarker.template.TemplateModelException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import javax.annotation.PostConstruct;

@Component
public class ShiroTagsFreeMarkerCfg
{
    @Autowired
    private FreeMarkerConfigurer freeMarkerConfigurer;

    @PostConstruct
    public void setSharedVariable() throws TemplateModelException
    {
        Configuration cfg =  freeMarkerConfigurer.getConfiguration();
        cfg.setSharedVariable("shiro", new ShiroTags());
        cfg.setNumberFormat("#");//防止页面输出数字,变成2,000
    }
}
