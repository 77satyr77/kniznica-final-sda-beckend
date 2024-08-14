package org.example.final02;

import io.jsonwebtoken.Jwts;
import jakarta.xml.bind.DatatypeConverter;
import org.junit.jupiter.api.Test;

import javax.crypto.SecretKey;

public class JwtSecretMakerTest {

    @Test
    public void generateJwtSecretKey() {
        //toto vsetko su triedy JWT a SPRING SECUROTY
        //vygenerovanie kluca vybranym algoritmom HS256
        SecretKey key = Jwts.SIG.HS256.key().build();
        //prevod kluca na string
        String encodedKey = DatatypeConverter.printHexBinary(key.getEncoded());
        System.out.println("Key = " + encodedKey);
    }
}
