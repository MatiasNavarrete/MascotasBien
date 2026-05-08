package com.example.propietario.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record PropietarioRequestDto(

        @NotBlank(message = "El nombre es obligatorio")
        @NotBlank(message = "El email es obligatorio") @Email
        @NotBlank(message = "El numero de telefono es obligatorio")
        String address,
        String secondaryContactName,
        String secondaryContactPhone

) {
}
