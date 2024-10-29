package org.example.viaje.controller;


import org.example.viaje.service.ViajeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("viaje")
public class ViajeController {

    @Autowired
    ViajeService viajeService;
}
