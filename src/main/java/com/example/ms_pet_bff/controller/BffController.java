package com.example.ms_pet_bff.controller;

import com.example.ms_pet_bff.client.MascotaClient;
import com.example.ms_pet_bff.client.PropietarioClient;
import com.example.ms_pet_bff.dto.DashboardPetResponseDto;
import com.example.ms_pet_bff.dto.MascotaResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/bff")
@CrossOrigin(origins = "http://localhost:5173") // El puerto de tu frontend
public class BffController {

    @Autowired
    private PropietarioClient propClient;
    @Autowired private MascotaClient mascClient;

    @GetMapping("/dashboard")
    public ResponseEntity<List<DashboardPetResponseDto>> getDashboardData() {
        var propietarios = propClient.getAll();

        var listaCombinada = propietarios.stream().map(p -> {
            //Buscamos la mascota para este propietario
            List<MascotaResponseDto> mascotas = mascClient.getByPropietarioId(p.id());

            //Tomamos la primera mascota si existe (asumiendo lógica de dashboard simple)
            MascotaResponseDto m = (mascotas != null && !mascotas.isEmpty()) ? mascotas.get(0) : null;

            return new DashboardPetResponseDto(
                    p.id(),
                    p.name(),
                    p.phoneNumber(),
                    p.address(),
                    m != null ? m.nameMascota() : "Sin mascota",
                    m != null ? m.raza() : "N/A",
                    m != null ? m.estadoBusqueda() : null,
                    p.tipoPropietario(),
                    p.estadoCuenta()
            );
        }).toList();

        return ResponseEntity.ok(listaCombinada);
    }
}