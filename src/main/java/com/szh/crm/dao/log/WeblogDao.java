/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 * @autoor szh
 */

package com.szh.crm.dao.log;

import com.szh.crm.domain.log.Weblog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author  szh
 * QQ:873689
 * @date 2018-12-21 13:34:53
 */
@Repository
public interface WeblogDao extends  JpaRepository<Weblog,Long>{

}
