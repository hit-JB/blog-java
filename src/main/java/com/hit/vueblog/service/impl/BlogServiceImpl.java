package com.hit.vueblog.service.impl;

import com.hit.vueblog.entity.Blog;
import com.hit.vueblog.mapper.BlogMapper;
import com.hit.vueblog.service.BlogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jb
 * @since 2021-09-03
 */
@Service
public class BlogServiceImpl extends ServiceImpl<BlogMapper, Blog> implements BlogService {

}
