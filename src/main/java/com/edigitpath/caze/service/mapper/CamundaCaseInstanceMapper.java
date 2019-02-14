package com.edigitpath.caze.service.mapper;

import com.edigitpath.caze.domain.*;
import com.edigitpath.caze.service.dto.CamundaCaseInstanceDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity CamundaCaseInstance and its DTO CamundaCaseInstanceDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface CamundaCaseInstanceMapper extends EntityMapper<CamundaCaseInstanceDTO, CamundaCaseInstance> {


    @Mapping(target = "cazeInstances", ignore = true)
    CamundaCaseInstance toEntity(CamundaCaseInstanceDTO camundaCaseInstanceDTO);

    default CamundaCaseInstance fromId(Long id) {
        if (id == null) {
            return null;
        }
        CamundaCaseInstance camundaCaseInstance = new CamundaCaseInstance();
        camundaCaseInstance.setId(id);
        return camundaCaseInstance;
    }
}
