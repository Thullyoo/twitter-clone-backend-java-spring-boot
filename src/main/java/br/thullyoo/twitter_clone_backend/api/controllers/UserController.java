package br.thullyoo.twitter_clone_backend.api.controllers;

import br.thullyoo.twitter_clone_backend.api.dto.JwtResponse;
import br.thullyoo.twitter_clone_backend.api.dto.UserRequest;
import br.thullyoo.twitter_clone_backend.api.dto.UserResponse;
import br.thullyoo.twitter_clone_backend.config.SecurityConfig;
import br.thullyoo.twitter_clone_backend.domain.service.UserServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@Tag(name = "User", description = "Controller for registering and logging in Users")
@SecurityRequirement(name = SecurityConfig.SECURITY)
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    @PostMapping("/register")
    @Operation(summary = "Register User", description = "Method to register a user")
    @ApiResponse(responseCode = "201", description = "User successfully registered")
    @ApiResponse(responseCode = "400", description = "Email or Nickname already in use")
    public ResponseEntity<UserResponse> registerUser(@RequestBody UserRequest userRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.userService.registerUser(userRequest));
    }

    @PostMapping("/login")
    @Operation(summary = "User Login", description = "Method for user login")
    @ApiResponse(responseCode = "200", description = "Login successful")
    @ApiResponse(responseCode = "401", description = "Incorrect username or password")
    public ResponseEntity<JwtResponse> loginUser(Authentication authentication) {
        return ResponseEntity.status(HttpStatus.OK).body(this.userService.loginUser(authentication));
    }
}
