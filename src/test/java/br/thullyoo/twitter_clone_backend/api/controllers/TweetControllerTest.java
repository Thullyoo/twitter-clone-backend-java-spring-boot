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
import org.springframework.data.domain.PageImpl;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TweetControllerTest {

    @Mock
    TweetServiceImpl tweetService;

    @InjectMocks
    TweetController tweetController;

    @Captor
    ArgumentCaptor<TweetRequest> tweetRequestCaptor;

    @Captor
    ArgumentCaptor<Integer> integerCaptor;

    @Captor
    ArgumentCaptor<UUID> uuidCaptor;

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
            verify(tweetService, times(1)).registerTweet(tweetRequest);

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
            verify(tweetService, times(1)).registerTweet(tweetRequest);

        }

        @Test
        void shouldReturnResponseBodyCorrectly() {
            //ARRANGE
            var tweetRequest = TweetRequestFactory.build();
            var tweetResponse =  TweetResponseFactory.build();
            doReturn(tweetResponse).when(tweetService).registerTweet(tweetRequest);

            //ACT
            var response =  tweetController.registerTweet(tweetRequest);

            //ASSERT
            assertNotNull(response);
            assertEquals(tweetResponse.text(), response.getBody().text());
            assertEquals(tweetResponse.id(), response.getBody().id());
            assertEquals(tweetResponse.nickname(), response.getBody().nickname());
            verify(tweetService, times(1)).registerTweet(tweetRequest);
        }
    }

    @Nested
    class getAllTweets{

        @Test
        void shouldReturnHttpOK(){
            //ARRANGE
            var tweetResponse = TweetResponseFactory.build();
            var pageResponse = new PageImpl<>(List.of(tweetResponse));
            doReturn(pageResponse).when(tweetService).getAllTweets();
            //ACT
            var response =  tweetController.getAllTweets();
            //ASSERT
            assertNotNull(response);
            assertEquals(HttpStatus.OK, response.getStatusCode());
            verify(tweetService, times(1)).getAllTweets();

        }

        @Test
        void shouldReturnResponseBodyCorrectly() {
            //ARRANGE
            var tweetResponse = TweetResponseFactory.build();
            var pageResponse = new PageImpl<>(List.of(tweetResponse));
            doReturn(pageResponse).when(tweetService).getAllTweets();
            //ACT
            var response =  tweetController.getAllTweets();
            //ASSERT
            assertEquals(response.getBody().getContent().get(0).nickname(), pageResponse.getContent().get(0).nickname());
            assertEquals(response.getBody().getContent().get(0).text(), pageResponse.getContent().get(0).text());
            assertEquals(response.getBody().getContent().get(0).id(), pageResponse.getContent().get(0).id());
            verify(tweetService, times(1)).getAllTweets();
        }

    }

    @Nested
    class deleteTweet{

        @Test
        void shouldReturnHttpNoContent() {
            //ARRANGE
            int tweet_id = 0;
            UUID user_id = UUID.fromString("550e8400-e29b-41d4-a716-446655440000");
            //ACT
            var response = tweetController.deleteTweet(tweet_id,user_id);
            //ASSERT
            assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
            verify(tweetService, times(1)).deleteTweet(tweet_id, user_id);
        }

        @Test
        void shouldPassCorrectParamtersToService() {
            //ARRANGE
            int tweet_id = 0;
            UUID user_id = UUID.fromString("550e8400-e29b-41d4-a716-446655440000");
            doNothing()
                    .when(tweetService)
                    .deleteTweet(integerCaptor.capture(), uuidCaptor.capture());

            //ACT
            var response =  tweetController.deleteTweet(tweet_id, user_id);

            //ASSERT
            assertEquals(tweet_id, integerCaptor.getValue());
            assertEquals(user_id, uuidCaptor.getValue());
            verify(tweetService, times(1)).deleteTweet(tweet_id, user_id);
        }


    }

}