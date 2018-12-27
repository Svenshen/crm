/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 * @autoor szh
 */

package com.szh.crm.dao;

import com.szh.crm.domain.Quanxian;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author  szh
 * QQ:873689
 * @date 2018-11-30 8:06:17
 */
@Repository
public interface QuanxianDao extends  JpaRepository<Quanxian,String>{
    
    public Quanxian findByQuanxian(String quanxian);
}
