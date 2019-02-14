package com.edigitpath.caze.repository;

import com.edigitpath.caze.domain.CamundaProcessInstance;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the CamundaProcessInstance entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CamundaProcessInstanceRepository extends JpaRepository<CamundaProcessInstance, Long> {

}
