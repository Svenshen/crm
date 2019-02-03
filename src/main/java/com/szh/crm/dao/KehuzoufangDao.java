/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 * @autoor szh
 */

package com.szh.crm.dao;

import com.szh.crm.domain.Kehuzoufang;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author  szh
 * QQ:873689
 * @date 2019-1-18 10:04:39
 */
@Repository
public interface KehuzoufangDao extends JpaRepository<Kehuzoufang,Long>{

    public Kehuzoufang findByIdAndIsdel(Long id,boolean isdel);
    
    public List<Kehuzoufang> findByKehumingchengAndIsdelOrderByShijianDesc(String kehumingcheng,boolean isdel);
    
    public List<Kehuzoufang> findByBumenAndIsdelOrderByShijianDesc(String bumen,boolean isdel);
    
    public List<Kehuzoufang> findByChuangjianrenAndIsdelOrderByShijianDesc(String chuangjianren,boolean isdel);
    
    public List<Kehuzoufang> findByIsdelOrderByShijianDesc(boolean isdel);
}
