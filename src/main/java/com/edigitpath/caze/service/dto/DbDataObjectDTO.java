package com.edigitpath.caze.service.dto;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the DbDataObject entity.
 */
public class DbDataObjectDTO implements Serializable {

    private Long id;

    private String tableName;

    private String columnName;

    private String columnValue;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getColumnValue() {
        return columnValue;
    }

    public void setColumnValue(String columnValue) {
        this.columnValue = columnValue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        DbDataObjectDTO dbDataObjectDTO = (DbDataObjectDTO) o;
        if (dbDataObjectDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), dbDataObjectDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "DbDataObjectDTO{" +
            "id=" + getId() +
            ", tableName='" + getTableName() + "'" +
            ", columnName='" + getColumnName() + "'" +
            ", columnValue='" + getColumnValue() + "'" +
            "}";
    }
}
