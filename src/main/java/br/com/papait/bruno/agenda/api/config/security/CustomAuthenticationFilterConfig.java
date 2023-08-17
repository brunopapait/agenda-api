package br.com.papait.bruno.agenda.api.config.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Slf4j
@RequiredArgsConstructor
public class CustomAuthenticationFilterConfig extends UsernamePasswordAuthenticationFilter {

  private final AuthenticationManager authenticationManager;

  @Override
  public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
    String username = request.getParameter(SPRING_SECURITY_FORM_USERNAME_KEY);
    String password = request.getParameter(SPRING_SECURITY_FORM_PASSWORD_KEY);

    log.info("Usuário: {}, senha: {}", username, password);

    var usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(username, password);
    return this.authenticationManager.authenticate(usernamePasswordAuthenticationToken);
  }

  @Override
  protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
    var algorithm = Algorithm.HMAC256("my-secret-key");
    var user = (User) authResult.getPrincipal();

    var accessToken = JWT.create()
        .withSubject(user.getUsername())
        .withExpiresAt(new Date(System.currentTimeMillis() + 10 * 60 * 1000))
        .withIssuer(request.getRequestURL().toString())
        .sign(algorithm);

    Map<String, String> token = new HashMap<>();
    token.put("access_token", accessToken);
    response.setContentType(APPLICATION_JSON_VALUE);

    new ObjectMapper().writeValue(response.getOutputStream(), token);
  }
}
