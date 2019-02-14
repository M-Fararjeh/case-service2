package com.edigitpath.caze.repository;

import com.edigitpath.caze.domain.CazeType;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the CazeType entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CazeTypeRepository extends JpaRepository<CazeType, Long>, JpaSpecificationExecutor<CazeType> {

}
