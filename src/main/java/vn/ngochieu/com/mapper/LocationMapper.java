package vn.ngochieu.com.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import vn.ngochieu.com.entity.Locations;
import vn.ngochieu.com.entity.Users;
import vn.ngochieu.com.payload.request.CreateLocationRequest;
import vn.ngochieu.com.payload.response.LocationResponse;
import vn.ngochieu.com.payload.response.UserResponse;

import java.util.List;

@Mapper(componentModel = "spring")
public interface LocationMapper {

    // Map CreateLocationRequest -> Locations
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "user", ignore = true)
    Locations toEntity(CreateLocationRequest createLocationRequest);

    // Map Locations -> LocationResponse
    @Mapping(source = "user", target = "user")
    LocationResponse toDto(Locations location);

    // Map List<Locations> -> List<LocationResponse>
    List<LocationResponse> toDtoList(List<Locations> locations);

    // Map Users -> UserResponse
    UserResponse toUserResponse(Users user);
}
