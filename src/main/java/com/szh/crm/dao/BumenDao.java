/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 * @autoor szh
 */

package com.szh.crm.dao;

import com.szh.crm.domain.Bumen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author  szh
 * QQ:873689
 * @date 2018-11-30 8:06:07
 */
@Repository
public interface BumenDao extends  JpaRepository<Bumen,String>{
    
    public Bumen findByJigouhao(String jigouhao);
    
}
