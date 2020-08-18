package com.ynrd.config;

import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.realm.jdbc.JdbcRealm;
import org.apache.shiro.realm.text.IniRealm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class Conifg
{

//    @Bean //shiro使用shiro.ini数据源
//    public IniRealm getIniRealm()
//    {
//        IniRealm iniRealm = new IniRealm("classpath:shiro.ini");
//        return iniRealm;
//    }

//    @Bean //使用shiro默认数据表结构数据源
//    public JdbcRealm getJdbcRealm(DataSource dataSource)
//    {
//        JdbcRealm jdbcRealm = new JdbcRealm();
//        jdbcRealm.setDataSource(dataSource);
//        //JdbcRealm默认开启认证功能，需要手动开启授权功能
//        jdbcRealm.setPermissionsLookupEnabled(true);
//        return jdbcRealm;
//    }

    @Bean //设置shiro加密方式
    public HashedCredentialsMatcher getHashedCredentialsMatcher()
    {
        HashedCredentialsMatcher matcher = new HashedCredentialsMatcher();
        //matcher 指定加密规则
        matcher.setHashAlgorithmName("md5");//加密方式
        //hash加密的次数
        matcher.setHashIterations(1);
        return matcher;
    }

    @Bean //自定义shiro数据源
    public MyRealm getMyRealm(HashedCredentialsMatcher matcher)
    {
        MyRealm myRealm = new MyRealm();
        myRealm.setCredentialsMatcher(matcher);
        return myRealm;
    }

    @Bean
    public DefaultWebSecurityManager getDefaultWebSecurityManager(MyRealm myRealm)
    {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        // securityManager 完成校验需要realm
        securityManager.setRealm(myRealm);
        return securityManager;
    }

    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(DefaultWebSecurityManager securityManager)
    {
        ShiroFilterFactoryBean filter = new ShiroFilterFactoryBean();
        //过滤器是shiro进行权限的核心，进行认证和授权是需要securityManager
        filter.setSecurityManager(securityManager);

        //设置拦截规则
        //anon 匿名用户可访问
        //authc 认证用户可访问
        //user 使用remeber me可访问
        //role 有某种角色可访问
        Map<String, String> filterMap = new HashMap<>();
        filterMap.put("/", "anon");
        filterMap.put("/login", "anon");
        filterMap.put("/register", "anon");
        filterMap.put("/user/login", "anon");
        filterMap.put("/user/register", "anon");
        filterMap.put("/static/layui/**", "anon");
        filterMap.put("/static/**", "anon");//不拦截
        filterMap.put("/**", "authc");//登录后不拦截
        filterMap.put("/c_add", "perms[sys:c:save]");//只能拥有sys:c:add权限的才能访问c_add页面
        filterMap.put("/logout", "logout");//退出后

        filter.setFilterChainDefinitionMap(filterMap);
        filter.setLoginUrl("/");
        //设置未授权访问页面路径
        filter.setUnauthorizedUrl("/lesspermission");
        return filter;
    }


}
