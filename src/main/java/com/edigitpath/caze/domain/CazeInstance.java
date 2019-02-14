package com.edigitpath.caze.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

import com.edigitpath.caze.domain.enumeration.CasePriority;

/**
 * A CazeInstance.
 */
@Entity
@Table(name = "caze_instance")
public class CazeInstance implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "jhi_number")
    private String number;

    @Column(name = "creator_id")
    private String creatorId;

    @Column(name = "issuer_id")
    private String issuerID;

    @Column(name = "creation_date")
    private LocalDate creationDate;

    @Column(name = "case_date")
    private LocalDate caseDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "priority")
    private CasePriority priority;

    @Column(name = "required_time")
    private Integer requiredTime;

    @Column(name = "secured")
    private Boolean secured;

    @Column(name = "cmmn_id")
    private String cmmnId;

    @Column(name = "request_id")
    private Long requestId;

    @OneToMany(mappedBy = "cazeInstance")
    private Set<CaseDataObject> caseDataObjects = new HashSet<>();
    @ManyToOne
    @JsonIgnoreProperties("cazeInstances")
    private CazeType cazeType;

    @ManyToMany
    @JoinTable(name = "caze_instance_camunda_case_instance",
               joinColumns = @JoinColumn(name = "caze_instance_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "camunda_case_instance_id", referencedColumnName = "id"))
    private Set<CamundaCaseInstance> camundaCaseInstances = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "caze_instance_camunda_process_instance",
               joinColumns = @JoinColumn(name = "caze_instance_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "camunda_process_instance_id", referencedColumnName = "id"))
    private Set<CamundaProcessInstance> camundaProcessInstances = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "caze_instance_related_caze",
               joinColumns = @JoinColumn(name = "caze_instance_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "related_caze_id", referencedColumnName = "id"))
    private Set<CazeInstance> relatedCazes = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
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

    public Long getRequestId() {
        return requestId;
    }

    public void setRequestId(Long requestId) {
        this.requestId = requestId;
    }

    public Set<CaseDataObject> getCaseDataObjects() {
        return caseDataObjects;
    }

    public void setCaseDataObjects(Set<CaseDataObject> caseDataObjects) {
        this.caseDataObjects = caseDataObjects;
    }

    public CazeType getCazeType() {
        return cazeType;
    }

    public void setCazeType(CazeType cazeType) {
        this.cazeType = cazeType;
    }

    public Set<CamundaCaseInstance> getCamundaCaseInstances() {
        return camundaCaseInstances;
    }

    public void setCamundaCaseInstances(Set<CamundaCaseInstance> camundaCaseInstances) {
        this.camundaCaseInstances = camundaCaseInstances;
    }

    public Set<CamundaProcessInstance> getCamundaProcessInstances() {
        return camundaProcessInstances;
    }

    public void setCamundaProcessInstances(Set<CamundaProcessInstance> camundaProcessInstances) {
        this.camundaProcessInstances = camundaProcessInstances;
    }

    public Set<CazeInstance> getRelatedCazes() {
        return relatedCazes;
    }

    public void setRelatedCazes(Set<CazeInstance> cazeInstances) {
        this.relatedCazes = cazeInstances;
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
        CazeInstance cazeInstance = (CazeInstance) o;
        if (cazeInstance.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), cazeInstance.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "CazeInstance{" +
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
            ", requestId=" + getRequestId() +
            "}";
    }
}
