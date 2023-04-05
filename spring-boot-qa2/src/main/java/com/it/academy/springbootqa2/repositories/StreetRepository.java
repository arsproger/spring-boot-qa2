package com.it.academy.springbootqa2.repositories;

import com.it.academy.springbootqa2.models.Street;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StreetRepository extends JpaRepository<Street, Long> {
    List<Street> findByCityId(Long id);
}
