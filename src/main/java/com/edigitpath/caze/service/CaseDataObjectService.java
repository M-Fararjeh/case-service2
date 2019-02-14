package com.edigitpath.caze.service;

import com.edigitpath.caze.domain.CaseDataObject;
import com.edigitpath.caze.repository.CaseDataObjectRepository;
import com.edigitpath.caze.service.dto.CaseDataObjectDTO;
import com.edigitpath.caze.service.mapper.CaseDataObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing CaseDataObject.
 */
@Service
@Transactional
public class CaseDataObjectService {

    private final Logger log = LoggerFactory.getLogger(CaseDataObjectService.class);

    private final CaseDataObjectRepository caseDataObjectRepository;

    private final CaseDataObjectMapper caseDataObjectMapper;

    public CaseDataObjectService(CaseDataObjectRepository caseDataObjectRepository, CaseDataObjectMapper caseDataObjectMapper) {
        this.caseDataObjectRepository = caseDataObjectRepository;
        this.caseDataObjectMapper = caseDataObjectMapper;
    }

    /**
     * Save a caseDataObject.
     *
     * @param caseDataObjectDTO the entity to save
     * @return the persisted entity
     */
    public CaseDataObjectDTO save(CaseDataObjectDTO caseDataObjectDTO) {
        log.debug("Request to save CaseDataObject : {}", caseDataObjectDTO);
        CaseDataObject caseDataObject = caseDataObjectMapper.toEntity(caseDataObjectDTO);
        caseDataObject = caseDataObjectRepository.save(caseDataObject);
        return caseDataObjectMapper.toDto(caseDataObject);
    }

    /**
     * Get all the caseDataObjects.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public Page<CaseDataObjectDTO> findAll(Pageable pageable) {
        log.debug("Request to get all CaseDataObjects");
        return caseDataObjectRepository.findAll(pageable)
            .map(caseDataObjectMapper::toDto);
    }


    /**
     * Get one caseDataObject by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Transactional(readOnly = true)
    public Optional<CaseDataObjectDTO> findOne(Long id) {
        log.debug("Request to get CaseDataObject : {}", id);
        return caseDataObjectRepository.findById(id)
            .map(caseDataObjectMapper::toDto);
    }

    /**
     * Delete the caseDataObject by id.
     *
     * @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete CaseDataObject : {}", id);        caseDataObjectRepository.deleteById(id);
    }
}
