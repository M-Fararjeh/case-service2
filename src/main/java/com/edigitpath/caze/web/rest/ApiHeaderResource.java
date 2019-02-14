package com.edigitpath.caze.web.rest;
import com.edigitpath.caze.service.ApiHeaderService;
import com.edigitpath.caze.web.rest.errors.BadRequestAlertException;
import com.edigitpath.caze.web.rest.util.HeaderUtil;
import com.edigitpath.caze.web.rest.util.PaginationUtil;
import com.edigitpath.caze.service.dto.ApiHeaderDTO;
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
 * REST controller for managing ApiHeader.
 */
@RestController
@RequestMapping("/api")
public class ApiHeaderResource {

    private final Logger log = LoggerFactory.getLogger(ApiHeaderResource.class);

    private static final String ENTITY_NAME = "apiHeader";

    private final ApiHeaderService apiHeaderService;

    public ApiHeaderResource(ApiHeaderService apiHeaderService) {
        this.apiHeaderService = apiHeaderService;
    }

    /**
     * POST  /api-headers : Create a new apiHeader.
     *
     * @param apiHeaderDTO the apiHeaderDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new apiHeaderDTO, or with status 400 (Bad Request) if the apiHeader has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/api-headers")
    public ResponseEntity<ApiHeaderDTO> createApiHeader(@RequestBody ApiHeaderDTO apiHeaderDTO) throws URISyntaxException {
        log.debug("REST request to save ApiHeader : {}", apiHeaderDTO);
        if (apiHeaderDTO.getId() != null) {
            throw new BadRequestAlertException("A new apiHeader cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ApiHeaderDTO result = apiHeaderService.save(apiHeaderDTO);
        return ResponseEntity.created(new URI("/api/api-headers/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /api-headers : Updates an existing apiHeader.
     *
     * @param apiHeaderDTO the apiHeaderDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated apiHeaderDTO,
     * or with status 400 (Bad Request) if the apiHeaderDTO is not valid,
     * or with status 500 (Internal Server Error) if the apiHeaderDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/api-headers")
    public ResponseEntity<ApiHeaderDTO> updateApiHeader(@RequestBody ApiHeaderDTO apiHeaderDTO) throws URISyntaxException {
        log.debug("REST request to update ApiHeader : {}", apiHeaderDTO);
        if (apiHeaderDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ApiHeaderDTO result = apiHeaderService.save(apiHeaderDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, apiHeaderDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /api-headers : get all the apiHeaders.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of apiHeaders in body
     */
    @GetMapping("/api-headers")
    public ResponseEntity<List<ApiHeaderDTO>> getAllApiHeaders(Pageable pageable) {
        log.debug("REST request to get a page of ApiHeaders");
        Page<ApiHeaderDTO> page = apiHeaderService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/api-headers");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /api-headers/:id : get the "id" apiHeader.
     *
     * @param id the id of the apiHeaderDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the apiHeaderDTO, or with status 404 (Not Found)
     */
    @GetMapping("/api-headers/{id}")
    public ResponseEntity<ApiHeaderDTO> getApiHeader(@PathVariable Long id) {
        log.debug("REST request to get ApiHeader : {}", id);
        Optional<ApiHeaderDTO> apiHeaderDTO = apiHeaderService.findOne(id);
        return ResponseUtil.wrapOrNotFound(apiHeaderDTO);
    }

    /**
     * DELETE  /api-headers/:id : delete the "id" apiHeader.
     *
     * @param id the id of the apiHeaderDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/api-headers/{id}")
    public ResponseEntity<Void> deleteApiHeader(@PathVariable Long id) {
        log.debug("REST request to delete ApiHeader : {}", id);
        apiHeaderService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
