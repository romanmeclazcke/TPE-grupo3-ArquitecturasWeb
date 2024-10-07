package com.example.entregable3.Controllers;


import com.example.entregable3.Services.EstudianteServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/estudiante")
public class EstudianteController {


    @Autowired
    private EstudianteServices estudianteServices;


}
