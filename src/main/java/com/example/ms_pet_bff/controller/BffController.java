package com.example.ms_pet_bff.controller;

import com.example.ms_pet_bff.client.MascotaClient;
import com.example.ms_pet_bff.client.PropietarioClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/bff")
@CrossOrigin(origins = "http://localhost:5173") // El puerto de tu frontend
public class BffController {

    @Autowired
    private PropietarioClient propClient;
    @Autowired private MascotaClient mascClient;

    @GetMapping("/dashboard")
    public ResponseEntity<?> getDashboardData() {
        var propietarios = propClient.getAll();

        var listaCombinada = propietarios.stream().map(p -> {
            Map<String, Object> map = new HashMap<>();
            map.put("propietario", p);
            try {
                map.put("mascota", mascClient.getByPropietarioId(p.id()));
            } catch (Exception e) {
                map.put("mascota", null);
            }
            return map;
        }).toList();

        return ResponseEntity.ok(listaCombinada);
    }
}