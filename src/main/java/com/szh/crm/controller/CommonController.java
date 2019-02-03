/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 * @autoor szh
 */

package com.szh.crm.controller;

import com.szh.crm.domain.User;
import com.szh.crm.service.KehuzoufangrenwuService;
import java.util.List;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author  szh
 * QQ:873689
 * @date 2019-2-2 14:12:57
 */
@Controller
public class CommonController {
    
    @Autowired
    KehuzoufangrenwuService kehuzoufangrenwuService;
    
    
    public void Common(ModelAndView modelAndView){
        Subject subject = SecurityUtils.getSubject();
        User user = (User)subject.getPrincipal();
        modelAndView.addObject("user",user);
        //findByRenwujieshouren
        List list = kehuzoufangrenwuService.findByDengdaizoufang(user.getUsername());
        if(list == null){
            modelAndView.addObject("renwunum",0);
        }else{
            modelAndView.addObject("renwunum",list.size());
        }
        
    }
    
}
