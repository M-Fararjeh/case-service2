package com.edigitpath.caze.service;

import com.edigitpath.caze.domain.FileDataObject;
import com.edigitpath.caze.repository.FileDataObjectRepository;
import com.edigitpath.caze.service.dto.FileDataObjectDTO;
import com.edigitpath.caze.service.mapper.FileDataObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing FileDataObject.
 */
@Service
@Transactional
public class FileDataObjectService {

    private final Logger log = LoggerFactory.getLogger(FileDataObjectService.class);

    private final FileDataObjectRepository fileDataObjectRepository;

    private final FileDataObjectMapper fileDataObjectMapper;

    public FileDataObjectService(FileDataObjectRepository fileDataObjectRepository, FileDataObjectMapper fileDataObjectMapper) {
        this.fileDataObjectRepository = fileDataObjectRepository;
        this.fileDataObjectMapper = fileDataObjectMapper;
    }

    /**
     * Save a fileDataObject.
     *
     * @param fileDataObjectDTO the entity to save
     * @return the persisted entity
     */
    public FileDataObjectDTO save(FileDataObjectDTO fileDataObjectDTO) {
        log.debug("Request to save FileDataObject : {}", fileDataObjectDTO);
        FileDataObject fileDataObject = fileDataObjectMapper.toEntity(fileDataObjectDTO);
        fileDataObject = fileDataObjectRepository.save(fileDataObject);
        return fileDataObjectMapper.toDto(fileDataObject);
    }

    /**
     * Get all the fileDataObjects.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public Page<FileDataObjectDTO> findAll(Pageable pageable) {
        log.debug("Request to get all FileDataObjects");
        return fileDataObjectRepository.findAll(pageable)
            .map(fileDataObjectMapper::toDto);
    }


    /**
     * Get one fileDataObject by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Transactional(readOnly = true)
    public Optional<FileDataObjectDTO> findOne(Long id) {
        log.debug("Request to get FileDataObject : {}", id);
        return fileDataObjectRepository.findById(id)
            .map(fileDataObjectMapper::toDto);
    }

    /**
     * Delete the fileDataObject by id.
     *
     * @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete FileDataObject : {}", id);        fileDataObjectRepository.deleteById(id);
    }
}
