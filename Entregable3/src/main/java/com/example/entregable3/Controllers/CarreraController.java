package com.example.entregable3.Controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@Controller("personas")
@Api(value="personasController", description ="api rest para personas")
public class CarreraController {

    @Autowired
    private PersonasServices personasServices;
}

