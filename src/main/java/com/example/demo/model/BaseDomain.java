package com.example.demo.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.ZonedDateTime;

/**
 * Base domain (manage createdOn and updatedOn fields).
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@MappedSuperclass
//@EntityListeners(AuditingEntityListener.class)
@SuperBuilder(toBuilder = true)
public abstract class BaseDomain {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(nullable = false , name = "ID")
    Long id;

    @Column(nullable = true , name = "IS_DELETED")
    Boolean isDeleted = false;

    @Version
    Long version= 0l;


    /** Data created on. */
    @CreatedDate
    @Column(name = "CREATED_ON")
    private ZonedDateTime createdOn = ZonedDateTime.now();

    /** Data updated on. */
    @LastModifiedDate
    @Column(name = "UPDATED_ON")
    private ZonedDateTime updatedOn = ZonedDateTime.now();

}
