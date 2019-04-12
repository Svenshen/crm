/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 * @autoor szh
 */

package com.szh.crm.service;

import com.szh.crm.domain.Kehu;
import com.szh.crm.domain.Kehuzoufang;
import java.util.List;

/**
 *
 * @author  szh
 * QQ:873689
 * @date 2018-12-6 14:39:02
 */
public interface KehuService {
    
    public List<Kehu> findAll();
    
    public List<Kehu> findAlldel();
    
    public List<Kehu> findByChuangjianren(String chuangjianren);
    
    public List<Kehu> findByZhandian(String zhandian);
    
    public Kehu findByKehumingcheng(String kehumingcheng) ;
    
    public Kehu findByKehumingchengdel(String kehumingcheng) ;
    
    
    public void add(Kehu kehu) throws Exception;
    
    public void add(Kehu kehu,Kehuzoufang kehuzoufang) throws Exception;
    
    public void update(Kehu kehu) throws Exception;
}
