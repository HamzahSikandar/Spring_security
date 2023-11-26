package com.spring.security.springbootsecurity.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;

import java.security.Key;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class jwtService {
    private final static String SECRET_KEY="O1rSFyDlucKInmcfRWV7qt2zqBxi4HuJPfpH/CtBr9NhzfdrB4Lx2oYJRvyCQ57E ";
    public String extractUsername(String jwt) {

        return extractClaim( jwt,  Claims::getSubject);
    }


// Method for generate a token with just userdetails
    public String generateToken(UserDetails userDetails){
        return generateToken(new HashMap<>(),userDetails);
    }


//    Metod for Validate a Token
    public boolean validateToken(String token,UserDetails userDetails){
        return (extractUsername(token).equals(userDetails.getUsername())) &&  !isTokenExpire(token);
    }

    private boolean isTokenExpire(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token,Claims::getExpiration);
    }


    //    Method for generate a token from extract claims
    public String generateToken(
             Map<String, Object> extraClaims,
             UserDetails userDetails
) {
    return Jwts
            .builder()
            .setClaims(extraClaims)
            .setSubject(userDetails.getUsername())
            .setIssuedAt(new Date(System.currentTimeMillis()))
            .setExpiration(new Date(System.currentTimeMillis()+1000*60*24))
            .signWith(SignatureAlgorithm.HS256,SECRET_KEY)
            .compact();
}



    //    Method for extract a single claim from extractAllClaims method
    public <T> T extractClaim (String token , Function<Claims,T> claimsResolvers){
        final Claims claims=extractAllClaims(token);
        return claimsResolvers.apply(claims);
    }



//    Method for extract all claims
    private Claims extractAllClaims(String token){
//        return Jwts
//                .builder()
//                .setSigningKey(getSignInKey())
//                .build()
//                .parseClaimsJws(token)
//                .getBody();
        return Jwts.parser()
                .setSigningKey(token)
                .parseClaimsJws(token)
                .getBody();

    }

//    private Key getSignInKey() {
////        byte[] keyBytes = Base64.getDecoder().decode(SECRET_KEY);
////        return Key
//    }
}
