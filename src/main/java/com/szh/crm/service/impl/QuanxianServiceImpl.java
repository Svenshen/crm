/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 * @autoor szh
 */

package com.szh.crm.service.impl;

import com.szh.crm.dao.QuanxianDao;
import com.szh.crm.domain.Quanxian;
import com.szh.crm.service.QuanxianService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author  szh
 * QQ:873689
 * @date 2018-11-30 9:08:05
 */
@Service
public class QuanxianServiceImpl implements QuanxianService{

    @Autowired
    QuanxianDao quanxianDao;
    
    @Override
    public List<Quanxian> findAll() {
        return quanxianDao.findAll();
    }

    @Override
    public Quanxian findQuanxian(String quanxian) {
        return quanxianDao.findByQuanxian(quanxian);
    }
    
}
