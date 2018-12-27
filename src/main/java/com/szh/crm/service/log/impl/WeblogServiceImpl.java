/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 * @autoor szh
 */

package com.szh.crm.service.log.impl;

import com.szh.crm.dao.log.WeblogDao;
import com.szh.crm.domain.log.Weblog;
import com.szh.crm.service.log.WeblogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author  szh
 * QQ:873689
 * @date 2018-12-21 13:25:54
 */
@Service
public class WeblogServiceImpl implements WeblogService{

    @Autowired
    WeblogDao weblogDao;
    
    @Override
    public void add(Weblog weblog) {
        weblogDao.save(weblog);
    }

}
