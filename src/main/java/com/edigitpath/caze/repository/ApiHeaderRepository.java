package com.edigitpath.caze.repository;

import com.edigitpath.caze.domain.ApiHeader;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the ApiHeader entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ApiHeaderRepository extends JpaRepository<ApiHeader, Long> {

}
