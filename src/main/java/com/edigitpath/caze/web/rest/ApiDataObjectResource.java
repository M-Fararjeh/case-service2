package com.edigitpath.caze.web.rest;
import com.edigitpath.caze.service.ApiDataObjectService;
import com.edigitpath.caze.web.rest.errors.BadRequestAlertException;
import com.edigitpath.caze.web.rest.util.HeaderUtil;
import com.edigitpath.caze.web.rest.util.PaginationUtil;
import com.edigitpath.caze.service.dto.ApiDataObjectDTO;
import com.edigitpath.caze.service.dto.ApiDataObjectCriteria;
import com.edigitpath.caze.service.ApiDataObjectQueryService;
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
 * REST controller for managing ApiDataObject.
 */
@RestController
@RequestMapping("/api")
public class ApiDataObjectResource {

    private final Logger log = LoggerFactory.getLogger(ApiDataObjectResource.class);

    private static final String ENTITY_NAME = "apiDataObject";

    private final ApiDataObjectService apiDataObjectService;

    private final ApiDataObjectQueryService apiDataObjectQueryService;

    public ApiDataObjectResource(ApiDataObjectService apiDataObjectService, ApiDataObjectQueryService apiDataObjectQueryService) {
        this.apiDataObjectService = apiDataObjectService;
        this.apiDataObjectQueryService = apiDataObjectQueryService;
    }

    /**
     * POST  /api-data-objects : Create a new apiDataObject.
     *
     * @param apiDataObjectDTO the apiDataObjectDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new apiDataObjectDTO, or with status 400 (Bad Request) if the apiDataObject has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/api-data-objects")
    public ResponseEntity<ApiDataObjectDTO> createApiDataObject(@RequestBody ApiDataObjectDTO apiDataObjectDTO) throws URISyntaxException {
        log.debug("REST request to save ApiDataObject : {}", apiDataObjectDTO);
        if (apiDataObjectDTO.getId() != null) {
            throw new BadRequestAlertException("A new apiDataObject cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ApiDataObjectDTO result = apiDataObjectService.save(apiDataObjectDTO);
        return ResponseEntity.created(new URI("/api/api-data-objects/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /api-data-objects : Updates an existing apiDataObject.
     *
     * @param apiDataObjectDTO the apiDataObjectDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated apiDataObjectDTO,
     * or with status 400 (Bad Request) if the apiDataObjectDTO is not valid,
     * or with status 500 (Internal Server Error) if the apiDataObjectDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/api-data-objects")
    public ResponseEntity<ApiDataObjectDTO> updateApiDataObject(@RequestBody ApiDataObjectDTO apiDataObjectDTO) throws URISyntaxException {
        log.debug("REST request to update ApiDataObject : {}", apiDataObjectDTO);
        if (apiDataObjectDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ApiDataObjectDTO result = apiDataObjectService.save(apiDataObjectDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, apiDataObjectDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /api-data-objects : get all the apiDataObjects.
     *
     * @param pageable the pagination information
     * @param criteria the criterias which the requested entities should match
     * @return the ResponseEntity with status 200 (OK) and the list of apiDataObjects in body
     */
    @GetMapping("/api-data-objects")
    public ResponseEntity<List<ApiDataObjectDTO>> getAllApiDataObjects(ApiDataObjectCriteria criteria, Pageable pageable) {
        log.debug("REST request to get ApiDataObjects by criteria: {}", criteria);
        Page<ApiDataObjectDTO> page = apiDataObjectQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/api-data-objects");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * GET  /api-data-objects/count : count all the apiDataObjects.
    *
    * @param criteria the criterias which the requested entities should match
    * @return the ResponseEntity with status 200 (OK) and the count in body
    */
    @GetMapping("/api-data-objects/count")
    public ResponseEntity<Long> countApiDataObjects(ApiDataObjectCriteria criteria) {
        log.debug("REST request to count ApiDataObjects by criteria: {}", criteria);
        return ResponseEntity.ok().body(apiDataObjectQueryService.countByCriteria(criteria));
    }

    /**
     * GET  /api-data-objects/:id : get the "id" apiDataObject.
     *
     * @param id the id of the apiDataObjectDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the apiDataObjectDTO, or with status 404 (Not Found)
     */
    @GetMapping("/api-data-objects/{id}")
    public ResponseEntity<ApiDataObjectDTO> getApiDataObject(@PathVariable Long id) {
        log.debug("REST request to get ApiDataObject : {}", id);
        Optional<ApiDataObjectDTO> apiDataObjectDTO = apiDataObjectService.findOne(id);
        return ResponseUtil.wrapOrNotFound(apiDataObjectDTO);
    }

    /**
     * DELETE  /api-data-objects/:id : delete the "id" apiDataObject.
     *
     * @param id the id of the apiDataObjectDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/api-data-objects/{id}")
    public ResponseEntity<Void> deleteApiDataObject(@PathVariable Long id) {
        log.debug("REST request to delete ApiDataObject : {}", id);
        apiDataObjectService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
