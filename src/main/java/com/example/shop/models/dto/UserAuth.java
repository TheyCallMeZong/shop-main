package com.example.shop.models.dto;

import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotEmpty;

@Getter
@Setter
public class UserAuth {
    @NotEmpty(message = "Логин не может быть пустым")
    @Email(message = "Укажите свою почту для входа")
    private String login;

    @NotEmpty(message = "Пароль не может быть пустым")
    private String password;
}
