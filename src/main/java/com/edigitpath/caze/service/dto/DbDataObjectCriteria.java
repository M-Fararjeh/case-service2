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
 * Criteria class for the DbDataObject entity. This class is used in DbDataObjectResource to
 * receive all the possible filtering options from the Http GET request parameters.
 * For example the following could be a valid requests:
 * <code> /db-data-objects?id.greaterThan=5&amp;attr1.contains=something&amp;attr2.specified=false</code>
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class DbDataObjectCriteria implements Serializable {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter tableName;

    private StringFilter columnName;

    private StringFilter columnValue;

    private LongFilter caseDataObjectId;

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public StringFilter getTableName() {
        return tableName;
    }

    public void setTableName(StringFilter tableName) {
        this.tableName = tableName;
    }

    public StringFilter getColumnName() {
        return columnName;
    }

    public void setColumnName(StringFilter columnName) {
        this.columnName = columnName;
    }

    public StringFilter getColumnValue() {
        return columnValue;
    }

    public void setColumnValue(StringFilter columnValue) {
        this.columnValue = columnValue;
    }

    public LongFilter getCaseDataObjectId() {
        return caseDataObjectId;
    }

    public void setCaseDataObjectId(LongFilter caseDataObjectId) {
        this.caseDataObjectId = caseDataObjectId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final DbDataObjectCriteria that = (DbDataObjectCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(tableName, that.tableName) &&
            Objects.equals(columnName, that.columnName) &&
            Objects.equals(columnValue, that.columnValue) &&
            Objects.equals(caseDataObjectId, that.caseDataObjectId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        tableName,
        columnName,
        columnValue,
        caseDataObjectId
        );
    }

    @Override
    public String toString() {
        return "DbDataObjectCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (tableName != null ? "tableName=" + tableName + ", " : "") +
                (columnName != null ? "columnName=" + columnName + ", " : "") +
                (columnValue != null ? "columnValue=" + columnValue + ", " : "") +
                (caseDataObjectId != null ? "caseDataObjectId=" + caseDataObjectId + ", " : "") +
            "}";
    }

}
