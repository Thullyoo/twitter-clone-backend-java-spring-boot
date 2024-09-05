package br.thullyoo.twitter_clone_backend.service;

import br.thullyoo.twitter_clone_backend.api.dto.UserRequest;
import br.thullyoo.twitter_clone_backend.api.dto.UserResponse;


public interface UserService {

    public UserResponse registerUser(UserRequest userRequest);
}
