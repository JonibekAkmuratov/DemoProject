package com.example.demoproject.entity;


import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor
public abstract class Auditable implements BaseDomain {

    @CreatedDate
    @CreationTimestamp
    @Column(updatable = false, nullable = false)
    private LocalDateTime createdAt;

    @CreatedBy
    @Column(updatable = false, nullable = false)
    private String createdBy;

    @LastModifiedDate
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @LastModifiedBy
    @Column(updatable = false, nullable = false)
    private String updatedBy;

    @Column(name = "is_deleted")
    private boolean deleted = false;

}
