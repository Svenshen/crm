/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 * @autoor szh
 */

package com.szh.crm.controller;

import com.szh.crm.domain.Kehuzoufang;
import com.szh.crm.domain.Kehuzoufangrenwu;
import com.szh.crm.domain.User;
import com.szh.crm.service.BumenService;
import com.szh.crm.service.KehuzoufangService;
import com.szh.crm.service.KehuzoufangrenwuService;
import com.szh.crm.service.UserService;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
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
 * @date 2019-1-22 14:33:19
 */
@Controller
@ResponseBody
@RequestMapping("/kehuzoufangrenwu")
public class KehuzoufangrenwuController extends CommonController{
    
    final String addhtml = "kehuzoufangrenwu-add";
    final String querylisthtml = "kehuzoufangrenwu-querylist";
    final String shenqingshenpilisthtml= "kehuzoufangrenwu-shenpilist";
    final String kehuzoufangrenwuzoufanglog = "kehuzoufangrenwu-zoufanglog";
    final String shenqingshenpihtml="kehuzoufangrenwu-shenpi";
    final String kehuzoufangrenwurenwu = "kehuzoufangrenwu-renwu";
    final String kehuzoufangrenwurenwuzoufang = "kehuzoufangrenwu-renwuzoufang";
    final String kehuzoufangrenwuqueryzoufang = "kehuzoufangrenwu-queryzoufang";
    
    
    final String[] listzoufangjieguo =  {"开发中","已合作","已放弃"};
    
    @Autowired
    KehuzoufangrenwuService kehuzoufangrenwuService;
    @Autowired
    KehuzoufangService kehuzoufangService;
    @Autowired
    UserService userService;
    
    @Autowired
    BumenService bumenService;
    
    
    @GetMapping(value = "/add/{kehumingcheng}")
    public ModelAndView add(ModelAndView modelAndView,@PathVariable("kehumingcheng")  String kehumingcheng){
        Common(modelAndView);
        Subject subject = SecurityUtils.getSubject();
        User user = (User)subject.getPrincipal();
        Kehuzoufangrenwu kehuzoufangrenwu = new Kehuzoufangrenwu();
        kehuzoufangrenwu.setKehumingcheng(kehumingcheng);
        kehuzoufangrenwu.setFaqiren(user.getUsername());
        kehuzoufangrenwu.setFaqibumen(user.getBumen());
        kehuzoufangrenwu.setShenqingshijian(new Date());
        try{
            kehuzoufangrenwuService.add(kehuzoufangrenwu);
        }catch(Exception e){
            e.printStackTrace();
        }
        
        return new ModelAndView("redirect:/kehuzoufangrenwu/querylist");
    }
    
    @GetMapping(value = "/querylist")
    public ModelAndView querylist(ModelAndView modelAndView){
        Common(modelAndView);
        modelAndView.setViewName(querylisthtml);
        return modelAndView;
    }
    
    
    @GetMapping(value = "/querylistrest")
    public List querylistrest(){
        Subject subject = SecurityUtils.getSubject();
        User user = (User)subject.getPrincipal();
        List<Kehuzoufangrenwu> list = null;
        if(user.getQuanxian().equals("40") ){
            list = kehuzoufangrenwuService.findAll();
        }else if(user.getQuanxian().equals("20") ||user.getQuanxian().equals("30")){
            list = kehuzoufangrenwuService.findByFaqibumen(user.getBumen());
        }else{
            list = kehuzoufangrenwuService.findByFaqiren(user.getUsername());
        }
         /**
        * 0拒绝
        * 10发起
        * 20派发
        * 30已走访
        */
        for(Kehuzoufangrenwu k : list){
            k.setFaqibumen( k.getFaqibumen() != null ? bumenService.findByCode(k.getFaqibumen()).getBumen() : "");
            k.setFaqiren( k.getFaqiren() != null ? userService.findByUsername(k.getFaqiren()).getName() : "");
            k.setPaifaren(k.getPaifaren() != null ? userService.findByUsername(k.getPaifaren()).getName() : "");
            k.setRenwujieshouren(k.getRenwujieshouren() != null ? userService.findByUsername(k.getRenwujieshouren()).getName() : "");
            switch(k.getZhuangtai()){
                case "0":
                    k.setZhuangtai("已拒绝");
                    break;
                case "10":
                    k.setZhuangtai("审核中");
                    break;
                case "20":
                    k.setZhuangtai("等待走访");
                    break;
                case "30":
                    k.setZhuangtai("已走访");
                    break;
            }
            
        }
        
        return list;
    }
    //shenqingshenpilist
    @GetMapping(value = "/shenqingshenpilist")
    @RequiresRoles(value={"40","99"}, logical= Logical.OR)
    public ModelAndView shenqingshenpilist(ModelAndView modelAndView){
        Common(modelAndView);
        List<User> listuser = userService.findAll();
        List<User> listusers = new ArrayList<>();
        for(User userx:listuser){
            switch (userx.getQuanxian()){
                case "10":
                    break;
                default:
                    listusers.add(userx);
                    break;
            }
            userx.setPassword("");
        }
        modelAndView.addObject("users",listusers);
        modelAndView.setViewName(shenqingshenpilisthtml);
        return modelAndView;
    }
    
    
    
    @GetMapping(value = "/shenqingshenpilistrest")
    @RequiresRoles(value={"40","99"}, logical= Logical.OR)
    public List shenqingshenpilistrest(){
        List<Kehuzoufangrenwu> list = kehuzoufangrenwuService.findByZhuangtai("10");
         /**
        * 0拒绝
        * 10发起
        * 20派发
        * 30已走访
        */
        for(Kehuzoufangrenwu k : list){
            k.setFaqibumen( k.getFaqibumen() != null ? bumenService.findByCode(k.getFaqibumen()).getBumen() : "");
            k.setFaqiren( k.getFaqiren() != null ? userService.findByUsername(k.getFaqiren()).getName() : "");
            k.setPaifaren(k.getPaifaren() != null ? userService.findByUsername(k.getPaifaren()).getName() : "");
            k.setRenwujieshouren(k.getRenwujieshouren() != null ? userService.findByUsername(k.getRenwujieshouren()).getName() : "");
            switch(k.getZhuangtai()){
                case "0":
                    k.setZhuangtai("已拒绝");
                    break;
                case "10":
                    k.setZhuangtai("审核中");
                    break;
                case "20":
                    k.setZhuangtai("等待走访");
                    break;
                case "30":
                    k.setZhuangtai("已走访");
                    break;
            }
            
        }
        return list;
    }
    
    @GetMapping(value = "/zoufanglog/{id}")
    public ModelAndView zoufanglog(ModelAndView modelAndView,@PathVariable("id")  String id){
        Common(modelAndView);
        modelAndView.addObject("id",id);
        modelAndView.setViewName(kehuzoufangrenwuzoufanglog);
        return modelAndView;
    }
    
    //kehuzoufangrenwu
    @PostMapping(value = "/paifa")
    @RequiresRoles(value={"40","99"}, logical= Logical.OR)
    public ModelAndView shenhe(ModelAndView modelAndView,String jieshourenusername,Long id){
        Common(modelAndView);
        Subject subject = SecurityUtils.getSubject();
        User user = (User)subject.getPrincipal();
        try{
            kehuzoufangrenwuService.paifa(kehuzoufangrenwuService.findById(id),user, userService.findByUsername(jieshourenusername));
        }catch(Exception e){
            e.printStackTrace();
        }
        return new ModelAndView("redirect:/kehuzoufangrenwu/shenqingshenpilist");
    }
    
    @GetMapping(value = "/reject/{id}")
    @RequiresRoles(value={"40","99"}, logical= Logical.OR)
    public ModelAndView reject(ModelAndView modelAndView,@PathVariable("id")  Long id){
        Common(modelAndView);
        Subject subject = SecurityUtils.getSubject();
        User user = (User)subject.getPrincipal();
        try{
            kehuzoufangrenwuService.reject(kehuzoufangrenwuService.findById(id), user);
        }catch(Exception e){
            e.printStackTrace();
        }
        return new ModelAndView("redirect:/kehuzoufangrenwu/shenqingshenpilist");
    }
    
    
    @GetMapping(value = "/renwu")
    public ModelAndView zoufangrenwu(ModelAndView modelAndView){
        Common(modelAndView);
        modelAndView.setViewName(kehuzoufangrenwurenwu);
        return modelAndView;
    }
    
    @GetMapping(value = "/renwurest")
    public List zoufangrenwurest(ModelAndView modelAndView){
        Subject subject = SecurityUtils.getSubject();
        User user = (User)subject.getPrincipal();
        
        List<Kehuzoufangrenwu> listk = kehuzoufangrenwuService.findByRenwujieshouren(user.getUsername());
        for(Kehuzoufangrenwu k : listk){
            k.setFaqibumen( k.getFaqibumen() != null ? bumenService.findByCode(k.getFaqibumen()).getBumen() : "");
            k.setFaqiren( k.getFaqiren() != null ? userService.findByUsername(k.getFaqiren()).getName() : "");
            k.setPaifaren(k.getPaifaren() != null ? userService.findByUsername(k.getPaifaren()).getName() : "");
            k.setRenwujieshouren(k.getRenwujieshouren() != null ? userService.findByUsername(k.getRenwujieshouren()).getName() : "");
            switch(k.getZhuangtai()){
                case "0":
                    k.setZhuangtai("已拒绝");
                    break;
                case "10":
                    k.setZhuangtai("审核中");
                    break;
                case "20":
                    k.setZhuangtai("等待走访");
                    break;
                case "30":
                    k.setZhuangtai("已走访");
                    break;
            }
        }
        return listk;
    }
    
    @GetMapping(value = "/renwuzoufang/{id}")
    //kehuzoufangrenwurenwuzoufang
    public ModelAndView renwuzoufang(ModelAndView modelAndView,@PathVariable("id")  Long id){
        //listzoufangjieguo
        Common(modelAndView);
        modelAndView.addObject("renwuid",id);
        modelAndView.addObject("kehumingcheng",kehuzoufangrenwuService.findById(id).getKehumingcheng());
        modelAndView.addObject("listzoufangjieguo", listzoufangjieguo);
        modelAndView.setViewName(kehuzoufangrenwurenwuzoufang);
        return modelAndView;
    }
    //kehumingcheng
    
    @PostMapping(value = "/renwuzoufang/{id}")
    public ModelAndView renwuzoufang(ModelAndView modelAndView,@PathVariable("id")  Long id,Kehuzoufang kehuzoufang){
        Common(modelAndView);
        Subject subject = SecurityUtils.getSubject();
        User user = (User)subject.getPrincipal();
        kehuzoufang.setChuangjianren(user.getUsername());
        kehuzoufang.setShijian(new Date());
        kehuzoufang.setBumen(user.getBumen());
        try{
            kehuzoufangrenwuService.zoufang(kehuzoufangrenwuService.findById(id),  kehuzoufang);
        }catch(Exception e){
            e.printStackTrace();
        }
        return new ModelAndView("redirect:/kehuzoufangrenwu/renwu");
    }
    //renwuzoufangquery
    @GetMapping(value = "/renwuzoufangquery/{id}")
    public ModelAndView renwuzoufangquery(ModelAndView modelAndView,@PathVariable("id")  Long id){
        Common(modelAndView);
        modelAndView.setViewName(kehuzoufangrenwuqueryzoufang);
        return modelAndView;
    }
    //kehuzoufangrenwuqueryzoufang
    @GetMapping(value = "/renwuzoufangqueryrest/{id}")
    public List renwuzoufangqueryrest(@PathVariable("id")  Long id){
        Long zoufangid = kehuzoufangrenwuService.findById(id).getZoufangid();
        List<Kehuzoufang> list = new ArrayList<>();
        Kehuzoufang k = kehuzoufangService.findById(zoufangid);
        k.setBumen(bumenService.findByCode(k.getBumen()).getBumen());
        k.setChuangjianren(userService.findByUsername(k.getChuangjianren()).getName());
        list.add(k);
        return  list;
    }
}
