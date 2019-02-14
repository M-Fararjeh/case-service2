package com.edigitpath.caze.service;

import com.edigitpath.caze.domain.CamundaProcessInstance;
import com.edigitpath.caze.repository.CamundaProcessInstanceRepository;
import com.edigitpath.caze.service.dto.CamundaProcessInstanceDTO;
import com.edigitpath.caze.service.mapper.CamundaProcessInstanceMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing CamundaProcessInstance.
 */
@Service
@Transactional
public class CamundaProcessInstanceService {

    private final Logger log = LoggerFactory.getLogger(CamundaProcessInstanceService.class);

    private final CamundaProcessInstanceRepository camundaProcessInstanceRepository;

    private final CamundaProcessInstanceMapper camundaProcessInstanceMapper;

    public CamundaProcessInstanceService(CamundaProcessInstanceRepository camundaProcessInstanceRepository, CamundaProcessInstanceMapper camundaProcessInstanceMapper) {
        this.camundaProcessInstanceRepository = camundaProcessInstanceRepository;
        this.camundaProcessInstanceMapper = camundaProcessInstanceMapper;
    }

    /**
     * Save a camundaProcessInstance.
     *
     * @param camundaProcessInstanceDTO the entity to save
     * @return the persisted entity
     */
    public CamundaProcessInstanceDTO save(CamundaProcessInstanceDTO camundaProcessInstanceDTO) {
        log.debug("Request to save CamundaProcessInstance : {}", camundaProcessInstanceDTO);
        CamundaProcessInstance camundaProcessInstance = camundaProcessInstanceMapper.toEntity(camundaProcessInstanceDTO);
        camundaProcessInstance = camundaProcessInstanceRepository.save(camundaProcessInstance);
        return camundaProcessInstanceMapper.toDto(camundaProcessInstance);
    }

    /**
     * Get all the camundaProcessInstances.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public Page<CamundaProcessInstanceDTO> findAll(Pageable pageable) {
        log.debug("Request to get all CamundaProcessInstances");
        return camundaProcessInstanceRepository.findAll(pageable)
            .map(camundaProcessInstanceMapper::toDto);
    }


    /**
     * Get one camundaProcessInstance by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Transactional(readOnly = true)
    public Optional<CamundaProcessInstanceDTO> findOne(Long id) {
        log.debug("Request to get CamundaProcessInstance : {}", id);
        return camundaProcessInstanceRepository.findById(id)
            .map(camundaProcessInstanceMapper::toDto);
    }

    /**
     * Delete the camundaProcessInstance by id.
     *
     * @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete CamundaProcessInstance : {}", id);        camundaProcessInstanceRepository.deleteById(id);
    }
}
