package br.thullyoo.twitter_clone_backend.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "tokens")
public class Token {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id", nullable = false)
    private UUID userId;

    @Getter
    @Column(name = "access_token", nullable = false)
    private String accessToken;

    @Setter
    @Column(name = "refresh_token", nullable = false)
    private String refreshToken;

    @Column(name = "expiration", nullable = false)
    private LocalDateTime expiration;
}

