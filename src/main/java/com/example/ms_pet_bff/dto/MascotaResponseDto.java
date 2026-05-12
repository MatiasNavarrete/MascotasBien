package com.example.ms_pet_bff.dto;

import com.example.ms_pet_bff.enums.EstadoBusqueda;

public record MascotaResponseDto(
        String nameMascota,
        String raza,
        String especie,
        EstadoBusqueda estadoBusqueda
) {
}
