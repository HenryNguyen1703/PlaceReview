package vn.ngochieu.com.features.user_management.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import vn.ngochieu.com.features.user_management.entity.Users;
import vn.ngochieu.com.features.user_management.payload.request.UserSignUpRequest;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "id", ignore = true)
    Users toEntity(UserSignUpRequest userSignUpRequest);

}
