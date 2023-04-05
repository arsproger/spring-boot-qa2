package com.it.academy.springbootqa2.controllers;

import com.it.academy.springbootqa2.dto.StreetDTO;
import com.it.academy.springbootqa2.mappers.StreetMapper;
import com.it.academy.springbootqa2.services.StreetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping("/city/{id}")
    public List<StreetDTO> getStreetsByCityId(@PathVariable Long id) {
        return streetService.getByCityId(id).stream().map(streetMapper::convertToDTO).toList();
    }

}
