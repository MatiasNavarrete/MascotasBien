package com.example.ms_pet_bff.controller;

import com.example.ms_pet_bff.client.MascotaClient;
import com.example.ms_pet_bff.client.PropietarioClient;
import com.example.ms_pet_bff.dto.DashboardPetResponseDto;
import com.example.ms_pet_bff.dto.MascotaResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/bff")
@CrossOrigin(origins = "http://localhost:5173")
public class BffController {

    @Autowired
    private PropietarioClient propClient;

    @Autowired
    private MascotaClient mascClient;

    @GetMapping("/dashboard")
    public ResponseEntity<List<DashboardPetResponseDto>> getDashboardData() {
        var propietarios = propClient.getAll();

        var listaCombinada = propietarios.stream().map(p -> {
            List<MascotaResponseDto> mascotas = mascClient.getByPropietarioId(p.id());
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

    @PostMapping("/registro")
    public ResponseEntity<?> registrarPropietarioYMascota(@RequestBody Map<String, Object> formulario) {
        try {
            //El BFF simplemente reenvía el JSON al microservicio de Propietarios
            return ResponseEntity.ok(propClient.registrar(formulario));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Error en el BFF: " + e.getMessage());
        }
    }

    @DeleteMapping("/propietario/{id}")
    public ResponseEntity<?> eliminarReporte(@PathVariable UUID id) {
        try {
            propClient.eliminar(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error al intentar eliminar: " + e.getMessage());
        }
    }
}