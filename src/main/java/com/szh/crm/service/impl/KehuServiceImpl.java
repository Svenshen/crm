/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 * @autoor szh
 */

package com.szh.crm.service.impl;

import com.szh.crm.dao.KehuDao;
import com.szh.crm.dao.KehuzoufangDao;
import com.szh.crm.domain.Kehu;
import com.szh.crm.domain.Kehuzoufang;
import com.szh.crm.service.KehuService;
import com.szh.crm.service.KehuzoufangService;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author  szh
 * QQ:873689
 * @date 2018-12-6 14:39:56
 */
@Service
public class KehuServiceImpl implements KehuService{

    @Autowired
    KehuDao kehuDao;
    @Autowired
    KehuzoufangService kehuzoufangService;
    
    @Override
    public List<Kehu> findAll() {
        return kehuDao.findByIsdel(false);
    }

    @Override
    public void add(Kehu kehu) throws Exception{
        if(kehuDao.existsById(kehu.getKehumingcheng()))  throw new Exception("客户已存在");
        
        kehuDao.save(kehu);
        
    }

    @Override
    public void update(Kehu kehu) throws Exception{
        if(!kehuDao.existsById(kehu.getKehumingcheng()))  throw new Exception("客户不存在");
        kehuDao.save(kehu);
    }

    @Override
    public List<Kehu> findByChuangjianren(String chuangjianren) {
        return kehuDao.findByChuangjianrenAndIsdel(chuangjianren,false);
    }

    @Override
    public List<Kehu> findByZhandian(String zhandian) {
        return kehuDao.findByZhandianAndIsdel(zhandian,false);
    }

    @Override
    public Kehu findByKehumingcheng(String kehumingcheng) {
        return kehuDao.findByKehumingchengAndIsdel(kehumingcheng, false);
    }

    @Override
    public Kehu findByKehumingchengdel(String kehumingcheng) {
        return kehuDao.findByKehumingchengAndIsdel(kehumingcheng, true);
    }

    @Override
    public List<Kehu> findAlldel() {
        return kehuDao.findByIsdel(true);
    }

    @Override
    @Transactional(rollbackFor=Exception.class)
    public void add(Kehu kehu, Kehuzoufang kehuzoufang) throws Exception {
        kehuzoufang.setBumen(kehu.getZhandian());
        kehuzoufang.setChuangjianren(kehu.getChuangjianren());
        kehuzoufang.setShijian(new Date());
        add(kehu);
        kehuzoufangService.add(kehuzoufang);
    }

}
