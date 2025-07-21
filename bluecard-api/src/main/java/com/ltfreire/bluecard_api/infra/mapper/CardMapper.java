package com.ltfreire.bluecard_api.infra.mapper;

import com.ltfreire.bluecard_api.domain.model.CardModel;
import com.ltfreire.bluecard_api.infra.entity.Card;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = UserMapper.class)
public interface CardMapper {
    CardModel entityToModel(Card entity);

    Card modelToEntity(CardModel model);
}
