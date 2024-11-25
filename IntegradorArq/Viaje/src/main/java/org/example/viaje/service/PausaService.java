package org.example.viaje.service;

import org.example.viaje.DTO.PausaResponseDto;
import org.example.viaje.entity.Pausa;
import org.example.viaje.entity.Viaje;
import org.example.viaje.repository.PausaRepository;
import org.example.viaje.repository.ViajeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
            p.setHora_inicio(LocalDateTime.now());
            p.setFecha_inicio( new Date());
            this.pausaRepository.save(p);

            return this.mapearDeEntidadADto(p);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public PausaResponseDto cerrarPausa(Long idPausa){
        try {
            Optional<Pausa> pausa = Optional.ofNullable(this.pausaRepository.findById(idPausa).orElseThrow(() -> new ChangeSetPersister.NotFoundException()));

            Pausa p = pausa.get();
            p.setFecha_fin(new Date());
            p.setHora_fin(LocalDateTime.now());
            this.pausaRepository.save(p);
            return this.mapearDeEntidadADto(p);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<PausaResponseDto> getPausasPorIdMonopatin(Long idMonopatin){
        try {
            List<Pausa> pausasPorMonopatin = this.pausaRepository.getPausasPorMonopatin(idMonopatin);
            List<PausaResponseDto> pausasDto = new ArrayList<>();
            for(Pausa p :pausasPorMonopatin){
                pausasDto.add(this.mapearDeEntidadADto(p));
            }
            return pausasDto;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public List<PausaResponseDto> getPausasPorViaje(Long idViaje){
        try {
            List<Pausa> pausas = this.pausaRepository.getPausasPorViaje(idViaje);
            List<PausaResponseDto> pausasResponseDtos = new ArrayList<>();
            for (Pausa pausa : pausas) {
                pausasResponseDtos.add(this.mapearDeEntidadADto(pausa));
            }
            return pausasResponseDtos;
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
