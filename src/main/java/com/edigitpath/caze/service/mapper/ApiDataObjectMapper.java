package com.edigitpath.caze.service.mapper;

import com.edigitpath.caze.domain.*;
import com.edigitpath.caze.service.dto.ApiDataObjectDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity ApiDataObject and its DTO ApiDataObjectDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ApiDataObjectMapper extends EntityMapper<ApiDataObjectDTO, ApiDataObject> {


    @Mapping(target = "apiHeaders", ignore = true)
    @Mapping(target = "caseDataObjects", ignore = true)
    ApiDataObject toEntity(ApiDataObjectDTO apiDataObjectDTO);

    default ApiDataObject fromId(Long id) {
        if (id == null) {
            return null;
        }
        ApiDataObject apiDataObject = new ApiDataObject();
        apiDataObject.setId(id);
        return apiDataObject;
    }
}
