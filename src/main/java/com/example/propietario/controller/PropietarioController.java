package com.example.propietario.controller;


import com.example.propietario.dto.PropietarioRequestDto;
import com.example.propietario.dto.PropietarioResponseDto;
import com.example.propietario.service.PropietarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/propietario")
@RequiredArgsConstructor
public class PropietarioController {

    private final PropietarioService service;

    @PostMapping
    public ResponseEntity<PropietarioResponseDto> register(@Valid @RequestBody PropietarioRequestDto dto){

        return ResponseEntity.status(HttpStatus.CREATED).body(service.register(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PropietarioResponseDto> findById(@PathVariable UUID id){

        return ResponseEntity.ok(service.findById(id));

    }

}
