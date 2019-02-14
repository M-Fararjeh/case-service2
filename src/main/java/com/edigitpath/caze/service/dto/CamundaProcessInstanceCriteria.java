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
 * Criteria class for the CamundaProcessInstance entity. This class is used in CamundaProcessInstanceResource to
 * receive all the possible filtering options from the Http GET request parameters.
 * For example the following could be a valid requests:
 * <code> /camunda-process-instances?id.greaterThan=5&amp;attr1.contains=something&amp;attr2.specified=false</code>
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class CamundaProcessInstanceCriteria implements Serializable {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter processInstanceId;

    private StringFilter processInstanceName;

    private LongFilter cazeInstanceId;

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public StringFilter getProcessInstanceId() {
        return processInstanceId;
    }

    public void setProcessInstanceId(StringFilter processInstanceId) {
        this.processInstanceId = processInstanceId;
    }

    public StringFilter getProcessInstanceName() {
        return processInstanceName;
    }

    public void setProcessInstanceName(StringFilter processInstanceName) {
        this.processInstanceName = processInstanceName;
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
        final CamundaProcessInstanceCriteria that = (CamundaProcessInstanceCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(processInstanceId, that.processInstanceId) &&
            Objects.equals(processInstanceName, that.processInstanceName) &&
            Objects.equals(cazeInstanceId, that.cazeInstanceId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        processInstanceId,
        processInstanceName,
        cazeInstanceId
        );
    }

    @Override
    public String toString() {
        return "CamundaProcessInstanceCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (processInstanceId != null ? "processInstanceId=" + processInstanceId + ", " : "") +
                (processInstanceName != null ? "processInstanceName=" + processInstanceName + ", " : "") +
                (cazeInstanceId != null ? "cazeInstanceId=" + cazeInstanceId + ", " : "") +
            "}";
    }

}
