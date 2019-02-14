package com.edigitpath.caze.service;

import com.edigitpath.caze.domain.ApiHeader;
import com.edigitpath.caze.repository.ApiHeaderRepository;
import com.edigitpath.caze.service.dto.ApiHeaderDTO;
import com.edigitpath.caze.service.mapper.ApiHeaderMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing ApiHeader.
 */
@Service
@Transactional
public class ApiHeaderService {

    private final Logger log = LoggerFactory.getLogger(ApiHeaderService.class);

    private final ApiHeaderRepository apiHeaderRepository;

    private final ApiHeaderMapper apiHeaderMapper;

    public ApiHeaderService(ApiHeaderRepository apiHeaderRepository, ApiHeaderMapper apiHeaderMapper) {
        this.apiHeaderRepository = apiHeaderRepository;
        this.apiHeaderMapper = apiHeaderMapper;
    }

    /**
     * Save a apiHeader.
     *
     * @param apiHeaderDTO the entity to save
     * @return the persisted entity
     */
    public ApiHeaderDTO save(ApiHeaderDTO apiHeaderDTO) {
        log.debug("Request to save ApiHeader : {}", apiHeaderDTO);
        ApiHeader apiHeader = apiHeaderMapper.toEntity(apiHeaderDTO);
        apiHeader = apiHeaderRepository.save(apiHeader);
        return apiHeaderMapper.toDto(apiHeader);
    }

    /**
     * Get all the apiHeaders.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public Page<ApiHeaderDTO> findAll(Pageable pageable) {
        log.debug("Request to get all ApiHeaders");
        return apiHeaderRepository.findAll(pageable)
            .map(apiHeaderMapper::toDto);
    }


    /**
     * Get one apiHeader by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Transactional(readOnly = true)
    public Optional<ApiHeaderDTO> findOne(Long id) {
        log.debug("Request to get ApiHeader : {}", id);
        return apiHeaderRepository.findById(id)
            .map(apiHeaderMapper::toDto);
    }

    /**
     * Delete the apiHeader by id.
     *
     * @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete ApiHeader : {}", id);        apiHeaderRepository.deleteById(id);
    }
}
