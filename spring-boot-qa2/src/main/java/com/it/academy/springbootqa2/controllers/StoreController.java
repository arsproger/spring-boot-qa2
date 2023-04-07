package com.it.academy.springbootqa2.controllers;

import com.it.academy.springbootqa2.dto.StoreDTO;
import com.it.academy.springbootqa2.mappers.StoreMapper;
import com.it.academy.springbootqa2.services.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.List;

@RestController
@RequestMapping("/store")
@Validated
public class StoreController {
    private final StoreService storeService;
    private final StoreMapper storeMapper;

    @Autowired
    public StoreController(StoreService storeService, StoreMapper storeMapper) {
        this.storeService = storeService;
        this.storeMapper = storeMapper;
    }

    @GetMapping
    public ResponseEntity<List<StoreDTO>> getStores(@RequestParam(required = false) String city,
                                                    @RequestParam(required = false) String street,
                                                    @RequestParam(required = false)
                                                    @Min(0) @Max(1) Integer open) {
        List<StoreDTO> storeDTOList = storeService.getStores(city, street, open)
                .stream().map(storeMapper::convertToDTO).toList();
        return new ResponseEntity<>(storeDTOList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StoreDTO> getById(@PathVariable Long id) {
        StoreDTO storeDTO = storeMapper.convertToDTO(storeService.getById(id));
        return new ResponseEntity<>(storeDTO, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Long> save(@RequestParam Long streetId, @RequestBody StoreDTO storeDTO) {
        Long savedStoreId = storeService.save(streetId, storeMapper.convertToEntity(storeDTO));
        return new ResponseEntity<>(savedStoreId, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Long> deleteById(@PathVariable Long id) {
        Long deletedStoreId = storeService.deleteById(id);
        return new ResponseEntity<>(deletedStoreId, HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Long> update(@PathVariable Long id, @RequestBody StoreDTO storeDTO) {
        Long updatedStoreId = storeService.updateById(id, storeMapper.convertToEntity(storeDTO));
        return new ResponseEntity<>(updatedStoreId, HttpStatus.OK);
    }

}
