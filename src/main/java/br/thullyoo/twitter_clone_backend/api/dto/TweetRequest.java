package br.thullyoo.twitter_clone_backend.api.dto;

import java.util.UUID;

public record TweetRequest(UUID id_user, String text) {
}
