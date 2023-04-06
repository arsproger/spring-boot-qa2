package com.it.academy.springbootqa2.controllers;

import com.it.academy.springbootqa2.dto.CityDTO;
import com.it.academy.springbootqa2.mappers.CityMapper;
import com.it.academy.springbootqa2.services.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/city")
public class CityController {
    private final CityService cityService;
    private final CityMapper cityMapper;

    @Autowired
    public CityController(CityService cityService, CityMapper cityMapper) {
        this.cityService = cityService;
        this.cityMapper = cityMapper;
    }

    @GetMapping
    public List<CityDTO> getAll() {
        return cityService.getAll().stream().map(cityMapper::convertToDTO).toList();
    }

    @GetMapping("/{id}")
    public CityDTO getById(@PathVariable Long id) {
        return cityMapper.convertToDTO(cityService.getById(id));
    }

    @PostMapping
    public Long save(@RequestBody CityDTO cityDTO) {
        return cityService.save(cityMapper.convertToEntity(cityDTO));
    }

    @DeleteMapping("/{id}")
    public Long deleteById(@PathVariable Long id) {
        return cityService.deleteById(id);
    }

    @PutMapping("/{id}")
    public Long update(@PathVariable Long id, @RequestBody CityDTO cityDTO) {
        return cityService.updateById(id, cityMapper.convertToEntity(cityDTO));
    }

}
