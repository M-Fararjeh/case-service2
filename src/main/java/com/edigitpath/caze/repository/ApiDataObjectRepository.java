package com.edigitpath.caze.repository;

import com.edigitpath.caze.domain.ApiDataObject;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the ApiDataObject entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ApiDataObjectRepository extends JpaRepository<ApiDataObject, Long> {

}
