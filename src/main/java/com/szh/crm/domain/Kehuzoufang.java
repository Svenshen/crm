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
 * @date 2019-1-17 10:12:53
 */
@Data
@Entity(name="crm_kehuzoufang")
public class Kehuzoufang implements Serializable {

    @Id
    @Column
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    Long id;
    @Column
    String kehumingcheng;
    @Column
    String zoufangjieguo;
    @Column(columnDefinition = "text")
    String zoufangrizhi;
    @Column
    String bumen;
    @Column
    String chuangjianren;
    @Column
    Date shijian;
    @Column
    boolean isdel = false;
}
