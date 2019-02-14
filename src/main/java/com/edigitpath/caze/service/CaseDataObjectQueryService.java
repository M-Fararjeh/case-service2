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

import com.edigitpath.caze.domain.CaseDataObject;
import com.edigitpath.caze.domain.*; // for static metamodels
import com.edigitpath.caze.repository.CaseDataObjectRepository;
import com.edigitpath.caze.service.dto.CaseDataObjectCriteria;
import com.edigitpath.caze.service.dto.CaseDataObjectDTO;
import com.edigitpath.caze.service.mapper.CaseDataObjectMapper;

/**
 * Service for executing complex queries for CaseDataObject entities in the database.
 * The main input is a {@link CaseDataObjectCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link CaseDataObjectDTO} or a {@link Page} of {@link CaseDataObjectDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class CaseDataObjectQueryService extends QueryService<CaseDataObject> {

    private final Logger log = LoggerFactory.getLogger(CaseDataObjectQueryService.class);

    private final CaseDataObjectRepository caseDataObjectRepository;

    private final CaseDataObjectMapper caseDataObjectMapper;

    public CaseDataObjectQueryService(CaseDataObjectRepository caseDataObjectRepository, CaseDataObjectMapper caseDataObjectMapper) {
        this.caseDataObjectRepository = caseDataObjectRepository;
        this.caseDataObjectMapper = caseDataObjectMapper;
    }

    /**
     * Return a {@link List} of {@link CaseDataObjectDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<CaseDataObjectDTO> findByCriteria(CaseDataObjectCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<CaseDataObject> specification = createSpecification(criteria);
        return caseDataObjectMapper.toDto(caseDataObjectRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link CaseDataObjectDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<CaseDataObjectDTO> findByCriteria(CaseDataObjectCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<CaseDataObject> specification = createSpecification(criteria);
        return caseDataObjectRepository.findAll(specification, page)
            .map(caseDataObjectMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(CaseDataObjectCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<CaseDataObject> specification = createSpecification(criteria);
        return caseDataObjectRepository.count(specification);
    }

    /**
     * Function to convert CaseDataObjectCriteria to a {@link Specification}
     */
    private Specification<CaseDataObject> createSpecification(CaseDataObjectCriteria criteria) {
        Specification<CaseDataObject> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), CaseDataObject_.id));
            }
            if (criteria.getType() != null) {
                specification = specification.and(buildSpecification(criteria.getType(), CaseDataObject_.type));
            }
            if (criteria.getKey() != null) {
                specification = specification.and(buildStringSpecification(criteria.getKey(), CaseDataObject_.key));
            }
            if (criteria.getApiDataObjectId() != null) {
                specification = specification.and(buildSpecification(criteria.getApiDataObjectId(),
                    root -> root.join(CaseDataObject_.apiDataObject, JoinType.LEFT).get(ApiDataObject_.id)));
            }
            if (criteria.getDbDataObjectId() != null) {
                specification = specification.and(buildSpecification(criteria.getDbDataObjectId(),
                    root -> root.join(CaseDataObject_.dbDataObject, JoinType.LEFT).get(DbDataObject_.id)));
            }
            if (criteria.getFileDataObjectId() != null) {
                specification = specification.and(buildSpecification(criteria.getFileDataObjectId(),
                    root -> root.join(CaseDataObject_.fileDataObject, JoinType.LEFT).get(FileDataObject_.id)));
            }
            if (criteria.getCazeInstanceId() != null) {
                specification = specification.and(buildSpecification(criteria.getCazeInstanceId(),
                    root -> root.join(CaseDataObject_.cazeInstance, JoinType.LEFT).get(CazeInstance_.id)));
            }
        }
        return specification;
    }
}
