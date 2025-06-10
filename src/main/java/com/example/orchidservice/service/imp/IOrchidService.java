package com.example.orchidservice.service.imp;

import com.example.orchidservice.pojo.Orchid;

import java.util.List;
import java.util.Optional;

public interface IOrchidService {
    List<Orchid> getAllOrchids();
    Optional<Orchid> getOrchidById(Integer id);
    Orchid saveOrchid(Orchid orchid);
    void deleteOrchid(Integer id);
    List<Orchid> getOrchidsByCategory(Integer categoryId);
    List<Orchid> getOrchidsByNatural(Boolean isNatural);
    List<Orchid> getOrchidsByPriceRange(Double minPrice, Double maxPrice);
}
