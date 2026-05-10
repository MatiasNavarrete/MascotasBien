package com.example.propietario.controller;


import com.example.propietario.dto.PropietarioRequestDto;
import com.example.propietario.dto.PropietarioResponseDto;
import com.example.propietario.service.PropietarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@Tag(name = "Propietario", description = "Endpoints para la gestión de dueños de mascotas (Microservicio Propietario)")
@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/v1/propietario")
@RequiredArgsConstructor
public class PropietarioController {

    private final PropietarioService service;

    @Operation(summary = "Registrar nuevo propietario", description = "Crea un registro con validaciones de email único y formato de teléfono chileno")
    @PostMapping
    public ResponseEntity<PropietarioResponseDto> register(@Valid @RequestBody PropietarioRequestDto dto){

        return ResponseEntity.status(HttpStatus.CREATED).body(service.register(dto));
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<PropietarioResponseDto> registrar(
            @ModelAttribute PropietarioRequestDto dto,
            @RequestParam("image") MultipartFile image
    ) {
        return ResponseEntity.ok(service.save(dto, image));
    }
    @Operation(summary = "Obtener todos los propietarios", description = "Retorna una lista de propietarios activos filtrados por el borrado lógico")
    @GetMapping
    public ResponseEntity<List<PropietarioResponseDto>> findAll(){
        return ResponseEntity.ok(service.findAll());
    }

    @Operation(summary = "Obtener propietario por id", description = "Se buscan los propietarios de los perros en base a su id")
    @GetMapping("/{id}")
    public ResponseEntity<PropietarioResponseDto> findById(@PathVariable UUID id){

        return ResponseEntity.ok(service.findById(id));

    }

    @Operation(summary = "Borrar propietario en base a id", description = "en base a la id del propietario se le borra de la base de datos")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removeOwner(@PathVariable UUID id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

}
