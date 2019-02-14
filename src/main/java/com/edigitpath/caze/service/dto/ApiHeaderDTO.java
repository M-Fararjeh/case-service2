package com.edigitpath.caze.service.dto;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the ApiHeader entity.
 */
public class ApiHeaderDTO implements Serializable {

    private Long id;

    private String key;

    private String value;


    private Long apiDataObjectId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Long getApiDataObjectId() {
        return apiDataObjectId;
    }

    public void setApiDataObjectId(Long apiDataObjectId) {
        this.apiDataObjectId = apiDataObjectId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ApiHeaderDTO apiHeaderDTO = (ApiHeaderDTO) o;
        if (apiHeaderDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), apiHeaderDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ApiHeaderDTO{" +
            "id=" + getId() +
            ", key='" + getKey() + "'" +
            ", value='" + getValue() + "'" +
            ", apiDataObject=" + getApiDataObjectId() +
            "}";
    }
}
