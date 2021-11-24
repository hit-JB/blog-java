package com.hit.vueblog;

import com.hit.vueblog.entity.Blog;
import com.hit.vueblog.service.BlogService;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.crazycake.shiro.RedisCacheManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDateTime;
import java.util.Date;


@SpringBootTest
class VueblogApplicationTests {
    @Autowired
    BlogService blogService;
    @Autowired
    RedisCacheManager redisCacheManager;
    @Test
    void contextLoads() {
        System.out.println(redisCacheManager);
    }

}
