package org.example.parada.service;

import org.example.parada.repository.ParadaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ParadaService {
    @Autowired
    ParadaRepository paradaRepository;
}
