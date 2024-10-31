package org.example.mantenimiento.service;

import org.example.mantenimiento.repository.MantenimientoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MantenimientoService {

    @Autowired
    MantenimientoRepository mantenimientoRepository;
}
