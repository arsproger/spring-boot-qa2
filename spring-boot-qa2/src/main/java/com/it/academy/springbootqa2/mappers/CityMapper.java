package com.it.academy.springbootqa2.mappers;

import com.it.academy.springbootqa2.dto.CityDTO;
import com.it.academy.springbootqa2.models.City;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CityMapper {
    private final ModelMapper modelMapper;

    @Autowired
    public CityMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public City convertToEntity(CityDTO cityDTO) {
        return modelMapper.map(cityDTO, City.class);
    }

    public CityDTO convertToDTO(City city) {
        return modelMapper.map(city, CityDTO.class);
    }

}
