package br.thullyoo.twitter_clone_backend.api.mapper;

import br.thullyoo.twitter_clone_backend.api.dto.TweetRequest;
import br.thullyoo.twitter_clone_backend.api.dto.TweetResponse;
import br.thullyoo.twitter_clone_backend.domain.entity.Tweet;
import br.thullyoo.twitter_clone_backend.domain.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface TweetMapper {

    Tweet toTweet (TweetRequest tweetRequest);

    @Mapping(source = "user", target = "nickname", qualifiedByName = "userToNickname")
    TweetResponse toTweetResponse(Tweet tweet);

    @Named("userToNickname")
    default String userToNickname(User user) {
        return user != null ? user.getNickname() : null;
    }

}
