/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 * @autoor szh
 */

package com.szh.crm.controller;

import com.szh.crm.domain.Bumen;
import com.szh.crm.domain.Quanxian;
import com.szh.crm.domain.User;
import com.szh.crm.service.BumenService;
import com.szh.crm.service.QuanxianService;
import com.szh.crm.service.UserService;
import com.szh.crm.util.CheckPassword;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author  szh
 * QQ:873689
 * @date 2018-11-29 14:23:56
 */
@Controller
@ResponseBody
@RequestMapping("/user")
public class UserController extends CommonController{
    
    @Autowired
    UserService userService;
    @Autowired
    QuanxianService quanxianService;
    @Autowired
    BumenService bumenService;
       
    final String changepasswordhtml = "user-changepassword";
    final String addhtml = "user-add";
    final String queryhtml = "user-query";
    final String updatehtml = "user-update";
    
    @PostMapping(value = "/changepassword")
    public ModelAndView changePassowrd(ModelAndView modelAndView,@RequestParam("password1") String password1,@RequestParam("password2") String password2){
        Subject subject = SecurityUtils.getSubject();
        User user = (User)subject.getPrincipal();
        Common(modelAndView);
        System.out.println("changepassword:"+password1+","+password2);
           
        if(password1.equals(password2)){
            user.setPassword(password1);
            try{
                userService.updatePassword(user);
                modelAndView.addObject("msg", "密码修改成功");
            }catch(Exception e){
                e.printStackTrace();
                modelAndView.addObject("msg", e.getMessage());
            }
        }else{
            modelAndView.addObject("msg", "两次密码不一致，请重新输入");
        }
        
        modelAndView.setViewName(changepasswordhtml);
        return modelAndView;
    }
    
    @GetMapping(value = "/changepassword")
    public ModelAndView changePassowrd(ModelAndView modelAndView){
        Common(modelAndView);
        modelAndView.setViewName(changepasswordhtml);
        return modelAndView;
    }  
    
    @GetMapping(value = "/add")
    @RequiresRoles(value={"40","99"}, logical= Logical.OR)
    public ModelAndView add(ModelAndView modelAndView){
        Common(modelAndView);
        List<Quanxian> listquanxian = quanxianService.findAll();
        List<Bumen> listbumen = bumenService.findAll();
        modelAndView.addObject("listquanxian",listquanxian);
        modelAndView.addObject("listbumen",listbumen);
        modelAndView.setViewName(addhtml);
        return modelAndView;
    }
    
    @PostMapping(value = "/add")
    @RequiresRoles(value={"40","99"}, logical= Logical.OR)
    public ModelAndView add(ModelAndView modelAndView,User userinfo){
        Common(modelAndView);
        List<Quanxian> listquanxian = quanxianService.findAll();
        List<Bumen> listbumen = bumenService.findAll();
        modelAndView.addObject("listquanxian",listquanxian);
        modelAndView.addObject("listbumen",listbumen);
        
        try{
            userService.addUser(userinfo);
            modelAndView.addObject("msg","增加成功");
        }catch(Exception e){
            e.printStackTrace();
            modelAndView.addObject("msg",e.getMessage());
            modelAndView.addObject("userinfo",userinfo);
        }
        
        
        modelAndView.setViewName(addhtml);
        return modelAndView;
    }
    
    @GetMapping(value = "/query")
    @RequiresRoles(value={"40","99"}, logical= Logical.OR)
    public ModelAndView query(ModelAndView modelAndView){
        Common(modelAndView);
           
        modelAndView.setViewName(queryhtml);
        return modelAndView;
    }
    
    @GetMapping(value = "/queryrest")
    @RequiresRoles(value={"40","99"}, logical= Logical.OR)
    public List query(){
        
        List<User> listusers = userService.findAll();
        for(User u : listusers){
            u.setPassword("123456");
            u.setBumen(bumenService.findByCode(u.getBumen()).getBumen());
            u.setQuanxian(quanxianService.findQuanxian(u.getQuanxian()).getQuanxianname());
        }
        
        return listusers;
    }
    
    
    @GetMapping(value = "/update/{username}")
    @RequiresRoles(value={"40","99"}, logical= Logical.OR)
    public ModelAndView update(ModelAndView modelAndView,@PathVariable("username")  String username){
        Common(modelAndView);
        User updateuser = userService.findByUsername(username);
        modelAndView.addObject("userinfo",updateuser);
        List<Quanxian> listquanxian = quanxianService.findAll();
        List<Bumen> listbumen = bumenService.findAll();
        modelAndView.addObject("listquanxian",listquanxian);
        modelAndView.addObject("listbumen",listbumen);
        modelAndView.setViewName(updatehtml);
        return modelAndView;
    }
    
    @PostMapping(value = "/update/{username}")
    @RequiresRoles(value={"40","99"}, logical= Logical.OR)
    public ModelAndView update(ModelAndView modelAndView,User userinfo){
        Common(modelAndView);
        System.out.println(userinfo);
        try{
            userService.updateUser(userinfo);
            modelAndView.addObject("msg","修改成功");
        }catch(Exception e){
            e.printStackTrace();
            userinfo.setPassword("");
            modelAndView.addObject("msg",e.getMessage());
        }
        
        modelAndView.addObject("userinfo",userinfo);
        List<Quanxian> listquanxian = quanxianService.findAll();
        List<Bumen> listbumen = bumenService.findAll();
        modelAndView.addObject("listquanxian",listquanxian);
        modelAndView.addObject("listbumen",listbumen);
        modelAndView.setViewName(updatehtml);
        return modelAndView;
    }    
    
    @GetMapping(value = "/delete/{username}")
    @RequiresRoles(value={"40","99"}, logical= Logical.OR)
    public ModelAndView delete(ModelAndView modelAndView,@PathVariable("username")  String username){
        Common(modelAndView);
        try{
            userService.delUser(username);
            modelAndView.addObject("msg","删除成功");
        }catch(Exception e){
            e.printStackTrace();
            modelAndView.addObject("msg",e.getMessage());
        }
        modelAndView.setViewName(updatehtml);
        return modelAndView;
    }
    
    
}
