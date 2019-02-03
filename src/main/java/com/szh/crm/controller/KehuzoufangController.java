/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 * @autoor szh
 */

package com.szh.crm.controller;

import com.szh.crm.domain.Kehuzoufang;
import com.szh.crm.domain.User;
import com.szh.crm.service.BumenService;
import com.szh.crm.service.KehuService;
import com.szh.crm.service.KehuzoufangService;
import com.szh.crm.service.UserService;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author  szh
 * QQ:873689
 * @date 2019-1-18 10:33:10
 */
@Controller
@ResponseBody
@RequestMapping("/kehuzoufang")
public class KehuzoufangController extends CommonController{
    
    final String addhtml = "kehuzoufang-add";
    final String queryhtml = "kehuzoufang-query";
    final String querylisthtml = "kehuzoufang-querylist";
    
    final String[] listzoufangjieguo =  {"开发中","已合作","已放弃"};
    
    @Autowired
    KehuService kehuService;
    @Autowired
    KehuzoufangService kehuzoufangService;
    
    @Autowired
    BumenService bumenService;
    @Autowired
    UserService userService;
    
    
    @GetMapping(value = "/add/{kehumingcheng}")
    public ModelAndView add(ModelAndView modelAndView,@PathVariable("kehumingcheng")  String kehumingcheng){
        Common(modelAndView);
        modelAndView.addObject("kehumingcheng",kehumingcheng);
        modelAndView.addObject("listzoufangjieguo",listzoufangjieguo);
        modelAndView.setViewName(addhtml);
        return modelAndView;
    }
    
    @PostMapping(value = "/add/{kehumingcheng}")
    public ModelAndView add(ModelAndView modelAndView,Kehuzoufang kehuzoufang,@PathVariable("kehumingcheng")  String kehumingcheng){
        Common(modelAndView);
        Subject subject = SecurityUtils.getSubject();
        User user = (User)subject.getPrincipal();
        modelAndView.addObject("kehumingcheng",kehumingcheng);
        modelAndView.addObject("listzoufangjieguo",listzoufangjieguo);
        modelAndView.setViewName(addhtml);
        kehuzoufang.setShijian(new Date());
        kehuzoufang.setChuangjianren(user.getUsername());
        kehuzoufang.setBumen(user.getBumen());
        kehuzoufang.setZoufangrizhi(kehuzoufang.getZoufangrizhi());
        try {
            kehuzoufangService.add(kehuzoufang);
            return new ModelAndView("redirect:/kehu/querylist");
        } catch (Exception ex) {
            modelAndView.addObject("msg",ex.getMessage());
        }
        
        return modelAndView;
    }
    
    @GetMapping(value = "/query/{kehumingcheng}")
    public ModelAndView query(ModelAndView modelAndView,@PathVariable("kehumingcheng")  String kehumingcheng){
        Common(modelAndView);
        
        
        modelAndView.addObject("kehumingcheng",kehumingcheng);
        modelAndView.setViewName(queryhtml);
        return modelAndView;
    }
    
    @GetMapping(value ="/queryrest/{kehumingcheng}")
     public List queryrest(@PathVariable("kehumingcheng")  String kehumingcheng){
        List<Kehuzoufang> list =  kehuzoufangService.findBykehu(kehumingcheng);
        for(Kehuzoufang k:list){
            k.setBumen(bumenService.findByCode(k.getBumen()).getBumen());
            k.setChuangjianren(userService.findByUsername(k.getChuangjianren()).getName());
        }
        
        return list;
     }
     
    @GetMapping(value = "/querylist")
    public ModelAndView querylist(ModelAndView modelAndView){
        Common(modelAndView);
        
        modelAndView.setViewName(querylisthtml);
        return modelAndView;
    }
    @GetMapping(value ="/querylistrest")
     public List querylistrest(){
        Subject subject = SecurityUtils.getSubject();
        User user = (User)subject.getPrincipal();
        List<Kehuzoufang> list = null;
        if(user.getQuanxian().equals("40") ||user.getQuanxian().equals("30")){
            list= kehuzoufangService.findAll();
        }else if(user.getQuanxian().equals("20")){
            list=kehuzoufangService.findByBumen(user.getBumen());
        }else{
            list=kehuzoufangService.findByChuangjianren(user.getUsername());
        } 
         
         
        for(Kehuzoufang k:list){
            k.setBumen(bumenService.findByCode(k.getBumen()).getBumen());
            k.setChuangjianren(userService.findByUsername(k.getChuangjianren()).getName());
        }
        
        return list;
     }
}
