package com.edigitpath.caze.web.rest;
import com.edigitpath.caze.service.CamundaCaseInstanceService;
import com.edigitpath.caze.web.rest.errors.BadRequestAlertException;
import com.edigitpath.caze.web.rest.util.HeaderUtil;
import com.edigitpath.caze.web.rest.util.PaginationUtil;
import com.edigitpath.caze.service.dto.CamundaCaseInstanceDTO;
import com.edigitpath.caze.service.dto.CamundaCaseInstanceCriteria;
import com.edigitpath.caze.service.CamundaCaseInstanceQueryService;
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
 * REST controller for managing CamundaCaseInstance.
 */
@RestController
@RequestMapping("/api")
public class CamundaCaseInstanceResource {

    private final Logger log = LoggerFactory.getLogger(CamundaCaseInstanceResource.class);

    private static final String ENTITY_NAME = "camundaCaseInstance";

    private final CamundaCaseInstanceService camundaCaseInstanceService;

    private final CamundaCaseInstanceQueryService camundaCaseInstanceQueryService;

    public CamundaCaseInstanceResource(CamundaCaseInstanceService camundaCaseInstanceService, CamundaCaseInstanceQueryService camundaCaseInstanceQueryService) {
        this.camundaCaseInstanceService = camundaCaseInstanceService;
        this.camundaCaseInstanceQueryService = camundaCaseInstanceQueryService;
    }

    /**
     * POST  /camunda-case-instances : Create a new camundaCaseInstance.
     *
     * @param camundaCaseInstanceDTO the camundaCaseInstanceDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new camundaCaseInstanceDTO, or with status 400 (Bad Request) if the camundaCaseInstance has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/camunda-case-instances")
    public ResponseEntity<CamundaCaseInstanceDTO> createCamundaCaseInstance(@RequestBody CamundaCaseInstanceDTO camundaCaseInstanceDTO) throws URISyntaxException {
        log.debug("REST request to save CamundaCaseInstance : {}", camundaCaseInstanceDTO);
        if (camundaCaseInstanceDTO.getId() != null) {
            throw new BadRequestAlertException("A new camundaCaseInstance cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CamundaCaseInstanceDTO result = camundaCaseInstanceService.save(camundaCaseInstanceDTO);
        return ResponseEntity.created(new URI("/api/camunda-case-instances/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /camunda-case-instances : Updates an existing camundaCaseInstance.
     *
     * @param camundaCaseInstanceDTO the camundaCaseInstanceDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated camundaCaseInstanceDTO,
     * or with status 400 (Bad Request) if the camundaCaseInstanceDTO is not valid,
     * or with status 500 (Internal Server Error) if the camundaCaseInstanceDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/camunda-case-instances")
    public ResponseEntity<CamundaCaseInstanceDTO> updateCamundaCaseInstance(@RequestBody CamundaCaseInstanceDTO camundaCaseInstanceDTO) throws URISyntaxException {
        log.debug("REST request to update CamundaCaseInstance : {}", camundaCaseInstanceDTO);
        if (camundaCaseInstanceDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        CamundaCaseInstanceDTO result = camundaCaseInstanceService.save(camundaCaseInstanceDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, camundaCaseInstanceDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /camunda-case-instances : get all the camundaCaseInstances.
     *
     * @param pageable the pagination information
     * @param criteria the criterias which the requested entities should match
     * @return the ResponseEntity with status 200 (OK) and the list of camundaCaseInstances in body
     */
    @GetMapping("/camunda-case-instances")
    public ResponseEntity<List<CamundaCaseInstanceDTO>> getAllCamundaCaseInstances(CamundaCaseInstanceCriteria criteria, Pageable pageable) {
        log.debug("REST request to get CamundaCaseInstances by criteria: {}", criteria);
        Page<CamundaCaseInstanceDTO> page = camundaCaseInstanceQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/camunda-case-instances");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * GET  /camunda-case-instances/count : count all the camundaCaseInstances.
    *
    * @param criteria the criterias which the requested entities should match
    * @return the ResponseEntity with status 200 (OK) and the count in body
    */
    @GetMapping("/camunda-case-instances/count")
    public ResponseEntity<Long> countCamundaCaseInstances(CamundaCaseInstanceCriteria criteria) {
        log.debug("REST request to count CamundaCaseInstances by criteria: {}", criteria);
        return ResponseEntity.ok().body(camundaCaseInstanceQueryService.countByCriteria(criteria));
    }

    /**
     * GET  /camunda-case-instances/:id : get the "id" camundaCaseInstance.
     *
     * @param id the id of the camundaCaseInstanceDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the camundaCaseInstanceDTO, or with status 404 (Not Found)
     */
    @GetMapping("/camunda-case-instances/{id}")
    public ResponseEntity<CamundaCaseInstanceDTO> getCamundaCaseInstance(@PathVariable Long id) {
        log.debug("REST request to get CamundaCaseInstance : {}", id);
        Optional<CamundaCaseInstanceDTO> camundaCaseInstanceDTO = camundaCaseInstanceService.findOne(id);
        return ResponseUtil.wrapOrNotFound(camundaCaseInstanceDTO);
    }

    /**
     * DELETE  /camunda-case-instances/:id : delete the "id" camundaCaseInstance.
     *
     * @param id the id of the camundaCaseInstanceDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/camunda-case-instances/{id}")
    public ResponseEntity<Void> deleteCamundaCaseInstance(@PathVariable Long id) {
        log.debug("REST request to delete CamundaCaseInstance : {}", id);
        camundaCaseInstanceService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
