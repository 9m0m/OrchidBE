package com.example.orchidservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.orchidservice.pojo.Orchid;

public interface OrchidRepository extends JpaRepository<Orchid, Integer> {
}
