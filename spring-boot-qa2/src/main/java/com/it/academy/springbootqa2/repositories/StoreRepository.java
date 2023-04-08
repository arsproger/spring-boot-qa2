package com.it.academy.springbootqa2.repositories;

import com.it.academy.springbootqa2.models.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StoreRepository extends JpaRepository<Store, Long> {

}
