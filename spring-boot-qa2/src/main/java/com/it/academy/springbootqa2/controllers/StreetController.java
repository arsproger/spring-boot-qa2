package com.it.academy.springbootqa2.controllers;

import com.it.academy.springbootqa2.dto.StreetDTO;
import com.it.academy.springbootqa2.mappers.StreetMapper;
import com.it.academy.springbootqa2.services.StreetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public ResponseEntity<List<StreetDTO>> getAll() {
        List<StreetDTO> streetDTOList = streetService.getAll().stream().map(
                streetMapper::convertToDTO).toList();
        return new ResponseEntity<>(streetDTOList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StreetDTO> getById(@PathVariable Long id) {
        StreetDTO streetDTO = streetMapper.convertToDTO(streetService.getById(id));
        return new ResponseEntity<>(streetDTO, HttpStatus.OK);
    }

    @GetMapping("/city/{id}")
    public ResponseEntity<List<StreetDTO>> getStreetsByCityId(@PathVariable Long id) {
        List<StreetDTO> streetDTOList = streetService.getByCityId(id)
                .stream().map(streetMapper::convertToDTO).toList();
        return new ResponseEntity<>(streetDTOList, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Long> save(@RequestParam Long cityId, @RequestBody StreetDTO streetDTO) {
        Long savedStreetId = streetService.save(cityId, streetMapper.convertToEntity(streetDTO));
        return new ResponseEntity<>(savedStreetId, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Long> deleteById(@PathVariable Long id) {
        Long deletedStreetId = streetService.deleteById(id);
        return new ResponseEntity<>(deletedStreetId, HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Long> update(@PathVariable Long id, @RequestBody StreetDTO streetDTO) {
        Long updatedStreetId = streetService.updateById(id, streetMapper.convertToEntity(streetDTO));
        return new ResponseEntity<>(updatedStreetId, HttpStatus.OK);
    }

}
