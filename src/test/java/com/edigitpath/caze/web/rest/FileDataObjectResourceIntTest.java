package com.edigitpath.caze.web.rest;

import com.edigitpath.caze.CaseServiceApp;

import com.edigitpath.caze.domain.FileDataObject;
import com.edigitpath.caze.repository.FileDataObjectRepository;
import com.edigitpath.caze.service.FileDataObjectService;
import com.edigitpath.caze.service.dto.FileDataObjectDTO;
import com.edigitpath.caze.service.mapper.FileDataObjectMapper;
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
 * Test class for the FileDataObjectResource REST controller.
 *
 * @see FileDataObjectResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = CaseServiceApp.class)
public class FileDataObjectResourceIntTest {

    private static final String DEFAULT_CMIS_ID = "AAAAAAAAAA";
    private static final String UPDATED_CMIS_ID = "BBBBBBBBBB";

    private static final String DEFAULT_PATH = "AAAAAAAAAA";
    private static final String UPDATED_PATH = "BBBBBBBBBB";

    @Autowired
    private FileDataObjectRepository fileDataObjectRepository;

    @Autowired
    private FileDataObjectMapper fileDataObjectMapper;

    @Autowired
    private FileDataObjectService fileDataObjectService;

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

    private MockMvc restFileDataObjectMockMvc;

    private FileDataObject fileDataObject;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final FileDataObjectResource fileDataObjectResource = new FileDataObjectResource(fileDataObjectService);
        this.restFileDataObjectMockMvc = MockMvcBuilders.standaloneSetup(fileDataObjectResource)
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
    public static FileDataObject createEntity(EntityManager em) {
        FileDataObject fileDataObject = new FileDataObject();
        fileDataObject.setCmisId(DEFAULT_CMIS_ID);
        fileDataObject.setPath(DEFAULT_PATH);
        return fileDataObject;
    }

    @Before
    public void initTest() {
        fileDataObject = createEntity(em);
    }

    @Test
    @Transactional
    public void createFileDataObject() throws Exception {
        int databaseSizeBeforeCreate = fileDataObjectRepository.findAll().size();

        // Create the FileDataObject
        FileDataObjectDTO fileDataObjectDTO = fileDataObjectMapper.toDto(fileDataObject);
        restFileDataObjectMockMvc.perform(post("/api/file-data-objects")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(fileDataObjectDTO)))
            .andExpect(status().isCreated());

        // Validate the FileDataObject in the database
        List<FileDataObject> fileDataObjectList = fileDataObjectRepository.findAll();
        assertThat(fileDataObjectList).hasSize(databaseSizeBeforeCreate + 1);
        FileDataObject testFileDataObject = fileDataObjectList.get(fileDataObjectList.size() - 1);
        assertThat(testFileDataObject.getCmisId()).isEqualTo(DEFAULT_CMIS_ID);
        assertThat(testFileDataObject.getPath()).isEqualTo(DEFAULT_PATH);
    }

    @Test
    @Transactional
    public void createFileDataObjectWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = fileDataObjectRepository.findAll().size();

        // Create the FileDataObject with an existing ID
        fileDataObject.setId(1L);
        FileDataObjectDTO fileDataObjectDTO = fileDataObjectMapper.toDto(fileDataObject);

        // An entity with an existing ID cannot be created, so this API call must fail
        restFileDataObjectMockMvc.perform(post("/api/file-data-objects")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(fileDataObjectDTO)))
            .andExpect(status().isBadRequest());

        // Validate the FileDataObject in the database
        List<FileDataObject> fileDataObjectList = fileDataObjectRepository.findAll();
        assertThat(fileDataObjectList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllFileDataObjects() throws Exception {
        // Initialize the database
        fileDataObjectRepository.saveAndFlush(fileDataObject);

        // Get all the fileDataObjectList
        restFileDataObjectMockMvc.perform(get("/api/file-data-objects?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(fileDataObject.getId().intValue())))
            .andExpect(jsonPath("$.[*].cmisId").value(hasItem(DEFAULT_CMIS_ID.toString())))
            .andExpect(jsonPath("$.[*].path").value(hasItem(DEFAULT_PATH.toString())));
    }
    
    @Test
    @Transactional
    public void getFileDataObject() throws Exception {
        // Initialize the database
        fileDataObjectRepository.saveAndFlush(fileDataObject);

        // Get the fileDataObject
        restFileDataObjectMockMvc.perform(get("/api/file-data-objects/{id}", fileDataObject.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(fileDataObject.getId().intValue()))
            .andExpect(jsonPath("$.cmisId").value(DEFAULT_CMIS_ID.toString()))
            .andExpect(jsonPath("$.path").value(DEFAULT_PATH.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingFileDataObject() throws Exception {
        // Get the fileDataObject
        restFileDataObjectMockMvc.perform(get("/api/file-data-objects/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateFileDataObject() throws Exception {
        // Initialize the database
        fileDataObjectRepository.saveAndFlush(fileDataObject);

        int databaseSizeBeforeUpdate = fileDataObjectRepository.findAll().size();

        // Update the fileDataObject
        FileDataObject updatedFileDataObject = fileDataObjectRepository.findById(fileDataObject.getId()).get();
        // Disconnect from session so that the updates on updatedFileDataObject are not directly saved in db
        em.detach(updatedFileDataObject);
        updatedFileDataObject.setCmisId(UPDATED_CMIS_ID);
        updatedFileDataObject.setPath(UPDATED_PATH);
        FileDataObjectDTO fileDataObjectDTO = fileDataObjectMapper.toDto(updatedFileDataObject);

        restFileDataObjectMockMvc.perform(put("/api/file-data-objects")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(fileDataObjectDTO)))
            .andExpect(status().isOk());

        // Validate the FileDataObject in the database
        List<FileDataObject> fileDataObjectList = fileDataObjectRepository.findAll();
        assertThat(fileDataObjectList).hasSize(databaseSizeBeforeUpdate);
        FileDataObject testFileDataObject = fileDataObjectList.get(fileDataObjectList.size() - 1);
        assertThat(testFileDataObject.getCmisId()).isEqualTo(UPDATED_CMIS_ID);
        assertThat(testFileDataObject.getPath()).isEqualTo(UPDATED_PATH);
    }

    @Test
    @Transactional
    public void updateNonExistingFileDataObject() throws Exception {
        int databaseSizeBeforeUpdate = fileDataObjectRepository.findAll().size();

        // Create the FileDataObject
        FileDataObjectDTO fileDataObjectDTO = fileDataObjectMapper.toDto(fileDataObject);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restFileDataObjectMockMvc.perform(put("/api/file-data-objects")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(fileDataObjectDTO)))
            .andExpect(status().isBadRequest());

        // Validate the FileDataObject in the database
        List<FileDataObject> fileDataObjectList = fileDataObjectRepository.findAll();
        assertThat(fileDataObjectList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteFileDataObject() throws Exception {
        // Initialize the database
        fileDataObjectRepository.saveAndFlush(fileDataObject);

        int databaseSizeBeforeDelete = fileDataObjectRepository.findAll().size();

        // Delete the fileDataObject
        restFileDataObjectMockMvc.perform(delete("/api/file-data-objects/{id}", fileDataObject.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<FileDataObject> fileDataObjectList = fileDataObjectRepository.findAll();
        assertThat(fileDataObjectList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(FileDataObject.class);
        FileDataObject fileDataObject1 = new FileDataObject();
        fileDataObject1.setId(1L);
        FileDataObject fileDataObject2 = new FileDataObject();
        fileDataObject2.setId(fileDataObject1.getId());
        assertThat(fileDataObject1).isEqualTo(fileDataObject2);
        fileDataObject2.setId(2L);
        assertThat(fileDataObject1).isNotEqualTo(fileDataObject2);
        fileDataObject1.setId(null);
        assertThat(fileDataObject1).isNotEqualTo(fileDataObject2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(FileDataObjectDTO.class);
        FileDataObjectDTO fileDataObjectDTO1 = new FileDataObjectDTO();
        fileDataObjectDTO1.setId(1L);
        FileDataObjectDTO fileDataObjectDTO2 = new FileDataObjectDTO();
        assertThat(fileDataObjectDTO1).isNotEqualTo(fileDataObjectDTO2);
        fileDataObjectDTO2.setId(fileDataObjectDTO1.getId());
        assertThat(fileDataObjectDTO1).isEqualTo(fileDataObjectDTO2);
        fileDataObjectDTO2.setId(2L);
        assertThat(fileDataObjectDTO1).isNotEqualTo(fileDataObjectDTO2);
        fileDataObjectDTO1.setId(null);
        assertThat(fileDataObjectDTO1).isNotEqualTo(fileDataObjectDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(fileDataObjectMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(fileDataObjectMapper.fromId(null)).isNull();
    }
}
