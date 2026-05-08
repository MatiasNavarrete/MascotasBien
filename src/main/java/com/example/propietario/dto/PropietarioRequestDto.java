package com.example.propietario.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record PropietarioRequestDto(

        @NotBlank(message = "El nombre es obligatorio")
        String name,
        @NotBlank(message = "El email es obligatorio")
        @Email
        String email,
        @NotBlank(message = "El numero de telefono es obligatorio")
        String phoneNumber,
        String address,
        String secondaryContactName,
        String secondaryContactPhone

) {
}
