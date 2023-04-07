package com.it.academy.springbootqa2.mappers;

import com.it.academy.springbootqa2.dto.StoreDTO;
import com.it.academy.springbootqa2.models.Store;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcProperties;
import org.springframework.stereotype.Component;

@Component
public class StoreMapper {
    private final ModelMapper modelMapper;

    @Autowired
    public StoreMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public Store convertToEntity(StoreDTO storeDTO) {
        return modelMapper.map(storeDTO, Store.class);
    }

    public StoreDTO convertToDTO(Store store) {
        return modelMapper.map(store, StoreDTO.class);
    }

}
