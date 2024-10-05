package br.thullyoo.twitter_clone_backend.exceptions;

import org.springframework.http.HttpStatusCode;

public record ExceptionResponseDTO(HttpStatusCode statusCode, String message) {
}
