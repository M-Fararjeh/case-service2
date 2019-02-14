package com.edigitpath.caze.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

import com.edigitpath.caze.domain.enumeration.ApiMethod;

/**
 * A ApiDataObject.
 */
@Entity
@Table(name = "api_data_object")
public class ApiDataObject implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "method")
    private ApiMethod method;

    @Column(name = "url")
    private String url;

    @Column(name = "jhi_body")
    private String body;

    @OneToMany(mappedBy = "apiDataObject")
    private Set<ApiHeader> apiHeaders = new HashSet<>();
    @OneToMany(mappedBy = "apiDataObject")
    private Set<CaseDataObject> caseDataObjects = new HashSet<>();
    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ApiMethod getMethod() {
        return method;
    }

    public void setMethod(ApiMethod method) {
        this.method = method;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Set<ApiHeader> getApiHeaders() {
        return apiHeaders;
    }

    public void setApiHeaders(Set<ApiHeader> apiHeaders) {
        this.apiHeaders = apiHeaders;
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
        ApiDataObject apiDataObject = (ApiDataObject) o;
        if (apiDataObject.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), apiDataObject.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ApiDataObject{" +
            "id=" + getId() +
            ", method='" + getMethod() + "'" +
            ", url='" + getUrl() + "'" +
            ", body='" + getBody() + "'" +
            "}";
    }
}
