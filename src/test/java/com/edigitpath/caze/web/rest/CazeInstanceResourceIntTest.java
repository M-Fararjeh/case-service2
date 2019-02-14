package com.edigitpath.caze.web.rest;

import com.edigitpath.caze.CaseServiceApp;

import com.edigitpath.caze.domain.CazeInstance;
import com.edigitpath.caze.repository.CazeInstanceRepository;
import com.edigitpath.caze.service.CazeInstanceService;
import com.edigitpath.caze.service.dto.CazeInstanceDTO;
import com.edigitpath.caze.service.mapper.CazeInstanceMapper;
import com.edigitpath.caze.web.rest.errors.ExceptionTranslator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Validator;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;


import static com.edigitpath.caze.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.edigitpath.caze.domain.enumeration.CasePriority;
/**
 * Test class for the CazeInstanceResource REST controller.
 *
 * @see CazeInstanceResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = CaseServiceApp.class)
public class CazeInstanceResourceIntTest {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final String DEFAULT_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_NUMBER = "BBBBBBBBBB";

    private static final String DEFAULT_CREATOR_ID = "AAAAAAAAAA";
    private static final String UPDATED_CREATOR_ID = "BBBBBBBBBB";

    private static final String DEFAULT_ISSUER_ID = "AAAAAAAAAA";
    private static final String UPDATED_ISSUER_ID = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_CREATION_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_CREATION_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_CASE_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_CASE_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final CasePriority DEFAULT_PRIORITY = CasePriority.HIGH;
    private static final CasePriority UPDATED_PRIORITY = CasePriority.NORMAL;

    private static final Integer DEFAULT_REQUIRED_TIME = 1;
    private static final Integer UPDATED_REQUIRED_TIME = 2;

    private static final Boolean DEFAULT_SECURED = false;
    private static final Boolean UPDATED_SECURED = true;

    private static final String DEFAULT_CMMN_ID = "AAAAAAAAAA";
    private static final String UPDATED_CMMN_ID = "BBBBBBBBBB";

    @Autowired
    private CazeInstanceRepository cazeInstanceRepository;

    @Mock
    private CazeInstanceRepository cazeInstanceRepositoryMock;

    @Autowired
    private CazeInstanceMapper cazeInstanceMapper;

    @Mock
    private CazeInstanceService cazeInstanceServiceMock;

    @Autowired
    private CazeInstanceService cazeInstanceService;

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

    private MockMvc restCazeInstanceMockMvc;

    private CazeInstance cazeInstance;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final CazeInstanceResource cazeInstanceResource = new CazeInstanceResource(cazeInstanceService);
        this.restCazeInstanceMockMvc = MockMvcBuilders.standaloneSetup(cazeInstanceResource)
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
    public static CazeInstance createEntity(EntityManager em) {
        CazeInstance cazeInstance = new CazeInstance();
        cazeInstance.setName(DEFAULT_NAME);
        cazeInstance.setDescription(DEFAULT_DESCRIPTION);
        cazeInstance.setNumber(DEFAULT_NUMBER);
        cazeInstance.setCreatorId(DEFAULT_CREATOR_ID);
        cazeInstance.setIssuerID(DEFAULT_ISSUER_ID);
        cazeInstance.setCreationDate(DEFAULT_CREATION_DATE);
        cazeInstance.setCaseDate(DEFAULT_CASE_DATE);
        cazeInstance.setPriority(DEFAULT_PRIORITY);
        cazeInstance.setRequiredTime(DEFAULT_REQUIRED_TIME);
        cazeInstance.setSecured(DEFAULT_SECURED);
        cazeInstance.setCmmnId(DEFAULT_CMMN_ID);
        return cazeInstance;
    }

    @Before
    public void initTest() {
        cazeInstance = createEntity(em);
    }

    @Test
    @Transactional
    public void createCazeInstance() throws Exception {
        int databaseSizeBeforeCreate = cazeInstanceRepository.findAll().size();

        // Create the CazeInstance
        CazeInstanceDTO cazeInstanceDTO = cazeInstanceMapper.toDto(cazeInstance);
        restCazeInstanceMockMvc.perform(post("/api/caze-instances")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(cazeInstanceDTO)))
            .andExpect(status().isCreated());

        // Validate the CazeInstance in the database
        List<CazeInstance> cazeInstanceList = cazeInstanceRepository.findAll();
        assertThat(cazeInstanceList).hasSize(databaseSizeBeforeCreate + 1);
        CazeInstance testCazeInstance = cazeInstanceList.get(cazeInstanceList.size() - 1);
        assertThat(testCazeInstance.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testCazeInstance.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testCazeInstance.getNumber()).isEqualTo(DEFAULT_NUMBER);
        assertThat(testCazeInstance.getCreatorId()).isEqualTo(DEFAULT_CREATOR_ID);
        assertThat(testCazeInstance.getIssuerID()).isEqualTo(DEFAULT_ISSUER_ID);
        assertThat(testCazeInstance.getCreationDate()).isEqualTo(DEFAULT_CREATION_DATE);
        assertThat(testCazeInstance.getCaseDate()).isEqualTo(DEFAULT_CASE_DATE);
        assertThat(testCazeInstance.getPriority()).isEqualTo(DEFAULT_PRIORITY);
        assertThat(testCazeInstance.getRequiredTime()).isEqualTo(DEFAULT_REQUIRED_TIME);
        assertThat(testCazeInstance.isSecured()).isEqualTo(DEFAULT_SECURED);
        assertThat(testCazeInstance.getCmmnId()).isEqualTo(DEFAULT_CMMN_ID);
    }

    @Test
    @Transactional
    public void createCazeInstanceWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = cazeInstanceRepository.findAll().size();

        // Create the CazeInstance with an existing ID
        cazeInstance.setId(1L);
        CazeInstanceDTO cazeInstanceDTO = cazeInstanceMapper.toDto(cazeInstance);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCazeInstanceMockMvc.perform(post("/api/caze-instances")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(cazeInstanceDTO)))
            .andExpect(status().isBadRequest());

        // Validate the CazeInstance in the database
        List<CazeInstance> cazeInstanceList = cazeInstanceRepository.findAll();
        assertThat(cazeInstanceList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllCazeInstances() throws Exception {
        // Initialize the database
        cazeInstanceRepository.saveAndFlush(cazeInstance);

        // Get all the cazeInstanceList
        restCazeInstanceMockMvc.perform(get("/api/caze-instances?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(cazeInstance.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION.toString())))
            .andExpect(jsonPath("$.[*].number").value(hasItem(DEFAULT_NUMBER.toString())))
            .andExpect(jsonPath("$.[*].creatorId").value(hasItem(DEFAULT_CREATOR_ID.toString())))
            .andExpect(jsonPath("$.[*].issuerID").value(hasItem(DEFAULT_ISSUER_ID.toString())))
            .andExpect(jsonPath("$.[*].creationDate").value(hasItem(DEFAULT_CREATION_DATE.toString())))
            .andExpect(jsonPath("$.[*].caseDate").value(hasItem(DEFAULT_CASE_DATE.toString())))
            .andExpect(jsonPath("$.[*].priority").value(hasItem(DEFAULT_PRIORITY.toString())))
            .andExpect(jsonPath("$.[*].requiredTime").value(hasItem(DEFAULT_REQUIRED_TIME)))
            .andExpect(jsonPath("$.[*].secured").value(hasItem(DEFAULT_SECURED.booleanValue())))
            .andExpect(jsonPath("$.[*].cmmnId").value(hasItem(DEFAULT_CMMN_ID.toString())));
    }
    
    @SuppressWarnings({"unchecked"})
    public void getAllCazeInstancesWithEagerRelationshipsIsEnabled() throws Exception {
        CazeInstanceResource cazeInstanceResource = new CazeInstanceResource(cazeInstanceServiceMock);
        when(cazeInstanceServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        MockMvc restCazeInstanceMockMvc = MockMvcBuilders.standaloneSetup(cazeInstanceResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter).build();

        restCazeInstanceMockMvc.perform(get("/api/caze-instances?eagerload=true"))
        .andExpect(status().isOk());

        verify(cazeInstanceServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    @SuppressWarnings({"unchecked"})
    public void getAllCazeInstancesWithEagerRelationshipsIsNotEnabled() throws Exception {
        CazeInstanceResource cazeInstanceResource = new CazeInstanceResource(cazeInstanceServiceMock);
            when(cazeInstanceServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));
            MockMvc restCazeInstanceMockMvc = MockMvcBuilders.standaloneSetup(cazeInstanceResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter).build();

        restCazeInstanceMockMvc.perform(get("/api/caze-instances?eagerload=true"))
        .andExpect(status().isOk());

            verify(cazeInstanceServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    @Test
    @Transactional
    public void getCazeInstance() throws Exception {
        // Initialize the database
        cazeInstanceRepository.saveAndFlush(cazeInstance);

        // Get the cazeInstance
        restCazeInstanceMockMvc.perform(get("/api/caze-instances/{id}", cazeInstance.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(cazeInstance.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION.toString()))
            .andExpect(jsonPath("$.number").value(DEFAULT_NUMBER.toString()))
            .andExpect(jsonPath("$.creatorId").value(DEFAULT_CREATOR_ID.toString()))
            .andExpect(jsonPath("$.issuerID").value(DEFAULT_ISSUER_ID.toString()))
            .andExpect(jsonPath("$.creationDate").value(DEFAULT_CREATION_DATE.toString()))
            .andExpect(jsonPath("$.caseDate").value(DEFAULT_CASE_DATE.toString()))
            .andExpect(jsonPath("$.priority").value(DEFAULT_PRIORITY.toString()))
            .andExpect(jsonPath("$.requiredTime").value(DEFAULT_REQUIRED_TIME))
            .andExpect(jsonPath("$.secured").value(DEFAULT_SECURED.booleanValue()))
            .andExpect(jsonPath("$.cmmnId").value(DEFAULT_CMMN_ID.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingCazeInstance() throws Exception {
        // Get the cazeInstance
        restCazeInstanceMockMvc.perform(get("/api/caze-instances/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCazeInstance() throws Exception {
        // Initialize the database
        cazeInstanceRepository.saveAndFlush(cazeInstance);

        int databaseSizeBeforeUpdate = cazeInstanceRepository.findAll().size();

        // Update the cazeInstance
        CazeInstance updatedCazeInstance = cazeInstanceRepository.findById(cazeInstance.getId()).get();
        // Disconnect from session so that the updates on updatedCazeInstance are not directly saved in db
        em.detach(updatedCazeInstance);
        updatedCazeInstance.setName(UPDATED_NAME);
        updatedCazeInstance.setDescription(UPDATED_DESCRIPTION);
        updatedCazeInstance.setNumber(UPDATED_NUMBER);
        updatedCazeInstance.setCreatorId(UPDATED_CREATOR_ID);
        updatedCazeInstance.setIssuerID(UPDATED_ISSUER_ID);
        updatedCazeInstance.setCreationDate(UPDATED_CREATION_DATE);
        updatedCazeInstance.setCaseDate(UPDATED_CASE_DATE);
        updatedCazeInstance.setPriority(UPDATED_PRIORITY);
        updatedCazeInstance.setRequiredTime(UPDATED_REQUIRED_TIME);
        updatedCazeInstance.setSecured(UPDATED_SECURED);
        updatedCazeInstance.setCmmnId(UPDATED_CMMN_ID);
        CazeInstanceDTO cazeInstanceDTO = cazeInstanceMapper.toDto(updatedCazeInstance);

        restCazeInstanceMockMvc.perform(put("/api/caze-instances")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(cazeInstanceDTO)))
            .andExpect(status().isOk());

        // Validate the CazeInstance in the database
        List<CazeInstance> cazeInstanceList = cazeInstanceRepository.findAll();
        assertThat(cazeInstanceList).hasSize(databaseSizeBeforeUpdate);
        CazeInstance testCazeInstance = cazeInstanceList.get(cazeInstanceList.size() - 1);
        assertThat(testCazeInstance.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testCazeInstance.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testCazeInstance.getNumber()).isEqualTo(UPDATED_NUMBER);
        assertThat(testCazeInstance.getCreatorId()).isEqualTo(UPDATED_CREATOR_ID);
        assertThat(testCazeInstance.getIssuerID()).isEqualTo(UPDATED_ISSUER_ID);
        assertThat(testCazeInstance.getCreationDate()).isEqualTo(UPDATED_CREATION_DATE);
        assertThat(testCazeInstance.getCaseDate()).isEqualTo(UPDATED_CASE_DATE);
        assertThat(testCazeInstance.getPriority()).isEqualTo(UPDATED_PRIORITY);
        assertThat(testCazeInstance.getRequiredTime()).isEqualTo(UPDATED_REQUIRED_TIME);
        assertThat(testCazeInstance.isSecured()).isEqualTo(UPDATED_SECURED);
        assertThat(testCazeInstance.getCmmnId()).isEqualTo(UPDATED_CMMN_ID);
    }

    @Test
    @Transactional
    public void updateNonExistingCazeInstance() throws Exception {
        int databaseSizeBeforeUpdate = cazeInstanceRepository.findAll().size();

        // Create the CazeInstance
        CazeInstanceDTO cazeInstanceDTO = cazeInstanceMapper.toDto(cazeInstance);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCazeInstanceMockMvc.perform(put("/api/caze-instances")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(cazeInstanceDTO)))
            .andExpect(status().isBadRequest());

        // Validate the CazeInstance in the database
        List<CazeInstance> cazeInstanceList = cazeInstanceRepository.findAll();
        assertThat(cazeInstanceList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteCazeInstance() throws Exception {
        // Initialize the database
        cazeInstanceRepository.saveAndFlush(cazeInstance);

        int databaseSizeBeforeDelete = cazeInstanceRepository.findAll().size();

        // Delete the cazeInstance
        restCazeInstanceMockMvc.perform(delete("/api/caze-instances/{id}", cazeInstance.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<CazeInstance> cazeInstanceList = cazeInstanceRepository.findAll();
        assertThat(cazeInstanceList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(CazeInstance.class);
        CazeInstance cazeInstance1 = new CazeInstance();
        cazeInstance1.setId(1L);
        CazeInstance cazeInstance2 = new CazeInstance();
        cazeInstance2.setId(cazeInstance1.getId());
        assertThat(cazeInstance1).isEqualTo(cazeInstance2);
        cazeInstance2.setId(2L);
        assertThat(cazeInstance1).isNotEqualTo(cazeInstance2);
        cazeInstance1.setId(null);
        assertThat(cazeInstance1).isNotEqualTo(cazeInstance2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(CazeInstanceDTO.class);
        CazeInstanceDTO cazeInstanceDTO1 = new CazeInstanceDTO();
        cazeInstanceDTO1.setId(1L);
        CazeInstanceDTO cazeInstanceDTO2 = new CazeInstanceDTO();
        assertThat(cazeInstanceDTO1).isNotEqualTo(cazeInstanceDTO2);
        cazeInstanceDTO2.setId(cazeInstanceDTO1.getId());
        assertThat(cazeInstanceDTO1).isEqualTo(cazeInstanceDTO2);
        cazeInstanceDTO2.setId(2L);
        assertThat(cazeInstanceDTO1).isNotEqualTo(cazeInstanceDTO2);
        cazeInstanceDTO1.setId(null);
        assertThat(cazeInstanceDTO1).isNotEqualTo(cazeInstanceDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(cazeInstanceMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(cazeInstanceMapper.fromId(null)).isNull();
    }
}
