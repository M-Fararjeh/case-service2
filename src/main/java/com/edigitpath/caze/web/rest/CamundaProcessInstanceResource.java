package com.edigitpath.caze.web.rest;
import com.edigitpath.caze.service.CamundaProcessInstanceService;
import com.edigitpath.caze.web.rest.errors.BadRequestAlertException;
import com.edigitpath.caze.web.rest.util.HeaderUtil;
import com.edigitpath.caze.web.rest.util.PaginationUtil;
import com.edigitpath.caze.service.dto.CamundaProcessInstanceDTO;
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
 * REST controller for managing CamundaProcessInstance.
 */
@RestController
@RequestMapping("/api")
public class CamundaProcessInstanceResource {

    private final Logger log = LoggerFactory.getLogger(CamundaProcessInstanceResource.class);

    private static final String ENTITY_NAME = "camundaProcessInstance";

    private final CamundaProcessInstanceService camundaProcessInstanceService;

    public CamundaProcessInstanceResource(CamundaProcessInstanceService camundaProcessInstanceService) {
        this.camundaProcessInstanceService = camundaProcessInstanceService;
    }

    /**
     * POST  /camunda-process-instances : Create a new camundaProcessInstance.
     *
     * @param camundaProcessInstanceDTO the camundaProcessInstanceDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new camundaProcessInstanceDTO, or with status 400 (Bad Request) if the camundaProcessInstance has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/camunda-process-instances")
    public ResponseEntity<CamundaProcessInstanceDTO> createCamundaProcessInstance(@RequestBody CamundaProcessInstanceDTO camundaProcessInstanceDTO) throws URISyntaxException {
        log.debug("REST request to save CamundaProcessInstance : {}", camundaProcessInstanceDTO);
        if (camundaProcessInstanceDTO.getId() != null) {
            throw new BadRequestAlertException("A new camundaProcessInstance cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CamundaProcessInstanceDTO result = camundaProcessInstanceService.save(camundaProcessInstanceDTO);
        return ResponseEntity.created(new URI("/api/camunda-process-instances/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /camunda-process-instances : Updates an existing camundaProcessInstance.
     *
     * @param camundaProcessInstanceDTO the camundaProcessInstanceDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated camundaProcessInstanceDTO,
     * or with status 400 (Bad Request) if the camundaProcessInstanceDTO is not valid,
     * or with status 500 (Internal Server Error) if the camundaProcessInstanceDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/camunda-process-instances")
    public ResponseEntity<CamundaProcessInstanceDTO> updateCamundaProcessInstance(@RequestBody CamundaProcessInstanceDTO camundaProcessInstanceDTO) throws URISyntaxException {
        log.debug("REST request to update CamundaProcessInstance : {}", camundaProcessInstanceDTO);
        if (camundaProcessInstanceDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        CamundaProcessInstanceDTO result = camundaProcessInstanceService.save(camundaProcessInstanceDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, camundaProcessInstanceDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /camunda-process-instances : get all the camundaProcessInstances.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of camundaProcessInstances in body
     */
    @GetMapping("/camunda-process-instances")
    public ResponseEntity<List<CamundaProcessInstanceDTO>> getAllCamundaProcessInstances(Pageable pageable) {
        log.debug("REST request to get a page of CamundaProcessInstances");
        Page<CamundaProcessInstanceDTO> page = camundaProcessInstanceService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/camunda-process-instances");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /camunda-process-instances/:id : get the "id" camundaProcessInstance.
     *
     * @param id the id of the camundaProcessInstanceDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the camundaProcessInstanceDTO, or with status 404 (Not Found)
     */
    @GetMapping("/camunda-process-instances/{id}")
    public ResponseEntity<CamundaProcessInstanceDTO> getCamundaProcessInstance(@PathVariable Long id) {
        log.debug("REST request to get CamundaProcessInstance : {}", id);
        Optional<CamundaProcessInstanceDTO> camundaProcessInstanceDTO = camundaProcessInstanceService.findOne(id);
        return ResponseUtil.wrapOrNotFound(camundaProcessInstanceDTO);
    }

    /**
     * DELETE  /camunda-process-instances/:id : delete the "id" camundaProcessInstance.
     *
     * @param id the id of the camundaProcessInstanceDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/camunda-process-instances/{id}")
    public ResponseEntity<Void> deleteCamundaProcessInstance(@PathVariable Long id) {
        log.debug("REST request to delete CamundaProcessInstance : {}", id);
        camundaProcessInstanceService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
