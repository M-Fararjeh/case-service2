package com.edigitpath.caze.domain;



import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

import com.edigitpath.caze.domain.enumeration.CasePriority;

/**
 * A CazeType.
 */
@Entity
@Table(name = "caze_type")
public class CazeType implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "priority")
    private CasePriority priority;

    @Column(name = "required_time")
    private Integer requiredTime;

    @Column(name = "secured")
    private Boolean secured;

    @OneToOne
    @JoinColumn(unique = true)
    private Category category;

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

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
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
        CazeType cazeType = (CazeType) o;
        if (cazeType.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), cazeType.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "CazeType{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", priority='" + getPriority() + "'" +
            ", requiredTime=" + getRequiredTime() +
            ", secured='" + isSecured() + "'" +
            "}";
    }
}
