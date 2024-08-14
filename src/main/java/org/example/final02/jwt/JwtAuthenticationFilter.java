package org.example.final02.jwt;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.final02.service.MyUserDetailService;
import org.example.final02.webtoken.JwtService;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.security.Security;

@Configuration
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    //DI JwtService aby sme mohli extrahovat username
    private final JwtService jwtService;
    private final MyUserDetailService myUserDetailService;

    public JwtAuthenticationFilter(JwtService jwtService, MyUserDetailService myUserDetailService) {
        this.jwtService = jwtService;
        this.myUserDetailService = myUserDetailService;
    }

    //OncePerRequestFilter trieda ktora sa stara o to aby bol kazdy response alebo reques kontrolovany filtrom
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }
        String jwt = authHeader.substring(7);
        String username = jwtService.extractUsrname(jwt);
        //kontrola ci uz predtym neprebehlo overovanie.ak nie String jwt sa rovna null
        if (username != null && SecurityContextHolder.getContext().getAuthentication()==null) {
            UserDetails userDetails = myUserDetailService.loadUserByUsername(username);
            if (userDetails != null && jwtService.isTokenValid(jwt)){
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                        username,
                        userDetails.getPassword(),
                        userDetails.getAuthorities()
                );
                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        }
        filterChain.doFilter(request, response);
    }
}
