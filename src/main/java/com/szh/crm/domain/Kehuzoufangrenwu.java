/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 * @autoor szh
 */

package com.szh.crm.domain;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Data;

/**
 *
 * @author  szh
 * QQ:873689
 * @date 2019-1-22 13:32:14
 */
@Data
@Entity(name="crm_kehuzoufangrenwu")
public class Kehuzoufangrenwu implements Serializable {

    @Id
    @Column
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    Long id;
    @Column
    String kehumingcheng;
    @Column
    String faqiren;
    @Column
    String faqibumen;
    @Column
    String paifaren;
    @Column
    String renwujieshouren;
    @Column
    Long zoufangid;
    
    /**
     * 0拒绝
     * 10发起
     * 20派发
     * 30已走访
     */
    @Column
    String zhuangtai = "10";
    @Column
    Date shenqingshijian;
    @Column
    Date paifashijian;
    @Column
    Date zoufangshijian;
    @Column
    boolean isdel=false;
    
}
