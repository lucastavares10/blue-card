package com.ltfreire.bluecard_api.domain.dto.user;

import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;

@Data
@Builder
public class ListUsersResponseDTO {
    private ArrayList<UserResponseDTO> users;
    private int count;
}