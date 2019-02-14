package com.edigitpath.caze.service;

import com.edigitpath.caze.domain.CamundaCaseInstance;
import com.edigitpath.caze.repository.CamundaCaseInstanceRepository;
import com.edigitpath.caze.service.dto.CamundaCaseInstanceDTO;
import com.edigitpath.caze.service.mapper.CamundaCaseInstanceMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing CamundaCaseInstance.
 */
@Service
@Transactional
public class CamundaCaseInstanceService {

    private final Logger log = LoggerFactory.getLogger(CamundaCaseInstanceService.class);

    private final CamundaCaseInstanceRepository camundaCaseInstanceRepository;

    private final CamundaCaseInstanceMapper camundaCaseInstanceMapper;

    public CamundaCaseInstanceService(CamundaCaseInstanceRepository camundaCaseInstanceRepository, CamundaCaseInstanceMapper camundaCaseInstanceMapper) {
        this.camundaCaseInstanceRepository = camundaCaseInstanceRepository;
        this.camundaCaseInstanceMapper = camundaCaseInstanceMapper;
    }

    /**
     * Save a camundaCaseInstance.
     *
     * @param camundaCaseInstanceDTO the entity to save
     * @return the persisted entity
     */
    public CamundaCaseInstanceDTO save(CamundaCaseInstanceDTO camundaCaseInstanceDTO) {
        log.debug("Request to save CamundaCaseInstance : {}", camundaCaseInstanceDTO);
        CamundaCaseInstance camundaCaseInstance = camundaCaseInstanceMapper.toEntity(camundaCaseInstanceDTO);
        camundaCaseInstance = camundaCaseInstanceRepository.save(camundaCaseInstance);
        return camundaCaseInstanceMapper.toDto(camundaCaseInstance);
    }

    /**
     * Get all the camundaCaseInstances.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public Page<CamundaCaseInstanceDTO> findAll(Pageable pageable) {
        log.debug("Request to get all CamundaCaseInstances");
        return camundaCaseInstanceRepository.findAll(pageable)
            .map(camundaCaseInstanceMapper::toDto);
    }


    /**
     * Get one camundaCaseInstance by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Transactional(readOnly = true)
    public Optional<CamundaCaseInstanceDTO> findOne(Long id) {
        log.debug("Request to get CamundaCaseInstance : {}", id);
        return camundaCaseInstanceRepository.findById(id)
            .map(camundaCaseInstanceMapper::toDto);
    }

    /**
     * Delete the camundaCaseInstance by id.
     *
     * @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete CamundaCaseInstance : {}", id);        camundaCaseInstanceRepository.deleteById(id);
    }
}
