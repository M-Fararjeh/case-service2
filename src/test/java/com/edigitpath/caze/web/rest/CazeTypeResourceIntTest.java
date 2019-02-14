package com.edigitpath.caze.web.rest;

import com.edigitpath.caze.CaseServiceApp;

import com.edigitpath.caze.domain.CazeType;
import com.edigitpath.caze.repository.CazeTypeRepository;
import com.edigitpath.caze.service.CazeTypeService;
import com.edigitpath.caze.service.dto.CazeTypeDTO;
import com.edigitpath.caze.service.mapper.CazeTypeMapper;
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

import com.edigitpath.caze.domain.enumeration.CasePriority;
/**
 * Test class for the CazeTypeResource REST controller.
 *
 * @see CazeTypeResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = CaseServiceApp.class)
public class CazeTypeResourceIntTest {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final CasePriority DEFAULT_PRIORITY = CasePriority.HIGH;
    private static final CasePriority UPDATED_PRIORITY = CasePriority.NORMAL;

    private static final Integer DEFAULT_REQUIRED_TIME = 1;
    private static final Integer UPDATED_REQUIRED_TIME = 2;

    private static final Boolean DEFAULT_SECURED = false;
    private static final Boolean UPDATED_SECURED = true;

    @Autowired
    private CazeTypeRepository cazeTypeRepository;

    @Autowired
    private CazeTypeMapper cazeTypeMapper;

    @Autowired
    private CazeTypeService cazeTypeService;

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

    private MockMvc restCazeTypeMockMvc;

    private CazeType cazeType;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final CazeTypeResource cazeTypeResource = new CazeTypeResource(cazeTypeService);
        this.restCazeTypeMockMvc = MockMvcBuilders.standaloneSetup(cazeTypeResource)
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
    public static CazeType createEntity(EntityManager em) {
        CazeType cazeType = new CazeType();
        cazeType.setName(DEFAULT_NAME);
        cazeType.setPriority(DEFAULT_PRIORITY);
        cazeType.setRequiredTime(DEFAULT_REQUIRED_TIME);
        cazeType.setSecured(DEFAULT_SECURED);
        return cazeType;
    }

    @Before
    public void initTest() {
        cazeType = createEntity(em);
    }

    @Test
    @Transactional
    public void createCazeType() throws Exception {
        int databaseSizeBeforeCreate = cazeTypeRepository.findAll().size();

        // Create the CazeType
        CazeTypeDTO cazeTypeDTO = cazeTypeMapper.toDto(cazeType);
        restCazeTypeMockMvc.perform(post("/api/caze-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(cazeTypeDTO)))
            .andExpect(status().isCreated());

        // Validate the CazeType in the database
        List<CazeType> cazeTypeList = cazeTypeRepository.findAll();
        assertThat(cazeTypeList).hasSize(databaseSizeBeforeCreate + 1);
        CazeType testCazeType = cazeTypeList.get(cazeTypeList.size() - 1);
        assertThat(testCazeType.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testCazeType.getPriority()).isEqualTo(DEFAULT_PRIORITY);
        assertThat(testCazeType.getRequiredTime()).isEqualTo(DEFAULT_REQUIRED_TIME);
        assertThat(testCazeType.isSecured()).isEqualTo(DEFAULT_SECURED);
    }

    @Test
    @Transactional
    public void createCazeTypeWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = cazeTypeRepository.findAll().size();

        // Create the CazeType with an existing ID
        cazeType.setId(1L);
        CazeTypeDTO cazeTypeDTO = cazeTypeMapper.toDto(cazeType);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCazeTypeMockMvc.perform(post("/api/caze-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(cazeTypeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the CazeType in the database
        List<CazeType> cazeTypeList = cazeTypeRepository.findAll();
        assertThat(cazeTypeList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllCazeTypes() throws Exception {
        // Initialize the database
        cazeTypeRepository.saveAndFlush(cazeType);

        // Get all the cazeTypeList
        restCazeTypeMockMvc.perform(get("/api/caze-types?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(cazeType.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].priority").value(hasItem(DEFAULT_PRIORITY.toString())))
            .andExpect(jsonPath("$.[*].requiredTime").value(hasItem(DEFAULT_REQUIRED_TIME)))
            .andExpect(jsonPath("$.[*].secured").value(hasItem(DEFAULT_SECURED.booleanValue())));
    }
    
    @Test
    @Transactional
    public void getCazeType() throws Exception {
        // Initialize the database
        cazeTypeRepository.saveAndFlush(cazeType);

        // Get the cazeType
        restCazeTypeMockMvc.perform(get("/api/caze-types/{id}", cazeType.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(cazeType.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.priority").value(DEFAULT_PRIORITY.toString()))
            .andExpect(jsonPath("$.requiredTime").value(DEFAULT_REQUIRED_TIME))
            .andExpect(jsonPath("$.secured").value(DEFAULT_SECURED.booleanValue()));
    }

    @Test
    @Transactional
    public void getNonExistingCazeType() throws Exception {
        // Get the cazeType
        restCazeTypeMockMvc.perform(get("/api/caze-types/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCazeType() throws Exception {
        // Initialize the database
        cazeTypeRepository.saveAndFlush(cazeType);

        int databaseSizeBeforeUpdate = cazeTypeRepository.findAll().size();

        // Update the cazeType
        CazeType updatedCazeType = cazeTypeRepository.findById(cazeType.getId()).get();
        // Disconnect from session so that the updates on updatedCazeType are not directly saved in db
        em.detach(updatedCazeType);
        updatedCazeType.setName(UPDATED_NAME);
        updatedCazeType.setPriority(UPDATED_PRIORITY);
        updatedCazeType.setRequiredTime(UPDATED_REQUIRED_TIME);
        updatedCazeType.setSecured(UPDATED_SECURED);
        CazeTypeDTO cazeTypeDTO = cazeTypeMapper.toDto(updatedCazeType);

        restCazeTypeMockMvc.perform(put("/api/caze-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(cazeTypeDTO)))
            .andExpect(status().isOk());

        // Validate the CazeType in the database
        List<CazeType> cazeTypeList = cazeTypeRepository.findAll();
        assertThat(cazeTypeList).hasSize(databaseSizeBeforeUpdate);
        CazeType testCazeType = cazeTypeList.get(cazeTypeList.size() - 1);
        assertThat(testCazeType.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testCazeType.getPriority()).isEqualTo(UPDATED_PRIORITY);
        assertThat(testCazeType.getRequiredTime()).isEqualTo(UPDATED_REQUIRED_TIME);
        assertThat(testCazeType.isSecured()).isEqualTo(UPDATED_SECURED);
    }

    @Test
    @Transactional
    public void updateNonExistingCazeType() throws Exception {
        int databaseSizeBeforeUpdate = cazeTypeRepository.findAll().size();

        // Create the CazeType
        CazeTypeDTO cazeTypeDTO = cazeTypeMapper.toDto(cazeType);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCazeTypeMockMvc.perform(put("/api/caze-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(cazeTypeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the CazeType in the database
        List<CazeType> cazeTypeList = cazeTypeRepository.findAll();
        assertThat(cazeTypeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteCazeType() throws Exception {
        // Initialize the database
        cazeTypeRepository.saveAndFlush(cazeType);

        int databaseSizeBeforeDelete = cazeTypeRepository.findAll().size();

        // Delete the cazeType
        restCazeTypeMockMvc.perform(delete("/api/caze-types/{id}", cazeType.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<CazeType> cazeTypeList = cazeTypeRepository.findAll();
        assertThat(cazeTypeList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(CazeType.class);
        CazeType cazeType1 = new CazeType();
        cazeType1.setId(1L);
        CazeType cazeType2 = new CazeType();
        cazeType2.setId(cazeType1.getId());
        assertThat(cazeType1).isEqualTo(cazeType2);
        cazeType2.setId(2L);
        assertThat(cazeType1).isNotEqualTo(cazeType2);
        cazeType1.setId(null);
        assertThat(cazeType1).isNotEqualTo(cazeType2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(CazeTypeDTO.class);
        CazeTypeDTO cazeTypeDTO1 = new CazeTypeDTO();
        cazeTypeDTO1.setId(1L);
        CazeTypeDTO cazeTypeDTO2 = new CazeTypeDTO();
        assertThat(cazeTypeDTO1).isNotEqualTo(cazeTypeDTO2);
        cazeTypeDTO2.setId(cazeTypeDTO1.getId());
        assertThat(cazeTypeDTO1).isEqualTo(cazeTypeDTO2);
        cazeTypeDTO2.setId(2L);
        assertThat(cazeTypeDTO1).isNotEqualTo(cazeTypeDTO2);
        cazeTypeDTO1.setId(null);
        assertThat(cazeTypeDTO1).isNotEqualTo(cazeTypeDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(cazeTypeMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(cazeTypeMapper.fromId(null)).isNull();
    }
}
