package com.barisyenigun.ToDo.security;

import com.barisyenigun.ToDo.config.SecurityConfig;


import io.jsonwebtoken.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;



import java.util.Date;

@Service
public class JwtService {
    private final SecurityConfig securityConfig;

    public JwtService(SecurityConfig securityConfig){
        this.securityConfig = securityConfig;
    }

    public Authentication verifyToken(String token){
        if (StringUtils.isNotEmpty(token) && token.startsWith("Bearer ")){
            try {
                byte[] signingKey = securityConfig.getJwtSecret().getBytes();
                Jws<Claims> parsedToken = Jwts.parser()
                        .setSigningKey(signingKey)
                        .parseClaimsJws(token.replace("Bearer ", ""));
                String subject = parsedToken
                        .getBody()
                        .getSubject();

                if (StringUtils.isNotEmpty(token)){
                    return new UsernamePasswordAuthenticationToken(subject,null,null);
                }
            }
            catch (ExpiredJwtException e){
                e.printStackTrace();
            }
            catch (UnsupportedJwtException e){
                e.printStackTrace();
            }
            catch (MalformedJwtException e){
                e.printStackTrace();
            }
            catch (SignatureException e){
                e.printStackTrace();
            }
            catch (IllegalArgumentException e){
                e.printStackTrace();
            }

        }
        return null;
    }

    public String createToken(String subject){
        byte[] signingKey = securityConfig.getJwtSecret().getBytes();
        return Jwts.builder()
                .signWith(SignatureAlgorithm.HS512,signingKey)
                .setHeaderParam("typ","JWT")
                .setIssuer("ToDo")
                .setAudience("ToDo")
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 864000000))
                .compact();
    }

}
