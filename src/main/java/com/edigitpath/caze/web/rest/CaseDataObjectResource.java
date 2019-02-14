package com.edigitpath.caze.web.rest;
import com.edigitpath.caze.service.CaseDataObjectService;
import com.edigitpath.caze.web.rest.errors.BadRequestAlertException;
import com.edigitpath.caze.web.rest.util.HeaderUtil;
import com.edigitpath.caze.web.rest.util.PaginationUtil;
import com.edigitpath.caze.service.dto.CaseDataObjectDTO;
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
 * REST controller for managing CaseDataObject.
 */
@RestController
@RequestMapping("/api")
public class CaseDataObjectResource {

    private final Logger log = LoggerFactory.getLogger(CaseDataObjectResource.class);

    private static final String ENTITY_NAME = "caseDataObject";

    private final CaseDataObjectService caseDataObjectService;

    public CaseDataObjectResource(CaseDataObjectService caseDataObjectService) {
        this.caseDataObjectService = caseDataObjectService;
    }

    /**
     * POST  /case-data-objects : Create a new caseDataObject.
     *
     * @param caseDataObjectDTO the caseDataObjectDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new caseDataObjectDTO, or with status 400 (Bad Request) if the caseDataObject has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/case-data-objects")
    public ResponseEntity<CaseDataObjectDTO> createCaseDataObject(@RequestBody CaseDataObjectDTO caseDataObjectDTO) throws URISyntaxException {
        log.debug("REST request to save CaseDataObject : {}", caseDataObjectDTO);
        if (caseDataObjectDTO.getId() != null) {
            throw new BadRequestAlertException("A new caseDataObject cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CaseDataObjectDTO result = caseDataObjectService.save(caseDataObjectDTO);
        return ResponseEntity.created(new URI("/api/case-data-objects/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /case-data-objects : Updates an existing caseDataObject.
     *
     * @param caseDataObjectDTO the caseDataObjectDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated caseDataObjectDTO,
     * or with status 400 (Bad Request) if the caseDataObjectDTO is not valid,
     * or with status 500 (Internal Server Error) if the caseDataObjectDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/case-data-objects")
    public ResponseEntity<CaseDataObjectDTO> updateCaseDataObject(@RequestBody CaseDataObjectDTO caseDataObjectDTO) throws URISyntaxException {
        log.debug("REST request to update CaseDataObject : {}", caseDataObjectDTO);
        if (caseDataObjectDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        CaseDataObjectDTO result = caseDataObjectService.save(caseDataObjectDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, caseDataObjectDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /case-data-objects : get all the caseDataObjects.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of caseDataObjects in body
     */
    @GetMapping("/case-data-objects")
    public ResponseEntity<List<CaseDataObjectDTO>> getAllCaseDataObjects(Pageable pageable) {
        log.debug("REST request to get a page of CaseDataObjects");
        Page<CaseDataObjectDTO> page = caseDataObjectService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/case-data-objects");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /case-data-objects/:id : get the "id" caseDataObject.
     *
     * @param id the id of the caseDataObjectDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the caseDataObjectDTO, or with status 404 (Not Found)
     */
    @GetMapping("/case-data-objects/{id}")
    public ResponseEntity<CaseDataObjectDTO> getCaseDataObject(@PathVariable Long id) {
        log.debug("REST request to get CaseDataObject : {}", id);
        Optional<CaseDataObjectDTO> caseDataObjectDTO = caseDataObjectService.findOne(id);
        return ResponseUtil.wrapOrNotFound(caseDataObjectDTO);
    }

    /**
     * DELETE  /case-data-objects/:id : delete the "id" caseDataObject.
     *
     * @param id the id of the caseDataObjectDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/case-data-objects/{id}")
    public ResponseEntity<Void> deleteCaseDataObject(@PathVariable Long id) {
        log.debug("REST request to delete CaseDataObject : {}", id);
        caseDataObjectService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
