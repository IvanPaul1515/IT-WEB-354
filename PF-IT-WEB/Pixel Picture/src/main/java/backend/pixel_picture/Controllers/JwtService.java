package backend.pixel_picture.Controllers;

import backend.pixel_picture.Entidades.AuthResponse;
import backend.pixel_picture.Entidades.Usuario;
import org.springframework.security.core.userdetails.UserDetails;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {
    private static final String SECRET_KEY="586E3272357538782F413F4428472B4B6250655368566B597033733676397924";

    public AuthResponse getToken(Usuario user) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("roles", String.join(",", user.getRol()));
        //
        return createToken(claims, user.getUserName());
    }

    private AuthResponse createToken(Map<String, Object> claims, String userName) {
        //Generando la fecha valida
        LocalDateTime localDateTime = LocalDateTime.now().plusMinutes(60);
        //
        Date fechaExpiracion = Date.from(localDateTime.toInstant(ZoneOffset.ofHours(-4)));

        //Generando el GWT
        String jwt =  Jwts.builder()
                .setIssuer("PWA-JWT")
                .setClaims(claims)
                .setSubject(userName)
                .setExpiration(fechaExpiracion)
                .signWith(getKey(), SignatureAlgorithm.HS256).compact();

        return new AuthResponse(jwt, fechaExpiracion.getTime());
    }

    private Key getKey() {
        byte[] keyBytes=Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String getUsernameFromToken(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username=getUsernameFromToken(token);
        return (username.equals(userDetails.getUsername())&& !isTokenExpired(token));
    }

    private Claims getAllClaims(String token)
    {
        return Jwts
                .parserBuilder()
                .setSigningKey(getKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaims(token);
        return claimsResolver.apply(claims);
    }

    public String extractClaim(String token, String nombre){
        Claims claims = getAllClaims(token);
        return claims.get(nombre, String.class);
    }

    private Date getExpiration(String token)
    {
        return extractClaim(token, Claims::getExpiration);
    }

    private boolean isTokenExpired(String token)
    {
        return getExpiration(token).before(new Date());
    }
}
