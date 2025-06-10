package com.example.orchidservice.service;

import com.example.orchidservice.repository.OrchidRepository;
import com.example.orchidservice.service.imp.IOrchidService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrchidService implements IOrchidService {

    @Autowired
    private OrchidRepository orchidRepository;

}
