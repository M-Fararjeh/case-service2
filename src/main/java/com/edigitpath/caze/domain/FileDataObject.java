package com.edigitpath.caze.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A FileDataObject.
 */
@Entity
@Table(name = "file_data_object")
public class FileDataObject implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "cmis_id")
    private String cmisId;

    @Column(name = "path")
    private String path;

    @OneToMany(mappedBy = "fileDataObject")
    private Set<CaseDataObject> caseDataObjects = new HashSet<>();
    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCmisId() {
        return cmisId;
    }

    public void setCmisId(String cmisId) {
        this.cmisId = cmisId;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Set<CaseDataObject> getCaseDataObjects() {
        return caseDataObjects;
    }

    public void setCaseDataObjects(Set<CaseDataObject> caseDataObjects) {
        this.caseDataObjects = caseDataObjects;
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
        FileDataObject fileDataObject = (FileDataObject) o;
        if (fileDataObject.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), fileDataObject.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "FileDataObject{" +
            "id=" + getId() +
            ", cmisId='" + getCmisId() + "'" +
            ", path='" + getPath() + "'" +
            "}";
    }
}
