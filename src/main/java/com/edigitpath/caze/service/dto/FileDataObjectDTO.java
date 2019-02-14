package com.edigitpath.caze.service.dto;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the FileDataObject entity.
 */
public class FileDataObjectDTO implements Serializable {

    private Long id;

    private String cmisId;

    private String path;


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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        FileDataObjectDTO fileDataObjectDTO = (FileDataObjectDTO) o;
        if (fileDataObjectDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), fileDataObjectDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "FileDataObjectDTO{" +
            "id=" + getId() +
            ", cmisId='" + getCmisId() + "'" +
            ", path='" + getPath() + "'" +
            "}";
    }
}
