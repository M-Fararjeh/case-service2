package com.edigitpath.caze.service.mapper;

import com.edigitpath.caze.domain.*;
import com.edigitpath.caze.service.dto.FileDataObjectDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity FileDataObject and its DTO FileDataObjectDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface FileDataObjectMapper extends EntityMapper<FileDataObjectDTO, FileDataObject> {


    @Mapping(target = "caseDataObjects", ignore = true)
    FileDataObject toEntity(FileDataObjectDTO fileDataObjectDTO);

    default FileDataObject fromId(Long id) {
        if (id == null) {
            return null;
        }
        FileDataObject fileDataObject = new FileDataObject();
        fileDataObject.setId(id);
        return fileDataObject;
    }
}
