package com.example.demo.model;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * {@link Message} repository.
 */
@Repository
public interface MicroServiceRepository extends JpaRepository<MicroService, Long>, JpaSpecificationExecutor<MicroService> {

    Optional<MicroService> findTopByOrderByIdDesc();

    @Modifying
    @Query("delete from MicroService t where t.id not in (:idList)")
    public void deleteByExcludedId(@Param("idList") List<Long> idList);

}
