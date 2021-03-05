package com.suxs.modules.sys.service.iface;

import com.suxs.core.base.BaseDao;
import com.suxs.core.base.BaseService;
import com.suxs.core.base.persistence.BaseEntity;
import com.suxs.modules.sys.dao.UserDao;
import com.suxs.modules.sys.entity.User;

public interface UserService extends BaseService<UserDao, User> {

    User login(String loginName);

}
