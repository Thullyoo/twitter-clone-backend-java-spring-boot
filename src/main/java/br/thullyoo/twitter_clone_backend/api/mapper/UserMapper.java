package br.thullyoo.twitter_clone_backend.api.mapper;

import br.thullyoo.twitter_clone_backend.api.dto.UserRequest;
import br.thullyoo.twitter_clone_backend.api.dto.UserResponse;
import br.thullyoo.twitter_clone_backend.domain.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User toUser(UserRequest userRequest);
    UserResponse toUserResponse(User user);
}
