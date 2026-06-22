package com.groupcordillera.ms_kpi.controller;

import com.groupcordillera.ms_kpi.model.Area;
import com.groupcordillera.ms_kpi.repository.AreaRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/areas")
@CrossOrigin(origins = "*")
public class AreaController {

    private final AreaRepository areaRepository;

    public AreaController(AreaRepository areaRepository) {
        this.areaRepository = areaRepository;
    }

    @GetMapping
    public List<Area> listarAreas() {
        return areaRepository.findAll();
    }

    @PostMapping
    public Area crearArea(@RequestBody Area area) {
        return areaRepository.save(area);
    }
}