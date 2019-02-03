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
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author  szh
 * QQ:873689
 * @date 2019-1-18 10:12:27
 */
@Service
public class KehuzoufangServiceImpl implements KehuzoufangService{

    @Autowired
    KehuzoufangDao kehuzoufangDao;
    @Autowired
    KehuDao kehuDao;
    
    @Override
    public List<Kehuzoufang> findAll() {
        return kehuzoufangDao.findByIsdelOrderByShijianDesc(false);
    }

    @Override
    public List<Kehuzoufang> findBykehu(String kehumingcheng) {
        return kehuzoufangDao.findByKehumingchengAndIsdelOrderByShijianDesc(kehumingcheng, false);
    }

    @Override
    @Transactional(rollbackFor=Exception.class)
    public void add(Kehuzoufang kehuzoufang) throws Exception{
        Kehu kehu = kehuDao.findByKehumingchengAndIsdel(kehuzoufang.getKehumingcheng(), false);
        kehu.setZoufangjieguo(kehuzoufang.getZoufangjieguo());
        kehuDao.save(kehu);
        kehuzoufangDao.save(kehuzoufang);
    }

    @Override
    public List<Kehuzoufang> findByBumen(String bumen) {
        return kehuzoufangDao.findByBumenAndIsdelOrderByShijianDesc(bumen, false);
    }

    @Override
    public List<Kehuzoufang> findByChuangjianren(String chuangjianren) {
        return kehuzoufangDao.findByChuangjianrenAndIsdelOrderByShijianDesc(chuangjianren, false);
    }

    @Override
    public Kehuzoufang findById(Long id) {
        return kehuzoufangDao.findByIdAndIsdel(id, false);
    }

}
