package com.chatapp.realtime_chat_service.service;

import com.chatapp.realtime_chat_service.entity.User;
import com.chatapp.realtime_chat_service.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public User register(String username, String password){
        if(userRepository.findByUsername(username).isPresent()){
            throw new RuntimeException("Username already taken");
        }
        User user = User.builder()
                .username(username)
                .password(passwordEncoder.encode(password))
                .roles(Set.of("USER"))
                .build();
        return userRepository.save(user);
    }

    public Optional<User> findByUsername(String username){
        return userRepository.findByUsername(username);
    }

    public boolean existsByUsername(String username){
        return userRepository.existsByUsername(username);
    }

    public Page<User> findByRolesContaining(String role, Pageable pageable){
        return userRepository.findByRolesContaining(role, pageable);
    }

    public Page<User> findByUsernameContainingIgnoreCase(String username, Pageable pageable){
        return userRepository.findByUsernameContainingIgnoreCase(username, pageable);
    }

    public void deleteByUsername(String username){
        userRepository.deleteByUsername(username);
    }

}
