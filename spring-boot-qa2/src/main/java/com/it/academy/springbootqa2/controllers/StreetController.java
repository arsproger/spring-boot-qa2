package com.it.academy.springbootqa2.controllers;

import com.it.academy.springbootqa2.dto.StreetDTO;
import com.it.academy.springbootqa2.mappers.StreetMapper;
import com.it.academy.springbootqa2.services.StreetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/street")
public class StreetController {
    private final StreetService streetService;
    private final StreetMapper streetMapper;

    @Autowired
    public StreetController(StreetService streetService, StreetMapper streetMapper) {
        this.streetService = streetService;
        this.streetMapper = streetMapper;
    }

    @GetMapping
    public List<StreetDTO> getAll() {
        return streetService.getAll().stream().map(
                streetMapper::convertToDTO).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public StreetDTO getById(@PathVariable Long id) {
        return streetMapper.convertToDTO(streetService.getById(id));
    }

    @GetMapping("/city/{id}")
    public List<StreetDTO> getStreetsByCityId(@PathVariable Long id) {
        return streetService.getByCityId(id).stream().map(streetMapper::convertToDTO).toList();
    }

    @PostMapping
    public Long save(@RequestBody StreetDTO streetDTO) {
        return streetService.save(streetMapper.convertToEntity(streetDTO));
    }

    @DeleteMapping("/{id}")
    public Long deleteById(@PathVariable Long id) {
        return streetService.deleteById(id);
    }

    @PutMapping("/{id}")
    public Long update(@PathVariable Long id, @RequestBody StreetDTO streetDTO) {
        return streetService.updateById(id, streetMapper.convertToEntity(streetDTO));
    }

}
