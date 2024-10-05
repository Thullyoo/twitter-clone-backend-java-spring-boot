package br.thullyoo.twitter_clone_backend.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ExceptionResponseDTO> userNotFoundException(String message){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ExceptionResponseDTO(HttpStatus.NOT_FOUND, message));
    }

    @ExceptionHandler(TweetNotFoundException.class)
    public ResponseEntity<ExceptionResponseDTO> tweetNotFoundException(String message){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ExceptionResponseDTO(HttpStatus.NOT_FOUND, message));
    }

    @ExceptionHandler(EmailAlreadyRegisteredException.class)
    public ResponseEntity<ExceptionResponseDTO> emailAlreadyRegisteredException(String message){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ExceptionResponseDTO(HttpStatus.BAD_REQUEST, message));
    }

    @ExceptionHandler(NicknameAlreadyRegisteredException.class)
    public ResponseEntity<ExceptionResponseDTO> nicknameAlreadyRegisteredException(String message){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ExceptionResponseDTO(HttpStatus.BAD_REQUEST, message));
    }
}
