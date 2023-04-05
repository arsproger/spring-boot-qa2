package com.it.academy.springbootqa2.repositories;

import com.it.academy.springbootqa2.models.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalTime;
import java.util.List;

@Repository
public interface StoreRepository extends JpaRepository<Store, Long> {
    List<Store> findByOpeningTimeBeforeAndClosingTimeAfter(LocalTime openingTime, LocalTime closingTime);

    List<Store> findByOpeningTimeAfterOrClosingTimeBefore(LocalTime openingTime, LocalTime closingTime);
}
