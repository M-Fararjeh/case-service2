package com.edigitpath.caze.service.dto;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the CamundaCaseInstance entity.
 */
public class CamundaCaseInstanceDTO implements Serializable {

    private Long id;

    private String caseInstanceId;

    private String caseInstanceName;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCaseInstanceId() {
        return caseInstanceId;
    }

    public void setCaseInstanceId(String caseInstanceId) {
        this.caseInstanceId = caseInstanceId;
    }

    public String getCaseInstanceName() {
        return caseInstanceName;
    }

    public void setCaseInstanceName(String caseInstanceName) {
        this.caseInstanceName = caseInstanceName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        CamundaCaseInstanceDTO camundaCaseInstanceDTO = (CamundaCaseInstanceDTO) o;
        if (camundaCaseInstanceDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), camundaCaseInstanceDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "CamundaCaseInstanceDTO{" +
            "id=" + getId() +
            ", caseInstanceId='" + getCaseInstanceId() + "'" +
            ", caseInstanceName='" + getCaseInstanceName() + "'" +
            "}";
    }
}
