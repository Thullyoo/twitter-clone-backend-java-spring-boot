package br.thullyoo.twitter_clone_backend.api.controllers;

import br.thullyoo.twitter_clone_backend.api.dto.TweetRequest;
import br.thullyoo.twitter_clone_backend.api.dto.TweetResponse;
import br.thullyoo.twitter_clone_backend.domain.service.TweetServiceImpl;
import br.thullyoo.twitter_clone_backend.factory.TweetRequestFactory;
import br.thullyoo.twitter_clone_backend.factory.TweetResponseFactory;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
class TweetControllerTest {

    @Mock
    TweetServiceImpl tweetService;

    @InjectMocks
    TweetController tweetController;

    @Nested
    class registerTweet{

        @Test
        void shouldReturnHttpOK() {
            //ARRANGE
            TweetRequest tweetRequest = TweetRequestFactory.build();
            TweetResponse tweetResponse = TweetResponseFactory.build();
            doReturn(tweetResponse).when(tweetService).registerTweet(any(TweetRequest.class));

            //ACT
            var response = tweetController.registerTweet(tweetRequest);

            //ASSERT
            assertEquals(HttpStatus.OK, response.getStatusCode());
        }



    }

}