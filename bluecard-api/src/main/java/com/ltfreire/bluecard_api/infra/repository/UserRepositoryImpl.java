package com.ltfreire.bluecard_api.infra.repository;

import com.ltfreire.bluecard_api.domain.interfaces.repository.IUserRepository;
import com.ltfreire.bluecard_api.domain.model.UserModel;
import com.ltfreire.bluecard_api.infra.entity.User;
import com.ltfreire.bluecard_api.infra.mapper.UserMapper;
import com.ltfreire.bluecard_api.infra.repository.jpa.UserJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements IUserRepository {

    private final UserJpaRepository jpaRepository;
    private final UserMapper userMapper;

    @Override
    public Optional<UserModel> findByEmail(String email) {
        return jpaRepository.findByEmail(email)
                .map(userMapper::entityToModel);
    }

    @Override
    public boolean existsByEmail(String email) {
        return jpaRepository.existsByEmail(email);
    }

    @Override
    public UserModel save(UserModel userModel) {
        User entity = userMapper.modelToEntity(userModel);

        User saved = jpaRepository.save(entity);

        return userMapper.entityToModel(saved);
    }
}
