package vn.ngochieu.com.features.businesscategory.mapper;

import org.mapstruct.*;
import vn.ngochieu.com.features.businesscategory.entity.BusinessCategory;
import vn.ngochieu.com.features.businesscategory.dto.CreateCategoryRequest;
import vn.ngochieu.com.features.businesscategory.dto.UpdateCategoryRequest;
import vn.ngochieu.com.features.businesscategory.dto.CategoryResponse;

import java.util.List;


@Mapper(componentModel = "spring")
public interface CategoryMapper {

    @Mapping(target = "categoryId", ignore = true)
    @Mapping(source = "code", target = "categoryCode")
    @Mapping(source = "name", target = "categoryName")
    BusinessCategory toEntity(CreateCategoryRequest request);


    @Mapping(source = "categoryId", target = "categoryId")
    @Mapping(source = "categoryCode", target = "code")
    @Mapping(source = "categoryName", target = "name")
    CategoryResponse toDto(BusinessCategory entity);

    List<CategoryResponse> toDtoList(List<BusinessCategory> entities);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mappings({
            @Mapping(source = "code", target = "categoryCode"),
            @Mapping(source = "name", target = "categoryName")
    })
    void update(@MappingTarget BusinessCategory target, UpdateCategoryRequest request);
}