/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 * @autoor szh
 */

package com.szh.crm.service;

import com.szh.crm.domain.Bumen;
import java.util.List;

/**
 *
 * @author  szh
 * QQ:873689
 * @date 2018-11-30 8:07:34
 */
public interface BumenService {

    public List<Bumen> findAll();
    
    public Bumen findByCode(String code);
}
