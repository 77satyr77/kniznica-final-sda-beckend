package org.example.final02.service;

import org.example.final02.model.entity.User;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    User createUser(User user);
}
