package com.edigitpath.caze.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A DbDataObject.
 */
@Entity
@Table(name = "db_data_object")
public class DbDataObject implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "table_name")
    private String tableName;

    @Column(name = "column_name")
    private String columnName;

    @Column(name = "column_value")
    private String columnValue;

    @OneToMany(mappedBy = "dbDataObject")
    private Set<CaseDataObject> caseDataObjects = new HashSet<>();
    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
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

    public Set<CaseDataObject> getCaseDataObjects() {
        return caseDataObjects;
    }

    public void setCaseDataObjects(Set<CaseDataObject> caseDataObjects) {
        this.caseDataObjects = caseDataObjects;
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
        DbDataObject dbDataObject = (DbDataObject) o;
        if (dbDataObject.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), dbDataObject.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "DbDataObject{" +
            "id=" + getId() +
            ", tableName='" + getTableName() + "'" +
            ", columnName='" + getColumnName() + "'" +
            ", columnValue='" + getColumnValue() + "'" +
            "}";
    }
}
