package com.edigitpath.caze.service;

import com.edigitpath.caze.domain.CazeType;
import com.edigitpath.caze.repository.CazeTypeRepository;
import com.edigitpath.caze.service.dto.CazeTypeDTO;
import com.edigitpath.caze.service.mapper.CazeTypeMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing CazeType.
 */
@Service
@Transactional
public class CazeTypeService {

    private final Logger log = LoggerFactory.getLogger(CazeTypeService.class);

    private final CazeTypeRepository cazeTypeRepository;

    private final CazeTypeMapper cazeTypeMapper;

    public CazeTypeService(CazeTypeRepository cazeTypeRepository, CazeTypeMapper cazeTypeMapper) {
        this.cazeTypeRepository = cazeTypeRepository;
        this.cazeTypeMapper = cazeTypeMapper;
    }

    /**
     * Save a cazeType.
     *
     * @param cazeTypeDTO the entity to save
     * @return the persisted entity
     */
    public CazeTypeDTO save(CazeTypeDTO cazeTypeDTO) {
        log.debug("Request to save CazeType : {}", cazeTypeDTO);
        CazeType cazeType = cazeTypeMapper.toEntity(cazeTypeDTO);
        cazeType = cazeTypeRepository.save(cazeType);
        return cazeTypeMapper.toDto(cazeType);
    }

    /**
     * Get all the cazeTypes.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public Page<CazeTypeDTO> findAll(Pageable pageable) {
        log.debug("Request to get all CazeTypes");
        return cazeTypeRepository.findAll(pageable)
            .map(cazeTypeMapper::toDto);
    }


    /**
     * Get one cazeType by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Transactional(readOnly = true)
    public Optional<CazeTypeDTO> findOne(Long id) {
        log.debug("Request to get CazeType : {}", id);
        return cazeTypeRepository.findById(id)
            .map(cazeTypeMapper::toDto);
    }

    /**
     * Delete the cazeType by id.
     *
     * @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete CazeType : {}", id);        cazeTypeRepository.deleteById(id);
    }
}
