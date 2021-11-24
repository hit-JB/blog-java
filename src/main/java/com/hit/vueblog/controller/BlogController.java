package com.hit.vueblog.controller;


import cn.hutool.core.lang.Assert;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hit.vueblog.commom.lang.Result;
import com.hit.vueblog.entity.Blog;
import com.hit.vueblog.service.BlogService;
import com.hit.vueblog.util.ShiroUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.security.PublicKey;
import java.time.LocalDateTime;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 关注公众号：MarkerHub
 * @since 2021-09-03
 */
@Slf4j
@RestController
public class BlogController {
    @Autowired
    BlogService blogService;
    @GetMapping("/blogs")
    public Result list(@RequestParam(defaultValue = "1") Integer currentPage){
        Page page = new Page(currentPage,5);
        IPage iPage = blogService.page(page, new QueryWrapper<Blog>().orderByDesc("created"));
        return Result.success(iPage);
    }
    @GetMapping("/blogs/{id}")
    public Result detail(@PathVariable(name = "id")Long id){
        Blog blog = blogService.getById(id);
        Assert.notNull(blog,"该博客已被删除");
        return Result.success(blog);
    }
    @RequiresAuthentication
    @PostMapping("/blogs/edit")
    public Result edit(@Validated @RequestBody Blog blog) {
        Blog temp;

        if (blog.getId() != null) {
            temp = blogService.getById(blog.getId());
            Assert.isTrue(temp.getUserId() ==(ShiroUtil.getProfile().getId().longValue()), "没有权限编辑");
        } else {
            log.info("id is null");
            temp = new Blog();
            log.info("userId " + ShiroUtil.getProfile().getId());
            temp.setUserId(ShiroUtil.getProfile().getId());
            temp.setCreated(LocalDateTime.now());
            temp.setStatus(0);
        }
        BeanUtils.copyProperties(blog,temp,"userId","id","created","status");
        if(blog.getId()==null) {
            blogService.save(temp);
        }
        else {
           blogService.update(temp, new UpdateWrapper<Blog>().eq(true, "id", temp.getId()));
        }
        log.info("成功更新数据");
        return Result.success(null);
    }
}
