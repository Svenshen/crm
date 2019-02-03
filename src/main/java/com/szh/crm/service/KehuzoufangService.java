/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 * @autoor szh
 */

package com.szh.crm.service;

import com.szh.crm.domain.Kehuzoufang;
import java.util.List;

/**
 *
 * @author  szh
 * QQ:873689
 * @date 2019-1-18 10:10:15
 */
public interface KehuzoufangService {

    public List<Kehuzoufang> findAll();
    
    public Kehuzoufang findById(Long id);
    
    public List<Kehuzoufang> findBykehu(String kehumingcheng);
    
    public List<Kehuzoufang> findByBumen(String bumen);
    
    public List<Kehuzoufang> findByChuangjianren(String chuangjianren);
    
    public void add(Kehuzoufang kehuzoufang) throws Exception;
    
    
}
