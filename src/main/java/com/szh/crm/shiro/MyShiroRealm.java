/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 * @autoor szh
 */

package com.szh.crm.shiro;

import antlr.MismatchedTokenException;
import com.szh.crm.domain.User;
import com.szh.crm.service.UserService;
import javax.annotation.Resource;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author  szh
 * QQ:873689
 * @date 2018-9-6 17:23:03
 */
public class MyShiroRealm extends AuthorizingRealm{
    @Resource
    UserService userService;
    
    /**
     * 
     * @param token
     * @return
     * @throws AuthenticationException 
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        //System.out.println("1213213 -- >");
        String username = (String) token.getPrincipal();
        User peichangUser = userService.findByUsername(username);
        //System.out.println("---->userinfo="+peichangUser);
        if(peichangUser == null){
            return null;
        }
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(peichangUser,peichangUser.getPassword(),getName());
        //System.out.println("---->userinfo22="+authenticationInfo);
        return authenticationInfo;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection pc) {
        System.out.println("权限配置 -- >");
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        User userInfo  = (User)pc.getPrimaryPrincipal();
        authorizationInfo.addRole(userInfo.getQuanxian());
        return authorizationInfo;
    }
    
    
    

}
