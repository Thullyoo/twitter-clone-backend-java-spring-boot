package br.thullyoo.twitter_clone_backend.api.controllers;

import br.thullyoo.twitter_clone_backend.api.dto.TweetRequest;
import br.thullyoo.twitter_clone_backend.api.dto.TweetResponse;
import br.thullyoo.twitter_clone_backend.domain.service.TweetServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController()
@RequestMapping("/tweet")
@CrossOrigin(origins = "http://localhost:4200")
public class TweetController {

    @Autowired
    private TweetServiceImpl tweetService;

    @PostMapping
    @PreAuthorize("hasAuthority('SCOPE_common')")
    public ResponseEntity<TweetResponse> registerTweet(@RequestBody TweetRequest tweetRequest){
        return ResponseEntity.status(HttpStatus.OK).body(this.tweetService.registerTweet(tweetRequest));
    }

    @GetMapping
    @PreAuthorize("hasAuthority('SCOPE_common') or hasAuthority('SCOPE_admin')")
    public ResponseEntity<Page<TweetResponse>> getAllTweets(){
        return ResponseEntity.status(HttpStatus.OK).body(this.tweetService.getAllTweets());
    }

    @DeleteMapping("/{tweet_id}/{user_id}")
    @PreAuthorize("hasAuthority('SCOPE_common') or hasAuthority('SCOPE_admin')")
    public ResponseEntity deleteTweet(@PathVariable int tweet_id, @PathVariable UUID user_id){
        this.tweetService.deleteTweet(tweet_id, user_id);
        return ResponseEntity.noContent().build();
    }
}
