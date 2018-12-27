/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 * @autoor szh
 */

package com.szh.crm.dao;

import com.szh.crm.domain.Kehu;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author  szh
 * QQ:873689
 * @date 2018-12-6 14:38:07
 */
@Repository
public interface KehuDao extends  JpaRepository<Kehu,String>{

    public List<Kehu> findByZhandianAndIsdel(String zhandian,boolean isdel);
    
    public List<Kehu> findByChuangjianrenAndIsdel(String chuangjianren,boolean isdel);
    
    public Kehu findByKehumingchengAndIsdel(String kehumingcheng,boolean isdel);
    
    public List<Kehu> findByIsdel(boolean isdel);
}
