package br.thullyoo.twitter_clone_backend.factory;

import br.thullyoo.twitter_clone_backend.api.dto.TweetRequest;

import java.util.UUID;

public class TweetRequestFactory {

    public static TweetRequest build(){
        return new TweetRequest(UUID.fromString("550e8400-e29b-41d4-a716-446655440000"), "Meu primeiro tweet");
    }

}
