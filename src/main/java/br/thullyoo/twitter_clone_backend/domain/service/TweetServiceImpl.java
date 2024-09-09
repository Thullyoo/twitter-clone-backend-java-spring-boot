package br.thullyoo.twitter_clone_backend.domain.service;

import br.thullyoo.twitter_clone_backend.api.dto.TweetRequest;
import br.thullyoo.twitter_clone_backend.api.dto.TweetResponse;
import br.thullyoo.twitter_clone_backend.api.mapper.TweetMapper;
import br.thullyoo.twitter_clone_backend.domain.entity.Tweet;
import br.thullyoo.twitter_clone_backend.domain.entity.User;
import br.thullyoo.twitter_clone_backend.domain.repository.TweetRepository;
import br.thullyoo.twitter_clone_backend.domain.repository.UserRepository;
import br.thullyoo.twitter_clone_backend.service.TweetService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class TweetServiceImpl implements TweetService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TweetRepository tweetRepository;

    @Autowired
    private TweetMapper tweetMapper;


    @Override
    @Transactional
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

    @Override
    public Page<TweetResponse> getAllTweets() {
        Pageable pageable = PageRequest.of(0, 10);
        return this.tweetRepository.findAll(pageable).map(tweet -> {
            return  tweetMapper.toTweetResponse(tweet);
        });
    }

    @Override
    @Transactional
    public void deleteTweet(int tweet_id, UUID user_id) {
        Optional<User> user = userRepository.findById(user_id);
        if (user.isEmpty()){
            throw new RuntimeException("Usuário não encontrado");
        }
        Optional<Tweet> tweet = user.get().getTweets().stream()
                .filter(tweet1 -> tweet1.getId() == tweet_id)
                .findFirst();

        if (tweet.isEmpty()){
            throw new RuntimeException("Tweet inexistente ou de outra pessoa");
        }

        tweetRepository.delete(tweet.get());
    }

}
