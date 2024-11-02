package org.example.parada.service;

import jakarta.validation.Valid;
import org.example.parada.DTO.ParadaRequestDto;
import org.example.parada.DTO.ParadaResponseDto;
import org.example.parada.entity.Parada;
import org.example.parada.repository.ParadaRepository;
import org.example.parada.service.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@Service
public class ParadaService {

    @Autowired
    ParadaRepository paradaRepository;

    @Transactional
    public ParadaResponseDto ubicarMonopatinEnParada(Long idParada, Long idMonopatin) {
        //Completar
        return null;
    }

    public ParadaResponseDto save(ParadaRequestDto paradaRequestDto) throws Exception {
        try {
            Parada parada = mapearDtoAEntidad(paradaRequestDto);
            this.paradaRepository.save(parada);
            return this.mapearEntidadADto(parada);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public void delete(@PathVariable Long id) {
        try {
            if (this.paradaRepository.existsById(id)) {
                this.paradaRepository.deleteById(id);
            } else
                throw new NotFoundException("Parada con ID: " + id + " no encontrada");
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    private Parada mapearDtoAEntidad(ParadaRequestDto paradaRequestDto) {
        Parada parada = new Parada();
        parada.setId_monopatines(paradaRequestDto.getMonopatines());
        return parada;
    }

    private ParadaResponseDto mapearEntidadADto(Parada parada) {
        ParadaResponseDto responseDto = new ParadaResponseDto();
        responseDto.setId(parada.getId());
        responseDto.setMonopatines(parada.getId_monopatines());
        return responseDto;
    }
}
