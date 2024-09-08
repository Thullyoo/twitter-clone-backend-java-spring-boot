package br.thullyoo.twitter_clone_backend.domain.service;

import br.thullyoo.twitter_clone_backend.api.dto.TweetRequest;
import br.thullyoo.twitter_clone_backend.api.dto.TweetResponse;
import br.thullyoo.twitter_clone_backend.api.mapper.TweetMapper;
import br.thullyoo.twitter_clone_backend.domain.entity.Tweet;
import br.thullyoo.twitter_clone_backend.domain.entity.User;
import br.thullyoo.twitter_clone_backend.domain.repository.TweetRepository;
import br.thullyoo.twitter_clone_backend.domain.repository.UserRepository;
import br.thullyoo.twitter_clone_backend.service.TweetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TweetServiceImpl implements TweetService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TweetRepository tweetRepository;

    @Autowired
    private TweetMapper tweetMapper;


    @Override
    public TweetResponse registerTweet(TweetRequest request) {

        Optional<User> user = this.userRepository.findById(request.id_user());
        if (user.isEmpty()){
            throw new RuntimeException("Usuário não encontrado");
        }

        Tweet tweet = tweetMapper.toTweet(request);
        tweet.setUser(user.get());

        this.tweetRepository.save(tweet);

        return tweetMapper.toTweetResponse(tweet);

    }
}
