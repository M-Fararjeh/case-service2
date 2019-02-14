package com.edigitpath.caze.service.mapper;

import com.edigitpath.caze.domain.*;
import com.edigitpath.caze.service.dto.ApiHeaderDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity ApiHeader and its DTO ApiHeaderDTO.
 */
@Mapper(componentModel = "spring", uses = {ApiDataObjectMapper.class})
public interface ApiHeaderMapper extends EntityMapper<ApiHeaderDTO, ApiHeader> {

    @Mapping(source = "apiDataObject.id", target = "apiDataObjectId")
    ApiHeaderDTO toDto(ApiHeader apiHeader);

    @Mapping(source = "apiDataObjectId", target = "apiDataObject")
    ApiHeader toEntity(ApiHeaderDTO apiHeaderDTO);

    default ApiHeader fromId(Long id) {
        if (id == null) {
            return null;
        }
        ApiHeader apiHeader = new ApiHeader();
        apiHeader.setId(id);
        return apiHeader;
    }
}
