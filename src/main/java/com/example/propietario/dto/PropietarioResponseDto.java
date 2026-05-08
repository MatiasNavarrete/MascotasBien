package com.example.propietario.dto;

import java.util.UUID;

public record PropietarioResponseDto(

        UUID id,
        String name,
        String email,
        String phoneNumber,
        String address
) {
}
