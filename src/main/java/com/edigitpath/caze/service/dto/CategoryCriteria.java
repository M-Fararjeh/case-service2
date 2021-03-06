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
 * Criteria class for the Category entity. This class is used in CategoryResource to
 * receive all the possible filtering options from the Http GET request parameters.
 * For example the following could be a valid requests:
 * <code> /categories?id.greaterThan=5&amp;attr1.contains=something&amp;attr2.specified=false</code>
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class CategoryCriteria implements Serializable {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter key;

    private StringFilter name;

    private LongFilter subCategoriesId;

    private LongFilter cazeTypeId;

    private LongFilter parentCategoryId;

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public StringFilter getKey() {
        return key;
    }

    public void setKey(StringFilter key) {
        this.key = key;
    }

    public StringFilter getName() {
        return name;
    }

    public void setName(StringFilter name) {
        this.name = name;
    }

    public LongFilter getSubCategoriesId() {
        return subCategoriesId;
    }

    public void setSubCategoriesId(LongFilter subCategoriesId) {
        this.subCategoriesId = subCategoriesId;
    }

    public LongFilter getCazeTypeId() {
        return cazeTypeId;
    }

    public void setCazeTypeId(LongFilter cazeTypeId) {
        this.cazeTypeId = cazeTypeId;
    }

    public LongFilter getParentCategoryId() {
        return parentCategoryId;
    }

    public void setParentCategoryId(LongFilter parentCategoryId) {
        this.parentCategoryId = parentCategoryId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final CategoryCriteria that = (CategoryCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(key, that.key) &&
            Objects.equals(name, that.name) &&
            Objects.equals(subCategoriesId, that.subCategoriesId) &&
            Objects.equals(cazeTypeId, that.cazeTypeId) &&
            Objects.equals(parentCategoryId, that.parentCategoryId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        key,
        name,
        subCategoriesId,
        cazeTypeId,
        parentCategoryId
        );
    }

    @Override
    public String toString() {
        return "CategoryCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (key != null ? "key=" + key + ", " : "") +
                (name != null ? "name=" + name + ", " : "") +
                (subCategoriesId != null ? "subCategoriesId=" + subCategoriesId + ", " : "") +
                (cazeTypeId != null ? "cazeTypeId=" + cazeTypeId + ", " : "") +
                (parentCategoryId != null ? "parentCategoryId=" + parentCategoryId + ", " : "") +
            "}";
    }

}
