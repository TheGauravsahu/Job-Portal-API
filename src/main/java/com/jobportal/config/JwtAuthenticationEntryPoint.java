package com.jobportal.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jobportal.utility.ErrorInfo;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDateTime;


@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {

        ErrorInfo error = new ErrorInfo(
                HttpStatus.UNAUTHORIZED.value(),
                "Unauthorized: Invalid or missing token.",
                LocalDateTime.now()
        );

        response.setContentType("application/json");
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        new ObjectMapper().writeValue(response.getOutputStream(), error);
    }
}
