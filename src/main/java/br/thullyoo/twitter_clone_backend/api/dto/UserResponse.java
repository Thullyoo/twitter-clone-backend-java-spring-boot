package br.thullyoo.twitter_clone_backend.api.dto;

import java.util.UUID;

public record UserResponse(UUID id, String email,String nickname, String name, String password) {
}
