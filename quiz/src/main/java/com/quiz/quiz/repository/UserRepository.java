package com.quiz.quiz.repository;

import com.quiz.quiz.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository  extends JpaRepository<User, UUID> {
    boolean existsByEmail(String email);
    boolean existsByUserName(String email);
    Optional<User> findByUserName(String userName);
}
