package com.edigitpath.caze.web.rest;
import com.edigitpath.caze.service.DbDataObjectService;
import com.edigitpath.caze.web.rest.errors.BadRequestAlertException;
import com.edigitpath.caze.web.rest.util.HeaderUtil;
import com.edigitpath.caze.web.rest.util.PaginationUtil;
import com.edigitpath.caze.service.dto.DbDataObjectDTO;
import com.edigitpath.caze.service.dto.DbDataObjectCriteria;
import com.edigitpath.caze.service.DbDataObjectQueryService;
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
 * REST controller for managing DbDataObject.
 */
@RestController
@RequestMapping("/api")
public class DbDataObjectResource {

    private final Logger log = LoggerFactory.getLogger(DbDataObjectResource.class);

    private static final String ENTITY_NAME = "dbDataObject";

    private final DbDataObjectService dbDataObjectService;

    private final DbDataObjectQueryService dbDataObjectQueryService;

    public DbDataObjectResource(DbDataObjectService dbDataObjectService, DbDataObjectQueryService dbDataObjectQueryService) {
        this.dbDataObjectService = dbDataObjectService;
        this.dbDataObjectQueryService = dbDataObjectQueryService;
    }

    /**
     * POST  /db-data-objects : Create a new dbDataObject.
     *
     * @param dbDataObjectDTO the dbDataObjectDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new dbDataObjectDTO, or with status 400 (Bad Request) if the dbDataObject has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/db-data-objects")
    public ResponseEntity<DbDataObjectDTO> createDbDataObject(@RequestBody DbDataObjectDTO dbDataObjectDTO) throws URISyntaxException {
        log.debug("REST request to save DbDataObject : {}", dbDataObjectDTO);
        if (dbDataObjectDTO.getId() != null) {
            throw new BadRequestAlertException("A new dbDataObject cannot already have an ID", ENTITY_NAME, "idexists");
        }
        DbDataObjectDTO result = dbDataObjectService.save(dbDataObjectDTO);
        return ResponseEntity.created(new URI("/api/db-data-objects/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /db-data-objects : Updates an existing dbDataObject.
     *
     * @param dbDataObjectDTO the dbDataObjectDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated dbDataObjectDTO,
     * or with status 400 (Bad Request) if the dbDataObjectDTO is not valid,
     * or with status 500 (Internal Server Error) if the dbDataObjectDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/db-data-objects")
    public ResponseEntity<DbDataObjectDTO> updateDbDataObject(@RequestBody DbDataObjectDTO dbDataObjectDTO) throws URISyntaxException {
        log.debug("REST request to update DbDataObject : {}", dbDataObjectDTO);
        if (dbDataObjectDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        DbDataObjectDTO result = dbDataObjectService.save(dbDataObjectDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, dbDataObjectDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /db-data-objects : get all the dbDataObjects.
     *
     * @param pageable the pagination information
     * @param criteria the criterias which the requested entities should match
     * @return the ResponseEntity with status 200 (OK) and the list of dbDataObjects in body
     */
    @GetMapping("/db-data-objects")
    public ResponseEntity<List<DbDataObjectDTO>> getAllDbDataObjects(DbDataObjectCriteria criteria, Pageable pageable) {
        log.debug("REST request to get DbDataObjects by criteria: {}", criteria);
        Page<DbDataObjectDTO> page = dbDataObjectQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/db-data-objects");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * GET  /db-data-objects/count : count all the dbDataObjects.
    *
    * @param criteria the criterias which the requested entities should match
    * @return the ResponseEntity with status 200 (OK) and the count in body
    */
    @GetMapping("/db-data-objects/count")
    public ResponseEntity<Long> countDbDataObjects(DbDataObjectCriteria criteria) {
        log.debug("REST request to count DbDataObjects by criteria: {}", criteria);
        return ResponseEntity.ok().body(dbDataObjectQueryService.countByCriteria(criteria));
    }

    /**
     * GET  /db-data-objects/:id : get the "id" dbDataObject.
     *
     * @param id the id of the dbDataObjectDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the dbDataObjectDTO, or with status 404 (Not Found)
     */
    @GetMapping("/db-data-objects/{id}")
    public ResponseEntity<DbDataObjectDTO> getDbDataObject(@PathVariable Long id) {
        log.debug("REST request to get DbDataObject : {}", id);
        Optional<DbDataObjectDTO> dbDataObjectDTO = dbDataObjectService.findOne(id);
        return ResponseUtil.wrapOrNotFound(dbDataObjectDTO);
    }

    /**
     * DELETE  /db-data-objects/:id : delete the "id" dbDataObject.
     *
     * @param id the id of the dbDataObjectDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/db-data-objects/{id}")
    public ResponseEntity<Void> deleteDbDataObject(@PathVariable Long id) {
        log.debug("REST request to delete DbDataObject : {}", id);
        dbDataObjectService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
