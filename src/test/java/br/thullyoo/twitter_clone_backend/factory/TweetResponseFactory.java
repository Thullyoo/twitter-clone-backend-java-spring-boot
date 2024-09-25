package br.thullyoo.twitter_clone_backend.factory;

import br.thullyoo.twitter_clone_backend.api.dto.TweetResponse;

public class TweetResponseFactory {

    public static TweetResponse build(){
        return new TweetResponse(1, "Meu primeiro tweet", "lucas");
    }
}
