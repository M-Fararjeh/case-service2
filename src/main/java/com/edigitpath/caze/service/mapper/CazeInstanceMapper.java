package com.edigitpath.caze.service.mapper;

import com.edigitpath.caze.domain.*;
import com.edigitpath.caze.service.dto.CazeInstanceDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity CazeInstance and its DTO CazeInstanceDTO.
 */
@Mapper(componentModel = "spring", uses = {CazeTypeMapper.class, CamundaCaseInstanceMapper.class, CamundaProcessInstanceMapper.class})
public interface CazeInstanceMapper extends EntityMapper<CazeInstanceDTO, CazeInstance> {

    @Mapping(source = "cazeType.id", target = "cazeTypeId")
    CazeInstanceDTO toDto(CazeInstance cazeInstance);

    @Mapping(target = "caseDataObjects", ignore = true)
    @Mapping(source = "cazeTypeId", target = "cazeType")
    CazeInstance toEntity(CazeInstanceDTO cazeInstanceDTO);

    default CazeInstance fromId(Long id) {
        if (id == null) {
            return null;
        }
        CazeInstance cazeInstance = new CazeInstance();
        cazeInstance.setId(id);
        return cazeInstance;
    }
}
