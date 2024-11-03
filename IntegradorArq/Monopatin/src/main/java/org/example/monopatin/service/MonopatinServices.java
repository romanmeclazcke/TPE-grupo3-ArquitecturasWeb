package org.example.monopatin.service;

import org.example.monopatin.DTO.MonopatinRequestDto;
import org.example.monopatin.DTO.MonopatinResponseDto;
import org.example.monopatin.entity.Monopatin;
//import org.example.monopatin.feignClient.ViajeFeignClient;
import org.example.monopatin.repository.MonopatinRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MonopatinServices {

    @Autowired
    MonopatinRepository monopatinRepository;
//    @Autowired
//    ViajeFeignClient viajeFeignClient;

    public MonopatinResponseDto save(MonopatinRequestDto MonopatinRequesDto) throws Exception {
        try{
            Monopatin monopatin = mapearDtoAEntidad(MonopatinRequesDto);
            this.monopatinRepository.save(monopatin);
            return this.mapearEntidadADto(monopatin);
        } catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }


    public void delete(@PathVariable Long id) {
        try{
            if(this.monopatinRepository.existsById(id))
                this.monopatinRepository.deleteById(id);
            else
                throw new Exception("El Monopatin con id: " + id + " no ha sido encontrado.");

        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }


    public MonopatinResponseDto editarMonopatin(Long monopatinId, MonopatinRequestDto monopatinRequestDto) throws Exception {
        Monopatin monopatin = this.monopatinRepository.findById(monopatinId)
                .orElseThrow(() -> new Exception("El monopatin a editar no existe"));
        monopatin.setTiempo_uso(monopatinRequestDto.getTiempo_uso());
        monopatin.setKilometros(monopatinRequestDto.getKilometros());
        monopatin.setDisponible(monopatinRequestDto.isDisponible());
        monopatin.setDisponible(monopatinRequestDto.isDisponible());
        this.monopatinRepository.save(monopatin);
        return this.mapearEntidadADto(monopatin);
    }


    public List<MonopatinResponseDto> getAll() throws Exception {
        try{
            List<Monopatin> monopatines = monopatinRepository.findAll();

            return monopatines.stream()
                    .map(this::mapearEntidadADto)
                    .collect(Collectors.toList());
        } catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }


    public MonopatinResponseDto getById(@PathVariable Long id) throws Exception {
        try{
            Monopatin monopatin = this.monopatinRepository.findById(id).get();
            return this.mapearEntidadADto(monopatin);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public MonopatinResponseDto activarMonopatin(Long monopatinId, Long usuarioId) throws Exception{
        Monopatin monopatin = this.monopatinRepository.findById(monopatinId)
                .orElseThrow(() -> new Exception("El monopatin requerido no existe"));
        if (monopatin.getDisponible()==true) {
            monopatin.setDisponible(false);
            this.monopatinRepository.save(monopatin);


            return this.mapearEntidadADto(monopatin);
        } else {
            throw new Exception("El monopatin requerido, no se encuentra disponible");
        }
        
    }

    private Monopatin mapearDtoAEntidad(MonopatinRequestDto MonopatinRequesDto) {
        Monopatin monopatin = new Monopatin();
        monopatin.setTiempo_uso(MonopatinRequesDto.getTiempo_uso());
        monopatin.setKilometros(MonopatinRequesDto.getKilometros());
        monopatin.setDisponible(MonopatinRequesDto.isDisponible());
        return monopatin;
    }

    private MonopatinResponseDto mapearEntidadADto(Monopatin monopatin) {
        MonopatinResponseDto monopatinResponseDto = new MonopatinResponseDto();
        monopatinResponseDto.setId(monopatin.getId());
        monopatinResponseDto.setTiempo_uso(monopatin.getTiempo_uso());
        monopatinResponseDto.setKilometros(monopatin.getKilometros());
        monopatinResponseDto.setDisponible(monopatin.getDisponible());
        return monopatinResponseDto;
    }
}
