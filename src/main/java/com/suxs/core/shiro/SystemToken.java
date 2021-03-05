package com.suxs.core.shiro;

import org.apache.shiro.authc.AuthenticationToken;

public class SystemToken implements AuthenticationToken {
    private String token;

    public SystemToken(String token) {
        this.token = token;
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
