package br.thullyoo.twitter_clone_backend.domain.repository;

import br.thullyoo.twitter_clone_backend.domain.entity.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TokenRepository extends JpaRepository<Token, Long> {
}
