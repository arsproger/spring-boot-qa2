package com.it.academy.springbootqa2.controllers;

import com.it.academy.springbootqa2.dto.StoreDTO;
import com.it.academy.springbootqa2.mappers.StoreMapper;
import com.it.academy.springbootqa2.services.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.List;

@RestController
@RequestMapping("/store")
public class StoreController {
    private final StoreService storeService;
    private final StoreMapper storeMapper;

    @Autowired
    public StoreController(StoreService storeService, StoreMapper storeMapper) {
        this.storeService = storeService;
        this.storeMapper = storeMapper;
    }

    @GetMapping
    public List<StoreDTO> getStores(@RequestParam(required = false) String city,
                                    @RequestParam(required = false) String street,
                                    @RequestParam(required = false) @Min(value = 0) @Max(value = 1) Integer open) {
        return storeService.getStores(city, street, open).stream().map(storeMapper::convertToDTO).toList();
    }

    @PostMapping
    public Long save(StoreDTO storeDTO) {
        return storeService.save(storeMapper.convertToEntity(storeDTO));
    }
}
