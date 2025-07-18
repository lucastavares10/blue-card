package com.ltfreire.bluecard_api.infra.repository;

import com.ltfreire.bluecard_api.domain.interfaces.repository.IUserRepository;
import com.ltfreire.bluecard_api.domain.model.UserModel;
import com.ltfreire.bluecard_api.infra.entity.User;
import com.ltfreire.bluecard_api.infra.mapper.UserMapper;
import com.ltfreire.bluecard_api.infra.repository.jpa.UserJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    @Override
    public Optional<UserModel> findById(String id) {
        return jpaRepository.findById(Long.valueOf(id))
                .map(userMapper::entityToModel);
    }

    @Override
    public boolean existsById(String id) {
        return jpaRepository.existsById(Long.valueOf(id));
    }

    @Override
    public void deleteById(String id) {
        jpaRepository.deleteById(Long.valueOf(id));
    }

    @Override
    public List<UserModel> findAll() {
        return jpaRepository.findAll()
                .stream()
                .map(userMapper::entityToModel)
                .collect(Collectors.toList());
    }

    @Override
    public UserModel update(UserModel userModel) {
        User userEntity = userMapper.modelToEntity(userModel);
        User updatedUser = jpaRepository.save(userEntity);
        return userMapper.entityToModel(updatedUser);
    }
}
