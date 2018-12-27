/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 * @autoor szh
 */

package com.szh.crm.service;

import com.szh.crm.domain.User;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author  szh
 * QQ:873689
 * @date 2018-9-6 14:40:06
 */
public interface UserService {
    
    public User findByUsername(String username);
    
    public User findByUsernamedel(String username);
    
    
    public void updatePassword(User user) throws Exception;
    
    public void addUser(User user) throws Exception;
    
    public void delUser(String  username) throws Exception;
    
    public void updateUser(User user) throws Exception;
    
    public List<User> findAll();
    
    public List<User> findAlldel();
}
