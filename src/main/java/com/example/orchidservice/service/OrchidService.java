package com.example.orchidservice.service;

import com.example.orchidservice.pojo.Orchid;
import com.example.orchidservice.repository.OrchidRepository;
import com.example.orchidservice.service.imp.IOrchidService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class OrchidService implements IOrchidService {

    @Autowired
    private OrchidRepository orchidRepository;

    @Override
    public List<Orchid> getAllOrchids() {
        return orchidRepository.findAll();
    }

    @Override
    public Optional<Orchid> getOrchidById(Integer id) {
        return orchidRepository.findById(id);
    }

    @Override
    public Orchid saveOrchid(Orchid orchid) {
        return orchidRepository.save(orchid);
    }

    @Override
    public void deleteOrchid(Integer id) {
        orchidRepository.deleteById(id);
    }

    @Override
    public List<Orchid> getOrchidsByCategory(Integer categoryId) {
        return orchidRepository.findByCategoryId(categoryId);
    }

    @Override
    public List<Orchid> getOrchidsByNatural(Boolean isNatural) {
        return orchidRepository.findByIsNatural(isNatural);
    }

    @Override
    public List<Orchid> getOrchidsByPriceRange(Double minPrice, Double maxPrice) {
        return orchidRepository.findByPriceBetween(minPrice, maxPrice);
    }
}
