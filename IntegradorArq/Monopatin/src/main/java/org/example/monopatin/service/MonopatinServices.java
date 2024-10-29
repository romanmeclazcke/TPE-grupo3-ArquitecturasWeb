package org.example.monopatin.service;

import org.example.monopatin.repository.MonopatinRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MonopatinServices {

    @Autowired
    MonopatinRepository monopatinRepository;
}
