/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 * @autoor szh
 */

package com.szh.crm.service.impl;

import com.szh.crm.dao.BumenDao;
import com.szh.crm.domain.Bumen;
import com.szh.crm.service.BumenService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author  szh
 * QQ:873689
 * @date 2018-11-30 9:08:17
 */
@Service
public class BumenServiceImpl implements BumenService{

    @Autowired
    BumenDao bumenDao;
    
    @Override
    public List<Bumen> findAll() {
        return bumenDao.findAll();
    }

    @Override
    public Bumen findByCode(String code) {
        return bumenDao.findByJigouhao(code);
    }

}
