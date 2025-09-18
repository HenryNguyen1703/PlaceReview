package vn.ngochieu.com.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.userdetails.UserDetails;
import vn.ngochieu.com.features.user_management.entity.Users;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.function.Function;

public class JwtUtils {
    private static final long EXPIRATION_TIME = 60 * 60 * 24 * 7 * 1000L; // 7 days
    private static final String SECRET = "ThisIsASecureJWTSecretKey1234567890HenryThompson@@@";

    public static String createToken(Users user, HttpServletRequest request) {
        Date expirationDate = new Date(System.currentTimeMillis() + EXPIRATION_TIME);
        return Jwts.builder()
                .id(String.valueOf(user.getId()))
                .subject(user.getEmail())
                .issuedAt(new Date())
                .issuer("Henry")
                .expiration(expirationDate)
                .signWith(getKey())
                .claim("role", user.getRole().name())
                .claim("userAgent", request.getHeader("User-Agent"))
                .compact();
    }

    private static SecretKey getKey() {
        byte [] keyBytes = Decoders.BASE64.decode(SECRET);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    //Validate token
    public static Boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    private static Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }


    //Extract claims
    private static Claims extractAllClaims(String token){
        return Jwts.parser()
                .verifyWith(getKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    private static <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    //username is email
    public static String extractUsername(String token){
        return extractClaim(token, Claims::getSubject);
    }

    public static Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }
}
