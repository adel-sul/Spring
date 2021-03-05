package com.cybertek.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@MappedSuperclass
@Getter
@Setter
public class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "is_deleted")
    private boolean isDeleted;

    @JsonIgnore
    @Column(name = "created_date", nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    @JsonIgnore
    @Column(name = "created_date", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedDate;

    @JsonIgnore
    @Column(name = "user_created", updatable = false)
    private Integer createdUserId;

    @JsonIgnore
    @Column(name = "user_updated", nullable = false)
    private Integer updatedUserId;

    @PrePersist
    private void onPrePersist() {
        this.createdDate = new Date();
        this.updatedDate = new Date();
        this.createdUserId = 1;
        this.updatedUserId = 1;
    }

    @PreUpdate
    private void onPreUpdate() {
        this.updatedDate = new Date();
        this.updatedUserId = 1;
    }
}
