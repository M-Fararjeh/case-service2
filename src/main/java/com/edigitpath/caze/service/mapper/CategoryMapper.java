package com.edigitpath.caze.service.mapper;

import com.edigitpath.caze.domain.*;
import com.edigitpath.caze.service.dto.CategoryDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Category and its DTO CategoryDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface CategoryMapper extends EntityMapper<CategoryDTO, Category> {

    @Mapping(source = "parentCategory.id", target = "parentCategoryId")
    CategoryDTO toDto(Category category);

    @Mapping(target = "subCategories", ignore = true)
    @Mapping(target = "cazeType", ignore = true)
    @Mapping(source = "parentCategoryId", target = "parentCategory")
    Category toEntity(CategoryDTO categoryDTO);

    default Category fromId(Long id) {
        if (id == null) {
            return null;
        }
        Category category = new Category();
        category.setId(id);
        return category;
    }
}
