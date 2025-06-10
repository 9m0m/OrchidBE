package com.example.orchidservice.repository;

import com.example.orchidservice.pojo.Orchid;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface OrchidRepository {
    List<Orchid> findAll();
    Optional<Orchid> findById(Integer id);
    Orchid save(Orchid orchid);
    void deleteById(Integer id);
    List<Orchid> findByCategoryId(Integer categoryId);
    List<Orchid> findByIsNatural(Boolean isNatural);
    List<Orchid> findByPriceBetween(Double minPrice, Double maxPrice);
}

