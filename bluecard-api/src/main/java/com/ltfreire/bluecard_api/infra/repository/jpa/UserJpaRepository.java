package com.ltfreire.bluecard_api.infra.repository.jpa;

import com.ltfreire.bluecard_api.infra.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserJpaRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    boolean existsByEmail(String email);
}
