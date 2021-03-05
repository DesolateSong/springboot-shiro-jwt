package com.suxs.core.shiro;

import com.suxs.core.shiro.utils.JWtUtil;
import com.suxs.modules.sys.entity.Menu;
import com.suxs.modules.sys.entity.Role;
import com.suxs.modules.sys.entity.User;
import com.suxs.modules.sys.service.iface.UserService;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class SystemAuthorizingRealm extends AuthorizingRealm {
    private static final Logger LOGGER = LoggerFactory.getLogger(SystemAuthorizingRealm.class);

    @Autowired
    private UserService userService;

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof SystemToken;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

        String authtoken = (String) token.getCredentials();
        String username = JWtUtil.getUsername(authtoken);

        if (username == null) {
            throw new AuthenticationException("token invalid");
        }

        User user = userService.login(username);
        if (user == null) {
            throw new AuthenticationException("User didn't existed!");
        }
        if (!JWtUtil.verify(authtoken, username, user.getPassword())) {
            throw new AuthenticationException("Username or password error");
        }

        return new SimpleAuthenticationInfo(authtoken, authtoken, getName());
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {

        String username = JWtUtil.getUsername(principals.toString());
        User user = userService.login(username);

        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        Set<Role> roles = user.getRoles();
        List<String> permissions = new ArrayList<>();
        roles.forEach(role -> {
            Set<Menu> menuSet = role.getMenus();
            for (Menu menu : menuSet) {
                String perms = menu.getPermission();
                if (StringUtils.isNotEmpty(perms)) {
                    permissions.addAll(Arrays.asList(perms.split(",")));
                }
            }
        });

        authorizationInfo.addRoles(roles.stream().map(Role::getEname).collect(Collectors.toList()));
        authorizationInfo.addStringPermissions(permissions);
        return authorizationInfo;
    }

}
