package com.edigitpath.caze.service.dto;
import java.io.Serializable;
import java.util.Objects;
import com.edigitpath.caze.domain.enumeration.DataObjectType;

/**
 * A DTO for the CaseDataObject entity.
 */
public class CaseDataObjectDTO implements Serializable {

    private Long id;

    private DataObjectType type;

    private String key;


    private Long apiDataObjectId;

    private Long dbDataObjectId;

    private Long fileDataObjectId;

    private Long cazeInstanceId;

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

    public Long getApiDataObjectId() {
        return apiDataObjectId;
    }

    public void setApiDataObjectId(Long apiDataObjectId) {
        this.apiDataObjectId = apiDataObjectId;
    }

    public Long getDbDataObjectId() {
        return dbDataObjectId;
    }

    public void setDbDataObjectId(Long dbDataObjectId) {
        this.dbDataObjectId = dbDataObjectId;
    }

    public Long getFileDataObjectId() {
        return fileDataObjectId;
    }

    public void setFileDataObjectId(Long fileDataObjectId) {
        this.fileDataObjectId = fileDataObjectId;
    }

    public Long getCazeInstanceId() {
        return cazeInstanceId;
    }

    public void setCazeInstanceId(Long cazeInstanceId) {
        this.cazeInstanceId = cazeInstanceId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        CaseDataObjectDTO caseDataObjectDTO = (CaseDataObjectDTO) o;
        if (caseDataObjectDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), caseDataObjectDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "CaseDataObjectDTO{" +
            "id=" + getId() +
            ", type='" + getType() + "'" +
            ", key='" + getKey() + "'" +
            ", apiDataObject=" + getApiDataObjectId() +
            ", dbDataObject=" + getDbDataObjectId() +
            ", fileDataObject=" + getFileDataObjectId() +
            ", cazeInstance=" + getCazeInstanceId() +
            "}";
    }
}
