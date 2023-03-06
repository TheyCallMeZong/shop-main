package com.example.shop.models.dto;

import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

@Getter
@Setter
public class UserRegistration {
    @NotEmpty(message = "Укажите имя")
    @Size(min = 2, max = 50, message = "Имя должно быть длиной от 2 до 50 символов")
    private String firstName;

    @NotEmpty(message = "Укажите фамилию")
    @Size(min = 2, max = 50, message = "Фамилия должно быть длиной от 2 до 50 символов")
    private String surname;
    private String patronymic;

    @Email(message = "Почта должна быть валидной")
    private String email;

    @NotEmpty(message = "Укажите пароль")
    @Size(min = 8, message = "Пароль должен быть длиннее 8 символов")
    private String password;
}
