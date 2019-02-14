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

import com.edigitpath.caze.domain.DbDataObject;
import com.edigitpath.caze.domain.*; // for static metamodels
import com.edigitpath.caze.repository.DbDataObjectRepository;
import com.edigitpath.caze.service.dto.DbDataObjectCriteria;
import com.edigitpath.caze.service.dto.DbDataObjectDTO;
import com.edigitpath.caze.service.mapper.DbDataObjectMapper;

/**
 * Service for executing complex queries for DbDataObject entities in the database.
 * The main input is a {@link DbDataObjectCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link DbDataObjectDTO} or a {@link Page} of {@link DbDataObjectDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class DbDataObjectQueryService extends QueryService<DbDataObject> {

    private final Logger log = LoggerFactory.getLogger(DbDataObjectQueryService.class);

    private final DbDataObjectRepository dbDataObjectRepository;

    private final DbDataObjectMapper dbDataObjectMapper;

    public DbDataObjectQueryService(DbDataObjectRepository dbDataObjectRepository, DbDataObjectMapper dbDataObjectMapper) {
        this.dbDataObjectRepository = dbDataObjectRepository;
        this.dbDataObjectMapper = dbDataObjectMapper;
    }

    /**
     * Return a {@link List} of {@link DbDataObjectDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<DbDataObjectDTO> findByCriteria(DbDataObjectCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<DbDataObject> specification = createSpecification(criteria);
        return dbDataObjectMapper.toDto(dbDataObjectRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link DbDataObjectDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<DbDataObjectDTO> findByCriteria(DbDataObjectCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<DbDataObject> specification = createSpecification(criteria);
        return dbDataObjectRepository.findAll(specification, page)
            .map(dbDataObjectMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(DbDataObjectCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<DbDataObject> specification = createSpecification(criteria);
        return dbDataObjectRepository.count(specification);
    }

    /**
     * Function to convert DbDataObjectCriteria to a {@link Specification}
     */
    private Specification<DbDataObject> createSpecification(DbDataObjectCriteria criteria) {
        Specification<DbDataObject> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), DbDataObject_.id));
            }
            if (criteria.getTableName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTableName(), DbDataObject_.tableName));
            }
            if (criteria.getColumnName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getColumnName(), DbDataObject_.columnName));
            }
            if (criteria.getColumnValue() != null) {
                specification = specification.and(buildStringSpecification(criteria.getColumnValue(), DbDataObject_.columnValue));
            }
            if (criteria.getCaseDataObjectId() != null) {
                specification = specification.and(buildSpecification(criteria.getCaseDataObjectId(),
                    root -> root.join(DbDataObject_.caseDataObjects, JoinType.LEFT).get(CaseDataObject_.id)));
            }
        }
        return specification;
    }
}
