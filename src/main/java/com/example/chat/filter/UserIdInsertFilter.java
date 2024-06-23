package com.example.chat.filter;

import com.example.chat.config.CustomRequestWrapper;
import com.example.chat.domain.common.model.BaseRequest;
import com.example.chat.utils.JwtUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ReadListener;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
public class UserIdInsertFilter extends OncePerRequestFilter {

    private final JwtUtils jwtUtils;
    private final ObjectMapper objectMapper;

    private final List<Pattern> excludeUrlPatterns = Arrays.asList(
            "/api/auth/.*",
            "/swagger-ui/.*",
            "/v3/api-docs/.*",
            "/ws/.*"
    ).stream().map(Pattern::compile).collect(Collectors.toList());

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        String servletPath = request.getServletPath();
        return excludeUrlPatterns.stream().anyMatch(pattern -> pattern.matcher(servletPath).matches());
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        final String authorizationHeader = request.getHeader("Authorization");

        String userId = null;
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            String jwt = authorizationHeader.substring(7);
            userId = jwtUtils.getUserNameFromJwtToken(jwt);
        }

        CustomRequestWrapper customRequestWrapper = new CustomRequestWrapper(request);
        String requestBody = customRequestWrapper.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
        BaseRequest<?> baseRequest = extractBaseRequestFromBody(requestBody.getBytes(StandardCharsets.UTF_8));
        if(baseRequest  != null && userId != null){
            baseRequest.setUserId(userId);
            String newRequestBody = objectMapper.writeValueAsString(baseRequest);
            customRequestWrapper = new CustomRequestWrapper(request,newRequestBody.getBytes(StandardCharsets.UTF_8));
        }
        filterChain.doFilter(customRequestWrapper,response);
    }

    private BaseRequest<?> extractBaseRequestFromBody(byte[] requestBytes) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(requestBytes, BaseRequest.class);
        } catch (IOException e) {
            log.error("Failed to extract BaseRequest from body", e);
            return null;
        }
    }
}