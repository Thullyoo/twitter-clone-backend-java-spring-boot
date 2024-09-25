package br.thullyoo.twitter_clone_backend.api.controllers;

import br.thullyoo.twitter_clone_backend.api.dto.TweetRequest;
import br.thullyoo.twitter_clone_backend.api.dto.TweetResponse;
import br.thullyoo.twitter_clone_backend.domain.service.TweetServiceImpl;
import br.thullyoo.twitter_clone_backend.factory.TweetRequestFactory;
import br.thullyoo.twitter_clone_backend.factory.TweetResponseFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
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

    @Captor
    ArgumentCaptor<TweetRequest> tweetRequestCaptor;

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

        @Test
        void shouldPassCorrectParamtersToService() {
            //ARRANGE
            var tweetRequest = TweetRequestFactory.build();
            doReturn(TweetResponseFactory.build())
                    .when(tweetService)
                    .registerTweet(tweetRequestCaptor.capture());

            //ACT
            var response =  tweetController.registerTweet(tweetRequest);

            //ASSERT
            assertEquals(tweetRequest.id_user(), tweetRequestCaptor.getValue().id_user());
            assertEquals(tweetRequest.text(), tweetRequestCaptor.getValue().text());
        }

    }

}