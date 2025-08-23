package vn.ngochieu.com.user_management.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import vn.ngochieu.com.entity.Users;
import vn.ngochieu.com.user_management.payload.request.UserSignUpRequest;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "id", ignore = true)
    Users toEntity(UserSignUpRequest userSignUpRequest);

}
