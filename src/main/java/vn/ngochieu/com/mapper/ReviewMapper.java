package vn.ngochieu.com.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import vn.ngochieu.com.entity.Locations;
import vn.ngochieu.com.entity.Reviews;
import vn.ngochieu.com.entity.Users;
import vn.ngochieu.com.payload.request.CreateReviewRequest;
import vn.ngochieu.com.payload.response.CreateReviewResponse;
import vn.ngochieu.com.payload.response.LocationResponse;
import vn.ngochieu.com.payload.response.UserResponse;

@Mapper(componentModel = "spring")
public interface ReviewMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "location", ignore = true)
    Reviews toEntity(CreateReviewRequest createReviewRequest);

    CreateReviewResponse toCreateReviewResponse(Reviews review);

    @Mapping(source = "user", target = "user")
    LocationResponse toDto(Locations location);

    UserResponse toUserResponse(Users user);

}
