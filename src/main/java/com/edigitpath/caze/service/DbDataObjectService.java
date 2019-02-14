package com.edigitpath.caze.service;

import com.edigitpath.caze.domain.DbDataObject;
import com.edigitpath.caze.repository.DbDataObjectRepository;
import com.edigitpath.caze.service.dto.DbDataObjectDTO;
import com.edigitpath.caze.service.mapper.DbDataObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing DbDataObject.
 */
@Service
@Transactional
public class DbDataObjectService {

    private final Logger log = LoggerFactory.getLogger(DbDataObjectService.class);

    private final DbDataObjectRepository dbDataObjectRepository;

    private final DbDataObjectMapper dbDataObjectMapper;

    public DbDataObjectService(DbDataObjectRepository dbDataObjectRepository, DbDataObjectMapper dbDataObjectMapper) {
        this.dbDataObjectRepository = dbDataObjectRepository;
        this.dbDataObjectMapper = dbDataObjectMapper;
    }

    /**
     * Save a dbDataObject.
     *
     * @param dbDataObjectDTO the entity to save
     * @return the persisted entity
     */
    public DbDataObjectDTO save(DbDataObjectDTO dbDataObjectDTO) {
        log.debug("Request to save DbDataObject : {}", dbDataObjectDTO);
        DbDataObject dbDataObject = dbDataObjectMapper.toEntity(dbDataObjectDTO);
        dbDataObject = dbDataObjectRepository.save(dbDataObject);
        return dbDataObjectMapper.toDto(dbDataObject);
    }

    /**
     * Get all the dbDataObjects.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public Page<DbDataObjectDTO> findAll(Pageable pageable) {
        log.debug("Request to get all DbDataObjects");
        return dbDataObjectRepository.findAll(pageable)
            .map(dbDataObjectMapper::toDto);
    }


    /**
     * Get one dbDataObject by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Transactional(readOnly = true)
    public Optional<DbDataObjectDTO> findOne(Long id) {
        log.debug("Request to get DbDataObject : {}", id);
        return dbDataObjectRepository.findById(id)
            .map(dbDataObjectMapper::toDto);
    }

    /**
     * Delete the dbDataObject by id.
     *
     * @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete DbDataObject : {}", id);        dbDataObjectRepository.deleteById(id);
    }
}
