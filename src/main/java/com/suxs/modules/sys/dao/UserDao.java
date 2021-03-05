package com.suxs.modules.sys.dao;

import com.suxs.core.base.BaseDao;
import com.suxs.modules.sys.entity.User;

import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends BaseDao<User, String> {

    User getByLoginName(String loginName);

}
