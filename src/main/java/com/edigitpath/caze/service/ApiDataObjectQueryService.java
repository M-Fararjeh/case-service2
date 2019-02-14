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

import com.edigitpath.caze.domain.ApiDataObject;
import com.edigitpath.caze.domain.*; // for static metamodels
import com.edigitpath.caze.repository.ApiDataObjectRepository;
import com.edigitpath.caze.service.dto.ApiDataObjectCriteria;
import com.edigitpath.caze.service.dto.ApiDataObjectDTO;
import com.edigitpath.caze.service.mapper.ApiDataObjectMapper;

/**
 * Service for executing complex queries for ApiDataObject entities in the database.
 * The main input is a {@link ApiDataObjectCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link ApiDataObjectDTO} or a {@link Page} of {@link ApiDataObjectDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class ApiDataObjectQueryService extends QueryService<ApiDataObject> {

    private final Logger log = LoggerFactory.getLogger(ApiDataObjectQueryService.class);

    private final ApiDataObjectRepository apiDataObjectRepository;

    private final ApiDataObjectMapper apiDataObjectMapper;

    public ApiDataObjectQueryService(ApiDataObjectRepository apiDataObjectRepository, ApiDataObjectMapper apiDataObjectMapper) {
        this.apiDataObjectRepository = apiDataObjectRepository;
        this.apiDataObjectMapper = apiDataObjectMapper;
    }

    /**
     * Return a {@link List} of {@link ApiDataObjectDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<ApiDataObjectDTO> findByCriteria(ApiDataObjectCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<ApiDataObject> specification = createSpecification(criteria);
        return apiDataObjectMapper.toDto(apiDataObjectRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link ApiDataObjectDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<ApiDataObjectDTO> findByCriteria(ApiDataObjectCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<ApiDataObject> specification = createSpecification(criteria);
        return apiDataObjectRepository.findAll(specification, page)
            .map(apiDataObjectMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(ApiDataObjectCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<ApiDataObject> specification = createSpecification(criteria);
        return apiDataObjectRepository.count(specification);
    }

    /**
     * Function to convert ApiDataObjectCriteria to a {@link Specification}
     */
    private Specification<ApiDataObject> createSpecification(ApiDataObjectCriteria criteria) {
        Specification<ApiDataObject> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), ApiDataObject_.id));
            }
            if (criteria.getMethod() != null) {
                specification = specification.and(buildSpecification(criteria.getMethod(), ApiDataObject_.method));
            }
            if (criteria.getUrl() != null) {
                specification = specification.and(buildStringSpecification(criteria.getUrl(), ApiDataObject_.url));
            }
            if (criteria.getBody() != null) {
                specification = specification.and(buildStringSpecification(criteria.getBody(), ApiDataObject_.body));
            }
            if (criteria.getApiHeaderId() != null) {
                specification = specification.and(buildSpecification(criteria.getApiHeaderId(),
                    root -> root.join(ApiDataObject_.apiHeaders, JoinType.LEFT).get(ApiHeader_.id)));
            }
            if (criteria.getCaseDataObjectId() != null) {
                specification = specification.and(buildSpecification(criteria.getCaseDataObjectId(),
                    root -> root.join(ApiDataObject_.caseDataObjects, JoinType.LEFT).get(CaseDataObject_.id)));
            }
        }
        return specification;
    }
}
