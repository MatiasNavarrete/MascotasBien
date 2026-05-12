package com.example.mascota.controller;

import com.example.mascota.dto.MascotaRequestDTO;
import com.example.mascota.dto.MascotaResponseDTO;
import com.example.mascota.entity.MascotaEntity;
import com.example.mascota.mapper.MascotaMapper;
import com.example.mascota.service.MascotaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/mascotas")
@RequiredArgsConstructor
public class MascotaController {

    private final MascotaService mascotaService;
    private final MascotaMapper mascotaMapper;

    @PostMapping
    public ResponseEntity<MascotaResponseDTO> crearMascota(@Valid @RequestBody MascotaRequestDTO request) {
        //El controlador solo delega al servicio
        MascotaEntity nuevaMascota = mascotaService.registrarMascota(request);

        //Transforma la entidad a ResponseDTO para el frontend
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(mascotaMapper.toResponseDto(nuevaMascota));
    }

    @GetMapping
    public ResponseEntity<List<MascotaResponseDTO>> listarTodas() {
        List<MascotaResponseDTO> respuesta = mascotaService.listarTodas()
                .stream()
                .map(mascotaMapper::toResponseDto)
                .collect(Collectors.toList());

        return ResponseEntity.ok(respuesta);
    }

    @GetMapping("/propietario/{propietarioId}")
    public ResponseEntity<List<MascotaResponseDTO>> listarPorPropietario(@PathVariable UUID propietarioId) {
        List<MascotaResponseDTO> respuesta = mascotaService.listarPorPropietario(propietarioId)
                .stream()
                .map(mascotaMapper::toResponseDto)
                .collect(Collectors.toList());

        return ResponseEntity.ok(respuesta);
    }
}