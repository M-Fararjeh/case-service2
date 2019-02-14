package com.edigitpath.caze.repository;

import com.edigitpath.caze.domain.DbDataObject;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the DbDataObject entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DbDataObjectRepository extends JpaRepository<DbDataObject, Long>, JpaSpecificationExecutor<DbDataObject> {

}
