package com.it.academy.springbootqa2.services;

import com.it.academy.springbootqa2.models.City;
import com.it.academy.springbootqa2.repositories.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CityService {
    private final CityRepository cityRepository;

    @Autowired
    public CityService(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    public List<City> getAll() {
        return cityRepository.findAll();
    }

    public City getById(Long id) {
        return cityRepository.findById(id).orElse(null);
    }

    public Long save(City city) {
        return cityRepository.save(city).getId();
    }

    public Long deleteById(Long id) {
        cityRepository.deleteById(id);
        return id;
    }

    public Long updateById(Long id, City updatedCity) {
        City city = cityRepository.findById(id).orElse(null);

        if(city != null) {
            city.setName(updatedCity.getName());
            return id;
        }

        return 0L;
    }

}
