package com.example.ms_pet_bff.client;

import com.example.ms_pet_bff.dto.MascotaResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

@FeignClient(name = "mascota", url = "http://localhost:8081/api/v1/mascota")
public interface MascotaClient {
    @GetMapping("/propietario/{ownerId}")
    MascotaResponseDto getByPropietarioId(@PathVariable("id")UUID id);
}
