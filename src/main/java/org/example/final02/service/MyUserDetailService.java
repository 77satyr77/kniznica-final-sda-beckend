package org.example.final02.service;

import org.example.final02.model.entity.MyUser;
import org.example.final02.repository.MyUserRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MyUserDetailService implements UserDetailsService {

    private final MyUserRepository repository;

    public MyUserDetailService(MyUserRepository myUserRepository) {
        this.repository = myUserRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<MyUser> user = repository.findByUsername(username);
        if (user.isPresent()) {
            var userObj = user.get();
            return User.builder()
                    .username(userObj.getLoginName())
                    .password(userObj.getPassword())
                    .roles(String.valueOf(userObj.getRole()))
                    .build();
        }else {
            throw new UsernameNotFoundException(username);
        }
    }
}
