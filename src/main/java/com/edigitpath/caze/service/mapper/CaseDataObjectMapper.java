package com.edigitpath.caze.service.mapper;

import com.edigitpath.caze.domain.*;
import com.edigitpath.caze.service.dto.CaseDataObjectDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity CaseDataObject and its DTO CaseDataObjectDTO.
 */
@Mapper(componentModel = "spring", uses = {ApiDataObjectMapper.class, DbDataObjectMapper.class, FileDataObjectMapper.class, CazeInstanceMapper.class})
public interface CaseDataObjectMapper extends EntityMapper<CaseDataObjectDTO, CaseDataObject> {

    @Mapping(source = "apiDataObject.id", target = "apiDataObjectId")
    @Mapping(source = "dbDataObject.id", target = "dbDataObjectId")
    @Mapping(source = "fileDataObject.id", target = "fileDataObjectId")
    @Mapping(source = "cazeInstance.id", target = "cazeInstanceId")
    CaseDataObjectDTO toDto(CaseDataObject caseDataObject);

    @Mapping(source = "apiDataObjectId", target = "apiDataObject")
    @Mapping(source = "dbDataObjectId", target = "dbDataObject")
    @Mapping(source = "fileDataObjectId", target = "fileDataObject")
    @Mapping(source = "cazeInstanceId", target = "cazeInstance")
    CaseDataObject toEntity(CaseDataObjectDTO caseDataObjectDTO);

    default CaseDataObject fromId(Long id) {
        if (id == null) {
            return null;
        }
        CaseDataObject caseDataObject = new CaseDataObject();
        caseDataObject.setId(id);
        return caseDataObject;
    }
}
