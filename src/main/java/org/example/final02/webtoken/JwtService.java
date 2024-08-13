package org.example.final02.webtoken;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import javax.crypto.SecretKey;
import java.time.Instant;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
public class JwtService {

    //pre secretKey pouzijeme metodu "generateJwtSecretKey" z triedy "JwtSecretMakerTest"
    //todo nazov premennej v navodoch byva pisany velkymi pismenami.je to zauzivane v tomto pripade,alobo sa drzat camelcase?
    private static final String SECRETKEY = "310B120B0BFD37D402A08745DB60C776A1CD7068BA2E36CBCD8D9D22C3B5439D";
    //premenna pre pouzitie pri nastavovani doby platnosti tokenu
    private static final Long VALIDITY = TimeUnit.MINUTES.toMillis(30);

    public String generateToken(UserDetails userDetails) {
        Map<String, String> claims = new HashMap<>();
        claims.put("author", "Ivo.Sabo");
        return Jwts.builder()
                //Nastaví sa mapa claims obsahujúca všetky tvrdenia (informácie) do tokenu. V tomto prípade sa pridá len tvrdenie iss.
                .claims(claims)
                //Nastaví sa "subject" (téma) tokenu, čo je obvykle identifikátor používateľa, v tomto prípade používateľské meno (userDetails.getUsername()).
                .subject(userDetails.getUsername())
                .issuedAt(Date.from(Instant.now()))
                .expiration(Date.from(Instant.now().plusMillis(VALIDITY)))
                //Tu sa token podpíše pomocou HMAC-SHA algoritmu a tajného kľúča (SecretKey), ktorý sa získava pomocou metódy getSecretKey(). Tento podpis zabezpečuje, že token nebol zmenený po jeho vytvorení.
                .signWith(getSecretKey())
                .compact();
    }

    private SecretKey getSecretKey() {
        byte[] decodedKey = Base64.getDecoder().decode(SECRETKEY);
        return Keys.hmacShaKeyFor(decodedKey);
    }

    public String extractUsrname(String jwt) {
        Claims claims = Jwts.parser()
                .verifyWith(getSecretKey())
                .build()
                .parseSignedClaims(jwt)
                .getPayload();
        return claims.getSubject();
    }
}
