package br.thullyoo.twitter_clone_backend.api.dto;

public record UserRequest(String email, String nickname, String name, String password) {
}
