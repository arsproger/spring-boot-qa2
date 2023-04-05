package com.it.academy.springbootqa2.services;

import com.it.academy.springbootqa2.models.Street;
import com.it.academy.springbootqa2.repositories.StreetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StreetService {
    private final StreetRepository streetRepository;

    @Autowired
    public StreetService(StreetRepository streetRepository) {
        this.streetRepository = streetRepository;
    }

    public List<Street> getAll() {
        return streetRepository.findAll();
    }

    public Street getById(Long id) {
        return streetRepository.findById(id).orElse(null);
    }

    public Long save(Street street) {
        return streetRepository.save(street).getId();
    }

    public Long deleteById(Long id) {
        streetRepository.deleteById(id);
        return id;
    }

    public Long updateById(Long id, Street updatedStreet) {
        Street street = streetRepository.findById(id).orElse(null);

        if(street != null) {
            street.setName(updatedStreet.getName());
            return id;
        }

        return 0L;
    }

    public List<Street> getByCityId(Long id) {
        return streetRepository.findByCityId(id);
    }
}
