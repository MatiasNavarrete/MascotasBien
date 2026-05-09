package com.example.propietario.service;

import com.example.propietario.dto.PropietarioRequestDto;
import com.example.propietario.dto.PropietarioResponseDto;
import com.example.propietario.entity.Propietario;
import com.example.propietario.enums.EstadoBusqueda;
import com.example.propietario.enums.EstadoCuenta;
import com.example.propietario.enums.TipoPropietario;
import com.example.propietario.mapper.PropietarioMapper;
import com.example.propietario.repository.PropietarioRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PropietarioServiceTest {

    @Mock
    private PropietarioRepository propietarioRepository;

    @Mock
    private PropietarioMapper propietarioMapper;

    @InjectMocks
    private PropietarioServiceImpl propietarioService;

    @Test
    void cuandoGuardarPropietario_entoncesRetornaPropietarioGuardado() {
        // 1. Arrange
        UUID idSimulado = UUID.randomUUID();

        // Entidad intermedia
        Propietario propietarioParaGuardar = new Propietario();
        propietarioParaGuardar.setName("Pepe");
        propietarioParaGuardar.setEmail("pepe@u.cl");

        // Entidad que sale de la DB
        Propietario propietarioGuardado = new Propietario();
        propietarioGuardado.setId(idSimulado);
        propietarioGuardado.setName("Pepe");
        propietarioGuardado.setEmail("pepe@u.cl");

        // El DTO que el servicio debe retornar al final (Ajusta los campos según tu record)
        PropietarioResponseDto responseSimulado = new PropietarioResponseDto(
                idSimulado,
                "Pepe",
                "pepe@u.cl",
                "+569",
                "aqui",
                TipoPropietario.NATURAL,
                EstadoCuenta.ACTIVO,
                EstadoBusqueda.BUSCANDO
        );

        // Simulamos los 3 pasos del ServiceImpl
        when(propietarioMapper.toEntity(any(PropietarioRequestDto.class))).thenReturn(propietarioParaGuardar);
        when(propietarioRepository.save(any(Propietario.class))).thenReturn(propietarioGuardado);
        when(propietarioMapper.toResponseDto(any(Propietario.class))).thenReturn(responseSimulado); // <-- EL PASO QUE FALTABA

        // Datos de entrada
        PropietarioRequestDto request = new PropietarioRequestDto(
                "Pepe",
                "pepe@u.cl",
                "+569",
                "Direccion",
                "S",
                "1",
                TipoPropietario.NATURAL,
                EstadoCuenta.ACTIVO,
                EstadoBusqueda.BUSCANDO
        );

        // 2. Act
        var resultado = propietarioService.register(request);

        // 3. Assert
        assertNotNull(resultado);
        assertEquals("Pepe", resultado.name()); // Cambia a .getName() si no es record
        assertEquals(idSimulado, resultado.id());
    }
    @Test
    void cuandoGuardarPropietarioConEmailExistente_entoncesLanzaExcepcion() {
        // 1. Arrange
        String emailRepetido = "fade@u.cl";

        // Usamos LENIENT para que Mockito no se queje de los otros mocks que no usamos aquí
        lenient().when(propietarioRepository.existsByEmail(emailRepetido)).thenReturn(true);

        PropietarioRequestDto request = new PropietarioRequestDto(
                "Fade", emailRepetido, "+569", "Direccion", "S", "1",
                TipoPropietario.NATURAL, EstadoCuenta.ACTIVO, EstadoBusqueda.BUSCANDO
        );

        // 2. Act & 3. Assert
        assertThrows(RuntimeException.class, () -> {
            propietarioService.register(request);
        });
    }
}