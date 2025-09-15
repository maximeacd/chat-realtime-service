package com.chatapp.realtime_chat_service.controller;

import com.chatapp.realtime_chat_service.entity.User;
import com.chatapp.realtime_chat_service.security.JwtUtil;
import com.chatapp.realtime_chat_service.service.UserService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final UserService userService;

    @PostMapping("/register")
    public User register(@RequestBody AuthRequest request){
        return userService.register(request.getUsername(), request.getPassword());
    }

    @PostMapping("/login")
    public String login(@RequestBody AuthRequest request){
        try{
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
            return jwtUtil.generateToken(authentication.getName());
        }
        catch(AuthenticationException e){
            throw new RuntimeException("Invalid credentials");
        }
    }

    @Data
    public static class AuthRequest{
        private String username;
        private String password;
    }

}
