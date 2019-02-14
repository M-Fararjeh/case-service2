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

import com.edigitpath.caze.domain.CamundaProcessInstance;
import com.edigitpath.caze.domain.*; // for static metamodels
import com.edigitpath.caze.repository.CamundaProcessInstanceRepository;
import com.edigitpath.caze.service.dto.CamundaProcessInstanceCriteria;
import com.edigitpath.caze.service.dto.CamundaProcessInstanceDTO;
import com.edigitpath.caze.service.mapper.CamundaProcessInstanceMapper;

/**
 * Service for executing complex queries for CamundaProcessInstance entities in the database.
 * The main input is a {@link CamundaProcessInstanceCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link CamundaProcessInstanceDTO} or a {@link Page} of {@link CamundaProcessInstanceDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class CamundaProcessInstanceQueryService extends QueryService<CamundaProcessInstance> {

    private final Logger log = LoggerFactory.getLogger(CamundaProcessInstanceQueryService.class);

    private final CamundaProcessInstanceRepository camundaProcessInstanceRepository;

    private final CamundaProcessInstanceMapper camundaProcessInstanceMapper;

    public CamundaProcessInstanceQueryService(CamundaProcessInstanceRepository camundaProcessInstanceRepository, CamundaProcessInstanceMapper camundaProcessInstanceMapper) {
        this.camundaProcessInstanceRepository = camundaProcessInstanceRepository;
        this.camundaProcessInstanceMapper = camundaProcessInstanceMapper;
    }

    /**
     * Return a {@link List} of {@link CamundaProcessInstanceDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<CamundaProcessInstanceDTO> findByCriteria(CamundaProcessInstanceCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<CamundaProcessInstance> specification = createSpecification(criteria);
        return camundaProcessInstanceMapper.toDto(camundaProcessInstanceRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link CamundaProcessInstanceDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<CamundaProcessInstanceDTO> findByCriteria(CamundaProcessInstanceCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<CamundaProcessInstance> specification = createSpecification(criteria);
        return camundaProcessInstanceRepository.findAll(specification, page)
            .map(camundaProcessInstanceMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(CamundaProcessInstanceCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<CamundaProcessInstance> specification = createSpecification(criteria);
        return camundaProcessInstanceRepository.count(specification);
    }

    /**
     * Function to convert CamundaProcessInstanceCriteria to a {@link Specification}
     */
    private Specification<CamundaProcessInstance> createSpecification(CamundaProcessInstanceCriteria criteria) {
        Specification<CamundaProcessInstance> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), CamundaProcessInstance_.id));
            }
            if (criteria.getProcessInstanceId() != null) {
                specification = specification.and(buildStringSpecification(criteria.getProcessInstanceId(), CamundaProcessInstance_.processInstanceId));
            }
            if (criteria.getProcessInstanceName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getProcessInstanceName(), CamundaProcessInstance_.processInstanceName));
            }
            if (criteria.getCazeInstanceId() != null) {
                specification = specification.and(buildSpecification(criteria.getCazeInstanceId(),
                    root -> root.join(CamundaProcessInstance_.cazeInstances, JoinType.LEFT).get(CazeInstance_.id)));
            }
        }
        return specification;
    }
}
