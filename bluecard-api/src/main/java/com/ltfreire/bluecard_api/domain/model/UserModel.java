package com.ltfreire.bluecard_api.domain.model;

import com.ltfreire.bluecard_api.application.enums.UserRole;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserModel {

    private Long id;
    private String name;
    private String email;
    private String password;
    private UserRole role;
}