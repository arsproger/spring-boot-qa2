package com.it.academy.springbootqa2.controllers;

import com.it.academy.springbootqa2.dto.CityDTO;
import com.it.academy.springbootqa2.mappers.CityMapper;
import com.it.academy.springbootqa2.services.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<List<CityDTO>> getAll() {
        List<CityDTO> cityDTOList = cityService.getAll().stream().map(cityMapper::convertToDTO).toList();
        return new ResponseEntity<>(cityDTOList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CityDTO> getById(@PathVariable Long id) {
        CityDTO cityDTO = cityMapper.convertToDTO(cityService.getById(id));
        return new ResponseEntity<>(cityDTO, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Long> save(@RequestBody CityDTO cityDTO) {
        Long savedCityId = cityService.save(cityMapper.convertToEntity(cityDTO));
        return new ResponseEntity<>(savedCityId, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Long> deleteById(@PathVariable Long id) {
        Long deletedCityId = cityService.deleteById(id);
        return new ResponseEntity<>(deletedCityId, HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Long> update(@PathVariable Long id, @RequestBody CityDTO cityDTO) {
        Long updatedCityId = cityService.updateById(id, cityMapper.convertToEntity(cityDTO));
        return new ResponseEntity<>(updatedCityId, HttpStatus.OK);
    }

}

