package com.impuls.user_service.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Service
public class JwtProvider {

    static SecretKey key = Keys.hmacShaKeyFor(JwtConstant.SECRET_KEY.getBytes());

    public String generateToken(Authentication auth){
        Collection<? extends GrantedAuthority> authorities = auth.getAuthorities();
        String roles = populateAuthorities(authorities);

        return Jwts.builder().setIssuedAt(new Date())
                .setExpiration((new Date(new Date().getTime()+86400000)))
                .claim("email",auth.getName())
                .claim("authorities",roles)
                .signWith(key)
                .compact();
    }

    public String getEmailFromJwtToken(String jwt){
        jwt = jwt.substring(7);
        Claims claims = Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(jwt)
                .getPayload();
        return String.valueOf(claims.get("email"));
    }

    public static String populateAuthorities(Collection<? extends GrantedAuthority> authorities) {
        Set<String> auths = new HashSet<>();
        for(GrantedAuthority authority: authorities) {
            String authorityName = authority.getAuthority();
            if(!authorityName.startsWith("ROLE_")) {
                authorityName = "ROLE_" + authorityName;
            }
            auths.add(authorityName);
        }

        return String.join(",",auths);
    }

}
