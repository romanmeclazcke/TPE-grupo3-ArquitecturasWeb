package org.example.gestion.controller;

import org.example.gestion.service.GestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/gestion")
public class GestionController {

    @Autowired
    GestionService gestionService;
}
