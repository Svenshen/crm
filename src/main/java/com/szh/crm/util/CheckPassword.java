/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 * @autoor szh
 */

package com.szh.crm.util;

/**
 *
 * @author  szh
 * QQ:873689
 * @date 2018-11-29 14:18:39
 */

public class CheckPassword {

    /**
     * 密码强度
     * 
     * @return Z = 字母 S = 数字 T = 特殊字符
     */

/*  一、假定密码字符数范围6-16，除英文数字和字母外的字符都视为特殊字符：
    弱：^[0-9A-Za-z]{6,16}$
    中：^(?=.{6,16})[0-9A-Za-z]*[^0-9A-Za-z][0-9A-Za-z]*$
    强：^(?=.{6,16})([0-9A-Za-z]*[^0-9A-Za-z][0-9A-Za-z]*){2,}$
    二、假定密码字符数范围6-16，密码字符允许范围为ASCII码表字符：
    弱：^[0-9A-Za-z]{6,16}$
    中：^(?=.{6,16})[0-9A-Za-z]*[\x00-\x2f\x3A-\x40\x5B-\xFF][0-9A-Za-z]*$
    强：^(?=.{6,16})([0-9A-Za-z]*[\x00-\x2F\x3A-\x40\x5B-\xFF][0-9A-Za-z]*){2,}$*/
    public static boolean checkPassword(String passwordStr) {
        String regexZ = "\\d*";
        String regexS = "[a-zA-Z]+";
        String regexT = "\\W+$";
        String regexZT = "\\D*";
        String regexST = "[\\d\\W]*";
        String regexZS = "\\w*";
        String regexZST = "[\\w\\W]*";

        if (passwordStr.matches(regexZ)) {
            return false;
        }
        if (passwordStr.matches(regexS)) {
            return false;
        }
        if (passwordStr.matches(regexT)) {
            return false;
        }
        if (passwordStr.matches(regexZT)) {
            return false;
        }
        if (passwordStr.matches(regexST)) {
            return false;
        }
        if (passwordStr.matches(regexZS)) {
            return false;
        }
        if (passwordStr.matches(regexZST)) {
            return true;
        }
        return false;

    }
}
