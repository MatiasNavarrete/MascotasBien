package com.example.propietario.service;

import com.example.propietario.dto.PropietarioRequestDto;
import com.example.propietario.dto.PropietarioResponseDto;

import java.util.List;
import java.util.UUID;

public interface PropietarioService {

    PropietarioResponseDto register(PropietarioRequestDto dto);
    PropietarioResponseDto findById (UUID id);
    void delete(UUID id);
    List<PropietarioResponseDto> findAll();


}
