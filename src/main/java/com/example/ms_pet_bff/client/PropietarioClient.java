package com.example.ms_pet_bff.client;

import com.example.ms_pet_bff.dto.PropietarioResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name = "propietario", url = "http://localhost:8080/api/v1/propietario")
public interface PropietarioClient {
    @GetMapping
    List<PropietarioResponseDto> getAll();
}
