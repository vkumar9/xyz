package com.xyz.common.jpa.entities.base;
import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.persistence.Version;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Getter @Setter @ToString
public class BaseEntityVO {

    @CreatedDate
    @Column(updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @CreatedBy
    @Column(updatable = false)
    private String createdBy;
    @Version
    @LastModifiedDate
    @Column(insertable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;

    @LastModifiedBy
    @Column(insertable = false)
    private String updatedBy;
}
