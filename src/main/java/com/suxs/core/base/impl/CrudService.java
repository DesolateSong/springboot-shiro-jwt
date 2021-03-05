package com.suxs.core.base.impl;

import com.suxs.core.base.BaseDao;
import com.suxs.core.base.BaseService;
import com.suxs.core.base.persistence.BaseEntity;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

public class CrudService<D extends BaseDao, E extends BaseEntity> implements BaseService<D, E> {

    @Autowired
    protected D dao;

    @Override
    public D get(String id) {
        return (D) dao.getOne(id);
    }

    @Transactional
    @Override
    public void save(E entity) {
        if (StringUtils.isEmpty(entity.getId())) {
            entity.preInsert();
        } else {
            entity.preUpdate();
        }
        dao.save(entity);
    }

    @Transactional
    @Override
    public void delete(E entity) {
        dao.delete(entity.getId());
    }

}
