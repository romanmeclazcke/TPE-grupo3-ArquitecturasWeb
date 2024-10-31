package org.example.monopatin.service;

import org.example.monopatin.DTO.MonopatinRequestDto;
import org.example.monopatin.DTO.MonopatinResponseDto;
import org.example.monopatin.entity.Monopatin;
import org.example.monopatin.repository.MonopatinRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MonopatinServices {

    @Autowired
    MonopatinRepository monopatinRepository;

    public MonopatinResponseDto save(MonopatinRequestDto MonopatinRequesDto) throws Exception {
        try{
            Monopatin monopatin = mapearDtoAEntidad(MonopatinRequesDto);
            monopatinRepository.save(monopatin);
            return this.mapearEntidadADto(monopatin);
        } catch (Exception e){
            throw new Exception(e.getMessage());
        }
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
