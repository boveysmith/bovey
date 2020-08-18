package com.ynrd.config;

import com.ynrd.bean.User;
import com.ynrd.mapper.PermissionMapper;
import com.ynrd.mapper.RoleMapper;
import com.ynrd.mapper.UserMapper;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import javax.annotation.Resource;
import java.util.Set;

/*
 * 1、创建一个类继承AuthorizingRealm（实现了Realm接口的类）
 * 2、重写doGetAuthorizationInfo和doGetAuthenticationInfo方法
 * 3、重写getName方法
 */
public class MyRealm extends AuthorizingRealm
{

    @Resource
    UserMapper userMapper;
    @Resource
    RoleMapper roleMapper;
    @Resource
    PermissionMapper permissionMapper;

    @Override
    public String getName()
    {
        return "MyRealm";
    }

    /**
     * 获取授权数据
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection)
    {
        //获取当前用户的用户名
        String username = (String) principalCollection.iterator().next();
        //根据用户名查询当前用户的角色列表
        Set<String> roleNames = roleMapper.queryRoleNamesByUsername(username);
        //根据用户名查询当前用户的权限列表
        Set<String> ps = permissionMapper.queryPermissionsByUsername(username);
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.setRoles(roleNames);
        info.setStringPermissions(ps);
        return info;
    }

    /**
     * 获取认证的安全数据（从数据库查询的正确数据）
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException
    {
        //参数token就是传递的subject.login(token)
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        //从token中获取用户名
        String username = token.getUsername();
        //根据用户名，查询当前用户的安全数据
        User user = userMapper.queryUserByUsername(username);
        if (user == null)
        {
            return null;
        }
        AuthenticationInfo info = new SimpleAuthenticationInfo(
                username,//用户名
                user.getUserPwd(),//从数据库查询出来的安全密码
                ByteSource.Util.bytes(user.getPwdSalt()),//如果数据库中密码是加盐的,加该参数
                getName());
        return info;
    }
}
