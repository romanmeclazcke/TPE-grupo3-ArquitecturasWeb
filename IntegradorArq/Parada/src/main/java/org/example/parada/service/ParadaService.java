package org.example.parada.service;

import org.example.monopatin.entity.Monopatin;
import org.example.parada.DTO.ParadaResponseDto;
import org.example.parada.entity.Parada;
import org.example.parada.feignClients.MonopatinFeignClient;
import org.example.parada.repository.ParadaRepository;
import org.example.parada.service.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ParadaService {

    @Autowired
    ParadaRepository paradaRepository;

    @Autowired
    MonopatinFeignClient monopatinFeignClient;

    @Transactional
    public ParadaResponseDto ubicarMonopatinEnParada(Long idParada, Long idMonopatin) {
        try{
            Monopatin monopatin = this.monopatinFeignClient.getMonopatinById(idMonopatin);
            if(monopatin==null){
                throw new NotFoundException("monopatin con id" +idMonopatin+"no encontrado");
            }

            Parada parada = this.paradaRepository.findById(idParada).orElseThrow(() -> new NotFoundException("Parada no encontrada"));


            parada.getId_monopatines().add(idMonopatin);
            this.paradaRepository.save(parada);
            return this.mapearEntidadADto(parada);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
    public ParadaResponseDto retirarMonopatinDeParada(Long idParada, Long idMonopatin) {
        try{
            ParadaResponseDto paradaResponseDto = new ParadaResponseDto();
            Parada parada = this.paradaRepository.findById(idParada).orElseThrow(()-> new NotFoundException("Parada no encontrada"));
            Boolean eliminado =parada.getId_monopatines().remove(idMonopatin);
            this.paradaRepository.save(parada);
            if(!eliminado){
                 paradaResponseDto.setMensaje("Monopatin no encontado en la parada");
                 paradaResponseDto.setExito(false);
                return paradaResponseDto;
            }
            paradaResponseDto.setMensaje("Monopatin eliminado");
            paradaResponseDto.setExito(true);
            paradaResponseDto.setMonopatines(parada.getId_monopatines());
            paradaResponseDto.setId(parada.getId());
            return paradaResponseDto;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public ParadaResponseDto save() throws Exception {
        try {
            Parada parada = new Parada();
            this.paradaRepository.save(parada);
            ParadaResponseDto paradaResponseDto = this.mapearEntidadADto(parada);
            paradaResponseDto.setExito(true);
            paradaResponseDto.setMensaje("Parada creada con exito");
            return paradaResponseDto;
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


    public ParadaResponseDto getParadaById(Long idParada) {
        try {
            Parada parada = this.paradaRepository.findById(idParada)
                    .orElseThrow(() -> new NotFoundException("Parada con ID: " + idParada + " no encontrada"));
            return this.mapearEntidadADto(parada);
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener la parada: " + e.getMessage());
        }
    }

    public List<ParadaResponseDto> getParadas() {
        try {

            List<Parada> paradas = paradaRepository.findAll();
            return paradas.stream()
                    .map(this::mapearEntidadADto)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener las paradas: " + e.getMessage(), e);
        }
    }


    public ParadaResponseDto getParadaContieneMonopatin(Long idMonopatin)throws  Exception{
        try{
            Parada p =this.paradaRepository.getParadaContieneMonopatin(idMonopatin);
            return this.mapearEntidadADto(p);
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }


    private ParadaResponseDto mapearEntidadADto(Parada parada) {
        ParadaResponseDto responseDto = new ParadaResponseDto();
        responseDto.setY(parada.getY());
        responseDto.setX(parada.getX());
        responseDto.setId(parada.getId());
        responseDto.setMonopatines(parada.getId_monopatines());
        return responseDto;
    }
}
