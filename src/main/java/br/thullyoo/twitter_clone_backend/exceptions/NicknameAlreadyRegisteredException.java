package br.thullyoo.twitter_clone_backend.exceptions;

public class NicknameAlreadyRegisteredException extends RuntimeException{
    public NicknameAlreadyRegisteredException(String message) {
        super(message);
    }
}
