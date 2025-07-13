package com.ltfreire.bluecard_api.infra.repository;

import java.util.Optional;

import com.ltfreire.bluecard_api.infra.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

    boolean existsByEmail(String email);
}