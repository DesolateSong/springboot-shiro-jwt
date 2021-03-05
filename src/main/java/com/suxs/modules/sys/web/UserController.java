package com.suxs.modules.sys.web;

import com.suxs.common.Result;
import com.suxs.core.shiro.utils.JWtUtil;
import com.suxs.core.shiro.utils.ShiroUtil;
import com.suxs.modules.sys.entity.User;
import com.suxs.modules.sys.service.iface.UserService;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public Result login(String username, String password) {
        User loginUser = userService.login(username);
        if (StringUtils.isNotEmpty(loginUser.getLoginName())) {
            String realPassword = loginUser.getPassword();
            if (!realPassword.equals(ShiroUtil.md5EncryptPassword(username, password))) {
                return Result.fail().add("info", "密码错误");
            } else {
                return Result.success().add("token", JWtUtil.createToken(username, realPassword));
            }
        }
        return Result.fail().add("info", "用户名错误");
    }


    @RequestMapping(path = "/unauthorized/{message}")
    public Result unauthorized(@PathVariable String message) throws UnsupportedEncodingException {
        return Result.fail().add("info", message);
    }

    @PostMapping("/getMessage")
    @RequiresRoles(logical = Logical.OR, value = {"user", "admin"})
    public Result getMessage() {
        return Result.success().add("info", "成功获得信息！");
    }

    @PostMapping("/getVipMessage")
    @RequiresRoles(logical = Logical.OR, value = {"user", "admin"})
    @RequiresPermissions("vip")
    public Result getVipMessage() {
        return Result.success().add("info", "成功获得 vip 信息！");
    }

    @GetMapping("/getUser")
    public Result getUser(String username) {
        Result result = Result.success();
        result.getData().put("user", userService.login(username));
        return result;
    }
}
