package org.example.final02.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractAuthenticationFilterConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .authorizeHttpRequests(registry->{
                    //nastavujeme prava pristupu k stranka
                    //home pristupna pre vsetkych ---permitAll
                    registry.requestMatchers("/home").permitAll();
                    registry.requestMatchers("/admin/**").hasRole("ADMIN");
                    registry.requestMatchers("/user/**").hasRole("USER");
                    registry.anyRequest().authenticated();
        })
                .formLogin(AbstractAuthenticationFilterConfigurer::permitAll)
                .build();
    }

    @Bean
    //UserDetailService UserDetail a User su triedy springu.
    public UserDetailsService userDetailsService() {
        UserDetails normalUser = User.builder()
                .username("user01")
                //heslo kodujeme aby nebolo viditelne
                //momentalne pouzity online decoder,porom sa pouzije metoda PasswordEncoder...nizsie
                .password("$2a$12$JqidyGdaFwLQYXTrhUic5ulMLXn75Bu6b5IJVx.XZPS82Xq2W/3yO")
                .roles("USER")
                .build();
        UserDetails adminlUser = User.builder()
                .username("admin01")
                .password("$2a$12$xJwIg859c2rSKpf73SNmc.viTZVhMj3jNMMieDqc6Tib9JzrTZSEW")
                .roles("ADMIN" , "USER") //admin ma mat pristup aj k strankam pre USER
                .build();
        return new InMemoryUserDetailsManager(normalUser , adminlUser);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
