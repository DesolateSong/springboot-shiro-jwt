package com.suxs.core.base.persistence;

import com.suxs.modules.sys.entity.User;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ConstraintMode;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class DataEntity<T> extends BaseEntity<T> {


    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(
            name = "create_by"
            , referencedColumnName = "id"
            , foreignKey = @ForeignKey(value = ConstraintMode.NO_CONSTRAINT)
    )
    protected User createBy;
    @Column(name = "create_date")
    protected LocalDateTime createDate;
    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(
            name = "update_user"
            , referencedColumnName = "id"
            , foreignKey = @ForeignKey(value = ConstraintMode.NO_CONSTRAINT)
    )
    protected User updateUser;
    @Column(name = "update_date")
    protected LocalDateTime updateDate;


    @Override
    public void preInsert() {

    }

    @Override
    public void preUpdate() {

    }
}
