package com.edigitpath.caze.service.dto;

import java.io.Serializable;
import java.util.Objects;
import com.edigitpath.caze.domain.enumeration.CasePriority;
import io.github.jhipster.service.filter.BooleanFilter;
import io.github.jhipster.service.filter.DoubleFilter;
import io.github.jhipster.service.filter.Filter;
import io.github.jhipster.service.filter.FloatFilter;
import io.github.jhipster.service.filter.IntegerFilter;
import io.github.jhipster.service.filter.LongFilter;
import io.github.jhipster.service.filter.StringFilter;
import io.github.jhipster.service.filter.LocalDateFilter;

/**
 * Criteria class for the CazeInstance entity. This class is used in CazeInstanceResource to
 * receive all the possible filtering options from the Http GET request parameters.
 * For example the following could be a valid requests:
 * <code> /caze-instances?id.greaterThan=5&amp;attr1.contains=something&amp;attr2.specified=false</code>
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class CazeInstanceCriteria implements Serializable {
    /**
     * Class for filtering CasePriority
     */
    public static class CasePriorityFilter extends Filter<CasePriority> {
    }

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter name;

    private StringFilter description;

    private StringFilter number;

    private StringFilter creatorId;

    private StringFilter issuerID;

    private LocalDateFilter creationDate;

    private LocalDateFilter caseDate;

    private CasePriorityFilter priority;

    private IntegerFilter requiredTime;

    private BooleanFilter secured;

    private StringFilter cmmnId;

    private LongFilter requestId;

    private LongFilter caseDataObjectId;

    private LongFilter cazeTypeId;

    private LongFilter camundaCaseInstanceId;

    private LongFilter camundaProcessInstanceId;

    private LongFilter relatedCazeId;

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public StringFilter getName() {
        return name;
    }

    public void setName(StringFilter name) {
        this.name = name;
    }

    public StringFilter getDescription() {
        return description;
    }

    public void setDescription(StringFilter description) {
        this.description = description;
    }

    public StringFilter getNumber() {
        return number;
    }

    public void setNumber(StringFilter number) {
        this.number = number;
    }

    public StringFilter getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(StringFilter creatorId) {
        this.creatorId = creatorId;
    }

    public StringFilter getIssuerID() {
        return issuerID;
    }

    public void setIssuerID(StringFilter issuerID) {
        this.issuerID = issuerID;
    }

    public LocalDateFilter getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateFilter creationDate) {
        this.creationDate = creationDate;
    }

    public LocalDateFilter getCaseDate() {
        return caseDate;
    }

    public void setCaseDate(LocalDateFilter caseDate) {
        this.caseDate = caseDate;
    }

    public CasePriorityFilter getPriority() {
        return priority;
    }

    public void setPriority(CasePriorityFilter priority) {
        this.priority = priority;
    }

    public IntegerFilter getRequiredTime() {
        return requiredTime;
    }

    public void setRequiredTime(IntegerFilter requiredTime) {
        this.requiredTime = requiredTime;
    }

    public BooleanFilter getSecured() {
        return secured;
    }

    public void setSecured(BooleanFilter secured) {
        this.secured = secured;
    }

    public StringFilter getCmmnId() {
        return cmmnId;
    }

    public void setCmmnId(StringFilter cmmnId) {
        this.cmmnId = cmmnId;
    }

    public LongFilter getRequestId() {
        return requestId;
    }

    public void setRequestId(LongFilter requestId) {
        this.requestId = requestId;
    }

    public LongFilter getCaseDataObjectId() {
        return caseDataObjectId;
    }

    public void setCaseDataObjectId(LongFilter caseDataObjectId) {
        this.caseDataObjectId = caseDataObjectId;
    }

    public LongFilter getCazeTypeId() {
        return cazeTypeId;
    }

    public void setCazeTypeId(LongFilter cazeTypeId) {
        this.cazeTypeId = cazeTypeId;
    }

    public LongFilter getCamundaCaseInstanceId() {
        return camundaCaseInstanceId;
    }

    public void setCamundaCaseInstanceId(LongFilter camundaCaseInstanceId) {
        this.camundaCaseInstanceId = camundaCaseInstanceId;
    }

    public LongFilter getCamundaProcessInstanceId() {
        return camundaProcessInstanceId;
    }

    public void setCamundaProcessInstanceId(LongFilter camundaProcessInstanceId) {
        this.camundaProcessInstanceId = camundaProcessInstanceId;
    }

    public LongFilter getRelatedCazeId() {
        return relatedCazeId;
    }

    public void setRelatedCazeId(LongFilter relatedCazeId) {
        this.relatedCazeId = relatedCazeId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final CazeInstanceCriteria that = (CazeInstanceCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(name, that.name) &&
            Objects.equals(description, that.description) &&
            Objects.equals(number, that.number) &&
            Objects.equals(creatorId, that.creatorId) &&
            Objects.equals(issuerID, that.issuerID) &&
            Objects.equals(creationDate, that.creationDate) &&
            Objects.equals(caseDate, that.caseDate) &&
            Objects.equals(priority, that.priority) &&
            Objects.equals(requiredTime, that.requiredTime) &&
            Objects.equals(secured, that.secured) &&
            Objects.equals(cmmnId, that.cmmnId) &&
            Objects.equals(requestId, that.requestId) &&
            Objects.equals(caseDataObjectId, that.caseDataObjectId) &&
            Objects.equals(cazeTypeId, that.cazeTypeId) &&
            Objects.equals(camundaCaseInstanceId, that.camundaCaseInstanceId) &&
            Objects.equals(camundaProcessInstanceId, that.camundaProcessInstanceId) &&
            Objects.equals(relatedCazeId, that.relatedCazeId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        name,
        description,
        number,
        creatorId,
        issuerID,
        creationDate,
        caseDate,
        priority,
        requiredTime,
        secured,
        cmmnId,
        requestId,
        caseDataObjectId,
        cazeTypeId,
        camundaCaseInstanceId,
        camundaProcessInstanceId,
        relatedCazeId
        );
    }

    @Override
    public String toString() {
        return "CazeInstanceCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (name != null ? "name=" + name + ", " : "") +
                (description != null ? "description=" + description + ", " : "") +
                (number != null ? "number=" + number + ", " : "") +
                (creatorId != null ? "creatorId=" + creatorId + ", " : "") +
                (issuerID != null ? "issuerID=" + issuerID + ", " : "") +
                (creationDate != null ? "creationDate=" + creationDate + ", " : "") +
                (caseDate != null ? "caseDate=" + caseDate + ", " : "") +
                (priority != null ? "priority=" + priority + ", " : "") +
                (requiredTime != null ? "requiredTime=" + requiredTime + ", " : "") +
                (secured != null ? "secured=" + secured + ", " : "") +
                (cmmnId != null ? "cmmnId=" + cmmnId + ", " : "") +
                (requestId != null ? "requestId=" + requestId + ", " : "") +
                (caseDataObjectId != null ? "caseDataObjectId=" + caseDataObjectId + ", " : "") +
                (cazeTypeId != null ? "cazeTypeId=" + cazeTypeId + ", " : "") +
                (camundaCaseInstanceId != null ? "camundaCaseInstanceId=" + camundaCaseInstanceId + ", " : "") +
                (camundaProcessInstanceId != null ? "camundaProcessInstanceId=" + camundaProcessInstanceId + ", " : "") +
                (relatedCazeId != null ? "relatedCazeId=" + relatedCazeId + ", " : "") +
            "}";
    }

}
