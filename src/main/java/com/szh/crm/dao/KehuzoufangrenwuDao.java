/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 * @autoor szh
 */

package com.szh.crm.dao;

import com.szh.crm.domain.Kehuzoufangrenwu;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author  szh
 * QQ:873689
 * @date 2019-1-22 13:39:32
 */
@Repository
public interface KehuzoufangrenwuDao extends JpaRepository<Kehuzoufangrenwu,Long>{
    
    public Kehuzoufangrenwu findByIdAndIsdel(Long id,boolean isdel);

    public List<Kehuzoufangrenwu> findByRenwujieshourenAndIsdelOrderByShenqingshijianDesc(String renwujieshouren,boolean isdel);
    
    public List<Kehuzoufangrenwu> findByRenwujieshourenAndIsdelAndZhuangtai(String renwujieshouren,boolean isdel,String zhuangtai);
    
    public List<Kehuzoufangrenwu> findByFaqirenAndIsdelOrderByShenqingshijianDesc(String faqiren,boolean isdel);
    
    public List<Kehuzoufangrenwu> findByFaqibumenAndIsdelOrderByShenqingshijianDesc(String faqibumen,boolean isdel);
    
    public List<Kehuzoufangrenwu> findByIsdelOrderByShenqingshijianDesc(boolean isdel);
    
    public List<Kehuzoufangrenwu> findByZhuangtaiAndIsdel(String zhuangtai,boolean isdel);
}
