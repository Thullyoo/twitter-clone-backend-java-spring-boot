package br.thullyoo.twitter_clone_backend.service;

import br.thullyoo.twitter_clone_backend.api.dto.TweetRequest;
import br.thullyoo.twitter_clone_backend.api.dto.TweetResponse;
import org.springframework.data.domain.Page;

import java.util.UUID;

public interface TweetService {

    public TweetResponse registerTweet(TweetRequest request);

    public Page<TweetResponse> getAllTweets();

    public void deleteTweet(int id_tweet, UUID id_user);
}
