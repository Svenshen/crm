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
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.validation.constraints.NotEmpty;
import lombok.Data;

/**
 *
 * @author  szh
 * QQ:873689
 * @date 2018-12-6 12:13:48
 */
@Data
@Entity(name="crm_kehu")
public class Kehu implements Serializable{

    @Id
    @Column
    String kehumingcheng;
    @Column
    Date dengjishijian;
    @Column
    String zhandian;
    @Column
    @NotEmpty(message="地址不能为空")
    String dizhi;
    @Column
    @NotEmpty(message="联系人不能为空")
    String lianxiren;
    @Column
    @NotEmpty(message="联系电话不能为空")
    String lianxidianhua;
    @Column
    String shifouyoujidixuqiu;
    @Column
    String  guoneiyewuliang;
    @Column
    String guoneiyewushouru;
    @Column
    String guojiyewuliang;
    @Column
    String guojiyewushouru;
    @Column
    String muqianshiyongkuaidigongsi;
    @Column
    String beizhu;
    @Column
    String zoufangjieguo;
    @Column
    String chuangjianren;
    @Column
    boolean isdel = false;
}
