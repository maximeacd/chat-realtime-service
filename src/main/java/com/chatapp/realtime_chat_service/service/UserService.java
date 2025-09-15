package com.chatapp.realtime_chat_service.service;

import com.chatapp.realtime_chat_service.entity.User;
import com.chatapp.realtime_chat_service.repository.UserRepository;
import lombok.RequiredArgsConstructor;
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

}
