/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 * @autoor szh
 */

package com.szh.crm.domain.log;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import lombok.Data;

/**
 *
 * @author  szh
 * QQ:873689
 * @date 2018-12-21 13:18:34
 */
@Data
@Entity(name="crm_weblog")
public class Weblog implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;
    @Column
    private String url;
    @Column
    private String httpmethod;
    @Column
    private String classmethod;
    @Column
    private String ip;
    @Column
    private String userinfo;
    @Column(columnDefinition = "text")
    private String httpvalue;
    @Column
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date createtime;
    
}
