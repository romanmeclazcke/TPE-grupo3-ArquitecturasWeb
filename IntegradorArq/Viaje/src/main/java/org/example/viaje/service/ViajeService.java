package org.example.viaje.service;

import org.example.usuario.entity.Usuario;
import org.example.viaje.DTO.ViajeRequestDTO;
import org.example.viaje.entity.Viaje;
import org.example.viaje.repository.ViajeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ViajeService {

    @Autowired
    ViajeRepository viajeRepository;

    public Viaje save(ViajeRequestDTO viajeDTO) {

        Viaje nuevo = convertirDTOaViaje(viajeDTO);
        return viajeRepository.save(nuevo);
    }

    private Viaje convertirDTOaViaje(ViajeRequestDTO viajeDTO) {
        Viaje viaje = new Viaje();
        viaje.setId_usuario(viajeDTO.getIdUsuario()); 
        viaje.setId_monopatin(viajeDTO.getIdMonopatin()); 
        viaje.setFecha_inicio(viajeDTO.getFechaInicio());
        viaje.setFecha_fin(viajeDTO.getFechaFin());
        viaje.setKm(viajeDTO.getKilometrosRecorridos());
        
        return viaje;
    }
    
}
