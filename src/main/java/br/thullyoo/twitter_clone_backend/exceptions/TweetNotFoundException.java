package br.thullyoo.twitter_clone_backend.exceptions;

public class TweetNotFoundException extends RuntimeException{
    public TweetNotFoundException(String message) {
        super(message);
    }
}
