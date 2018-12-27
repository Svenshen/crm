/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 * @autoor szh
 */

package com.szh.crm.domain;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.Data;

/**
 *
 * @author  szh
 * QQ:873689
 * @date 2018-11-29 16:43:37
 */
@Data
@Entity(name="crm_quanxian")
public class Quanxian implements Serializable {
    @Id
    @Column
    String quanxian;
    @Column
    String quanxianname;
}
