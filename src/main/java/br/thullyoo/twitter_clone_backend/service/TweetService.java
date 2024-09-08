package br.thullyoo.twitter_clone_backend.service;

import br.thullyoo.twitter_clone_backend.api.dto.TweetRequest;
import br.thullyoo.twitter_clone_backend.api.dto.TweetResponse;

public interface TweetService {

    public TweetResponse registerTweet(TweetRequest request);
}
