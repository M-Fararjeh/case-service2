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

/**
 * Criteria class for the CazeType entity. This class is used in CazeTypeResource to
 * receive all the possible filtering options from the Http GET request parameters.
 * For example the following could be a valid requests:
 * <code> /caze-types?id.greaterThan=5&amp;attr1.contains=something&amp;attr2.specified=false</code>
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class CazeTypeCriteria implements Serializable {
    /**
     * Class for filtering CasePriority
     */
    public static class CasePriorityFilter extends Filter<CasePriority> {
    }

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter name;

    private CasePriorityFilter priority;

    private IntegerFilter requiredTime;

    private BooleanFilter secured;

    private LongFilter categoryId;

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

    public LongFilter getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(LongFilter categoryId) {
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
        final CazeTypeCriteria that = (CazeTypeCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(name, that.name) &&
            Objects.equals(priority, that.priority) &&
            Objects.equals(requiredTime, that.requiredTime) &&
            Objects.equals(secured, that.secured) &&
            Objects.equals(categoryId, that.categoryId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        name,
        priority,
        requiredTime,
        secured,
        categoryId
        );
    }

    @Override
    public String toString() {
        return "CazeTypeCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (name != null ? "name=" + name + ", " : "") +
                (priority != null ? "priority=" + priority + ", " : "") +
                (requiredTime != null ? "requiredTime=" + requiredTime + ", " : "") +
                (secured != null ? "secured=" + secured + ", " : "") +
                (categoryId != null ? "categoryId=" + categoryId + ", " : "") +
            "}";
    }

}
