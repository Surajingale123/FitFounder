package com.example.demo.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.demo.service.IJwtService;
import com.example.demo.service.IUserService;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final IJwtService jwtService;
    private final IUserService userService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        final String authHeader = request.getHeader("Authorization");
        final String jwt;
        final String userEmail;

        if (StringUtils.isEmpty(authHeader) || !StringUtils.startsWith(authHeader,"Bearer")) {
            filterChain.doFilter(request,response);
            return;
        }

        jwt = authHeader.substring(7);
        userEmail = jwtService.extractUserName(jwt);

        if (StringUtils.isNotEmpty(userEmail) && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = userService.userDetailsService().loadUserByUsername(userEmail);

            if(jwtService.validateToken(jwt,userDetails)) {
                SecurityContext securityContext = SecurityContextHolder.createEmptyContext();
                UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                        userDetails,
                        null,
                        userDetails.getAuthorities()
                );
                token.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                securityContext.setAuthentication(token);
                SecurityContextHolder.setContext(securityContext);
            }
        }

        filterChain.doFilter(request,response);
    }
}




























//
//
//package com.example.demo.config;
//
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import lombok.RequiredArgsConstructor;
//import org.apache.commons.lang3.StringUtils;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.context.SecurityContext;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
//import org.springframework.stereotype.Component;
//import org.springframework.web.filter.OncePerRequestFilter;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import com.example.demo.service.IJwtService;
//import com.example.demo.service.IUserService;
//
//import java.io.IOException;
//
//@Component
//@RequiredArgsConstructor
//public class JwtAuthenticationFilter extends OncePerRequestFilter {
//
//    private static final Logger logger = LoggerFactory.getLogger(JwtAuthenticationFilter.class);
//
//    private final IJwtService jwtService;
//    private final IUserService userService;
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
//        final String authHeader = request.getHeader("Authorization");
//        final String jwt;
//        final String userEmail;
//
//        // Check if Authorization header is present and starts with "Bearer "
//        if (StringUtils.isEmpty(authHeader) || !StringUtils.startsWith(authHeader, "Bearer ")) {
//            filterChain.doFilter(request, response);
//            return;
//        }
//
//        jwt = authHeader.substring(7); // Extract the JWT token from the header
//        logger.info("Authorization Header: {}", authHeader);
//        logger.info("JWT: {}", jwt);
//        System.out.println(authHeader);
//        // Check if the JWT format is valid (contains exactly two periods)
//        if (StringUtils.countMatches(jwt, ".") != 2) {
//            logger.error("Invalid JWT format: {}", jwt);
//            filterChain.doFilter(request, response);
//            return;
//        }
//
//        try {
//            userEmail = jwtService.extractUserName(jwt); // Extract username from JWT
//            logger.info("Extracted User Email: {}", userEmail);
//        } catch (Exception e) {
//            logger.error("Error extracting username from JWT", e);
//            filterChain.doFilter(request, response);
//            return;
//        }
//
//        // If userEmail is found and no authentication is set in the security context
//        if (StringUtils.isNotEmpty(userEmail) && SecurityContextHolder.getContext().getAuthentication() == null) {
//            try {
//                UserDetails userDetails = userService.userDetailsService().loadUserByUsername(userEmail);
//
//                // Validate the token
//                if (jwtService.validateToken(jwt, userDetails)) {
//                    SecurityContext securityContext = SecurityContextHolder.createEmptyContext();
//                    UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
//                            userDetails,
//                            null,
//                            userDetails.getAuthorities()
//                    );
//                    token.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//                    securityContext.setAuthentication(token);
//                    SecurityContextHolder.setContext(securityContext);
//                    logger.info("Successfully authenticated user: {}", userEmail);
//                }
//            } catch (Exception e) {
//                logger.error("Error during JWT validation", e);
//            }
//        }
//       System.out.println(jwt);
//        filterChain.doFilter(request, response);
//    }
//}
//
