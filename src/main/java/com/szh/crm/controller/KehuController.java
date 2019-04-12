/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 * @autoor szh
 */

package com.szh.crm.controller;

import com.szh.crm.domain.Kehu;
import com.szh.crm.domain.Kehuzoufang;
import com.szh.crm.domain.User;
import com.szh.crm.service.BumenService;
import com.szh.crm.service.KehuService;
import com.szh.crm.service.UserService;
import java.util.Date;
import java.util.List;
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
 * @date 2018-12-6 12:13:38
 */
@Controller
@ResponseBody
@RequestMapping("/kehu")
public class KehuController extends CommonController{

    final String addhtml = "kehu-add";
    final String querylisthtml  = "kehu-querylist";
    final String updatehtml = "kehu-update";
    
    final String[] listzoufangjieguo =  {"开发中","已合作","已放弃"};
    
    final String[] listshifouyoujidixuqiu =  {"是","否"};
    final String[] listguoneiyewuliang =  {"无","0-20件","20-100件","100-1000件","1000-10000件","万件以上"};
    final String[] listguoneiyewushouru =  {"无","0-400元","400-4000元","4000-10000元","万元以上"};
    final String[] listguojiyewuliang =  {"无","0-20件","20-100件","100-1000件","1000-10000件","万件以上"};
    final String[] listguojiyewushouru =  {"无","0-400元","400-4000元","4000-10000元","万元以上"};
    final String[] listmuqianshiyongkuaidigongsi =  {"顺丰","申通","中通","圆通","韵达","百世汇通","天天","联邦","DHL","TNT","国际专线","优速","EMS","其他"};
    
    
    @Autowired
    KehuService kehuService;
    @Autowired
    BumenService bumenService;
    @Autowired
    UserService userService;
    
    //
    @GetMapping(value = "/add")
    public ModelAndView add(ModelAndView modelAndView){
        Common(modelAndView);
        modelAndView.addObject("listshifouyoujidixuqiu",listshifouyoujidixuqiu);
        modelAndView.addObject("listguoneiyewuliang",listguoneiyewuliang);
        modelAndView.addObject("listguoneiyewushouru",listguoneiyewushouru);
        modelAndView.addObject("listguojiyewuliang",listguojiyewuliang);
        modelAndView.addObject("listguojiyewuliang",listguojiyewuliang);
        modelAndView.addObject("listguojiyewushouru",listguojiyewushouru);
        modelAndView.addObject("listmuqianshiyongkuaidigongsi",listmuqianshiyongkuaidigongsi);
        modelAndView.addObject("listzoufangjieguo",listzoufangjieguo);
        modelAndView.setViewName(addhtml);
        return modelAndView;
    }
    
    @PostMapping(value = "/add")
    public ModelAndView add(ModelAndView modelAndView,Kehu kehu,Kehuzoufang kehuzoufang){
        Subject subject = SecurityUtils.getSubject();
        User user = (User)subject.getPrincipal();
        Common(modelAndView);
        modelAndView.addObject("listshifouyoujidixuqiu",listshifouyoujidixuqiu);
        modelAndView.addObject("listguoneiyewuliang",listguoneiyewuliang);
        modelAndView.addObject("listguoneiyewushouru",listguoneiyewushouru);
        modelAndView.addObject("listguojiyewuliang",listguojiyewuliang);
        modelAndView.addObject("listguojiyewuliang",listguojiyewuliang);
        modelAndView.addObject("listguojiyewushouru",listguojiyewushouru);
        modelAndView.addObject("listmuqianshiyongkuaidigongsi",listmuqianshiyongkuaidigongsi);
        modelAndView.addObject("listzoufangjieguo",listzoufangjieguo);
        kehu.setZhandian(user.getBumen());
        kehu.setChuangjianren(user.getUsername());
        kehu.setDengjishijian(new Date());
        try{
            kehuService.add(kehu,kehuzoufang);
            modelAndView.addObject("msg","增加成功");
        }catch(Exception e){
            modelAndView.addObject("msg",e.getMessage());
            modelAndView.addObject("kehuinfo",kehu);
        }
        modelAndView.setViewName(addhtml);
        return modelAndView;
    }
    
    @GetMapping(value = "/querylist")
    public ModelAndView query(ModelAndView modelAndView){
        Common(modelAndView);
//        List<Kehu> listkehu = null;
//        if(user.getQuanxian().equals("40") ||user.getQuanxian().equals("30")){
//            listkehu= kehuService.findAll();
//        }else if(user.getQuanxian().equals("20")){
//            listkehu=kehuService.findByZhandian(user.getBumen());
//        }else{
//            listkehu=kehuService.findByChuangjianren(user.getUsername());
//        }
//        for(Kehu k:listkehu){
//            k.setZhandian(bumenService.findByCode(k.getZhandian()).getBumen());
//            k.setChuangjianren(userService.findByUsername(k.getChuangjianren()).getName());
//        }
//        modelAndView.addObject("listkehu",listkehu);
        
        modelAndView.setViewName(querylisthtml);
        return modelAndView;
    }
    
    @GetMapping(value = "/querylistrest")
    
    public List queryrest(){
        Subject subject = SecurityUtils.getSubject();
        User user = (User)subject.getPrincipal();
        
        List<Kehu> listkehu = null;
        if(user.getQuanxian().equals("40") ||user.getQuanxian().equals("30")){
            listkehu= kehuService.findAll();
        }else if(user.getQuanxian().equals("20")){
            listkehu=kehuService.findByZhandian(user.getBumen());
        }else{
            listkehu=kehuService.findByChuangjianren(user.getUsername());
        }
        for(Kehu k:listkehu){
            k.setZhandian(bumenService.findByCode(k.getZhandian()).getBumen());
            k.setChuangjianren(userService.findByUsername(k.getChuangjianren()).getName());
        }
        
        return listkehu;
    }
    
    @GetMapping(value = "/update/{kehumingcheng}")
    public ModelAndView update(ModelAndView modelAndView,@PathVariable("kehumingcheng")  String kehumingcheng){
        Subject subject = SecurityUtils.getSubject();
        User user = (User)subject.getPrincipal();
        Common(modelAndView);
        try{
            Kehu kehu = kehuService.findByKehumingcheng(kehumingcheng);
            if(user.getQuanxian().equals("40") ||user.getQuanxian().equals("30")){
                modelAndView.addObject("kehuinfo",kehu);
            }else if(user.getQuanxian().equals("20")){
                if(kehu.getZhandian().equals(user.getBumen())){
                    modelAndView.addObject("kehuinfo",kehu);
                }else{
                    modelAndView.addObject("msg","权限不足");
                }
            }else{
                if(kehu.getChuangjianren().equals(user.getUsername())){
                    modelAndView.addObject("kehuinfo",kehu);
                }else{
                    modelAndView.addObject("msg","权限不足");
                }
            }
        }catch(Exception e){
            e.printStackTrace();
            modelAndView.addObject("msg",e.getMessage());
        }
        modelAndView.addObject("listshifouyoujidixuqiu",listshifouyoujidixuqiu);
        modelAndView.addObject("listguoneiyewuliang",listguoneiyewuliang);
        modelAndView.addObject("listguoneiyewushouru",listguoneiyewushouru);
        modelAndView.addObject("listguojiyewuliang",listguojiyewuliang);
        modelAndView.addObject("listguojiyewuliang",listguojiyewuliang);
        modelAndView.addObject("listguojiyewushouru",listguojiyewushouru);
        modelAndView.addObject("listmuqianshiyongkuaidigongsi",listmuqianshiyongkuaidigongsi);
        modelAndView.setViewName(updatehtml);
        return modelAndView;
    }
    
    @PostMapping(value = "/update/{kehumingcheng}")
    public ModelAndView update(ModelAndView modelAndView,Kehu kehu){
        Subject subject = SecurityUtils.getSubject();
        User user = (User)subject.getPrincipal();
        Common(modelAndView);
        try{
            Kehu kehuinfo =  kehuService.findByKehumingcheng(kehu.getKehumingcheng());
            if(kehuinfo == null){
                throw new Exception("客户不存在");
            }else{
                kehu.setZhandian(kehuinfo.getZhandian());
                kehu.setChuangjianren(kehuinfo.getChuangjianren());
                kehu.setDengjishijian(kehuinfo.getDengjishijian());
            }
            if(user.getQuanxian().equals("40") ||user.getQuanxian().equals("30")){
                kehuService.update(kehu);
                modelAndView.addObject("kehuinfo",kehu);
                modelAndView.addObject("msg","修改成功");
            }else if(user.getQuanxian().equals("20")){
                if(kehu.getZhandian().equals(user.getBumen())){
                    kehuService.update(kehu);
                    modelAndView.addObject("kehuinfo",kehu);
                    modelAndView.addObject("msg","修改成功");
                }else{
                    modelAndView.addObject("msg","权限不足");
                }
            }else{
                if(kehu.getChuangjianren().equals(user.getUsername())){
                    kehuService.update(kehu);
                    modelAndView.addObject("kehuinfo",kehu);
                    modelAndView.addObject("msg","修改成功");
                }else{
                    modelAndView.addObject("msg","权限不足");
                }
            }
        }catch(Exception e){
            e.printStackTrace();
            modelAndView.addObject("msg",e.getMessage());
        }
        modelAndView.addObject("listshifouyoujidixuqiu",listshifouyoujidixuqiu);
        modelAndView.addObject("listguoneiyewuliang",listguoneiyewuliang);
        modelAndView.addObject("listguoneiyewushouru",listguoneiyewushouru);
        modelAndView.addObject("listguojiyewuliang",listguojiyewuliang);
        modelAndView.addObject("listguojiyewuliang",listguojiyewuliang);
        modelAndView.addObject("listguojiyewushouru",listguojiyewushouru);
        modelAndView.addObject("listmuqianshiyongkuaidigongsi",listmuqianshiyongkuaidigongsi);
        modelAndView.setViewName(updatehtml);
        return modelAndView;
    }
    
    @GetMapping(value = "/delete/{kehumingcheng}")
    public ModelAndView delete(ModelAndView modelAndView,@PathVariable("kehumingcheng")  String kehumingcheng){
        Subject subject = SecurityUtils.getSubject();
        User user = (User)subject.getPrincipal();
        Common(modelAndView);
        try{
            Kehu kehuinfo =  kehuService.findByKehumingcheng(kehumingcheng);
            if(kehuinfo == null){
                throw new Exception("客户不存在");
            }
            if(user.getQuanxian().equals("40") ||user.getQuanxian().equals("30")){
                kehuinfo.setIsdel(true);
                kehuService.update(kehuinfo);
                modelAndView.addObject("kehuinfo",kehuinfo);
                modelAndView.addObject("msg","删除成功");
            }else if(user.getQuanxian().equals("20")){
                if(kehuinfo.getZhandian().equals(user.getBumen())){
                    kehuinfo.setIsdel(true);
                    kehuService.update(kehuinfo);
                    modelAndView.addObject("kehuinfo",kehuinfo);
                    modelAndView.addObject("msg","删除成功");
                }else{
                    modelAndView.addObject("msg","权限不足");
                }
            }else{
                if(kehuinfo.getChuangjianren().equals(user.getUsername())){
                    kehuinfo.setIsdel(true);
                    kehuService.update(kehuinfo);
                    modelAndView.addObject("kehuinfo",kehuinfo);
                    modelAndView.addObject("msg","删除成功");
                }else{
                    modelAndView.addObject("msg","权限不足");
                }
            }
        }catch(Exception e){
            e.printStackTrace();
            modelAndView.addObject("msg",e.getMessage());
        }
       
        modelAndView.setViewName(updatehtml);
        return modelAndView;
    }
    
}
