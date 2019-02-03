/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 * @autoor szh
 */

package com.szh.crm.dao;


import com.szh.crm.domain.User;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author  szh
 * QQ:873689
 * @date 2018-9-6 14:38:33
 */
@Repository
public interface UserDao extends  JpaRepository<User,String>{
    
    public User findByUsernameAndIsdel(String username,boolean isdel);
    
    public List<User> findByIsdel(boolean isdel);
    
    public User findByUsername(String username);
    
}
