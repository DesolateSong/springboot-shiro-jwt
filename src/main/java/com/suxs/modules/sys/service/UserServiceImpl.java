package com.suxs.modules.sys.service;

import com.suxs.core.base.impl.CrudService;
import com.suxs.modules.sys.dao.UserDao;
import com.suxs.modules.sys.entity.User;
import com.suxs.modules.sys.service.iface.UserService;

import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends CrudService<UserDao, User> implements UserService {

    @Override
    public User login(String loginName) {
        return dao.getByLoginName(loginName);
    }

}
