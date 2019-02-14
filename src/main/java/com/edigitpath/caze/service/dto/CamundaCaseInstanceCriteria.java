package com.edigitpath.caze.service.dto;

import java.io.Serializable;
import java.util.Objects;
import io.github.jhipster.service.filter.BooleanFilter;
import io.github.jhipster.service.filter.DoubleFilter;
import io.github.jhipster.service.filter.Filter;
import io.github.jhipster.service.filter.FloatFilter;
import io.github.jhipster.service.filter.IntegerFilter;
import io.github.jhipster.service.filter.LongFilter;
import io.github.jhipster.service.filter.StringFilter;

/**
 * Criteria class for the CamundaCaseInstance entity. This class is used in CamundaCaseInstanceResource to
 * receive all the possible filtering options from the Http GET request parameters.
 * For example the following could be a valid requests:
 * <code> /camunda-case-instances?id.greaterThan=5&amp;attr1.contains=something&amp;attr2.specified=false</code>
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class CamundaCaseInstanceCriteria implements Serializable {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter caseInstanceId;

    private StringFilter caseInstanceName;

    private LongFilter cazeInstanceId;

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public StringFilter getCaseInstanceId() {
        return caseInstanceId;
    }

    public void setCaseInstanceId(StringFilter caseInstanceId) {
        this.caseInstanceId = caseInstanceId;
    }

    public StringFilter getCaseInstanceName() {
        return caseInstanceName;
    }

    public void setCaseInstanceName(StringFilter caseInstanceName) {
        this.caseInstanceName = caseInstanceName;
    }

    public LongFilter getCazeInstanceId() {
        return cazeInstanceId;
    }

    public void setCazeInstanceId(LongFilter cazeInstanceId) {
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
        final CamundaCaseInstanceCriteria that = (CamundaCaseInstanceCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(caseInstanceId, that.caseInstanceId) &&
            Objects.equals(caseInstanceName, that.caseInstanceName) &&
            Objects.equals(cazeInstanceId, that.cazeInstanceId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        caseInstanceId,
        caseInstanceName,
        cazeInstanceId
        );
    }

    @Override
    public String toString() {
        return "CamundaCaseInstanceCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (caseInstanceId != null ? "caseInstanceId=" + caseInstanceId + ", " : "") +
                (caseInstanceName != null ? "caseInstanceName=" + caseInstanceName + ", " : "") +
                (cazeInstanceId != null ? "cazeInstanceId=" + cazeInstanceId + ", " : "") +
            "}";
    }

}
