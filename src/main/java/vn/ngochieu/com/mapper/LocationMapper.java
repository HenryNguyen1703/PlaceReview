package vn.ngochieu.com.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import vn.ngochieu.com.entity.Locations;
import vn.ngochieu.com.payload.request.CreateLocationRequest;

@Mapper(componentModel = "spring")
public interface LocationMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "user", ignore = true)
    Locations toEntity(CreateLocationRequest createLocationRequest);
}
