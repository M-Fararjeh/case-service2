package com.edigitpath.caze.service.dto;
import java.io.Serializable;
import java.util.Objects;
import com.edigitpath.caze.domain.enumeration.ApiMethod;

/**
 * A DTO for the ApiDataObject entity.
 */
public class ApiDataObjectDTO implements Serializable {

    private Long id;

    private ApiMethod method;

    private String url;

    private String body;


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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ApiDataObjectDTO apiDataObjectDTO = (ApiDataObjectDTO) o;
        if (apiDataObjectDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), apiDataObjectDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ApiDataObjectDTO{" +
            "id=" + getId() +
            ", method='" + getMethod() + "'" +
            ", url='" + getUrl() + "'" +
            ", body='" + getBody() + "'" +
            "}";
    }
}
