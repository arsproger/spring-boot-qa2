package com.it.academy.springbootqa2.services;

import com.it.academy.springbootqa2.exceptions.CityNotFoundException;
import com.it.academy.springbootqa2.models.City;
import com.it.academy.springbootqa2.repositories.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
        return cityRepository.findById(id).orElseThrow(CityNotFoundException::new);
    }

    public Long save(City city) {
        return cityRepository.save(city).getId();
    }

    public Long deleteById(Long id) {
        cityRepository.findById(id).orElseThrow(CityNotFoundException::new);
        cityRepository.deleteById(id);
        return id;
    }

    public Long updateById(Long id, City updatedCity) {
        City city = cityRepository.findById(id).orElseThrow(CityNotFoundException::new);

        city.setName(updatedCity.getName());
        return cityRepository.save(city).getId();
    }

}
