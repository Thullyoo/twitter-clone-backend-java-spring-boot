package br.thullyoo.twitter_clone_backend.security;

import br.thullyoo.twitter_clone_backend.domain.entity.User;
import br.thullyoo.twitter_clone_backend.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.stream.Collectors;

@Service
public class JWTService {

    @Autowired
    private JwtEncoder encoder;

    @Autowired
    private UserRepository userRepository;

    public String generateToken(Authentication authentication){
        Instant now = Instant.now();
        Long expiresAt = 4000L;

        String scopes = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(" "));

        User user = userRepository.findByNickname(authentication.getName()).get();

        var claims = JwtClaimsSet.builder()
                .issuedAt(now)
                .expiresAt(now.plusSeconds(expiresAt))
                .issuer("jwt-service")
                .subject(user.toString())
                .claim("scope", scopes)
                .build();

        return encoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
    }

}
