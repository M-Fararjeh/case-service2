package com.edigitpath.caze.service.dto;
import java.io.Serializable;
import java.util.Objects;
import com.edigitpath.caze.domain.enumeration.CasePriority;

/**
 * A DTO for the CazeType entity.
 */
public class CazeTypeDTO implements Serializable {

    private Long id;

    private String name;

    private CasePriority priority;

    private Integer requiredTime;

    private Boolean secured;


    private Long categoryId;

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

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        CazeTypeDTO cazeTypeDTO = (CazeTypeDTO) o;
        if (cazeTypeDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), cazeTypeDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "CazeTypeDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", priority='" + getPriority() + "'" +
            ", requiredTime=" + getRequiredTime() +
            ", secured='" + isSecured() + "'" +
            ", category=" + getCategoryId() +
            "}";
    }
}
