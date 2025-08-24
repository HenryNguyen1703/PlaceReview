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

import java.util.List;

@Mapper(componentModel = "spring")
public interface ReviewMapper {

    // Map CreateReviewRequest -> Review
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "location", ignore = true)
    Reviews toEntity(CreateReviewRequest createReviewRequest);

    // Map Reviews to CreateReviewResponse
    CreateReviewResponse toCreateReviewResponse(Reviews review);

    // Map Locations -> LocationResponse
    @Mapping(source = "user", target = "user")
    LocationResponse toDto(Locations location);

    // Map User -> User response
    UserResponse toUserResponse(Users user);

    // Map List<Reviews> -> List<ReviewDTO> (CreateReviewResponse)
    List<CreateReviewResponse> toCreateReviewResponse(List<Reviews> reviews);
}
