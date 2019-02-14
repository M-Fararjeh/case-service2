package com.edigitpath.caze.web.rest;

import com.edigitpath.caze.CaseServiceApp;

import com.edigitpath.caze.domain.ApiHeader;
import com.edigitpath.caze.repository.ApiHeaderRepository;
import com.edigitpath.caze.service.ApiHeaderService;
import com.edigitpath.caze.service.dto.ApiHeaderDTO;
import com.edigitpath.caze.service.mapper.ApiHeaderMapper;
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
 * Test class for the ApiHeaderResource REST controller.
 *
 * @see ApiHeaderResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = CaseServiceApp.class)
public class ApiHeaderResourceIntTest {

    private static final String DEFAULT_KEY = "AAAAAAAAAA";
    private static final String UPDATED_KEY = "BBBBBBBBBB";

    private static final String DEFAULT_VALUE = "AAAAAAAAAA";
    private static final String UPDATED_VALUE = "BBBBBBBBBB";

    @Autowired
    private ApiHeaderRepository apiHeaderRepository;

    @Autowired
    private ApiHeaderMapper apiHeaderMapper;

    @Autowired
    private ApiHeaderService apiHeaderService;

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

    private MockMvc restApiHeaderMockMvc;

    private ApiHeader apiHeader;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ApiHeaderResource apiHeaderResource = new ApiHeaderResource(apiHeaderService);
        this.restApiHeaderMockMvc = MockMvcBuilders.standaloneSetup(apiHeaderResource)
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
    public static ApiHeader createEntity(EntityManager em) {
        ApiHeader apiHeader = new ApiHeader();
        apiHeader.setKey(DEFAULT_KEY);
        apiHeader.setValue(DEFAULT_VALUE);
        return apiHeader;
    }

    @Before
    public void initTest() {
        apiHeader = createEntity(em);
    }

    @Test
    @Transactional
    public void createApiHeader() throws Exception {
        int databaseSizeBeforeCreate = apiHeaderRepository.findAll().size();

        // Create the ApiHeader
        ApiHeaderDTO apiHeaderDTO = apiHeaderMapper.toDto(apiHeader);
        restApiHeaderMockMvc.perform(post("/api/api-headers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(apiHeaderDTO)))
            .andExpect(status().isCreated());

        // Validate the ApiHeader in the database
        List<ApiHeader> apiHeaderList = apiHeaderRepository.findAll();
        assertThat(apiHeaderList).hasSize(databaseSizeBeforeCreate + 1);
        ApiHeader testApiHeader = apiHeaderList.get(apiHeaderList.size() - 1);
        assertThat(testApiHeader.getKey()).isEqualTo(DEFAULT_KEY);
        assertThat(testApiHeader.getValue()).isEqualTo(DEFAULT_VALUE);
    }

    @Test
    @Transactional
    public void createApiHeaderWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = apiHeaderRepository.findAll().size();

        // Create the ApiHeader with an existing ID
        apiHeader.setId(1L);
        ApiHeaderDTO apiHeaderDTO = apiHeaderMapper.toDto(apiHeader);

        // An entity with an existing ID cannot be created, so this API call must fail
        restApiHeaderMockMvc.perform(post("/api/api-headers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(apiHeaderDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ApiHeader in the database
        List<ApiHeader> apiHeaderList = apiHeaderRepository.findAll();
        assertThat(apiHeaderList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllApiHeaders() throws Exception {
        // Initialize the database
        apiHeaderRepository.saveAndFlush(apiHeader);

        // Get all the apiHeaderList
        restApiHeaderMockMvc.perform(get("/api/api-headers?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(apiHeader.getId().intValue())))
            .andExpect(jsonPath("$.[*].key").value(hasItem(DEFAULT_KEY.toString())))
            .andExpect(jsonPath("$.[*].value").value(hasItem(DEFAULT_VALUE.toString())));
    }
    
    @Test
    @Transactional
    public void getApiHeader() throws Exception {
        // Initialize the database
        apiHeaderRepository.saveAndFlush(apiHeader);

        // Get the apiHeader
        restApiHeaderMockMvc.perform(get("/api/api-headers/{id}", apiHeader.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(apiHeader.getId().intValue()))
            .andExpect(jsonPath("$.key").value(DEFAULT_KEY.toString()))
            .andExpect(jsonPath("$.value").value(DEFAULT_VALUE.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingApiHeader() throws Exception {
        // Get the apiHeader
        restApiHeaderMockMvc.perform(get("/api/api-headers/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateApiHeader() throws Exception {
        // Initialize the database
        apiHeaderRepository.saveAndFlush(apiHeader);

        int databaseSizeBeforeUpdate = apiHeaderRepository.findAll().size();

        // Update the apiHeader
        ApiHeader updatedApiHeader = apiHeaderRepository.findById(apiHeader.getId()).get();
        // Disconnect from session so that the updates on updatedApiHeader are not directly saved in db
        em.detach(updatedApiHeader);
        updatedApiHeader.setKey(UPDATED_KEY);
        updatedApiHeader.setValue(UPDATED_VALUE);
        ApiHeaderDTO apiHeaderDTO = apiHeaderMapper.toDto(updatedApiHeader);

        restApiHeaderMockMvc.perform(put("/api/api-headers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(apiHeaderDTO)))
            .andExpect(status().isOk());

        // Validate the ApiHeader in the database
        List<ApiHeader> apiHeaderList = apiHeaderRepository.findAll();
        assertThat(apiHeaderList).hasSize(databaseSizeBeforeUpdate);
        ApiHeader testApiHeader = apiHeaderList.get(apiHeaderList.size() - 1);
        assertThat(testApiHeader.getKey()).isEqualTo(UPDATED_KEY);
        assertThat(testApiHeader.getValue()).isEqualTo(UPDATED_VALUE);
    }

    @Test
    @Transactional
    public void updateNonExistingApiHeader() throws Exception {
        int databaseSizeBeforeUpdate = apiHeaderRepository.findAll().size();

        // Create the ApiHeader
        ApiHeaderDTO apiHeaderDTO = apiHeaderMapper.toDto(apiHeader);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restApiHeaderMockMvc.perform(put("/api/api-headers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(apiHeaderDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ApiHeader in the database
        List<ApiHeader> apiHeaderList = apiHeaderRepository.findAll();
        assertThat(apiHeaderList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteApiHeader() throws Exception {
        // Initialize the database
        apiHeaderRepository.saveAndFlush(apiHeader);

        int databaseSizeBeforeDelete = apiHeaderRepository.findAll().size();

        // Delete the apiHeader
        restApiHeaderMockMvc.perform(delete("/api/api-headers/{id}", apiHeader.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<ApiHeader> apiHeaderList = apiHeaderRepository.findAll();
        assertThat(apiHeaderList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ApiHeader.class);
        ApiHeader apiHeader1 = new ApiHeader();
        apiHeader1.setId(1L);
        ApiHeader apiHeader2 = new ApiHeader();
        apiHeader2.setId(apiHeader1.getId());
        assertThat(apiHeader1).isEqualTo(apiHeader2);
        apiHeader2.setId(2L);
        assertThat(apiHeader1).isNotEqualTo(apiHeader2);
        apiHeader1.setId(null);
        assertThat(apiHeader1).isNotEqualTo(apiHeader2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ApiHeaderDTO.class);
        ApiHeaderDTO apiHeaderDTO1 = new ApiHeaderDTO();
        apiHeaderDTO1.setId(1L);
        ApiHeaderDTO apiHeaderDTO2 = new ApiHeaderDTO();
        assertThat(apiHeaderDTO1).isNotEqualTo(apiHeaderDTO2);
        apiHeaderDTO2.setId(apiHeaderDTO1.getId());
        assertThat(apiHeaderDTO1).isEqualTo(apiHeaderDTO2);
        apiHeaderDTO2.setId(2L);
        assertThat(apiHeaderDTO1).isNotEqualTo(apiHeaderDTO2);
        apiHeaderDTO1.setId(null);
        assertThat(apiHeaderDTO1).isNotEqualTo(apiHeaderDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(apiHeaderMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(apiHeaderMapper.fromId(null)).isNull();
    }
}
