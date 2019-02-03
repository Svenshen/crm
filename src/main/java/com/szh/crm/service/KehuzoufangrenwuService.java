/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 * @autoor szh
 */

package com.szh.crm.service;

import com.szh.crm.domain.Kehuzoufang;
import com.szh.crm.domain.Kehuzoufangrenwu;
import com.szh.crm.domain.User;
import java.util.List;

/**
 *
 * @author  szh
 * QQ:873689
 * @date 2019-1-22 13:47:55
 */
public interface KehuzoufangrenwuService {

    public List<Kehuzoufangrenwu> findAll();
    
    public List<Kehuzoufangrenwu> findByFaqiren(String faqiren);
    
    public List<Kehuzoufangrenwu> findByFaqibumen(String faqibumen);
    
    public List<Kehuzoufangrenwu> findByRenwujieshouren(String renwujieshouren);
    
    public List<Kehuzoufangrenwu> findByDengdaizoufang(String renwujieshouren);
    
    public Kehuzoufangrenwu findById(Long id);
    
    public void add(Kehuzoufangrenwu kehuzoufangrenwu) throws Exception;
    
    public void reject(Kehuzoufangrenwu kehuzoufangrenwu,User user) throws Exception;
    
    public void paifa(Kehuzoufangrenwu kehuzoufangrenwu,User user,User jieshouser) throws Exception;
    
    public void zoufang(Kehuzoufangrenwu kehuzoufangrenwu,Kehuzoufang kehuzoufang) throws Exception;
    
    public List<Kehuzoufangrenwu> findByZhuangtai(String zhuangtai);
}
