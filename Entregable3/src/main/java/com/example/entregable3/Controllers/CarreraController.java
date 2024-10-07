package com.example.entregable3.Controllers;


import com.example.entregable3.Services.CarreraServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/carrera")
public class CarreraController {

    @Autowired
    private  CarreraServices carreraServices;

    


}

