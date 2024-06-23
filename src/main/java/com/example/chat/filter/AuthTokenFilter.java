package com.example.chat.filter;

import com.example.chat.config.CustomRequestWrapper;
import com.example.chat.config.security.CustomUserDetailsService;
import com.example.chat.utils.JwtUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
public class AuthTokenFilter extends OncePerRequestFilter {
    private final JwtUtils jwtUtils;
    private final CustomUserDetailsService customUserDetailsService;
    private static List<String> excludeUrl = Arrays.asList(
            "/api/auth/.*","/swagger-ui/.*","/v3/api-docs/.*","/ws/.*"
    );



    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            String currentPath = request.getRequestURI();
//            CustomRequestWrapper customRequestWrapper = new CustomRequestWrapper(request);
//            StringBuilder sb = getBodyToStringBuilder(response,2 filterChain, customRequestWrapper);
            System.out.println("Current path: " + currentPath);
            String jwt = parseJwt(request);
            if(jwt != null && jwtUtils.validateJwtToken(jwt)){
                String userId = jwtUtils.getUserNameFromJwtToken(jwt);
                UserDetails userDetails = customUserDetailsService.loadUserByUsername(userId);
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        }catch (Exception e){
            log.error("Cannot set user authentication : {}", e);
        }
        filterChain.doFilter(request,response);
    }

    private StringBuilder getBodyToStringBuilder(HttpServletResponse response, FilterChain filterChain, CustomRequestWrapper custromRequestWrapper) throws IOException, ServletException {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = null;
        //한줄씩 담을 변수
        String line = "";
        try {
            ServletInputStream inputStream = custromRequestWrapper.getInputStream();
            if(inputStream != null){
                br = new BufferedReader(new InputStreamReader(inputStream));
                while ((line = br.readLine()) != null) {
                    sb.append(line);
                }
            }
        } catch (IOException e) {
            log.debug("body에 요청이 없습니다.");
            filterChain.doFilter(custromRequestWrapper, response);
            return null;
        }
        return sb;
    }




    private String parseJwt(HttpServletRequest request){
        String headerAuth = request.getHeader("Authorization");
        if(StringUtils.hasText(headerAuth) && headerAuth.startsWith("Bearer ")){
            return headerAuth.substring(7, headerAuth.length());
        }
        return null;
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        String servletPath = request.getServletPath();
        return excludeUrl.stream().anyMatch(servletPath::matches);
    }
}

