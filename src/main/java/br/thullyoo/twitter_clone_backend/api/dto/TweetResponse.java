package br.thullyoo.twitter_clone_backend.api.dto;

import java.util.UUID;

public record TweetResponse(int id, String text, String nickname) {
}
