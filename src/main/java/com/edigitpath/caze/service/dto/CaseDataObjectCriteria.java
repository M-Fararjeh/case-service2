package com.edigitpath.caze.service.dto;

import java.io.Serializable;
import java.util.Objects;
import com.edigitpath.caze.domain.enumeration.DataObjectType;
import io.github.jhipster.service.filter.BooleanFilter;
import io.github.jhipster.service.filter.DoubleFilter;
import io.github.jhipster.service.filter.Filter;
import io.github.jhipster.service.filter.FloatFilter;
import io.github.jhipster.service.filter.IntegerFilter;
import io.github.jhipster.service.filter.LongFilter;
import io.github.jhipster.service.filter.StringFilter;

/**
 * Criteria class for the CaseDataObject entity. This class is used in CaseDataObjectResource to
 * receive all the possible filtering options from the Http GET request parameters.
 * For example the following could be a valid requests:
 * <code> /case-data-objects?id.greaterThan=5&amp;attr1.contains=something&amp;attr2.specified=false</code>
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class CaseDataObjectCriteria implements Serializable {
    /**
     * Class for filtering DataObjectType
     */
    public static class DataObjectTypeFilter extends Filter<DataObjectType> {
    }

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private DataObjectTypeFilter type;

    private StringFilter key;

    private LongFilter apiDataObjectId;

    private LongFilter dbDataObjectId;

    private LongFilter fileDataObjectId;

    private LongFilter cazeInstanceId;

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public DataObjectTypeFilter getType() {
        return type;
    }

    public void setType(DataObjectTypeFilter type) {
        this.type = type;
    }

    public StringFilter getKey() {
        return key;
    }

    public void setKey(StringFilter key) {
        this.key = key;
    }

    public LongFilter getApiDataObjectId() {
        return apiDataObjectId;
    }

    public void setApiDataObjectId(LongFilter apiDataObjectId) {
        this.apiDataObjectId = apiDataObjectId;
    }

    public LongFilter getDbDataObjectId() {
        return dbDataObjectId;
    }

    public void setDbDataObjectId(LongFilter dbDataObjectId) {
        this.dbDataObjectId = dbDataObjectId;
    }

    public LongFilter getFileDataObjectId() {
        return fileDataObjectId;
    }

    public void setFileDataObjectId(LongFilter fileDataObjectId) {
        this.fileDataObjectId = fileDataObjectId;
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
        final CaseDataObjectCriteria that = (CaseDataObjectCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(type, that.type) &&
            Objects.equals(key, that.key) &&
            Objects.equals(apiDataObjectId, that.apiDataObjectId) &&
            Objects.equals(dbDataObjectId, that.dbDataObjectId) &&
            Objects.equals(fileDataObjectId, that.fileDataObjectId) &&
            Objects.equals(cazeInstanceId, that.cazeInstanceId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        type,
        key,
        apiDataObjectId,
        dbDataObjectId,
        fileDataObjectId,
        cazeInstanceId
        );
    }

    @Override
    public String toString() {
        return "CaseDataObjectCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (type != null ? "type=" + type + ", " : "") +
                (key != null ? "key=" + key + ", " : "") +
                (apiDataObjectId != null ? "apiDataObjectId=" + apiDataObjectId + ", " : "") +
                (dbDataObjectId != null ? "dbDataObjectId=" + dbDataObjectId + ", " : "") +
                (fileDataObjectId != null ? "fileDataObjectId=" + fileDataObjectId + ", " : "") +
                (cazeInstanceId != null ? "cazeInstanceId=" + cazeInstanceId + ", " : "") +
            "}";
    }

}
