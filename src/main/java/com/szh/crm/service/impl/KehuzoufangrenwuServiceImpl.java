/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 * @autoor szh
 */

package com.szh.crm.service.impl;

import com.szh.crm.dao.KehuDao;
import com.szh.crm.dao.KehuzoufangDao;
import com.szh.crm.dao.KehuzoufangrenwuDao;
import com.szh.crm.domain.Kehuzoufang;
import com.szh.crm.domain.Kehuzoufangrenwu;
import com.szh.crm.domain.User;
import com.szh.crm.service.KehuzoufangrenwuService;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author  szh
 * QQ:873689
 * @date 2019-1-22 13:51:27
 */
@Service
public class KehuzoufangrenwuServiceImpl implements KehuzoufangrenwuService{

    @Autowired
    KehuzoufangrenwuDao kehuzoufangrenwuDao;
    @Autowired
    KehuzoufangDao kehuzoufangDao;
    @Autowired
    KehuDao kehuDao;
    
    @Override
    public List<Kehuzoufangrenwu> findAll() {
        return kehuzoufangrenwuDao.findByIsdelOrderByShenqingshijianDesc(false);
    }

    @Override
    public List<Kehuzoufangrenwu> findByFaqiren(String faqiren) {
        return kehuzoufangrenwuDao.findByFaqirenAndIsdelOrderByShenqingshijianDesc(faqiren, false);
    }

    @Override
    public List<Kehuzoufangrenwu> findByFaqibumen(String faqibumen) {
        return kehuzoufangrenwuDao.findByFaqibumenAndIsdelOrderByShenqingshijianDesc(faqibumen, false);
    }

    @Override
    public List<Kehuzoufangrenwu> findByRenwujieshouren(String renwujieshouren) {
        return kehuzoufangrenwuDao.findByRenwujieshourenAndIsdelOrderByShenqingshijianDesc(renwujieshouren, false);
    }

    @Override
    public Kehuzoufangrenwu findById(Long id) {
        return kehuzoufangrenwuDao.findByIdAndIsdel(id, false);
    }

    @Override
    public void add(Kehuzoufangrenwu kehuzoufangrenwu) throws Exception {
        if(kehuDao.findByKehumingchengAndIsdel(kehuzoufangrenwu.getKehumingcheng(), false) == null)
            throw new Exception("客户不存在");
        
        kehuzoufangrenwuDao.save(kehuzoufangrenwu);
    }

    @Override
    public void reject(Kehuzoufangrenwu kehuzoufangrenwu, User user) throws Exception {
        kehuzoufangrenwu.setZhuangtai("0");
        kehuzoufangrenwu.setPaifaren(user.getUsername());
        kehuzoufangrenwu.setPaifashijian(new Date());
        kehuzoufangrenwuDao.save(kehuzoufangrenwu);
    }

    @Override
    public void paifa(Kehuzoufangrenwu kehuzoufangrenwu, User user, User jieshouser) throws Exception {
        kehuzoufangrenwu.setZhuangtai("20");
        kehuzoufangrenwu.setPaifaren(user.getUsername());
        kehuzoufangrenwu.setRenwujieshouren(jieshouser.getUsername());
        kehuzoufangrenwu.setPaifashijian(new Date());
        kehuzoufangrenwuDao.save(kehuzoufangrenwu);
    }

    @Override
    @Transactional(rollbackFor=Exception.class)
    public void zoufang(Kehuzoufangrenwu kehuzoufangrenwu, Kehuzoufang kehuzoufang) throws Exception {
        kehuzoufang = kehuzoufangDao.save(kehuzoufang);
        kehuzoufangrenwu.setZhuangtai("30");
        kehuzoufangrenwu.setZoufangshijian(new Date());
        kehuzoufangrenwu.setZoufangid(kehuzoufang.getId());
        kehuzoufangrenwuDao.save(kehuzoufangrenwu);
    }

    @Override
    public List<Kehuzoufangrenwu> findByZhuangtai(String zhuangtai) {
        return kehuzoufangrenwuDao.findByZhuangtaiAndIsdel(zhuangtai, false);
    }

    @Override
    public List<Kehuzoufangrenwu> findByDengdaizoufang(String renwujieshouren) {
        return kehuzoufangrenwuDao.findByRenwujieshourenAndIsdelAndZhuangtai(renwujieshouren, false,"20");
    }

}
