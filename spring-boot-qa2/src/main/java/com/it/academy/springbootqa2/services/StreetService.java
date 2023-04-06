package com.it.academy.springbootqa2.services;

import com.it.academy.springbootqa2.exceptions.CityNotFoundException;
import com.it.academy.springbootqa2.exceptions.StreetNotFoundException;
import com.it.academy.springbootqa2.models.Street;
import com.it.academy.springbootqa2.repositories.CityRepository;
import com.it.academy.springbootqa2.repositories.StreetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StreetService {
    private final StreetRepository streetRepository;
    private final CityRepository cityRepository;

    @Autowired
    public StreetService(StreetRepository streetRepository, CityRepository cityRepository) {
        this.streetRepository = streetRepository;
        this.cityRepository = cityRepository;
    }

    public List<Street> getAll() {
        return streetRepository.findAll();
    }

    public Street getById(Long id) {
        return streetRepository.findById(id).orElseThrow(StreetNotFoundException::new);
    }

    public Long save(Street street) {
        return streetRepository.save(street).getId();
    }

    public Long deleteById(Long id) {
        streetRepository.findById(id).orElseThrow(StreetNotFoundException::new);
        streetRepository.deleteById(id);
        return id;
    }

    public Long updateById(Long id, Street updatedStreet) {
        Street street = streetRepository.findById(id).orElseThrow(StreetNotFoundException::new);

        street.setName(updatedStreet.getName());
        return streetRepository.save(street).getId();
    }

    public List<Street> getByCityId(Long id) {
        cityRepository.findById(id).orElseThrow(CityNotFoundException::new);
        return streetRepository.findByCityId(id);
    }
}
