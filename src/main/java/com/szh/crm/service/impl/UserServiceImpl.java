/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 * @autoor szh
 */

package com.szh.crm.service.impl;

import com.szh.crm.dao.UserDao;
import com.szh.crm.domain.User;
import com.szh.crm.service.UserService;
import com.szh.crm.util.CheckPassword;
import java.util.List;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author  szh
 * QQ:873689
 * @date 2018-9-7 10:59:08
 */
@Service
public class UserServiceImpl implements UserService{

    @Autowired
    UserDao userDao;
    
    /**
     * 
     * @param username
     * @return 
     */
    @Override
    public User findByUsername(String username) {
        return userDao.findByUsernameAndIsdel(username,false);
    }

    
    
    /**
     * 
     * @param user
     * @return 
     */
    @Override
    public void updatePassword(User user) throws Exception{
        
        if(!userDao.existsById(user.getUsername())) throw new Exception("用户不存在");
        if(!CheckPassword.checkPassword(user.getPassword())) throw new Exception("密码强度不够，请重新输入");
        String password = String.valueOf(new SimpleHash("MD5",user.getPassword(),null,1024));
        user.setPassword(password);
        userDao.save(user);
    }

    @Override
    public void addUser(User user) throws Exception{
        
        if(userDao.existsById(user.getUsername())) throw new Exception("用户已存在");
        if(!CheckPassword.checkPassword(user.getPassword())) throw new Exception("密码强度不够，请重新输入");
        String password = String.valueOf(new SimpleHash("MD5",user.getPassword(),null,1024));
        user.setPassword(password);
        userDao.save(user);
       
    }

    @Override
    public void delUser(String username) throws Exception{
        
        User info = findByUsername(username);
        if(info == null) throw new Exception("用户不存在");
        info.setIsdel(true);
        userDao.save(info);
        
    }

    @Override
    public void updateUser(User user) throws Exception{
        if(!userDao.existsById(user.getUsername())) throw new Exception("用户不存在");
        if(user.getPassword().equals("") || user.getPassword() == null){
            user.setPassword(findByUsername(user.getName()).getPassword());
        }else{
            if(!CheckPassword.checkPassword(user.getPassword())) throw new Exception("密码强度不够，请重新输入");
            user.setPassword(String.valueOf(new SimpleHash("MD5",user.getPassword(),null,1024)));
        }   
        userDao.save(user);
    }

    @Override
    public List<User> findAll() {
        return userDao.findByIsdel(false);
    }

    @Override
    public List<User> findAlldel() {
        return userDao.findByIsdel(true);
    }

    @Override
    public User findByUsernamedel(String username) {
        return userDao.findByUsernameAndIsdel(username,true);
    }

    
}
