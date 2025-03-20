package com.example.dio.security.jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component

public class JWTService {

    private final String secret = "zoT6O3/n8jTnDs0bNdrqtA10/js1AfzMB+CBCF0toL4=";
    private final Key key;

    {
        this.key = generateKey();
    }

    public Key generateKey(){
      return  Keys.hmacShaKeyFor(Decoders.BASE64.decode(secret));
    }

    public String generateToken(TokenPayload tokenPayload){
     return Jwts.builder()
                .setClaims(tokenPayload.claims())
                .setIssuedAt(new Date(tokenPayload.issuedAt()))
                .setExpiration(new Date(tokenPayload.expiration()))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

}
