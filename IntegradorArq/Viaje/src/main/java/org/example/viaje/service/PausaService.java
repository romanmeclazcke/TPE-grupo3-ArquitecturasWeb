package org.example.viaje.service;

import org.example.viaje.DTO.PausaResponseDto;
import org.example.viaje.entity.Pausa;
import org.example.viaje.entity.Viaje;
import org.example.viaje.repository.PausaRepository;
import org.example.viaje.repository.ViajeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.util.Date;
import java.util.Optional;

@Service
public class PausaService {

    @Autowired
    PausaRepository pausaRepository;

    @Autowired
    ViajeRepository viajeRepository;

    public PausaResponseDto crearPausa(Long idViaje) {
        try {
            PausaResponseDto responseDto = new PausaResponseDto();
            Optional<Viaje> optionalViaje = this.viajeRepository.findById(idViaje);

            if (!optionalViaje.isPresent()) {
                responseDto.setEstado(false);
                responseDto.setMensaje("El viaje con id " + idViaje + " no existe");
                return responseDto;
            }

            Viaje viaje = optionalViaje.get();

            Pausa p = new Pausa();
            p.setViaje(viaje);
            p.setHora_inicio(new Time(System.currentTimeMillis()));
            this.pausaRepository.save(p);

            return this.mapearDeEntidadADto(p);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public PausaResponseDto mapearDeEntidadADto(Pausa pausa){
        PausaResponseDto responseDto = new PausaResponseDto();
        responseDto.setId(pausa.getId());
        responseDto.setHora_inicio(pausa.getHora_inicio());
        responseDto.setFecha_inicio(pausa.getFecha_inicio());
        responseDto.setFecha_fin(pausa.getFecha_fin());
        responseDto.setHora_frin(pausa.getHora_fin());
        return responseDto;
    }

}
