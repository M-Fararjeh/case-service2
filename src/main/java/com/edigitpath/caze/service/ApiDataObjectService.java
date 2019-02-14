package com.edigitpath.caze.service;

import com.edigitpath.caze.domain.ApiDataObject;
import com.edigitpath.caze.repository.ApiDataObjectRepository;
import com.edigitpath.caze.service.dto.ApiDataObjectDTO;
import com.edigitpath.caze.service.mapper.ApiDataObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing ApiDataObject.
 */
@Service
@Transactional
public class ApiDataObjectService {

    private final Logger log = LoggerFactory.getLogger(ApiDataObjectService.class);

    private final ApiDataObjectRepository apiDataObjectRepository;

    private final ApiDataObjectMapper apiDataObjectMapper;

    public ApiDataObjectService(ApiDataObjectRepository apiDataObjectRepository, ApiDataObjectMapper apiDataObjectMapper) {
        this.apiDataObjectRepository = apiDataObjectRepository;
        this.apiDataObjectMapper = apiDataObjectMapper;
    }

    /**
     * Save a apiDataObject.
     *
     * @param apiDataObjectDTO the entity to save
     * @return the persisted entity
     */
    public ApiDataObjectDTO save(ApiDataObjectDTO apiDataObjectDTO) {
        log.debug("Request to save ApiDataObject : {}", apiDataObjectDTO);
        ApiDataObject apiDataObject = apiDataObjectMapper.toEntity(apiDataObjectDTO);
        apiDataObject = apiDataObjectRepository.save(apiDataObject);
        return apiDataObjectMapper.toDto(apiDataObject);
    }

    /**
     * Get all the apiDataObjects.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public Page<ApiDataObjectDTO> findAll(Pageable pageable) {
        log.debug("Request to get all ApiDataObjects");
        return apiDataObjectRepository.findAll(pageable)
            .map(apiDataObjectMapper::toDto);
    }


    /**
     * Get one apiDataObject by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Transactional(readOnly = true)
    public Optional<ApiDataObjectDTO> findOne(Long id) {
        log.debug("Request to get ApiDataObject : {}", id);
        return apiDataObjectRepository.findById(id)
            .map(apiDataObjectMapper::toDto);
    }

    /**
     * Delete the apiDataObject by id.
     *
     * @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete ApiDataObject : {}", id);        apiDataObjectRepository.deleteById(id);
    }
}
