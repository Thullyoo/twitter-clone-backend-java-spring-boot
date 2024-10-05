package br.thullyoo.twitter_clone_backend.api.controllers;

import br.thullyoo.twitter_clone_backend.api.dto.TweetRequest;
import br.thullyoo.twitter_clone_backend.api.dto.TweetResponse;
import br.thullyoo.twitter_clone_backend.domain.service.TweetServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/tweet")
@CrossOrigin(origins = "http://localhost:4200")
@Tag(name = "Tweet", description = "Controller for registering, deleting, and retrieving tweets")
public class TweetController {

    @Autowired
    private TweetServiceImpl tweetService;

    @PostMapping
    @PreAuthorize("hasAuthority('SCOPE_common')")
    @Operation(summary = "Save user tweet", description = "Method to register a user's tweet")
    @ApiResponse(responseCode = "200", description = "Tweet successfully registered")
    @ApiResponse(responseCode = "401", description = "Permission error")
    @ApiResponse(responseCode = "403", description = "Authentication error")
    @ApiResponse(responseCode = "404", description = "User not found")
    public ResponseEntity<TweetResponse> registerTweet(@RequestBody TweetRequest tweetRequest) {
        return ResponseEntity.status(HttpStatus.OK).body(this.tweetService.registerTweet(tweetRequest));
    }

    @GetMapping
    @PreAuthorize("hasAuthority('SCOPE_common') or hasAuthority('SCOPE_admin')")
    @Operation(summary = "Retrieve all tweets", description = "Method to retrieve all tweets")
    @ApiResponse(responseCode = "200", description = "Tweets successfully retrieved")
    @ApiResponse(responseCode = "401", description = "Permission error")
    @ApiResponse(responseCode = "403", description = "Authentication error")
    @ApiResponse(responseCode = "404", description = "User not found")
    public ResponseEntity<Page<TweetResponse>> getAllTweets() {
        return ResponseEntity.status(HttpStatus.OK).body(this.tweetService.getAllTweets());
    }

    @DeleteMapping("/{tweet_id}/{user_id}")
    @PreAuthorize("hasAuthority('SCOPE_common') or hasAuthority('SCOPE_admin')")
    @Operation(summary = "Delete user tweet", description = "Method to delete a user's tweet")
    @ApiResponse(responseCode = "204", description = "Tweet successfully deleted")
    @ApiResponse(responseCode = "401", description = "Permission error")
    @ApiResponse(responseCode = "403", description = "Authentication error")
    @ApiResponse(responseCode = "404", description = "Tweet or user not found")
    public ResponseEntity<Void> deleteTweet(@PathVariable int tweet_id, @PathVariable UUID user_id) {
        this.tweetService.deleteTweet(tweet_id, user_id);
        return ResponseEntity.noContent().build();
    }
}

