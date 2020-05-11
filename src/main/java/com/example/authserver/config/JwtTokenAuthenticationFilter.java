package com.example.authserver.config;

import com.example.authserver.Utils.ExampleParam;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Lakitha Prabudh on 5/11/20
 */
@Component
public class JwtTokenAuthenticationFilter extends OncePerRequestFilter {
    private final JwtAuthenticationConfig jwtAuthenticationConfig;

    @Autowired
    public JwtTokenAuthenticationFilter(JwtAuthenticationConfig jwtAuthenticationConfig) {
        this.jwtAuthenticationConfig = jwtAuthenticationConfig;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse rsp, FilterChain filterChain)
            throws ServletException, IOException {

        String token = req.getHeader(ExampleParam.AUTH_HEADER);
        if (token != null && token.startsWith(ExampleParam.JWT_PREFIX + " ")) {
            token = token.replace(ExampleParam.JWT_PREFIX + " ", "");
            try {
                Claims claims = Jwts.parser()
                        .setSigningKey(jwtAuthenticationConfig.getSecret().getBytes())
                        .parseClaimsJws(token)
                        .getBody();
                String username = claims.getSubject();
                List<String> authorities = claims.get("authorities", List.class);
                if (username != null) {
                    UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(username, null,
                            authorities.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList()));
                    SecurityContextHolder.getContext().setAuthentication(auth);
                }
            } catch (Exception ignore) {
                SecurityContextHolder.clearContext();
            }
        }
        filterChain.doFilter(req, rsp);
    }
}