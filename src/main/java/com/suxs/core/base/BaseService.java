package com.suxs.core.base;


import com.suxs.core.base.persistence.BaseEntity;

public interface BaseService<D extends BaseDao, E extends BaseEntity> {

    D get(String id);

    void save(E entity);

    void delete(E entity);

}
