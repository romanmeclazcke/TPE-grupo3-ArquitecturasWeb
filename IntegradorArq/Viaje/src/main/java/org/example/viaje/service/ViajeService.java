package org.example.viaje.service;

import org.example.viaje.repository.ViajeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ViajeService {

    @Autowired
    ViajeRepository viajeRepository;
}
