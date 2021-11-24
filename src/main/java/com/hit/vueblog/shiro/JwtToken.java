package com.hit.vueblog.shiro;

import org.apache.shiro.authc.AuthenticationToken;

import java.security.PublicKey;

public class JwtToken implements AuthenticationToken {
    private String token;

    public JwtToken(String jwt){
        this.token = jwt;
    }
    @Override
    public Object getPrincipal() {
        return token;
    }

    @Override
    public Object getCredentials() {
        return token;
    }
}
