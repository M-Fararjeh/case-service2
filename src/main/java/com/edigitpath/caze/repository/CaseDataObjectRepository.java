package com.edigitpath.caze.repository;

import com.edigitpath.caze.domain.CaseDataObject;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the CaseDataObject entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CaseDataObjectRepository extends JpaRepository<CaseDataObject, Long>, JpaSpecificationExecutor<CaseDataObject> {

}
