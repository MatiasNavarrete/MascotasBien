package com.example.ms_pet_bff.client;

import com.example.ms_pet_bff.dto.PropietarioResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "propietario", url = "http://localhost:8080/api/v1/propietario")
public interface PropietarioClient {
    @GetMapping
    List<PropietarioResponseDto> getAll();

    //Nuevo método para enviar el registro al microservicio de propietarios
    @PostMapping
    Object registrar(@RequestBody Object formulario);

    //Nuevo método para eliminar (si el frontend lo requiere a través del BFF)
    @DeleteMapping("/{id}")
    void eliminar(@PathVariable("id") java.util.UUID id);

}
