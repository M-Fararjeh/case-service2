package com.edigitpath.caze.service.mapper;

import com.edigitpath.caze.domain.*;
import com.edigitpath.caze.service.dto.CamundaProcessInstanceDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity CamundaProcessInstance and its DTO CamundaProcessInstanceDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface CamundaProcessInstanceMapper extends EntityMapper<CamundaProcessInstanceDTO, CamundaProcessInstance> {


    @Mapping(target = "cazeInstances", ignore = true)
    CamundaProcessInstance toEntity(CamundaProcessInstanceDTO camundaProcessInstanceDTO);

    default CamundaProcessInstance fromId(Long id) {
        if (id == null) {
            return null;
        }
        CamundaProcessInstance camundaProcessInstance = new CamundaProcessInstance();
        camundaProcessInstance.setId(id);
        return camundaProcessInstance;
    }
}
