package com.todolist.todolist.infrastructure.configuration;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Component;
import org.springframework.web.util.WebUtils;

import java.security.Key;
import java.time.Instant;
import java.util.Date;

/**
 * Componente responsável pela geração, validação e manipulação de tokens JWT.
 */
@Component
public class JwtUtils {
    private static final Logger logger = LoggerFactory.getLogger(JwtUtils.class);

    @Value("${todolist.app.jwtSecret}")
    private String jwtSecret;

    @Value("${todolist.app.jwtExpirationMs}")
    private int jwtExpiracaoEmMilisegundos;

    @Value("${todolist.app.jwtCookieName}")
    private String jwtCookie;

    public String getJWTFromCookies(HttpServletRequest request) {
        Cookie cookie = WebUtils.getCookie(request, jwtCookie);
        if (cookie != null) {
            return cookie.getValue();
        } else {
            return null;
        }
    }

    public ResponseCookie gerarJWTCookie(UserDetailsImpl userPrincipal) {
        String jwt = gerarTokenComUsuario(userPrincipal.getUsername());
        ResponseCookie cookie = ResponseCookie.from(jwtCookie, jwt).path("/").maxAge(24 * 60 * 60).httpOnly(true).build();
        return cookie;
    }

    public ResponseCookie limparJWTCookie() {
        ResponseCookie cookie = ResponseCookie.from(jwtCookie, null).path("/").build();
        return cookie;
    }

    public String obterUsernameFromJWTToken(String token) {
        return Jwts.parserBuilder().setSigningKey(obterKey()).build()
                .parseClaimsJws(token).getBody().getSubject();
    }

    private Key obterKey() {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
    }

    public boolean validarJWTToken(String authToken) {
        try {
            Jwts.parserBuilder().setSigningKey(obterKey()).build().parse(authToken);
            return true;
        } catch (MalformedJwtException e) {
            logger.error("O token JWT é invalido: {}", e.getMessage());
        } catch (ExpiredJwtException e) {
            logger.error("O token JWT expirou: {}", e.getMessage());
        } catch (UnsupportedJwtException e) {
            logger.error("O Token JWT não é válido: {}", e.getMessage());
        } catch (IllegalArgumentException e) {
            logger.error("O token não é válido: {}", e.getMessage());
        }

        return false;
    }

    public String gerarTokenComUsuario(String email) {
        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(Date.from(Instant.now()))
                .setExpiration(new Date((Date.from(Instant.now())).getTime() + jwtExpiracaoEmMilisegundos))
                .signWith(obterKey(), SignatureAlgorithm.HS256)
                .compact();
    }
}
