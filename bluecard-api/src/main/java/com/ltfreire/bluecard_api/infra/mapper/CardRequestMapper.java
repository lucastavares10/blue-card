package com.ltfreire.bluecard_api.infra.mapper;

import com.ltfreire.bluecard_api.domain.model.CardRequestModel;
import com.ltfreire.bluecard_api.infra.entity.CardRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = UserMapper.class)
public interface CardRequestMapper {

    @Mapping(source = "user.id", target = "ownerId")
    CardRequestModel entityToModel(CardRequest entity);

    @Mapping(source = "ownerId", target = "user.id")
    CardRequest modelToEntity(CardRequestModel model);
}
