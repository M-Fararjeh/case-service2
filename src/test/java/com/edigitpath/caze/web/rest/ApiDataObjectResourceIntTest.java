package com.edigitpath.caze.web.rest;

import com.edigitpath.caze.CaseServiceApp;

import com.edigitpath.caze.domain.ApiDataObject;
import com.edigitpath.caze.repository.ApiDataObjectRepository;
import com.edigitpath.caze.service.ApiDataObjectService;
import com.edigitpath.caze.service.dto.ApiDataObjectDTO;
import com.edigitpath.caze.service.mapper.ApiDataObjectMapper;
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

import com.edigitpath.caze.domain.enumeration.ApiMethod;
/**
 * Test class for the ApiDataObjectResource REST controller.
 *
 * @see ApiDataObjectResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = CaseServiceApp.class)
public class ApiDataObjectResourceIntTest {

    private static final ApiMethod DEFAULT_METHOD = ApiMethod.GET;
    private static final ApiMethod UPDATED_METHOD = ApiMethod.POST;

    private static final String DEFAULT_URL = "AAAAAAAAAA";
    private static final String UPDATED_URL = "BBBBBBBBBB";

    private static final String DEFAULT_BODY = "AAAAAAAAAA";
    private static final String UPDATED_BODY = "BBBBBBBBBB";

    @Autowired
    private ApiDataObjectRepository apiDataObjectRepository;

    @Autowired
    private ApiDataObjectMapper apiDataObjectMapper;

    @Autowired
    private ApiDataObjectService apiDataObjectService;

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

    private MockMvc restApiDataObjectMockMvc;

    private ApiDataObject apiDataObject;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ApiDataObjectResource apiDataObjectResource = new ApiDataObjectResource(apiDataObjectService);
        this.restApiDataObjectMockMvc = MockMvcBuilders.standaloneSetup(apiDataObjectResource)
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
    public static ApiDataObject createEntity(EntityManager em) {
        ApiDataObject apiDataObject = new ApiDataObject();
        apiDataObject.setMethod(DEFAULT_METHOD);
        apiDataObject.setUrl(DEFAULT_URL);
        apiDataObject.setBody(DEFAULT_BODY);
        return apiDataObject;
    }

    @Before
    public void initTest() {
        apiDataObject = createEntity(em);
    }

    @Test
    @Transactional
    public void createApiDataObject() throws Exception {
        int databaseSizeBeforeCreate = apiDataObjectRepository.findAll().size();

        // Create the ApiDataObject
        ApiDataObjectDTO apiDataObjectDTO = apiDataObjectMapper.toDto(apiDataObject);
        restApiDataObjectMockMvc.perform(post("/api/api-data-objects")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(apiDataObjectDTO)))
            .andExpect(status().isCreated());

        // Validate the ApiDataObject in the database
        List<ApiDataObject> apiDataObjectList = apiDataObjectRepository.findAll();
        assertThat(apiDataObjectList).hasSize(databaseSizeBeforeCreate + 1);
        ApiDataObject testApiDataObject = apiDataObjectList.get(apiDataObjectList.size() - 1);
        assertThat(testApiDataObject.getMethod()).isEqualTo(DEFAULT_METHOD);
        assertThat(testApiDataObject.getUrl()).isEqualTo(DEFAULT_URL);
        assertThat(testApiDataObject.getBody()).isEqualTo(DEFAULT_BODY);
    }

    @Test
    @Transactional
    public void createApiDataObjectWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = apiDataObjectRepository.findAll().size();

        // Create the ApiDataObject with an existing ID
        apiDataObject.setId(1L);
        ApiDataObjectDTO apiDataObjectDTO = apiDataObjectMapper.toDto(apiDataObject);

        // An entity with an existing ID cannot be created, so this API call must fail
        restApiDataObjectMockMvc.perform(post("/api/api-data-objects")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(apiDataObjectDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ApiDataObject in the database
        List<ApiDataObject> apiDataObjectList = apiDataObjectRepository.findAll();
        assertThat(apiDataObjectList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllApiDataObjects() throws Exception {
        // Initialize the database
        apiDataObjectRepository.saveAndFlush(apiDataObject);

        // Get all the apiDataObjectList
        restApiDataObjectMockMvc.perform(get("/api/api-data-objects?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(apiDataObject.getId().intValue())))
            .andExpect(jsonPath("$.[*].method").value(hasItem(DEFAULT_METHOD.toString())))
            .andExpect(jsonPath("$.[*].url").value(hasItem(DEFAULT_URL.toString())))
            .andExpect(jsonPath("$.[*].body").value(hasItem(DEFAULT_BODY.toString())));
    }
    
    @Test
    @Transactional
    public void getApiDataObject() throws Exception {
        // Initialize the database
        apiDataObjectRepository.saveAndFlush(apiDataObject);

        // Get the apiDataObject
        restApiDataObjectMockMvc.perform(get("/api/api-data-objects/{id}", apiDataObject.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(apiDataObject.getId().intValue()))
            .andExpect(jsonPath("$.method").value(DEFAULT_METHOD.toString()))
            .andExpect(jsonPath("$.url").value(DEFAULT_URL.toString()))
            .andExpect(jsonPath("$.body").value(DEFAULT_BODY.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingApiDataObject() throws Exception {
        // Get the apiDataObject
        restApiDataObjectMockMvc.perform(get("/api/api-data-objects/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateApiDataObject() throws Exception {
        // Initialize the database
        apiDataObjectRepository.saveAndFlush(apiDataObject);

        int databaseSizeBeforeUpdate = apiDataObjectRepository.findAll().size();

        // Update the apiDataObject
        ApiDataObject updatedApiDataObject = apiDataObjectRepository.findById(apiDataObject.getId()).get();
        // Disconnect from session so that the updates on updatedApiDataObject are not directly saved in db
        em.detach(updatedApiDataObject);
        updatedApiDataObject.setMethod(UPDATED_METHOD);
        updatedApiDataObject.setUrl(UPDATED_URL);
        updatedApiDataObject.setBody(UPDATED_BODY);
        ApiDataObjectDTO apiDataObjectDTO = apiDataObjectMapper.toDto(updatedApiDataObject);

        restApiDataObjectMockMvc.perform(put("/api/api-data-objects")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(apiDataObjectDTO)))
            .andExpect(status().isOk());

        // Validate the ApiDataObject in the database
        List<ApiDataObject> apiDataObjectList = apiDataObjectRepository.findAll();
        assertThat(apiDataObjectList).hasSize(databaseSizeBeforeUpdate);
        ApiDataObject testApiDataObject = apiDataObjectList.get(apiDataObjectList.size() - 1);
        assertThat(testApiDataObject.getMethod()).isEqualTo(UPDATED_METHOD);
        assertThat(testApiDataObject.getUrl()).isEqualTo(UPDATED_URL);
        assertThat(testApiDataObject.getBody()).isEqualTo(UPDATED_BODY);
    }

    @Test
    @Transactional
    public void updateNonExistingApiDataObject() throws Exception {
        int databaseSizeBeforeUpdate = apiDataObjectRepository.findAll().size();

        // Create the ApiDataObject
        ApiDataObjectDTO apiDataObjectDTO = apiDataObjectMapper.toDto(apiDataObject);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restApiDataObjectMockMvc.perform(put("/api/api-data-objects")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(apiDataObjectDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ApiDataObject in the database
        List<ApiDataObject> apiDataObjectList = apiDataObjectRepository.findAll();
        assertThat(apiDataObjectList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteApiDataObject() throws Exception {
        // Initialize the database
        apiDataObjectRepository.saveAndFlush(apiDataObject);

        int databaseSizeBeforeDelete = apiDataObjectRepository.findAll().size();

        // Delete the apiDataObject
        restApiDataObjectMockMvc.perform(delete("/api/api-data-objects/{id}", apiDataObject.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<ApiDataObject> apiDataObjectList = apiDataObjectRepository.findAll();
        assertThat(apiDataObjectList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ApiDataObject.class);
        ApiDataObject apiDataObject1 = new ApiDataObject();
        apiDataObject1.setId(1L);
        ApiDataObject apiDataObject2 = new ApiDataObject();
        apiDataObject2.setId(apiDataObject1.getId());
        assertThat(apiDataObject1).isEqualTo(apiDataObject2);
        apiDataObject2.setId(2L);
        assertThat(apiDataObject1).isNotEqualTo(apiDataObject2);
        apiDataObject1.setId(null);
        assertThat(apiDataObject1).isNotEqualTo(apiDataObject2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ApiDataObjectDTO.class);
        ApiDataObjectDTO apiDataObjectDTO1 = new ApiDataObjectDTO();
        apiDataObjectDTO1.setId(1L);
        ApiDataObjectDTO apiDataObjectDTO2 = new ApiDataObjectDTO();
        assertThat(apiDataObjectDTO1).isNotEqualTo(apiDataObjectDTO2);
        apiDataObjectDTO2.setId(apiDataObjectDTO1.getId());
        assertThat(apiDataObjectDTO1).isEqualTo(apiDataObjectDTO2);
        apiDataObjectDTO2.setId(2L);
        assertThat(apiDataObjectDTO1).isNotEqualTo(apiDataObjectDTO2);
        apiDataObjectDTO1.setId(null);
        assertThat(apiDataObjectDTO1).isNotEqualTo(apiDataObjectDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(apiDataObjectMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(apiDataObjectMapper.fromId(null)).isNull();
    }
}
