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

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "user", ignore = true)
    Locations toEntity(CreateLocationRequest createLocationRequest);

    @Mapping(source = "user", target = "user")
    LocationResponse toDto(Locations location);

    List<LocationResponse> toDtoList(List<Locations> locations);

    UserResponse toUserResponse(Users user);
}
