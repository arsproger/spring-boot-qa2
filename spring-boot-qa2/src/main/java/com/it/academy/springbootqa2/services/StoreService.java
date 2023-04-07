package com.it.academy.springbootqa2.services;

import com.it.academy.springbootqa2.enums.StoreStatus;
import com.it.academy.springbootqa2.exceptions.StoreNotFoundException;
import com.it.academy.springbootqa2.exceptions.StreetNotFoundException;
import com.it.academy.springbootqa2.models.Store;
import com.it.academy.springbootqa2.repositories.StoreRepository;
import com.it.academy.springbootqa2.repositories.StreetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StoreService {
    private final StoreRepository storeRepository;
    private final StreetRepository streetRepository;

    @Autowired
    public StoreService(StoreRepository storeRepository, StreetRepository streetRepository) {
        this.storeRepository = storeRepository;
        this.streetRepository = streetRepository;
    }

    public Store getById(Long id) {
        return storeRepository.findById(id).orElseThrow(StoreNotFoundException::new);
    }

    public Long save(Long streetId, Store store) {
        store.setStreet(streetRepository.findById(streetId).orElseThrow(StreetNotFoundException::new));
        return storeRepository.save(store).getId();
    }

    public Long deleteById(Long id) {
        storeRepository.findById(id).orElseThrow(StoreNotFoundException::new);
        storeRepository.deleteById(id);
        return id;
    }

    public Long updateById(Long id, Store updatedStore) {
        Store store = storeRepository.findById(id).orElseThrow(StoreNotFoundException::new);

        store.setName(updatedStore.getName());
        store.setOpeningTime(updatedStore.getOpeningTime());
        store.setClosingTime(updatedStore.getClosingTime());
        return storeRepository.save(store).getId();
    }

    public List<Store> getStores(String city, String street, Integer open) {
        List<Store> stores = storeRepository.findAll();

        if (city != null) {
            stores = stores.stream()
                    .filter(store -> store.getStreet().getCity().getName().equals(city))
                    .collect(Collectors.toList());
        }

        if (street != null) {
            stores = stores.stream()
                    .filter(store -> store.getStreet().getName().equals(street))
                    .collect(Collectors.toList());
        }

        if (open != null) {
            LocalTime now = LocalTime.now();

            StoreStatus status = StoreStatus.values()[open];

            stores = stores.stream()
                    .filter(store -> {
                        LocalTime openingTime = store.getOpeningTime();
                        LocalTime closingTime = store.getClosingTime();

                        if (closingTime.isBefore(openingTime)) {
                            closingTime = closingTime.plusHours(24);
                        }

                        boolean isOpen = now.isAfter(openingTime) && now.isBefore(closingTime);

                        return (status == StoreStatus.OPEN && isOpen) ||
                                (status == StoreStatus.CLOSED && !isOpen);
                    })
                    .collect(Collectors.toList());
        }

        return stores;
    }

}
