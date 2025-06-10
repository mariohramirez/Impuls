package com.impuls.user_service.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.crypto.SecretKey;
import java.io.IOException;

public class JwtTokenValidator extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        String jwt=request.getHeader(JwtConstant.JWT_HEADER);

        if(jwt!=null){
            //Cuando obtenemos el jwt del header de authorization lo recibimos como Bearer jwt
            //Por ello para no tener el bearer y solo el wjt, usamos el substring
            jwt = jwt.substring(7);
            try{
                SecretKey key = Keys.hmacShaKeyFor(JwtConstant.SECRET_KEY.getBytes());
                Claims claims = Jwts.parser()
                        .verifyWith(key)
                        .build()
                        .parseSignedClaims(jwt)
                        .getPayload();

                //Extrae todos los claims del token
                String email = String.valueOf(claims.get("email"));
                Long userId = claims.get("userId", Long.class);
                String uuid = claims.get("uuid", String.class);
                String authorities = String.valueOf(claims.get("authorities"));

                CustomUserPrincipal principal = new CustomUserPrincipal(
                        userId,
                        uuid,
                        email,
                        AuthorityUtils.commaSeparatedStringToAuthorityList(authorities)
                );

               //List<GrantedAuthority> auth = AuthorityUtils.commaSeparatedStringToAuthorityList(authorities);
                //Authentication authentication = new UsernamePasswordAuthenticationToken(email, null, auth);
                Authentication authentication = new UsernamePasswordAuthenticationToken(
                        principal,
                        null,
                        principal.getAuthorities()
                );
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
            catch (Exception e) {
                throw new BadCredentialsException("Token Invalido...");
            }



        }
        filterChain.doFilter(request, response);

    }
}
