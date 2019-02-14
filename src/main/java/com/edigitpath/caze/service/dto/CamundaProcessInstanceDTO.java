package com.edigitpath.caze.service.dto;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the CamundaProcessInstance entity.
 */
public class CamundaProcessInstanceDTO implements Serializable {

    private Long id;

    private String processInstanceId;

    private String processInstanceName;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProcessInstanceId() {
        return processInstanceId;
    }

    public void setProcessInstanceId(String processInstanceId) {
        this.processInstanceId = processInstanceId;
    }

    public String getProcessInstanceName() {
        return processInstanceName;
    }

    public void setProcessInstanceName(String processInstanceName) {
        this.processInstanceName = processInstanceName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        CamundaProcessInstanceDTO camundaProcessInstanceDTO = (CamundaProcessInstanceDTO) o;
        if (camundaProcessInstanceDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), camundaProcessInstanceDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "CamundaProcessInstanceDTO{" +
            "id=" + getId() +
            ", processInstanceId='" + getProcessInstanceId() + "'" +
            ", processInstanceName='" + getProcessInstanceName() + "'" +
            "}";
    }
}
