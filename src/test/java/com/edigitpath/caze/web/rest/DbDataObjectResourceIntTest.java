package com.edigitpath.caze.web.rest;

import com.edigitpath.caze.CaseServiceApp;

import com.edigitpath.caze.domain.DbDataObject;
import com.edigitpath.caze.repository.DbDataObjectRepository;
import com.edigitpath.caze.service.DbDataObjectService;
import com.edigitpath.caze.service.dto.DbDataObjectDTO;
import com.edigitpath.caze.service.mapper.DbDataObjectMapper;
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
 * Test class for the DbDataObjectResource REST controller.
 *
 * @see DbDataObjectResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = CaseServiceApp.class)
public class DbDataObjectResourceIntTest {

    private static final String DEFAULT_TABLE_NAME = "AAAAAAAAAA";
    private static final String UPDATED_TABLE_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_COLUMN_NAME = "AAAAAAAAAA";
    private static final String UPDATED_COLUMN_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_COLUMN_VALUE = "AAAAAAAAAA";
    private static final String UPDATED_COLUMN_VALUE = "BBBBBBBBBB";

    @Autowired
    private DbDataObjectRepository dbDataObjectRepository;

    @Autowired
    private DbDataObjectMapper dbDataObjectMapper;

    @Autowired
    private DbDataObjectService dbDataObjectService;

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

    private MockMvc restDbDataObjectMockMvc;

    private DbDataObject dbDataObject;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final DbDataObjectResource dbDataObjectResource = new DbDataObjectResource(dbDataObjectService);
        this.restDbDataObjectMockMvc = MockMvcBuilders.standaloneSetup(dbDataObjectResource)
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
    public static DbDataObject createEntity(EntityManager em) {
        DbDataObject dbDataObject = new DbDataObject();
        dbDataObject.setTableName(DEFAULT_TABLE_NAME);
        dbDataObject.setColumnName(DEFAULT_COLUMN_NAME);
        dbDataObject.setColumnValue(DEFAULT_COLUMN_VALUE);
        return dbDataObject;
    }

    @Before
    public void initTest() {
        dbDataObject = createEntity(em);
    }

    @Test
    @Transactional
    public void createDbDataObject() throws Exception {
        int databaseSizeBeforeCreate = dbDataObjectRepository.findAll().size();

        // Create the DbDataObject
        DbDataObjectDTO dbDataObjectDTO = dbDataObjectMapper.toDto(dbDataObject);
        restDbDataObjectMockMvc.perform(post("/api/db-data-objects")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(dbDataObjectDTO)))
            .andExpect(status().isCreated());

        // Validate the DbDataObject in the database
        List<DbDataObject> dbDataObjectList = dbDataObjectRepository.findAll();
        assertThat(dbDataObjectList).hasSize(databaseSizeBeforeCreate + 1);
        DbDataObject testDbDataObject = dbDataObjectList.get(dbDataObjectList.size() - 1);
        assertThat(testDbDataObject.getTableName()).isEqualTo(DEFAULT_TABLE_NAME);
        assertThat(testDbDataObject.getColumnName()).isEqualTo(DEFAULT_COLUMN_NAME);
        assertThat(testDbDataObject.getColumnValue()).isEqualTo(DEFAULT_COLUMN_VALUE);
    }

    @Test
    @Transactional
    public void createDbDataObjectWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = dbDataObjectRepository.findAll().size();

        // Create the DbDataObject with an existing ID
        dbDataObject.setId(1L);
        DbDataObjectDTO dbDataObjectDTO = dbDataObjectMapper.toDto(dbDataObject);

        // An entity with an existing ID cannot be created, so this API call must fail
        restDbDataObjectMockMvc.perform(post("/api/db-data-objects")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(dbDataObjectDTO)))
            .andExpect(status().isBadRequest());

        // Validate the DbDataObject in the database
        List<DbDataObject> dbDataObjectList = dbDataObjectRepository.findAll();
        assertThat(dbDataObjectList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllDbDataObjects() throws Exception {
        // Initialize the database
        dbDataObjectRepository.saveAndFlush(dbDataObject);

        // Get all the dbDataObjectList
        restDbDataObjectMockMvc.perform(get("/api/db-data-objects?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(dbDataObject.getId().intValue())))
            .andExpect(jsonPath("$.[*].tableName").value(hasItem(DEFAULT_TABLE_NAME.toString())))
            .andExpect(jsonPath("$.[*].columnName").value(hasItem(DEFAULT_COLUMN_NAME.toString())))
            .andExpect(jsonPath("$.[*].columnValue").value(hasItem(DEFAULT_COLUMN_VALUE.toString())));
    }
    
    @Test
    @Transactional
    public void getDbDataObject() throws Exception {
        // Initialize the database
        dbDataObjectRepository.saveAndFlush(dbDataObject);

        // Get the dbDataObject
        restDbDataObjectMockMvc.perform(get("/api/db-data-objects/{id}", dbDataObject.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(dbDataObject.getId().intValue()))
            .andExpect(jsonPath("$.tableName").value(DEFAULT_TABLE_NAME.toString()))
            .andExpect(jsonPath("$.columnName").value(DEFAULT_COLUMN_NAME.toString()))
            .andExpect(jsonPath("$.columnValue").value(DEFAULT_COLUMN_VALUE.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingDbDataObject() throws Exception {
        // Get the dbDataObject
        restDbDataObjectMockMvc.perform(get("/api/db-data-objects/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateDbDataObject() throws Exception {
        // Initialize the database
        dbDataObjectRepository.saveAndFlush(dbDataObject);

        int databaseSizeBeforeUpdate = dbDataObjectRepository.findAll().size();

        // Update the dbDataObject
        DbDataObject updatedDbDataObject = dbDataObjectRepository.findById(dbDataObject.getId()).get();
        // Disconnect from session so that the updates on updatedDbDataObject are not directly saved in db
        em.detach(updatedDbDataObject);
        updatedDbDataObject.setTableName(UPDATED_TABLE_NAME);
        updatedDbDataObject.setColumnName(UPDATED_COLUMN_NAME);
        updatedDbDataObject.setColumnValue(UPDATED_COLUMN_VALUE);
        DbDataObjectDTO dbDataObjectDTO = dbDataObjectMapper.toDto(updatedDbDataObject);

        restDbDataObjectMockMvc.perform(put("/api/db-data-objects")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(dbDataObjectDTO)))
            .andExpect(status().isOk());

        // Validate the DbDataObject in the database
        List<DbDataObject> dbDataObjectList = dbDataObjectRepository.findAll();
        assertThat(dbDataObjectList).hasSize(databaseSizeBeforeUpdate);
        DbDataObject testDbDataObject = dbDataObjectList.get(dbDataObjectList.size() - 1);
        assertThat(testDbDataObject.getTableName()).isEqualTo(UPDATED_TABLE_NAME);
        assertThat(testDbDataObject.getColumnName()).isEqualTo(UPDATED_COLUMN_NAME);
        assertThat(testDbDataObject.getColumnValue()).isEqualTo(UPDATED_COLUMN_VALUE);
    }

    @Test
    @Transactional
    public void updateNonExistingDbDataObject() throws Exception {
        int databaseSizeBeforeUpdate = dbDataObjectRepository.findAll().size();

        // Create the DbDataObject
        DbDataObjectDTO dbDataObjectDTO = dbDataObjectMapper.toDto(dbDataObject);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDbDataObjectMockMvc.perform(put("/api/db-data-objects")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(dbDataObjectDTO)))
            .andExpect(status().isBadRequest());

        // Validate the DbDataObject in the database
        List<DbDataObject> dbDataObjectList = dbDataObjectRepository.findAll();
        assertThat(dbDataObjectList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteDbDataObject() throws Exception {
        // Initialize the database
        dbDataObjectRepository.saveAndFlush(dbDataObject);

        int databaseSizeBeforeDelete = dbDataObjectRepository.findAll().size();

        // Delete the dbDataObject
        restDbDataObjectMockMvc.perform(delete("/api/db-data-objects/{id}", dbDataObject.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<DbDataObject> dbDataObjectList = dbDataObjectRepository.findAll();
        assertThat(dbDataObjectList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(DbDataObject.class);
        DbDataObject dbDataObject1 = new DbDataObject();
        dbDataObject1.setId(1L);
        DbDataObject dbDataObject2 = new DbDataObject();
        dbDataObject2.setId(dbDataObject1.getId());
        assertThat(dbDataObject1).isEqualTo(dbDataObject2);
        dbDataObject2.setId(2L);
        assertThat(dbDataObject1).isNotEqualTo(dbDataObject2);
        dbDataObject1.setId(null);
        assertThat(dbDataObject1).isNotEqualTo(dbDataObject2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(DbDataObjectDTO.class);
        DbDataObjectDTO dbDataObjectDTO1 = new DbDataObjectDTO();
        dbDataObjectDTO1.setId(1L);
        DbDataObjectDTO dbDataObjectDTO2 = new DbDataObjectDTO();
        assertThat(dbDataObjectDTO1).isNotEqualTo(dbDataObjectDTO2);
        dbDataObjectDTO2.setId(dbDataObjectDTO1.getId());
        assertThat(dbDataObjectDTO1).isEqualTo(dbDataObjectDTO2);
        dbDataObjectDTO2.setId(2L);
        assertThat(dbDataObjectDTO1).isNotEqualTo(dbDataObjectDTO2);
        dbDataObjectDTO1.setId(null);
        assertThat(dbDataObjectDTO1).isNotEqualTo(dbDataObjectDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(dbDataObjectMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(dbDataObjectMapper.fromId(null)).isNull();
    }
}
