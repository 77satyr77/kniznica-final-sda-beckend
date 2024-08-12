package org.example.final02.repository;

import org.example.final02.model.entity.MyUser;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface MyUserRepository extends JpaRepository<MyUser, Long> {

    Optional<MyUser>findByUsername(String username);
}
