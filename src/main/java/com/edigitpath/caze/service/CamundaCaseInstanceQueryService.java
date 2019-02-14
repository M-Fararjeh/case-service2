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

import com.edigitpath.caze.domain.CamundaCaseInstance;
import com.edigitpath.caze.domain.*; // for static metamodels
import com.edigitpath.caze.repository.CamundaCaseInstanceRepository;
import com.edigitpath.caze.service.dto.CamundaCaseInstanceCriteria;
import com.edigitpath.caze.service.dto.CamundaCaseInstanceDTO;
import com.edigitpath.caze.service.mapper.CamundaCaseInstanceMapper;

/**
 * Service for executing complex queries for CamundaCaseInstance entities in the database.
 * The main input is a {@link CamundaCaseInstanceCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link CamundaCaseInstanceDTO} or a {@link Page} of {@link CamundaCaseInstanceDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class CamundaCaseInstanceQueryService extends QueryService<CamundaCaseInstance> {

    private final Logger log = LoggerFactory.getLogger(CamundaCaseInstanceQueryService.class);

    private final CamundaCaseInstanceRepository camundaCaseInstanceRepository;

    private final CamundaCaseInstanceMapper camundaCaseInstanceMapper;

    public CamundaCaseInstanceQueryService(CamundaCaseInstanceRepository camundaCaseInstanceRepository, CamundaCaseInstanceMapper camundaCaseInstanceMapper) {
        this.camundaCaseInstanceRepository = camundaCaseInstanceRepository;
        this.camundaCaseInstanceMapper = camundaCaseInstanceMapper;
    }

    /**
     * Return a {@link List} of {@link CamundaCaseInstanceDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<CamundaCaseInstanceDTO> findByCriteria(CamundaCaseInstanceCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<CamundaCaseInstance> specification = createSpecification(criteria);
        return camundaCaseInstanceMapper.toDto(camundaCaseInstanceRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link CamundaCaseInstanceDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<CamundaCaseInstanceDTO> findByCriteria(CamundaCaseInstanceCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<CamundaCaseInstance> specification = createSpecification(criteria);
        return camundaCaseInstanceRepository.findAll(specification, page)
            .map(camundaCaseInstanceMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(CamundaCaseInstanceCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<CamundaCaseInstance> specification = createSpecification(criteria);
        return camundaCaseInstanceRepository.count(specification);
    }

    /**
     * Function to convert CamundaCaseInstanceCriteria to a {@link Specification}
     */
    private Specification<CamundaCaseInstance> createSpecification(CamundaCaseInstanceCriteria criteria) {
        Specification<CamundaCaseInstance> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), CamundaCaseInstance_.id));
            }
            if (criteria.getCaseInstanceId() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCaseInstanceId(), CamundaCaseInstance_.caseInstanceId));
            }
            if (criteria.getCaseInstanceName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCaseInstanceName(), CamundaCaseInstance_.caseInstanceName));
            }
            if (criteria.getCazeInstanceId() != null) {
                specification = specification.and(buildSpecification(criteria.getCazeInstanceId(),
                    root -> root.join(CamundaCaseInstance_.cazeInstances, JoinType.LEFT).get(CazeInstance_.id)));
            }
        }
        return specification;
    }
}
