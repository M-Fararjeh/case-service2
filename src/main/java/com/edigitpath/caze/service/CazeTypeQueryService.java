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

import com.edigitpath.caze.domain.CazeType;
import com.edigitpath.caze.domain.*; // for static metamodels
import com.edigitpath.caze.repository.CazeTypeRepository;
import com.edigitpath.caze.service.dto.CazeTypeCriteria;
import com.edigitpath.caze.service.dto.CazeTypeDTO;
import com.edigitpath.caze.service.mapper.CazeTypeMapper;

/**
 * Service for executing complex queries for CazeType entities in the database.
 * The main input is a {@link CazeTypeCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link CazeTypeDTO} or a {@link Page} of {@link CazeTypeDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class CazeTypeQueryService extends QueryService<CazeType> {

    private final Logger log = LoggerFactory.getLogger(CazeTypeQueryService.class);

    private final CazeTypeRepository cazeTypeRepository;

    private final CazeTypeMapper cazeTypeMapper;

    public CazeTypeQueryService(CazeTypeRepository cazeTypeRepository, CazeTypeMapper cazeTypeMapper) {
        this.cazeTypeRepository = cazeTypeRepository;
        this.cazeTypeMapper = cazeTypeMapper;
    }

    /**
     * Return a {@link List} of {@link CazeTypeDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<CazeTypeDTO> findByCriteria(CazeTypeCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<CazeType> specification = createSpecification(criteria);
        return cazeTypeMapper.toDto(cazeTypeRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link CazeTypeDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<CazeTypeDTO> findByCriteria(CazeTypeCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<CazeType> specification = createSpecification(criteria);
        return cazeTypeRepository.findAll(specification, page)
            .map(cazeTypeMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(CazeTypeCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<CazeType> specification = createSpecification(criteria);
        return cazeTypeRepository.count(specification);
    }

    /**
     * Function to convert CazeTypeCriteria to a {@link Specification}
     */
    private Specification<CazeType> createSpecification(CazeTypeCriteria criteria) {
        Specification<CazeType> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), CazeType_.id));
            }
            if (criteria.getName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getName(), CazeType_.name));
            }
            if (criteria.getPriority() != null) {
                specification = specification.and(buildSpecification(criteria.getPriority(), CazeType_.priority));
            }
            if (criteria.getRequiredTime() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getRequiredTime(), CazeType_.requiredTime));
            }
            if (criteria.getSecured() != null) {
                specification = specification.and(buildSpecification(criteria.getSecured(), CazeType_.secured));
            }
            if (criteria.getCategoryId() != null) {
                specification = specification.and(buildSpecification(criteria.getCategoryId(),
                    root -> root.join(CazeType_.category, JoinType.LEFT).get(Category_.id)));
            }
        }
        return specification;
    }
}
