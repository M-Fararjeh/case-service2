package com.edigitpath.caze.web.rest;
import com.edigitpath.caze.service.FileDataObjectService;
import com.edigitpath.caze.web.rest.errors.BadRequestAlertException;
import com.edigitpath.caze.web.rest.util.HeaderUtil;
import com.edigitpath.caze.web.rest.util.PaginationUtil;
import com.edigitpath.caze.service.dto.FileDataObjectDTO;
import com.edigitpath.caze.service.dto.FileDataObjectCriteria;
import com.edigitpath.caze.service.FileDataObjectQueryService;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing FileDataObject.
 */
@RestController
@RequestMapping("/api")
public class FileDataObjectResource {

    private final Logger log = LoggerFactory.getLogger(FileDataObjectResource.class);

    private static final String ENTITY_NAME = "fileDataObject";

    private final FileDataObjectService fileDataObjectService;

    private final FileDataObjectQueryService fileDataObjectQueryService;

    public FileDataObjectResource(FileDataObjectService fileDataObjectService, FileDataObjectQueryService fileDataObjectQueryService) {
        this.fileDataObjectService = fileDataObjectService;
        this.fileDataObjectQueryService = fileDataObjectQueryService;
    }

    /**
     * POST  /file-data-objects : Create a new fileDataObject.
     *
     * @param fileDataObjectDTO the fileDataObjectDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new fileDataObjectDTO, or with status 400 (Bad Request) if the fileDataObject has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/file-data-objects")
    public ResponseEntity<FileDataObjectDTO> createFileDataObject(@RequestBody FileDataObjectDTO fileDataObjectDTO) throws URISyntaxException {
        log.debug("REST request to save FileDataObject : {}", fileDataObjectDTO);
        if (fileDataObjectDTO.getId() != null) {
            throw new BadRequestAlertException("A new fileDataObject cannot already have an ID", ENTITY_NAME, "idexists");
        }
        FileDataObjectDTO result = fileDataObjectService.save(fileDataObjectDTO);
        return ResponseEntity.created(new URI("/api/file-data-objects/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /file-data-objects : Updates an existing fileDataObject.
     *
     * @param fileDataObjectDTO the fileDataObjectDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated fileDataObjectDTO,
     * or with status 400 (Bad Request) if the fileDataObjectDTO is not valid,
     * or with status 500 (Internal Server Error) if the fileDataObjectDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/file-data-objects")
    public ResponseEntity<FileDataObjectDTO> updateFileDataObject(@RequestBody FileDataObjectDTO fileDataObjectDTO) throws URISyntaxException {
        log.debug("REST request to update FileDataObject : {}", fileDataObjectDTO);
        if (fileDataObjectDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        FileDataObjectDTO result = fileDataObjectService.save(fileDataObjectDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, fileDataObjectDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /file-data-objects : get all the fileDataObjects.
     *
     * @param pageable the pagination information
     * @param criteria the criterias which the requested entities should match
     * @return the ResponseEntity with status 200 (OK) and the list of fileDataObjects in body
     */
    @GetMapping("/file-data-objects")
    public ResponseEntity<List<FileDataObjectDTO>> getAllFileDataObjects(FileDataObjectCriteria criteria, Pageable pageable) {
        log.debug("REST request to get FileDataObjects by criteria: {}", criteria);
        Page<FileDataObjectDTO> page = fileDataObjectQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/file-data-objects");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * GET  /file-data-objects/count : count all the fileDataObjects.
    *
    * @param criteria the criterias which the requested entities should match
    * @return the ResponseEntity with status 200 (OK) and the count in body
    */
    @GetMapping("/file-data-objects/count")
    public ResponseEntity<Long> countFileDataObjects(FileDataObjectCriteria criteria) {
        log.debug("REST request to count FileDataObjects by criteria: {}", criteria);
        return ResponseEntity.ok().body(fileDataObjectQueryService.countByCriteria(criteria));
    }

    /**
     * GET  /file-data-objects/:id : get the "id" fileDataObject.
     *
     * @param id the id of the fileDataObjectDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the fileDataObjectDTO, or with status 404 (Not Found)
     */
    @GetMapping("/file-data-objects/{id}")
    public ResponseEntity<FileDataObjectDTO> getFileDataObject(@PathVariable Long id) {
        log.debug("REST request to get FileDataObject : {}", id);
        Optional<FileDataObjectDTO> fileDataObjectDTO = fileDataObjectService.findOne(id);
        return ResponseUtil.wrapOrNotFound(fileDataObjectDTO);
    }

    /**
     * DELETE  /file-data-objects/:id : delete the "id" fileDataObject.
     *
     * @param id the id of the fileDataObjectDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/file-data-objects/{id}")
    public ResponseEntity<Void> deleteFileDataObject(@PathVariable Long id) {
        log.debug("REST request to delete FileDataObject : {}", id);
        fileDataObjectService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
