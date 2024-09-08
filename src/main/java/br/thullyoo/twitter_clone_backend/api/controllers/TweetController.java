package br.thullyoo.twitter_clone_backend.api.controllers;

import br.thullyoo.twitter_clone_backend.api.dto.TweetRequest;
import br.thullyoo.twitter_clone_backend.api.dto.TweetResponse;
import br.thullyoo.twitter_clone_backend.domain.service.TweetServiceImpl;
import br.thullyoo.twitter_clone_backend.service.TweetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/tweet")
public class TweetController {

    @Autowired
    private TweetServiceImpl tweetService;

    @PostMapping
    private ResponseEntity<TweetResponse> registerTweet(@RequestBody TweetRequest tweetRequest){
        return ResponseEntity.status(HttpStatus.OK).body(this.tweetService.registerTweet(tweetRequest));
    }
}
