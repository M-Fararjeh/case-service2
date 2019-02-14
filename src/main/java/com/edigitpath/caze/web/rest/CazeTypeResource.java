package com.edigitpath.caze.web.rest;
import com.edigitpath.caze.service.CazeTypeService;
import com.edigitpath.caze.web.rest.errors.BadRequestAlertException;
import com.edigitpath.caze.web.rest.util.HeaderUtil;
import com.edigitpath.caze.web.rest.util.PaginationUtil;
import com.edigitpath.caze.service.dto.CazeTypeDTO;
import com.edigitpath.caze.service.dto.CazeTypeCriteria;
import com.edigitpath.caze.service.CazeTypeQueryService;
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
 * REST controller for managing CazeType.
 */
@RestController
@RequestMapping("/api")
public class CazeTypeResource {

    private final Logger log = LoggerFactory.getLogger(CazeTypeResource.class);

    private static final String ENTITY_NAME = "cazeType";

    private final CazeTypeService cazeTypeService;

    private final CazeTypeQueryService cazeTypeQueryService;

    public CazeTypeResource(CazeTypeService cazeTypeService, CazeTypeQueryService cazeTypeQueryService) {
        this.cazeTypeService = cazeTypeService;
        this.cazeTypeQueryService = cazeTypeQueryService;
    }

    /**
     * POST  /caze-types : Create a new cazeType.
     *
     * @param cazeTypeDTO the cazeTypeDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new cazeTypeDTO, or with status 400 (Bad Request) if the cazeType has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/caze-types")
    public ResponseEntity<CazeTypeDTO> createCazeType(@RequestBody CazeTypeDTO cazeTypeDTO) throws URISyntaxException {
        log.debug("REST request to save CazeType : {}", cazeTypeDTO);
        if (cazeTypeDTO.getId() != null) {
            throw new BadRequestAlertException("A new cazeType cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CazeTypeDTO result = cazeTypeService.save(cazeTypeDTO);
        return ResponseEntity.created(new URI("/api/caze-types/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /caze-types : Updates an existing cazeType.
     *
     * @param cazeTypeDTO the cazeTypeDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated cazeTypeDTO,
     * or with status 400 (Bad Request) if the cazeTypeDTO is not valid,
     * or with status 500 (Internal Server Error) if the cazeTypeDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/caze-types")
    public ResponseEntity<CazeTypeDTO> updateCazeType(@RequestBody CazeTypeDTO cazeTypeDTO) throws URISyntaxException {
        log.debug("REST request to update CazeType : {}", cazeTypeDTO);
        if (cazeTypeDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        CazeTypeDTO result = cazeTypeService.save(cazeTypeDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, cazeTypeDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /caze-types : get all the cazeTypes.
     *
     * @param pageable the pagination information
     * @param criteria the criterias which the requested entities should match
     * @return the ResponseEntity with status 200 (OK) and the list of cazeTypes in body
     */
    @GetMapping("/caze-types")
    public ResponseEntity<List<CazeTypeDTO>> getAllCazeTypes(CazeTypeCriteria criteria, Pageable pageable) {
        log.debug("REST request to get CazeTypes by criteria: {}", criteria);
        Page<CazeTypeDTO> page = cazeTypeQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/caze-types");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * GET  /caze-types/count : count all the cazeTypes.
    *
    * @param criteria the criterias which the requested entities should match
    * @return the ResponseEntity with status 200 (OK) and the count in body
    */
    @GetMapping("/caze-types/count")
    public ResponseEntity<Long> countCazeTypes(CazeTypeCriteria criteria) {
        log.debug("REST request to count CazeTypes by criteria: {}", criteria);
        return ResponseEntity.ok().body(cazeTypeQueryService.countByCriteria(criteria));
    }

    /**
     * GET  /caze-types/:id : get the "id" cazeType.
     *
     * @param id the id of the cazeTypeDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the cazeTypeDTO, or with status 404 (Not Found)
     */
    @GetMapping("/caze-types/{id}")
    public ResponseEntity<CazeTypeDTO> getCazeType(@PathVariable Long id) {
        log.debug("REST request to get CazeType : {}", id);
        Optional<CazeTypeDTO> cazeTypeDTO = cazeTypeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(cazeTypeDTO);
    }

    /**
     * DELETE  /caze-types/:id : delete the "id" cazeType.
     *
     * @param id the id of the cazeTypeDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/caze-types/{id}")
    public ResponseEntity<Void> deleteCazeType(@PathVariable Long id) {
        log.debug("REST request to delete CazeType : {}", id);
        cazeTypeService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
