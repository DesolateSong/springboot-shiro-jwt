package com.suxs.core.shiro;

import com.suxs.core.shiro.utils.ShiroUtil;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;

public class CustomCredentialsMatcher extends HashedCredentialsMatcher {
    public CustomCredentialsMatcher() {
        super(ShiroUtil.DEFUALT_ENCRYPT_TYPE);
        setHashIterations(ShiroUtil.DEFAULT_HASH_ITERATIONS);
    }

    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        return super.doCredentialsMatch(token, info);
    }
}
