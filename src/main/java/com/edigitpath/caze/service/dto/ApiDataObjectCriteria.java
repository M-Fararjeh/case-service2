package com.edigitpath.caze.service.dto;

import java.io.Serializable;
import java.util.Objects;
import com.edigitpath.caze.domain.enumeration.ApiMethod;
import io.github.jhipster.service.filter.BooleanFilter;
import io.github.jhipster.service.filter.DoubleFilter;
import io.github.jhipster.service.filter.Filter;
import io.github.jhipster.service.filter.FloatFilter;
import io.github.jhipster.service.filter.IntegerFilter;
import io.github.jhipster.service.filter.LongFilter;
import io.github.jhipster.service.filter.StringFilter;

/**
 * Criteria class for the ApiDataObject entity. This class is used in ApiDataObjectResource to
 * receive all the possible filtering options from the Http GET request parameters.
 * For example the following could be a valid requests:
 * <code> /api-data-objects?id.greaterThan=5&amp;attr1.contains=something&amp;attr2.specified=false</code>
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class ApiDataObjectCriteria implements Serializable {
    /**
     * Class for filtering ApiMethod
     */
    public static class ApiMethodFilter extends Filter<ApiMethod> {
    }

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private ApiMethodFilter method;

    private StringFilter url;

    private StringFilter body;

    private LongFilter apiHeaderId;

    private LongFilter caseDataObjectId;

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public ApiMethodFilter getMethod() {
        return method;
    }

    public void setMethod(ApiMethodFilter method) {
        this.method = method;
    }

    public StringFilter getUrl() {
        return url;
    }

    public void setUrl(StringFilter url) {
        this.url = url;
    }

    public StringFilter getBody() {
        return body;
    }

    public void setBody(StringFilter body) {
        this.body = body;
    }

    public LongFilter getApiHeaderId() {
        return apiHeaderId;
    }

    public void setApiHeaderId(LongFilter apiHeaderId) {
        this.apiHeaderId = apiHeaderId;
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
        final ApiDataObjectCriteria that = (ApiDataObjectCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(method, that.method) &&
            Objects.equals(url, that.url) &&
            Objects.equals(body, that.body) &&
            Objects.equals(apiHeaderId, that.apiHeaderId) &&
            Objects.equals(caseDataObjectId, that.caseDataObjectId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        method,
        url,
        body,
        apiHeaderId,
        caseDataObjectId
        );
    }

    @Override
    public String toString() {
        return "ApiDataObjectCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (method != null ? "method=" + method + ", " : "") +
                (url != null ? "url=" + url + ", " : "") +
                (body != null ? "body=" + body + ", " : "") +
                (apiHeaderId != null ? "apiHeaderId=" + apiHeaderId + ", " : "") +
                (caseDataObjectId != null ? "caseDataObjectId=" + caseDataObjectId + ", " : "") +
            "}";
    }

}
