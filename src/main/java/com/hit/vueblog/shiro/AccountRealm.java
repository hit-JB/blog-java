package com.hit.vueblog.shiro;

import com.hit.vueblog.entity.User;
import com.hit.vueblog.service.UserService;
import com.hit.vueblog.util.JwtUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

//用户权限和授权的限制
@Component
public class AccountRealm extends AuthorizingRealm {
    @Autowired
    JwtUtils jwtUtils;
    @Autowired
    UserService userService;
    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JwtToken;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
       JwtToken jwtToken = (JwtToken) authenticationToken;
        String subject = jwtUtils.getClaimByToken((String) jwtToken.getPrincipal()).getSubject();
        User user = userService.getById(Long.valueOf(subject));//通过jwt获取到当前用户的的信息，通过id得到
        if(user==null){
            throw new UnknownAccountException("账户不存在");
        }
        if(user.getStatus()==-1){
            throw new LockedAccountException("账户已被锁定");
        }
        System.out.println("--------------");
        AccountProfile accountProfile = new AccountProfile();
        BeanUtils.copyProperties(user,accountProfile);
        SimpleAuthenticationInfo info =  new SimpleAuthenticationInfo(accountProfile,jwtToken.getCredentials(),getName());
        return info;

    }
}
