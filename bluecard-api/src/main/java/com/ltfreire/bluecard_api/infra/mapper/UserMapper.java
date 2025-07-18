package com.ltfreire.bluecard_api.infra.mapper;

import com.ltfreire.bluecard_api.domain.model.UserModel;
import com.ltfreire.bluecard_api.infra.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserModel entityToModel(User entity);
    User modelToEntity(UserModel model);
}
