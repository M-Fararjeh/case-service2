package com.edigitpath.caze.service.dto;
import java.time.LocalDate;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;
import com.edigitpath.caze.domain.enumeration.CasePriority;

/**
 * A DTO for the CazeInstance entity.
 */
public class CazeInstanceDTO implements Serializable {

    private Long id;

    private String name;

    private String description;

    private String number;

    private String creatorId;

    private String issuerID;

    private LocalDate creationDate;

    private LocalDate caseDate;

    private CasePriority priority;

    private Integer requiredTime;

    private Boolean secured;

    private String cmmnId;


    private Long cazeTypeId;

    private Set<CamundaCaseInstanceDTO> camundaCaseInstances = new HashSet<>();

    private Set<CamundaProcessInstanceDTO> camundaProcessInstances = new HashSet<>();

    private Set<CazeInstanceDTO> relatedCazes = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(String creatorId) {
        this.creatorId = creatorId;
    }

    public String getIssuerID() {
        return issuerID;
    }

    public void setIssuerID(String issuerID) {
        this.issuerID = issuerID;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public LocalDate getCaseDate() {
        return caseDate;
    }

    public void setCaseDate(LocalDate caseDate) {
        this.caseDate = caseDate;
    }

    public CasePriority getPriority() {
        return priority;
    }

    public void setPriority(CasePriority priority) {
        this.priority = priority;
    }

    public Integer getRequiredTime() {
        return requiredTime;
    }

    public void setRequiredTime(Integer requiredTime) {
        this.requiredTime = requiredTime;
    }

    public Boolean isSecured() {
        return secured;
    }

    public void setSecured(Boolean secured) {
        this.secured = secured;
    }

    public String getCmmnId() {
        return cmmnId;
    }

    public void setCmmnId(String cmmnId) {
        this.cmmnId = cmmnId;
    }

    public Long getCazeTypeId() {
        return cazeTypeId;
    }

    public void setCazeTypeId(Long cazeTypeId) {
        this.cazeTypeId = cazeTypeId;
    }

    public Set<CamundaCaseInstanceDTO> getCamundaCaseInstances() {
        return camundaCaseInstances;
    }

    public void setCamundaCaseInstances(Set<CamundaCaseInstanceDTO> camundaCaseInstances) {
        this.camundaCaseInstances = camundaCaseInstances;
    }

    public Set<CamundaProcessInstanceDTO> getCamundaProcessInstances() {
        return camundaProcessInstances;
    }

    public void setCamundaProcessInstances(Set<CamundaProcessInstanceDTO> camundaProcessInstances) {
        this.camundaProcessInstances = camundaProcessInstances;
    }

    public Set<CazeInstanceDTO> getRelatedCazes() {
        return relatedCazes;
    }

    public void setRelatedCazes(Set<CazeInstanceDTO> cazeInstances) {
        this.relatedCazes = cazeInstances;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        CazeInstanceDTO cazeInstanceDTO = (CazeInstanceDTO) o;
        if (cazeInstanceDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), cazeInstanceDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "CazeInstanceDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", description='" + getDescription() + "'" +
            ", number='" + getNumber() + "'" +
            ", creatorId='" + getCreatorId() + "'" +
            ", issuerID='" + getIssuerID() + "'" +
            ", creationDate='" + getCreationDate() + "'" +
            ", caseDate='" + getCaseDate() + "'" +
            ", priority='" + getPriority() + "'" +
            ", requiredTime=" + getRequiredTime() +
            ", secured='" + isSecured() + "'" +
            ", cmmnId='" + getCmmnId() + "'" +
            ", cazeType=" + getCazeTypeId() +
            "}";
    }
}
