package com.impuls.user_service.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

@Service
public class JwtProvider {

    static SecretKey key = Keys.hmacShaKeyFor(JwtConstant.SECRET_KEY.getBytes());

    public String generateToken(Authentication auth){
        CustomUser user = (CustomUser) auth.getPrincipal(); // Usamos al usuario personalizado

        return Jwts.builder().setIssuedAt(new Date())
                .issuer("Impuls")
                .issuedAt(new Date())
                .expiration((new Date(new Date().getTime()+86400000)))
                .subject(user.getUsername())
                .claim("userId", user.getId())
                .claim("uuid", user.getUuid())
                .claim("email",user.getUsername())
                //.claim("authorities",roles)
                .claim("authorities", populateAuthorities(user.getAuthorities()))
                .signWith(key)
                .compact();
    }

    public String getEmailFromToken(String jwt) {
        return extractClaim(jwt, claims -> claims.get("email", String.class));
    }

    public Long getUserIdFromToken(String jwt) {
        return extractClaim(jwt, claims -> claims.get("userId", Long.class));
    }

    public String getUuidFromToken(String jwt) {
        return extractClaim(jwt, claims -> claims.get("uuid", String.class));
    }

    private <T> T extractClaim(String jwt, java.util.function.Function<Claims, T> claimsResolver) {
        if (jwt == null || !jwt.startsWith("Bearer ")) {
            throw new BadCredentialsException("Invalid token format");
        }
        jwt = jwt.substring(7);
        Claims claims = Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(jwt)
                .getPayload();
        return claimsResolver.apply(claims);
    }

    private String populateAuthorities(Collection<? extends GrantedAuthority> authorities) {
        return authorities.stream()
                .map(authority ->
                        authority.getAuthority().startsWith("ROLE_") ?
                                authority.getAuthority() :
                                "ROLE_" + authority.getAuthority()
                )
                .collect(Collectors.joining(","));
    }

   /* private static String populateAuthorities(Collection<? extends GrantedAuthority> authorities) {
        Set<String> auths = new HashSet<>();
        for(GrantedAuthority authority: authorities) {
            String authorityName = authority.getAuthority();
            if(!authorityName.startsWith("ROLE_")) {
                authorityName = "ROLE_" + authorityName;
            }
            auths.add(authorityName);
        }

        return String.join(",",auths);
    }*/

}
