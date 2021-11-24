package com.hit.vueblog;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.hit.vueblog.config.ShiroConfig;
import org.apache.shiro.session.mgt.SessionManager;
import org.crazycake.shiro.RedisSessionDAO;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class VueblogApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(VueblogApplication.class, args);
    }
}
