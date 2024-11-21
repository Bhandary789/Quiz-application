package com.quiz.quiz.dao;

import com.quiz.quiz.entity.User;
import com.quiz.quiz.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
@Slf4j
@RequiredArgsConstructor
public class UserDao {
    private final UserRepository userRepository;
    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }
    public boolean existsByUserName(String userName) {
        return userRepository.existsByUserName(userName);
    }
    public User saveUser(User user) {
        return userRepository.save(user);
    }
    public Optional<User> findById(UUID userId) {
        return userRepository.findById(userId);
    }
    public Optional<User> findByUserName(String userName) {
        return userRepository.findByUserName(userName);
    }
}
