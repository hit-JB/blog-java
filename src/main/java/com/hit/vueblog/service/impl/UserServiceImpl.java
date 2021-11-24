package com.hit.vueblog.service.impl;

import com.hit.vueblog.entity.User;
import com.hit.vueblog.mapper.UserMapper;
import com.hit.vueblog.service.UserService;
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
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
