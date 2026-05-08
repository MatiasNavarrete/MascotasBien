package com.example.propietario.service;

import com.example.propietario.dto.PropietarioRequestDto;
import com.example.propietario.dto.PropietarioResponseDto;

import java.util.UUID;

public interface PropietarioService {

    PropietarioResponseDto register(PropietarioRequestDto dto);
    PropietarioResponseDto findById (UUID id);


}
