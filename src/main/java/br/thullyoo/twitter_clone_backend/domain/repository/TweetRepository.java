package br.thullyoo.twitter_clone_backend.domain.repository;

import br.thullyoo.twitter_clone_backend.domain.entity.Tweet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TweetRepository extends JpaRepository<Tweet, Integer> {
}
