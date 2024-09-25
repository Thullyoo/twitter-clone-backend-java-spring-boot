package br.thullyoo.twitter_clone_backend.api.dto;

public record JwtResponse(Long expiresAt, String acessToken) {
}
