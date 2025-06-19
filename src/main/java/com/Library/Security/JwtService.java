package com.Library.Security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.Date;

@Service
public class JwtService {


    @Value("${jwt.secret}")
    private String algorithmKey;

    @Value("${jwt.issuer}")
    private String issuer;

    @Value("${jwt.expiry}")
    private int expiry;

    @Value("${jwt.refresh-expiry}")
    private long refreshExpiry;


    private  Algorithm algorithm;

    @PostConstruct
    public void  postConstruct() throws UnsupportedEncodingException {
        algorithm=Algorithm.HMAC256(algorithmKey);
    }


    public String generateToken(String username,String role){

        return JWT.create()
                .withSubject(username)
                .withIssuer(issuer)
                .withClaim("role", role)
                .withExpiresAt(new Date(System.currentTimeMillis() +expiry))
                .sign(algorithm);
    }

    public String getUsernameFromToken(String token){
        return JWT.require(algorithm)
                .withIssuer(issuer)
                .build()
                .verify(token)
                .getSubject();
    }

    public String getRoleFromToken(String token) {
        return JWT.require(algorithm)
                .withIssuer(issuer)
                .build()
                .verify(token)
                .getClaim("role").asString();
    }
    public boolean isTokenValid(String token) {
        try {
            JWT.require(algorithm)
                    .withIssuer(issuer)
                    .build()
                    .verify(token); // Throws exception if invalid
            return true;
        } catch (Exception e) {
            return false;
        }



    }
    public String generateRefreshToken(String username) {
        return JWT.create()
                .withSubject(username)
                .withIssuer(issuer)
                .withExpiresAt(new Date(System.currentTimeMillis() + refreshExpiry))
                .sign(algorithm);
    }

}
