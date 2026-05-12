package com.example.mascota.service.impl;

import com.example.mascota.dto.MascotaRequestDTO;
import com.example.mascota.entity.MascotaEntity;
import com.example.mascota.mapper.MascotaMapper;
import com.example.mascota.repository.MascotaRepository;
import com.example.mascota.service.MascotaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MascotaServiceImpl implements MascotaService {

    private final MascotaRepository petRepository;
    private final MascotaMapper mascotaMapper;

    @Override
    @Transactional
    public MascotaEntity registrarMascota(MascotaRequestDTO dto) {

        MascotaEntity entity = mascotaMapper.toEntity(dto);
        return petRepository.save(entity);

    }

    @Override
    @Transactional(readOnly = true)
    public List<MascotaEntity> listarPorPropietario(UUID ownerId) {
        return petRepository.findByOwnerId(ownerId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<MascotaEntity> listarTodas() {
        return petRepository.findAll();
    }
}