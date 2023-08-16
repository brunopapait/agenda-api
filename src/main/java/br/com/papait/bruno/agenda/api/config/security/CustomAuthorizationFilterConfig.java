package br.com.papait.bruno.agenda.api.config.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.apache.logging.log4j.util.Strings;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class CustomAuthorizationFilterConfig extends OncePerRequestFilter {
  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
    String authorization = request.getHeader("Authorization");

    if (Strings.isNotEmpty(authorization) && authorization.startsWith("Bearer ")) {
      try {
        String token = authorization.substring("Bearer ".length());

        var algorithm = Algorithm.HMAC256("my-secret-key");
        var verifier = JWT.require(algorithm).build();

        var decodedJWT = verifier.verify(token);
        var username = decodedJWT.getSubject();

        var usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(username, null, null);
        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);

        filterChain.doFilter(request, response);
      } catch (Exception e) {
        throw new AuthenticationServiceException("Token inválido");
      }
    }

    filterChain.doFilter(request, response);
  }
}
