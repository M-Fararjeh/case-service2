package com.edigitpath.caze.web.rest;
import com.edigitpath.caze.service.CazeInstanceService;
import com.edigitpath.caze.web.rest.errors.BadRequestAlertException;
import com.edigitpath.caze.web.rest.util.HeaderUtil;
import com.edigitpath.caze.web.rest.util.PaginationUtil;
import com.edigitpath.caze.service.dto.CazeInstanceDTO;
import com.edigitpath.caze.service.dto.CazeInstanceCriteria;
import com.edigitpath.caze.service.CazeInstanceQueryService;
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
 * REST controller for managing CazeInstance.
 */
@RestController
@RequestMapping("/api")
public class CazeInstanceResource {

    private final Logger log = LoggerFactory.getLogger(CazeInstanceResource.class);

    private static final String ENTITY_NAME = "cazeInstance";

    private final CazeInstanceService cazeInstanceService;

    private final CazeInstanceQueryService cazeInstanceQueryService;

    public CazeInstanceResource(CazeInstanceService cazeInstanceService, CazeInstanceQueryService cazeInstanceQueryService) {
        this.cazeInstanceService = cazeInstanceService;
        this.cazeInstanceQueryService = cazeInstanceQueryService;
    }

    /**
     * POST  /caze-instances : Create a new cazeInstance.
     *
     * @param cazeInstanceDTO the cazeInstanceDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new cazeInstanceDTO, or with status 400 (Bad Request) if the cazeInstance has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/caze-instances")
    public ResponseEntity<CazeInstanceDTO> createCazeInstance(@RequestBody CazeInstanceDTO cazeInstanceDTO) throws URISyntaxException {
        log.debug("REST request to save CazeInstance : {}", cazeInstanceDTO);
        if (cazeInstanceDTO.getId() != null) {
            throw new BadRequestAlertException("A new cazeInstance cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CazeInstanceDTO result = cazeInstanceService.save(cazeInstanceDTO);
        return ResponseEntity.created(new URI("/api/caze-instances/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /caze-instances : Updates an existing cazeInstance.
     *
     * @param cazeInstanceDTO the cazeInstanceDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated cazeInstanceDTO,
     * or with status 400 (Bad Request) if the cazeInstanceDTO is not valid,
     * or with status 500 (Internal Server Error) if the cazeInstanceDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/caze-instances")
    public ResponseEntity<CazeInstanceDTO> updateCazeInstance(@RequestBody CazeInstanceDTO cazeInstanceDTO) throws URISyntaxException {
        log.debug("REST request to update CazeInstance : {}", cazeInstanceDTO);
        if (cazeInstanceDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        CazeInstanceDTO result = cazeInstanceService.save(cazeInstanceDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, cazeInstanceDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /caze-instances : get all the cazeInstances.
     *
     * @param pageable the pagination information
     * @param criteria the criterias which the requested entities should match
     * @return the ResponseEntity with status 200 (OK) and the list of cazeInstances in body
     */
    @GetMapping("/caze-instances")
    public ResponseEntity<List<CazeInstanceDTO>> getAllCazeInstances(CazeInstanceCriteria criteria, Pageable pageable) {
        log.debug("REST request to get CazeInstances by criteria: {}", criteria);
        Page<CazeInstanceDTO> page = cazeInstanceQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/caze-instances");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * GET  /caze-instances/count : count all the cazeInstances.
    *
    * @param criteria the criterias which the requested entities should match
    * @return the ResponseEntity with status 200 (OK) and the count in body
    */
    @GetMapping("/caze-instances/count")
    public ResponseEntity<Long> countCazeInstances(CazeInstanceCriteria criteria) {
        log.debug("REST request to count CazeInstances by criteria: {}", criteria);
        return ResponseEntity.ok().body(cazeInstanceQueryService.countByCriteria(criteria));
    }

    /**
     * GET  /caze-instances/:id : get the "id" cazeInstance.
     *
     * @param id the id of the cazeInstanceDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the cazeInstanceDTO, or with status 404 (Not Found)
     */
    @GetMapping("/caze-instances/{id}")
    public ResponseEntity<CazeInstanceDTO> getCazeInstance(@PathVariable Long id) {
        log.debug("REST request to get CazeInstance : {}", id);
        Optional<CazeInstanceDTO> cazeInstanceDTO = cazeInstanceService.findOne(id);
        return ResponseUtil.wrapOrNotFound(cazeInstanceDTO);
    }

    /**
     * DELETE  /caze-instances/:id : delete the "id" cazeInstance.
     *
     * @param id the id of the cazeInstanceDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/caze-instances/{id}")
    public ResponseEntity<Void> deleteCazeInstance(@PathVariable Long id) {
        log.debug("REST request to delete CazeInstance : {}", id);
        cazeInstanceService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
