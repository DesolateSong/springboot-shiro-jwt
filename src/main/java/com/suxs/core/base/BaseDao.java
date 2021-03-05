package com.suxs.core.base;

import com.suxs.core.base.persistence.BaseEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

@NoRepositoryBean
public interface BaseDao<E extends BaseEntity,ID extends Serializable> extends JpaRepository<E, ID> {

}
