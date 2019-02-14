package com.edigitpath.caze.repository;

import com.edigitpath.caze.domain.CazeInstance;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data  repository for the CazeInstance entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CazeInstanceRepository extends JpaRepository<CazeInstance, Long>, JpaSpecificationExecutor<CazeInstance> {

    @Query(value = "select distinct caze_instance from CazeInstance caze_instance left join fetch caze_instance.camundaCaseInstances left join fetch caze_instance.camundaProcessInstances left join fetch caze_instance.relatedCazes",
        countQuery = "select count(distinct caze_instance) from CazeInstance caze_instance")
    Page<CazeInstance> findAllWithEagerRelationships(Pageable pageable);

    @Query(value = "select distinct caze_instance from CazeInstance caze_instance left join fetch caze_instance.camundaCaseInstances left join fetch caze_instance.camundaProcessInstances left join fetch caze_instance.relatedCazes")
    List<CazeInstance> findAllWithEagerRelationships();

    @Query("select caze_instance from CazeInstance caze_instance left join fetch caze_instance.camundaCaseInstances left join fetch caze_instance.camundaProcessInstances left join fetch caze_instance.relatedCazes where caze_instance.id =:id")
    Optional<CazeInstance> findOneWithEagerRelationships(@Param("id") Long id);

}
