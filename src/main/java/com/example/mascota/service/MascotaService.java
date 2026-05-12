package com.example.mascota.service;

import com.example.mascota.dto.MascotaRequestDTO;
import com.example.mascota.entity.MascotaEntity;
import java.util.List;
import java.util.UUID;

public interface MascotaService {
    MascotaEntity registrarMascota(MascotaRequestDTO dto);
    List<MascotaEntity> listarPorPropietario(UUID ownerId);
    List<MascotaEntity> listarTodas();

}