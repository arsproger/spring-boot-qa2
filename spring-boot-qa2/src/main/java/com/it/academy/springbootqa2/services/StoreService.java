package com.it.academy.springbootqa2.services;

import com.it.academy.springbootqa2.StoreStatus;
import com.it.academy.springbootqa2.models.Store;
import com.it.academy.springbootqa2.repositories.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StoreService {
    private final StoreRepository storeRepository;

    @Autowired
    public StoreService(StoreRepository storeRepository) {
        this.storeRepository = storeRepository;
    }

    public List<Store> getAll() {
        return storeRepository.findAll();
    }

    public Store getById(Long id) {
        return storeRepository.findById(id).orElse(null);
    }

    public Long save(Store store) {
        return storeRepository.save(store).getId();
    }

    public Long deleteById(Long id) {
        storeRepository.deleteById(id);
        return id;
    }

    public Long updateById(Long id, Store updatedStore) {
        Store store = storeRepository.findById(id).orElse(null);

        if (store != null) {
            store.setName(updatedStore.getName());
            store.setStreet(updatedStore.getStreet());
            store.setOpeningTime(updatedStore.getOpeningTime());
            store.setClosingTime(updatedStore.getClosingTime());
            return id;
        }

        return 0L;
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
