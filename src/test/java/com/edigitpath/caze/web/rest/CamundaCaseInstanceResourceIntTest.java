package com.edigitpath.caze.web.rest;

import com.edigitpath.caze.CaseServiceApp;

import com.edigitpath.caze.domain.CamundaCaseInstance;
import com.edigitpath.caze.repository.CamundaCaseInstanceRepository;
import com.edigitpath.caze.service.CamundaCaseInstanceService;
import com.edigitpath.caze.service.dto.CamundaCaseInstanceDTO;
import com.edigitpath.caze.service.mapper.CamundaCaseInstanceMapper;
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

/**
 * Test class for the CamundaCaseInstanceResource REST controller.
 *
 * @see CamundaCaseInstanceResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = CaseServiceApp.class)
public class CamundaCaseInstanceResourceIntTest {

    private static final String DEFAULT_CASE_INSTANCE_ID = "AAAAAAAAAA";
    private static final String UPDATED_CASE_INSTANCE_ID = "BBBBBBBBBB";

    private static final String DEFAULT_CASE_INSTANCE_NAME = "AAAAAAAAAA";
    private static final String UPDATED_CASE_INSTANCE_NAME = "BBBBBBBBBB";

    @Autowired
    private CamundaCaseInstanceRepository camundaCaseInstanceRepository;

    @Autowired
    private CamundaCaseInstanceMapper camundaCaseInstanceMapper;

    @Autowired
    private CamundaCaseInstanceService camundaCaseInstanceService;

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

    private MockMvc restCamundaCaseInstanceMockMvc;

    private CamundaCaseInstance camundaCaseInstance;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final CamundaCaseInstanceResource camundaCaseInstanceResource = new CamundaCaseInstanceResource(camundaCaseInstanceService);
        this.restCamundaCaseInstanceMockMvc = MockMvcBuilders.standaloneSetup(camundaCaseInstanceResource)
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
    public static CamundaCaseInstance createEntity(EntityManager em) {
        CamundaCaseInstance camundaCaseInstance = new CamundaCaseInstance();
        camundaCaseInstance.setCaseInstanceId(DEFAULT_CASE_INSTANCE_ID);
        camundaCaseInstance.setCaseInstanceName(DEFAULT_CASE_INSTANCE_NAME);
        return camundaCaseInstance;
    }

    @Before
    public void initTest() {
        camundaCaseInstance = createEntity(em);
    }

    @Test
    @Transactional
    public void createCamundaCaseInstance() throws Exception {
        int databaseSizeBeforeCreate = camundaCaseInstanceRepository.findAll().size();

        // Create the CamundaCaseInstance
        CamundaCaseInstanceDTO camundaCaseInstanceDTO = camundaCaseInstanceMapper.toDto(camundaCaseInstance);
        restCamundaCaseInstanceMockMvc.perform(post("/api/camunda-case-instances")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(camundaCaseInstanceDTO)))
            .andExpect(status().isCreated());

        // Validate the CamundaCaseInstance in the database
        List<CamundaCaseInstance> camundaCaseInstanceList = camundaCaseInstanceRepository.findAll();
        assertThat(camundaCaseInstanceList).hasSize(databaseSizeBeforeCreate + 1);
        CamundaCaseInstance testCamundaCaseInstance = camundaCaseInstanceList.get(camundaCaseInstanceList.size() - 1);
        assertThat(testCamundaCaseInstance.getCaseInstanceId()).isEqualTo(DEFAULT_CASE_INSTANCE_ID);
        assertThat(testCamundaCaseInstance.getCaseInstanceName()).isEqualTo(DEFAULT_CASE_INSTANCE_NAME);
    }

    @Test
    @Transactional
    public void createCamundaCaseInstanceWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = camundaCaseInstanceRepository.findAll().size();

        // Create the CamundaCaseInstance with an existing ID
        camundaCaseInstance.setId(1L);
        CamundaCaseInstanceDTO camundaCaseInstanceDTO = camundaCaseInstanceMapper.toDto(camundaCaseInstance);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCamundaCaseInstanceMockMvc.perform(post("/api/camunda-case-instances")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(camundaCaseInstanceDTO)))
            .andExpect(status().isBadRequest());

        // Validate the CamundaCaseInstance in the database
        List<CamundaCaseInstance> camundaCaseInstanceList = camundaCaseInstanceRepository.findAll();
        assertThat(camundaCaseInstanceList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllCamundaCaseInstances() throws Exception {
        // Initialize the database
        camundaCaseInstanceRepository.saveAndFlush(camundaCaseInstance);

        // Get all the camundaCaseInstanceList
        restCamundaCaseInstanceMockMvc.perform(get("/api/camunda-case-instances?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(camundaCaseInstance.getId().intValue())))
            .andExpect(jsonPath("$.[*].caseInstanceId").value(hasItem(DEFAULT_CASE_INSTANCE_ID.toString())))
            .andExpect(jsonPath("$.[*].caseInstanceName").value(hasItem(DEFAULT_CASE_INSTANCE_NAME.toString())));
    }
    
    @Test
    @Transactional
    public void getCamundaCaseInstance() throws Exception {
        // Initialize the database
        camundaCaseInstanceRepository.saveAndFlush(camundaCaseInstance);

        // Get the camundaCaseInstance
        restCamundaCaseInstanceMockMvc.perform(get("/api/camunda-case-instances/{id}", camundaCaseInstance.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(camundaCaseInstance.getId().intValue()))
            .andExpect(jsonPath("$.caseInstanceId").value(DEFAULT_CASE_INSTANCE_ID.toString()))
            .andExpect(jsonPath("$.caseInstanceName").value(DEFAULT_CASE_INSTANCE_NAME.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingCamundaCaseInstance() throws Exception {
        // Get the camundaCaseInstance
        restCamundaCaseInstanceMockMvc.perform(get("/api/camunda-case-instances/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCamundaCaseInstance() throws Exception {
        // Initialize the database
        camundaCaseInstanceRepository.saveAndFlush(camundaCaseInstance);

        int databaseSizeBeforeUpdate = camundaCaseInstanceRepository.findAll().size();

        // Update the camundaCaseInstance
        CamundaCaseInstance updatedCamundaCaseInstance = camundaCaseInstanceRepository.findById(camundaCaseInstance.getId()).get();
        // Disconnect from session so that the updates on updatedCamundaCaseInstance are not directly saved in db
        em.detach(updatedCamundaCaseInstance);
        updatedCamundaCaseInstance.setCaseInstanceId(UPDATED_CASE_INSTANCE_ID);
        updatedCamundaCaseInstance.setCaseInstanceName(UPDATED_CASE_INSTANCE_NAME);
        CamundaCaseInstanceDTO camundaCaseInstanceDTO = camundaCaseInstanceMapper.toDto(updatedCamundaCaseInstance);

        restCamundaCaseInstanceMockMvc.perform(put("/api/camunda-case-instances")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(camundaCaseInstanceDTO)))
            .andExpect(status().isOk());

        // Validate the CamundaCaseInstance in the database
        List<CamundaCaseInstance> camundaCaseInstanceList = camundaCaseInstanceRepository.findAll();
        assertThat(camundaCaseInstanceList).hasSize(databaseSizeBeforeUpdate);
        CamundaCaseInstance testCamundaCaseInstance = camundaCaseInstanceList.get(camundaCaseInstanceList.size() - 1);
        assertThat(testCamundaCaseInstance.getCaseInstanceId()).isEqualTo(UPDATED_CASE_INSTANCE_ID);
        assertThat(testCamundaCaseInstance.getCaseInstanceName()).isEqualTo(UPDATED_CASE_INSTANCE_NAME);
    }

    @Test
    @Transactional
    public void updateNonExistingCamundaCaseInstance() throws Exception {
        int databaseSizeBeforeUpdate = camundaCaseInstanceRepository.findAll().size();

        // Create the CamundaCaseInstance
        CamundaCaseInstanceDTO camundaCaseInstanceDTO = camundaCaseInstanceMapper.toDto(camundaCaseInstance);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCamundaCaseInstanceMockMvc.perform(put("/api/camunda-case-instances")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(camundaCaseInstanceDTO)))
            .andExpect(status().isBadRequest());

        // Validate the CamundaCaseInstance in the database
        List<CamundaCaseInstance> camundaCaseInstanceList = camundaCaseInstanceRepository.findAll();
        assertThat(camundaCaseInstanceList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteCamundaCaseInstance() throws Exception {
        // Initialize the database
        camundaCaseInstanceRepository.saveAndFlush(camundaCaseInstance);

        int databaseSizeBeforeDelete = camundaCaseInstanceRepository.findAll().size();

        // Delete the camundaCaseInstance
        restCamundaCaseInstanceMockMvc.perform(delete("/api/camunda-case-instances/{id}", camundaCaseInstance.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<CamundaCaseInstance> camundaCaseInstanceList = camundaCaseInstanceRepository.findAll();
        assertThat(camundaCaseInstanceList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(CamundaCaseInstance.class);
        CamundaCaseInstance camundaCaseInstance1 = new CamundaCaseInstance();
        camundaCaseInstance1.setId(1L);
        CamundaCaseInstance camundaCaseInstance2 = new CamundaCaseInstance();
        camundaCaseInstance2.setId(camundaCaseInstance1.getId());
        assertThat(camundaCaseInstance1).isEqualTo(camundaCaseInstance2);
        camundaCaseInstance2.setId(2L);
        assertThat(camundaCaseInstance1).isNotEqualTo(camundaCaseInstance2);
        camundaCaseInstance1.setId(null);
        assertThat(camundaCaseInstance1).isNotEqualTo(camundaCaseInstance2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(CamundaCaseInstanceDTO.class);
        CamundaCaseInstanceDTO camundaCaseInstanceDTO1 = new CamundaCaseInstanceDTO();
        camundaCaseInstanceDTO1.setId(1L);
        CamundaCaseInstanceDTO camundaCaseInstanceDTO2 = new CamundaCaseInstanceDTO();
        assertThat(camundaCaseInstanceDTO1).isNotEqualTo(camundaCaseInstanceDTO2);
        camundaCaseInstanceDTO2.setId(camundaCaseInstanceDTO1.getId());
        assertThat(camundaCaseInstanceDTO1).isEqualTo(camundaCaseInstanceDTO2);
        camundaCaseInstanceDTO2.setId(2L);
        assertThat(camundaCaseInstanceDTO1).isNotEqualTo(camundaCaseInstanceDTO2);
        camundaCaseInstanceDTO1.setId(null);
        assertThat(camundaCaseInstanceDTO1).isNotEqualTo(camundaCaseInstanceDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(camundaCaseInstanceMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(camundaCaseInstanceMapper.fromId(null)).isNull();
    }
}
