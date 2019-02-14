package com.edigitpath.caze.service.mapper;

import com.edigitpath.caze.domain.*;
import com.edigitpath.caze.service.dto.CazeTypeDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity CazeType and its DTO CazeTypeDTO.
 */
@Mapper(componentModel = "spring", uses = {CategoryMapper.class})
public interface CazeTypeMapper extends EntityMapper<CazeTypeDTO, CazeType> {

    @Mapping(source = "category.id", target = "categoryId")
    CazeTypeDTO toDto(CazeType cazeType);

    @Mapping(source = "categoryId", target = "category")
    CazeType toEntity(CazeTypeDTO cazeTypeDTO);

    default CazeType fromId(Long id) {
        if (id == null) {
            return null;
        }
        CazeType cazeType = new CazeType();
        cazeType.setId(id);
        return cazeType;
    }
}
