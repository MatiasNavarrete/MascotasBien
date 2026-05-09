package com.example.propietario.dto;

import com.example.propietario.enums.EstadoBusqueda;
import com.example.propietario.enums.EstadoCuenta;
import com.example.propietario.enums.TipoPropietario;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record PropietarioRequestDto(

        @NotBlank(message = "El nombre es obligatorio")
        String name,
        @NotBlank(message = "El email es obligatorio")
        @Email(message = "Debes ingresar un metodo de email valido")
        String email,
        @NotBlank(message = "El numero de telefono es obligatorio")
        @Pattern(regexp = "^\\+569][0-9]{8}$", message = "El formato debe ser +569XXXXXXXX")
        String phoneNumber,
        String address,
        String secondaryContactName,
        String secondaryContactPhone,
        @NotNull(message = "El tipo de propietario es obligatorio")
        TipoPropietario tipoPropietario,
        EstadoCuenta estadoCuenta,
        EstadoBusqueda estadoBusqueda

) {
}
