package fr.fms.api_rest_spring.service;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwsHeader;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
public class JWTService {

    private final JwtEncoder encoder;

    public JWTService(JwtEncoder encoder) {
        this.encoder = encoder;
    }

    public String createToken(Authentication auth) {
        Instant now = Instant.now();
        List<String> roles = auth.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList();
        var claims = JwtClaimsSet.builder()
                .issuer("http://localhost:8080")
                .subject(auth.getName())
                .audience(List.of("training-api"))
                .issuedAt(now)
                .expiresAt(now.plusSeconds(6000))
                .claim("roles", roles)
                .build();

        var header = JwsHeader.with(MacAlgorithm.HS256).build();

        return encoder.encode(JwtEncoderParameters.from(header, claims)).getTokenValue();
    }
}
