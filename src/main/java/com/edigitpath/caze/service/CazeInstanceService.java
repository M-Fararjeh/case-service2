package com.edigitpath.caze.service;

import com.edigitpath.caze.domain.CazeInstance;
import com.edigitpath.caze.repository.CazeInstanceRepository;
import com.edigitpath.caze.service.dto.CazeInstanceDTO;
import com.edigitpath.caze.service.mapper.CazeInstanceMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing CazeInstance.
 */
@Service
@Transactional
public class CazeInstanceService {

    private final Logger log = LoggerFactory.getLogger(CazeInstanceService.class);

    private final CazeInstanceRepository cazeInstanceRepository;

    private final CazeInstanceMapper cazeInstanceMapper;

    public CazeInstanceService(CazeInstanceRepository cazeInstanceRepository, CazeInstanceMapper cazeInstanceMapper) {
        this.cazeInstanceRepository = cazeInstanceRepository;
        this.cazeInstanceMapper = cazeInstanceMapper;
    }

    /**
     * Save a cazeInstance.
     *
     * @param cazeInstanceDTO the entity to save
     * @return the persisted entity
     */
    public CazeInstanceDTO save(CazeInstanceDTO cazeInstanceDTO) {
        log.debug("Request to save CazeInstance : {}", cazeInstanceDTO);
        CazeInstance cazeInstance = cazeInstanceMapper.toEntity(cazeInstanceDTO);
        cazeInstance = cazeInstanceRepository.save(cazeInstance);
        return cazeInstanceMapper.toDto(cazeInstance);
    }

    /**
     * Get all the cazeInstances.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public Page<CazeInstanceDTO> findAll(Pageable pageable) {
        log.debug("Request to get all CazeInstances");
        return cazeInstanceRepository.findAll(pageable)
            .map(cazeInstanceMapper::toDto);
    }

    /**
     * Get all the CazeInstance with eager load of many-to-many relationships.
     *
     * @return the list of entities
     */
    public Page<CazeInstanceDTO> findAllWithEagerRelationships(Pageable pageable) {
        return cazeInstanceRepository.findAllWithEagerRelationships(pageable).map(cazeInstanceMapper::toDto);
    }
    

    /**
     * Get one cazeInstance by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Transactional(readOnly = true)
    public Optional<CazeInstanceDTO> findOne(Long id) {
        log.debug("Request to get CazeInstance : {}", id);
        return cazeInstanceRepository.findOneWithEagerRelationships(id)
            .map(cazeInstanceMapper::toDto);
    }

    /**
     * Delete the cazeInstance by id.
     *
     * @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete CazeInstance : {}", id);        cazeInstanceRepository.deleteById(id);
    }
}
