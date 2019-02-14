package com.edigitpath.caze.domain;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

import com.edigitpath.caze.domain.enumeration.DataObjectType;

/**
 * A CaseDataObject.
 */
@Entity
@Table(name = "case_data_object")
public class CaseDataObject implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "jhi_type")
    private DataObjectType type;

    @Column(name = "jhi_key")
    private String key;

    @ManyToOne
    @JsonIgnoreProperties("caseDataObjects")
    private ApiDataObject apiDataObject;

    @ManyToOne
    @JsonIgnoreProperties("caseDataObjects")
    private DbDataObject dbDataObject;

    @ManyToOne
    @JsonIgnoreProperties("caseDataObjects")
    private FileDataObject fileDataObject;

    @ManyToOne
    @JsonIgnoreProperties("caseDataObjects")
    private CazeInstance cazeInstance;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public DataObjectType getType() {
        return type;
    }

    public void setType(DataObjectType type) {
        this.type = type;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public ApiDataObject getApiDataObject() {
        return apiDataObject;
    }

    public void setApiDataObject(ApiDataObject apiDataObject) {
        this.apiDataObject = apiDataObject;
    }

    public DbDataObject getDbDataObject() {
        return dbDataObject;
    }

    public void setDbDataObject(DbDataObject dbDataObject) {
        this.dbDataObject = dbDataObject;
    }

    public FileDataObject getFileDataObject() {
        return fileDataObject;
    }

    public void setFileDataObject(FileDataObject fileDataObject) {
        this.fileDataObject = fileDataObject;
    }

    public CazeInstance getCazeInstance() {
        return cazeInstance;
    }

    public void setCazeInstance(CazeInstance cazeInstance) {
        this.cazeInstance = cazeInstance;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CaseDataObject caseDataObject = (CaseDataObject) o;
        if (caseDataObject.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), caseDataObject.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "CaseDataObject{" +
            "id=" + getId() +
            ", type='" + getType() + "'" +
            ", key='" + getKey() + "'" +
            "}";
    }
}
