package com.it.academy.springbootqa2.mappers;

import com.it.academy.springbootqa2.dto.CityDTO;
import com.it.academy.springbootqa2.dto.StreetDTO;
import com.it.academy.springbootqa2.models.City;
import com.it.academy.springbootqa2.models.Street;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StreetMapper {
    private final ModelMapper modelMapper;

    @Autowired
    public StreetMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public Street convertToEntity(StreetDTO streetDTO) {
        return modelMapper.map(streetDTO, Street.class);
    }

    public StreetDTO convertToDTO(Street street) {
        return modelMapper.map(street, StreetDTO.class);
    }

}
