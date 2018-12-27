/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 * @autoor szh
 */

package com.szh.crm.service;

import com.szh.crm.domain.Quanxian;
import java.util.List;

/**
 *
 * @author  szh
 * QQ:873689
 * @date 2018-11-30 9:07:52
 */
public interface QuanxianService {

    public List<Quanxian> findAll();
    
    public Quanxian findQuanxian(String quanxian);
}
