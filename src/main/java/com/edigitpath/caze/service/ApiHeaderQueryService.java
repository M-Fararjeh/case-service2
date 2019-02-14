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

import com.edigitpath.caze.domain.ApiHeader;
import com.edigitpath.caze.domain.*; // for static metamodels
import com.edigitpath.caze.repository.ApiHeaderRepository;
import com.edigitpath.caze.service.dto.ApiHeaderCriteria;
import com.edigitpath.caze.service.dto.ApiHeaderDTO;
import com.edigitpath.caze.service.mapper.ApiHeaderMapper;

/**
 * Service for executing complex queries for ApiHeader entities in the database.
 * The main input is a {@link ApiHeaderCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link ApiHeaderDTO} or a {@link Page} of {@link ApiHeaderDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class ApiHeaderQueryService extends QueryService<ApiHeader> {

    private final Logger log = LoggerFactory.getLogger(ApiHeaderQueryService.class);

    private final ApiHeaderRepository apiHeaderRepository;

    private final ApiHeaderMapper apiHeaderMapper;

    public ApiHeaderQueryService(ApiHeaderRepository apiHeaderRepository, ApiHeaderMapper apiHeaderMapper) {
        this.apiHeaderRepository = apiHeaderRepository;
        this.apiHeaderMapper = apiHeaderMapper;
    }

    /**
     * Return a {@link List} of {@link ApiHeaderDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<ApiHeaderDTO> findByCriteria(ApiHeaderCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<ApiHeader> specification = createSpecification(criteria);
        return apiHeaderMapper.toDto(apiHeaderRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link ApiHeaderDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<ApiHeaderDTO> findByCriteria(ApiHeaderCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<ApiHeader> specification = createSpecification(criteria);
        return apiHeaderRepository.findAll(specification, page)
            .map(apiHeaderMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(ApiHeaderCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<ApiHeader> specification = createSpecification(criteria);
        return apiHeaderRepository.count(specification);
    }

    /**
     * Function to convert ApiHeaderCriteria to a {@link Specification}
     */
    private Specification<ApiHeader> createSpecification(ApiHeaderCriteria criteria) {
        Specification<ApiHeader> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), ApiHeader_.id));
            }
            if (criteria.getKey() != null) {
                specification = specification.and(buildStringSpecification(criteria.getKey(), ApiHeader_.key));
            }
            if (criteria.getValue() != null) {
                specification = specification.and(buildStringSpecification(criteria.getValue(), ApiHeader_.value));
            }
            if (criteria.getApiDataObjectId() != null) {
                specification = specification.and(buildSpecification(criteria.getApiDataObjectId(),
                    root -> root.join(ApiHeader_.apiDataObject, JoinType.LEFT).get(ApiDataObject_.id)));
            }
        }
        return specification;
    }
}
