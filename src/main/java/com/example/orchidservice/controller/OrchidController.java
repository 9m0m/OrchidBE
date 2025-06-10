package com.example.orchidservice.controller;

import com.example.orchidservice.pojo.Orchid;
import com.example.orchidservice.service.imp.IOrchidService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/orchids")
public class OrchidController {

    @Autowired
    private IOrchidService orchidService;

    @GetMapping
    public ResponseEntity<List<Orchid>> getAllOrchids() {
        return ResponseEntity.ok(orchidService.getAllOrchids());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Orchid> getOrchidById(@PathVariable Integer id) {
        Optional<Orchid> orchid = orchidService.getOrchidById(id);
        return orchid.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Orchid> createOrchid(@RequestBody Orchid orchid) {
        return ResponseEntity.ok(orchidService.saveOrchid(orchid));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Orchid> updateOrchid(@PathVariable Integer id, @RequestBody Orchid orchid) {
        orchid.setOrchidId(id);
        return ResponseEntity.ok(orchidService.saveOrchid(orchid));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrchid(@PathVariable Integer id) {
        orchidService.deleteOrchid(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<Orchid>> getOrchidsByCategory(@PathVariable Integer categoryId) {
        return ResponseEntity.ok(orchidService.getOrchidsByCategory(categoryId));
    }

    @GetMapping("/natural/{isNatural}")
    public ResponseEntity<List<Orchid>> getOrchidsByNatural(@PathVariable Boolean isNatural) {
        return ResponseEntity.ok(orchidService.getOrchidsByNatural(isNatural));
    }

    @GetMapping("/price")
    public ResponseEntity<List<Orchid>> getOrchidsByPriceRange(
            @RequestParam Double minPrice, @RequestParam Double maxPrice) {
        return ResponseEntity.ok(orchidService.getOrchidsByPriceRange(minPrice, maxPrice));
    }
}
