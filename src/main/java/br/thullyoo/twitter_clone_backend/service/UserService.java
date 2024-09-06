package br.thullyoo.twitter_clone_backend.service;

import br.thullyoo.twitter_clone_backend.api.dto.JwtResponse;
import br.thullyoo.twitter_clone_backend.api.dto.UserRequest;
import br.thullyoo.twitter_clone_backend.api.dto.UserResponse;
import org.springframework.security.core.Authentication;


public interface UserService {

    public UserResponse registerUser(UserRequest userRequest);

    public JwtResponse loginUser(Authentication authentication);
}
