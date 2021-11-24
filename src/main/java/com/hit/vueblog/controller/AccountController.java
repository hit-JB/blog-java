package com.hit.vueblog.controller;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.map.MapUtil;
import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hit.vueblog.commom.dto.LoginDto;
import com.hit.vueblog.commom.lang.Result;
import com.hit.vueblog.entity.User;
import com.hit.vueblog.service.UserService;
import com.hit.vueblog.util.JwtUtils;
import freemarker.template.utility.SecurityUtilities;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.security.SecurityUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@Slf4j
@RestController
public class AccountController {
    @Autowired
    UserService userService;
    @Autowired
    JwtUtils jwtUtils;
    @PostMapping("/login")
    public Result login(@Validated @RequestBody LoginDto loginDto, HttpServletResponse response){
        User user = userService.getOne(new QueryWrapper<User>().eq("username", loginDto.getUsername()));
        Assert.notNull(user,"用户名错误");
        if(!user.getPassword().equals(SecureUtil.md5(loginDto.getPassword()))){//密码加密技术，这里还要再处理
            return Result.fail("密码不正确");
        }
        String token = jwtUtils.generateToken(user.getId());
        response.setHeader("Authorization",token);//这两个响应头应该重点查看
        response.setHeader("Access-control-Expose-Headers","Authorization");//响应头，暴露
        log.info("---------登录成功！----------");
        return Result.success(MapUtil.builder().put("id",user.getId())
        .put("username",user.getUsername())
        .put("avatar",user.getAvatar())
        .put("email",user.getEmail())
        .map());
    }
    @RequiresAuthentication
    @GetMapping("/logout")
    public Result logout(){
        System.out.println("成功退出");
        SecurityUtils.getSubject().logout();
        return Result.success(null);
    }
}
