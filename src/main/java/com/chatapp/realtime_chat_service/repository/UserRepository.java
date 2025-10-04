package com.chatapp.realtime_chat_service.repository;

import com.chatapp.realtime_chat_service.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);

    boolean existsByUsername(String username);

    Page<User> findByRolesContaining(String role, Pageable pageable);

    Page<User> findByUsernameContainingIgnoreCase(String keyword, Pageable pageable);

    void deleteByUsername(String username);
}
