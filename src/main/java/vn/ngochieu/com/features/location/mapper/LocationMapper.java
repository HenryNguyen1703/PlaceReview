package vn.ngochieu.com.features.location.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import vn.ngochieu.com.features.location.entity.Locations;
import vn.ngochieu.com.features.user_management.entity.Users;
import vn.ngochieu.com.payload.request.CreateLocationRequest;
import vn.ngochieu.com.payload.response.LocationResponse;
import vn.ngochieu.com.payload.response.UserResponse;

import java.util.List;

@Mapper(componentModel = "spring")
public interface LocationMapper {

    // Map CreateLocationRequest -> Locations
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "category", ignore = true)
    Locations toEntity(CreateLocationRequest createLocationRequest);

    // Map Locations -> LocationResponse
    @Mapping(source = "user", target = "user")
    @Mapping(source = "category.categoryCode", target = "categoryCode")
    @Mapping(source = "category.categoryName", target = "categoryName")
    LocationResponse toDto(Locations location);

    // Map List<Locations> -> List<LocationResponse>
    List<LocationResponse> toDtoList(List<Locations> locations);

    // Map Users -> UserResponse
    UserResponse toUserResponse(Users user);
}
