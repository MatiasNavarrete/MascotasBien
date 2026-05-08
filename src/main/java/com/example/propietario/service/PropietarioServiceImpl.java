package com.example.propietario.service;


import com.example.propietario.dto.PropietarioRequestDto;
import com.example.propietario.dto.PropietarioResponseDto;
import com.example.propietario.entity.Propietario;
import com.example.propietario.enums.EstadoBusqueda;
import com.example.propietario.enums.EstadoCuenta;
import com.example.propietario.mapper.PropietarioMapper;
import com.example.propietario.repository.PropietarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor // Inyecta automáticamente el Repository y el Mapper
public class PropietarioServiceImpl implements PropietarioService {

    private final PropietarioRepository repository;
    private final PropietarioMapper mapper;

    @Override
    @Transactional
    public PropietarioResponseDto register(PropietarioRequestDto dto) {
        if (repository.findByEmail(dto.email()).isPresent()) {
            throw new RuntimeException("El email " + dto.email() + " ya está registrado.");
        }
        Propietario entidad = mapper.toEntity(dto);
        Propietario guardado = repository.save(entidad);
        if (entidad.getEstadoCuenta() == null) entidad.setEstadoCuenta(EstadoCuenta.ACTIVO);
        if (entidad.getEstadoBusqueda() == null) entidad.setEstadoBusqueda(EstadoBusqueda.SIN_MASCOTAS_PERDIDAS);
        return mapper.toResponseDto(guardado);

    }

    @Override
    @Transactional(readOnly = true)
    public PropietarioResponseDto findById(UUID id) {
        return repository.findById(id)
                .map(mapper::toResponseDto)
                .orElseThrow(() -> new RuntimeException("No se encontró el propietario con ID: " + id));
    }
}