package com.edigitpath.caze.service;

import java.util.List;

import javax.persistence.criteria.JoinType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.github.jhipster.service.QueryService;

import com.edigitpath.caze.domain.CazeInstance;
import com.edigitpath.caze.domain.*; // for static metamodels
import com.edigitpath.caze.repository.CazeInstanceRepository;
import com.edigitpath.caze.service.dto.CazeInstanceCriteria;
import com.edigitpath.caze.service.dto.CazeInstanceDTO;
import com.edigitpath.caze.service.mapper.CazeInstanceMapper;

/**
 * Service for executing complex queries for CazeInstance entities in the database.
 * The main input is a {@link CazeInstanceCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link CazeInstanceDTO} or a {@link Page} of {@link CazeInstanceDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class CazeInstanceQueryService extends QueryService<CazeInstance> {

    private final Logger log = LoggerFactory.getLogger(CazeInstanceQueryService.class);

    private final CazeInstanceRepository cazeInstanceRepository;

    private final CazeInstanceMapper cazeInstanceMapper;

    public CazeInstanceQueryService(CazeInstanceRepository cazeInstanceRepository, CazeInstanceMapper cazeInstanceMapper) {
        this.cazeInstanceRepository = cazeInstanceRepository;
        this.cazeInstanceMapper = cazeInstanceMapper;
    }

    /**
     * Return a {@link List} of {@link CazeInstanceDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<CazeInstanceDTO> findByCriteria(CazeInstanceCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<CazeInstance> specification = createSpecification(criteria);
        return cazeInstanceMapper.toDto(cazeInstanceRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link CazeInstanceDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<CazeInstanceDTO> findByCriteria(CazeInstanceCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<CazeInstance> specification = createSpecification(criteria);
        return cazeInstanceRepository.findAll(specification, page)
            .map(cazeInstanceMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(CazeInstanceCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<CazeInstance> specification = createSpecification(criteria);
        return cazeInstanceRepository.count(specification);
    }

    /**
     * Function to convert CazeInstanceCriteria to a {@link Specification}
     */
    private Specification<CazeInstance> createSpecification(CazeInstanceCriteria criteria) {
        Specification<CazeInstance> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), CazeInstance_.id));
            }
            if (criteria.getName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getName(), CazeInstance_.name));
            }
            if (criteria.getDescription() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDescription(), CazeInstance_.description));
            }
            if (criteria.getNumber() != null) {
                specification = specification.and(buildStringSpecification(criteria.getNumber(), CazeInstance_.number));
            }
            if (criteria.getCreatorId() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCreatorId(), CazeInstance_.creatorId));
            }
            if (criteria.getIssuerID() != null) {
                specification = specification.and(buildStringSpecification(criteria.getIssuerID(), CazeInstance_.issuerID));
            }
            if (criteria.getCreationDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCreationDate(), CazeInstance_.creationDate));
            }
            if (criteria.getCaseDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCaseDate(), CazeInstance_.caseDate));
            }
            if (criteria.getPriority() != null) {
                specification = specification.and(buildSpecification(criteria.getPriority(), CazeInstance_.priority));
            }
            if (criteria.getRequiredTime() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getRequiredTime(), CazeInstance_.requiredTime));
            }
            if (criteria.getSecured() != null) {
                specification = specification.and(buildSpecification(criteria.getSecured(), CazeInstance_.secured));
            }
            if (criteria.getCmmnId() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCmmnId(), CazeInstance_.cmmnId));
            }
            if (criteria.getRequestId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getRequestId(), CazeInstance_.requestId));
            }
            if (criteria.getCaseDataObjectId() != null) {
                specification = specification.and(buildSpecification(criteria.getCaseDataObjectId(),
                    root -> root.join(CazeInstance_.caseDataObjects, JoinType.LEFT).get(CaseDataObject_.id)));
            }
            if (criteria.getCazeTypeId() != null) {
                specification = specification.and(buildSpecification(criteria.getCazeTypeId(),
                    root -> root.join(CazeInstance_.cazeType, JoinType.LEFT).get(CazeType_.id)));
            }
            if (criteria.getCamundaCaseInstanceId() != null) {
                specification = specification.and(buildSpecification(criteria.getCamundaCaseInstanceId(),
                    root -> root.join(CazeInstance_.camundaCaseInstances, JoinType.LEFT).get(CamundaCaseInstance_.id)));
            }
            if (criteria.getCamundaProcessInstanceId() != null) {
                specification = specification.and(buildSpecification(criteria.getCamundaProcessInstanceId(),
                    root -> root.join(CazeInstance_.camundaProcessInstances, JoinType.LEFT).get(CamundaProcessInstance_.id)));
            }
            if (criteria.getRelatedCazeId() != null) {
                specification = specification.and(buildSpecification(criteria.getRelatedCazeId(),
                    root -> root.join(CazeInstance_.relatedCazes, JoinType.LEFT).get(CazeInstance_.id)));
            }
        }
        return specification;
    }
}
