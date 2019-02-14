package com.edigitpath.caze.web.rest;

import com.edigitpath.caze.CaseServiceApp;

import com.edigitpath.caze.domain.CaseDataObject;
import com.edigitpath.caze.repository.CaseDataObjectRepository;
import com.edigitpath.caze.service.CaseDataObjectService;
import com.edigitpath.caze.service.dto.CaseDataObjectDTO;
import com.edigitpath.caze.service.mapper.CaseDataObjectMapper;
import com.edigitpath.caze.web.rest.errors.ExceptionTranslator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Validator;

import javax.persistence.EntityManager;
import java.util.List;


import static com.edigitpath.caze.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.edigitpath.caze.domain.enumeration.DataObjectType;
/**
 * Test class for the CaseDataObjectResource REST controller.
 *
 * @see CaseDataObjectResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = CaseServiceApp.class)
public class CaseDataObjectResourceIntTest {

    private static final DataObjectType DEFAULT_TYPE = DataObjectType.API;
    private static final DataObjectType UPDATED_TYPE = DataObjectType.DB;

    private static final String DEFAULT_KEY = "AAAAAAAAAA";
    private static final String UPDATED_KEY = "BBBBBBBBBB";

    @Autowired
    private CaseDataObjectRepository caseDataObjectRepository;

    @Autowired
    private CaseDataObjectMapper caseDataObjectMapper;

    @Autowired
    private CaseDataObjectService caseDataObjectService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    @Autowired
    private Validator validator;

    private MockMvc restCaseDataObjectMockMvc;

    private CaseDataObject caseDataObject;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final CaseDataObjectResource caseDataObjectResource = new CaseDataObjectResource(caseDataObjectService);
        this.restCaseDataObjectMockMvc = MockMvcBuilders.standaloneSetup(caseDataObjectResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter)
            .setValidator(validator).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CaseDataObject createEntity(EntityManager em) {
        CaseDataObject caseDataObject = new CaseDataObject();
        caseDataObject.setType(DEFAULT_TYPE);
        caseDataObject.setKey(DEFAULT_KEY);
        return caseDataObject;
    }

    @Before
    public void initTest() {
        caseDataObject = createEntity(em);
    }

    @Test
    @Transactional
    public void createCaseDataObject() throws Exception {
        int databaseSizeBeforeCreate = caseDataObjectRepository.findAll().size();

        // Create the CaseDataObject
        CaseDataObjectDTO caseDataObjectDTO = caseDataObjectMapper.toDto(caseDataObject);
        restCaseDataObjectMockMvc.perform(post("/api/case-data-objects")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(caseDataObjectDTO)))
            .andExpect(status().isCreated());

        // Validate the CaseDataObject in the database
        List<CaseDataObject> caseDataObjectList = caseDataObjectRepository.findAll();
        assertThat(caseDataObjectList).hasSize(databaseSizeBeforeCreate + 1);
        CaseDataObject testCaseDataObject = caseDataObjectList.get(caseDataObjectList.size() - 1);
        assertThat(testCaseDataObject.getType()).isEqualTo(DEFAULT_TYPE);
        assertThat(testCaseDataObject.getKey()).isEqualTo(DEFAULT_KEY);
    }

    @Test
    @Transactional
    public void createCaseDataObjectWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = caseDataObjectRepository.findAll().size();

        // Create the CaseDataObject with an existing ID
        caseDataObject.setId(1L);
        CaseDataObjectDTO caseDataObjectDTO = caseDataObjectMapper.toDto(caseDataObject);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCaseDataObjectMockMvc.perform(post("/api/case-data-objects")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(caseDataObjectDTO)))
            .andExpect(status().isBadRequest());

        // Validate the CaseDataObject in the database
        List<CaseDataObject> caseDataObjectList = caseDataObjectRepository.findAll();
        assertThat(caseDataObjectList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllCaseDataObjects() throws Exception {
        // Initialize the database
        caseDataObjectRepository.saveAndFlush(caseDataObject);

        // Get all the caseDataObjectList
        restCaseDataObjectMockMvc.perform(get("/api/case-data-objects?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(caseDataObject.getId().intValue())))
            .andExpect(jsonPath("$.[*].type").value(hasItem(DEFAULT_TYPE.toString())))
            .andExpect(jsonPath("$.[*].key").value(hasItem(DEFAULT_KEY.toString())));
    }
    
    @Test
    @Transactional
    public void getCaseDataObject() throws Exception {
        // Initialize the database
        caseDataObjectRepository.saveAndFlush(caseDataObject);

        // Get the caseDataObject
        restCaseDataObjectMockMvc.perform(get("/api/case-data-objects/{id}", caseDataObject.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(caseDataObject.getId().intValue()))
            .andExpect(jsonPath("$.type").value(DEFAULT_TYPE.toString()))
            .andExpect(jsonPath("$.key").value(DEFAULT_KEY.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingCaseDataObject() throws Exception {
        // Get the caseDataObject
        restCaseDataObjectMockMvc.perform(get("/api/case-data-objects/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCaseDataObject() throws Exception {
        // Initialize the database
        caseDataObjectRepository.saveAndFlush(caseDataObject);

        int databaseSizeBeforeUpdate = caseDataObjectRepository.findAll().size();

        // Update the caseDataObject
        CaseDataObject updatedCaseDataObject = caseDataObjectRepository.findById(caseDataObject.getId()).get();
        // Disconnect from session so that the updates on updatedCaseDataObject are not directly saved in db
        em.detach(updatedCaseDataObject);
        updatedCaseDataObject.setType(UPDATED_TYPE);
        updatedCaseDataObject.setKey(UPDATED_KEY);
        CaseDataObjectDTO caseDataObjectDTO = caseDataObjectMapper.toDto(updatedCaseDataObject);

        restCaseDataObjectMockMvc.perform(put("/api/case-data-objects")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(caseDataObjectDTO)))
            .andExpect(status().isOk());

        // Validate the CaseDataObject in the database
        List<CaseDataObject> caseDataObjectList = caseDataObjectRepository.findAll();
        assertThat(caseDataObjectList).hasSize(databaseSizeBeforeUpdate);
        CaseDataObject testCaseDataObject = caseDataObjectList.get(caseDataObjectList.size() - 1);
        assertThat(testCaseDataObject.getType()).isEqualTo(UPDATED_TYPE);
        assertThat(testCaseDataObject.getKey()).isEqualTo(UPDATED_KEY);
    }

    @Test
    @Transactional
    public void updateNonExistingCaseDataObject() throws Exception {
        int databaseSizeBeforeUpdate = caseDataObjectRepository.findAll().size();

        // Create the CaseDataObject
        CaseDataObjectDTO caseDataObjectDTO = caseDataObjectMapper.toDto(caseDataObject);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCaseDataObjectMockMvc.perform(put("/api/case-data-objects")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(caseDataObjectDTO)))
            .andExpect(status().isBadRequest());

        // Validate the CaseDataObject in the database
        List<CaseDataObject> caseDataObjectList = caseDataObjectRepository.findAll();
        assertThat(caseDataObjectList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteCaseDataObject() throws Exception {
        // Initialize the database
        caseDataObjectRepository.saveAndFlush(caseDataObject);

        int databaseSizeBeforeDelete = caseDataObjectRepository.findAll().size();

        // Delete the caseDataObject
        restCaseDataObjectMockMvc.perform(delete("/api/case-data-objects/{id}", caseDataObject.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<CaseDataObject> caseDataObjectList = caseDataObjectRepository.findAll();
        assertThat(caseDataObjectList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(CaseDataObject.class);
        CaseDataObject caseDataObject1 = new CaseDataObject();
        caseDataObject1.setId(1L);
        CaseDataObject caseDataObject2 = new CaseDataObject();
        caseDataObject2.setId(caseDataObject1.getId());
        assertThat(caseDataObject1).isEqualTo(caseDataObject2);
        caseDataObject2.setId(2L);
        assertThat(caseDataObject1).isNotEqualTo(caseDataObject2);
        caseDataObject1.setId(null);
        assertThat(caseDataObject1).isNotEqualTo(caseDataObject2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(CaseDataObjectDTO.class);
        CaseDataObjectDTO caseDataObjectDTO1 = new CaseDataObjectDTO();
        caseDataObjectDTO1.setId(1L);
        CaseDataObjectDTO caseDataObjectDTO2 = new CaseDataObjectDTO();
        assertThat(caseDataObjectDTO1).isNotEqualTo(caseDataObjectDTO2);
        caseDataObjectDTO2.setId(caseDataObjectDTO1.getId());
        assertThat(caseDataObjectDTO1).isEqualTo(caseDataObjectDTO2);
        caseDataObjectDTO2.setId(2L);
        assertThat(caseDataObjectDTO1).isNotEqualTo(caseDataObjectDTO2);
        caseDataObjectDTO1.setId(null);
        assertThat(caseDataObjectDTO1).isNotEqualTo(caseDataObjectDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(caseDataObjectMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(caseDataObjectMapper.fromId(null)).isNull();
    }
}
