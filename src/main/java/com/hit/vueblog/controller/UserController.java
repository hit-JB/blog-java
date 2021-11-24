package com.hit.vueblog.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hit.vueblog.commom.lang.Result;
import com.hit.vueblog.entity.User;
import com.hit.vueblog.service.UserService;
import com.hit.vueblog.shiro.AccountProfile;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author jb
 * @since 2021-09-03
 */
@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {
    private  final String URL = "http://localhost:8080/";

    @Autowired
    UserService userService;
    @GetMapping("/delete")
    @ResponseBody
    @RequiresAuthentication
    public Result index(User user){

        userService.removeById(user.getId());
        return Result.success(user);
    }

    @PostMapping("/register")
    public Result save(@Validated @RequestBody User user){
        log.info(user.toString());
        String password = user.getPassword();
        Md5Hash md5Hash = new Md5Hash(password);
        user.setPassword(md5Hash.toHex());
        userService.save(user);
        return Result.success(user);
    }
    @PostMapping("/changeUser")
    @RequiresAuthentication
    public Result changeUser(@Validated @RequestBody User user){
        AccountProfile accountProfile = (AccountProfile) SecurityUtils.getSubject().getPrincipal(); // 获取当前登录用户
        log.info(accountProfile.toString());
        String password = user.getPassword();
        Md5Hash md5Hash = new Md5Hash(password);
        user.setPassword(md5Hash.toHex());
        userService.update(user,new QueryWrapper<User>().eq(true,"id",accountProfile.getId()));
        return Result.success(user);
    }
    @GetMapping("/findUser")
    public Result findUser(@RequestParam("username") String username){
        User user= userService.getOne(new QueryWrapper<User>().eq(true,"username",username));
        if(user==null)
            return Result.success(null);
        return Result.fail("用户名已存在");
    }
    @GetMapping("/edit")
    @RequiresAuthentication
    public Result editUser(){
        AccountProfile accountProfile = (AccountProfile) SecurityUtils.getSubject().getPrincipal(); // 获取当前登录用户
        log.info(accountProfile.toString()+"user edit");
        return Result.success(accountProfile);
    }
    @GetMapping("/heads")
    public Result getHeaders(){
        Map<String,String> map = new HashMap<>();
        for(int i=1;i<=4;i++){
            map.put("head_"+i,URL+"static/"+"head/"+"img_"+i+".png");
        }
        return Result.success(map);
    }
}
