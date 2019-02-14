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

import com.edigitpath.caze.domain.FileDataObject;
import com.edigitpath.caze.domain.*; // for static metamodels
import com.edigitpath.caze.repository.FileDataObjectRepository;
import com.edigitpath.caze.service.dto.FileDataObjectCriteria;
import com.edigitpath.caze.service.dto.FileDataObjectDTO;
import com.edigitpath.caze.service.mapper.FileDataObjectMapper;

/**
 * Service for executing complex queries for FileDataObject entities in the database.
 * The main input is a {@link FileDataObjectCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link FileDataObjectDTO} or a {@link Page} of {@link FileDataObjectDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class FileDataObjectQueryService extends QueryService<FileDataObject> {

    private final Logger log = LoggerFactory.getLogger(FileDataObjectQueryService.class);

    private final FileDataObjectRepository fileDataObjectRepository;

    private final FileDataObjectMapper fileDataObjectMapper;

    public FileDataObjectQueryService(FileDataObjectRepository fileDataObjectRepository, FileDataObjectMapper fileDataObjectMapper) {
        this.fileDataObjectRepository = fileDataObjectRepository;
        this.fileDataObjectMapper = fileDataObjectMapper;
    }

    /**
     * Return a {@link List} of {@link FileDataObjectDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<FileDataObjectDTO> findByCriteria(FileDataObjectCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<FileDataObject> specification = createSpecification(criteria);
        return fileDataObjectMapper.toDto(fileDataObjectRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link FileDataObjectDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<FileDataObjectDTO> findByCriteria(FileDataObjectCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<FileDataObject> specification = createSpecification(criteria);
        return fileDataObjectRepository.findAll(specification, page)
            .map(fileDataObjectMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(FileDataObjectCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<FileDataObject> specification = createSpecification(criteria);
        return fileDataObjectRepository.count(specification);
    }

    /**
     * Function to convert FileDataObjectCriteria to a {@link Specification}
     */
    private Specification<FileDataObject> createSpecification(FileDataObjectCriteria criteria) {
        Specification<FileDataObject> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), FileDataObject_.id));
            }
            if (criteria.getCmisId() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCmisId(), FileDataObject_.cmisId));
            }
            if (criteria.getPath() != null) {
                specification = specification.and(buildStringSpecification(criteria.getPath(), FileDataObject_.path));
            }
            if (criteria.getCaseDataObjectId() != null) {
                specification = specification.and(buildSpecification(criteria.getCaseDataObjectId(),
                    root -> root.join(FileDataObject_.caseDataObjects, JoinType.LEFT).get(CaseDataObject_.id)));
            }
        }
        return specification;
    }
}
