package com.ltfreire.bluecard_api.domain.interfaces.repository;

import com.ltfreire.bluecard_api.domain.model.UserModel;

import java.util.List;
import java.util.Optional;

public interface IUserRepository {
    Optional<UserModel> findByEmail(String email);

    boolean existsByEmail(String email);

    UserModel save(UserModel user);

    Optional<UserModel> findById(String id);

    boolean existsById(String id);

    void deleteById(String id);

    List<UserModel> findAll();

    UserModel update(UserModel userModel);
}
