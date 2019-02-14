package com.edigitpath.caze.service.mapper;

import com.edigitpath.caze.domain.*;
import com.edigitpath.caze.service.dto.DbDataObjectDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity DbDataObject and its DTO DbDataObjectDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface DbDataObjectMapper extends EntityMapper<DbDataObjectDTO, DbDataObject> {


    @Mapping(target = "caseDataObjects", ignore = true)
    DbDataObject toEntity(DbDataObjectDTO dbDataObjectDTO);

    default DbDataObject fromId(Long id) {
        if (id == null) {
            return null;
        }
        DbDataObject dbDataObject = new DbDataObject();
        dbDataObject.setId(id);
        return dbDataObject;
    }
}
