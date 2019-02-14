package com.edigitpath.caze.repository;

import com.edigitpath.caze.domain.CamundaCaseInstance;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the CamundaCaseInstance entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CamundaCaseInstanceRepository extends JpaRepository<CamundaCaseInstance, Long> {

}
