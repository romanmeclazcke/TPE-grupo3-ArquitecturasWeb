package org.example.viaje.service;
import org.example.viaje.DTO.TarifaRequestDto;
import org.example.viaje.DTO.TarifaResponseDto;
import org.example.viaje.entity.Tarifa;
import org.example.viaje.repository.TarifaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TarifaService {

    @Autowired
    TarifaRepository tarifaRepository;

    public TarifaResponseDto crearTarfia(TarifaRequestDto TarifaRequestDto) throws Exception{
        try{
            Tarifa t = this.mapearDtoAEntididad(TarifaRequestDto);

            this.tarifaRepository.save(t);
            return this.mapearEntididadADto(t);
        }catch(Exception e){
            throw new Exception(e.getMessage());
        }
    }


    public List<TarifaResponseDto> getAll() throws Exception {
        try{
            List<Tarifa> tarifas = this.tarifaRepository.findAll();
            return tarifas.stream()
                    .map(this::mapearEntididadADto)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public TarifaResponseDto getById(Long idTarifa) throws Exception {
        try {
            return this.mapearEntididadADto(this.tarifaRepository.findById(idTarifa).get());
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }


    public TarifaResponseDto getTarifaEnPlazoValido() throws Exception {
        try {
            return this.mapearEntididadADto(this.tarifaRepository.getTarifaEnPlazoValido());
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }



    private Tarifa mapearDtoAEntididad(TarifaRequestDto TarifaRequestDto) {
        Tarifa tarifa = new Tarifa();
        tarifa.setTarifa(TarifaRequestDto.getTarifa());
        tarifa.setFecha_inicio(TarifaRequestDto.getFecha_inicio());
        tarifa.setTipo_tarifa(TarifaRequestDto.getTipo_tarifa());
        tarifa.setId(tarifa.getId());
        return tarifa;
    };

    private TarifaResponseDto mapearEntididadADto(Tarifa tarifa) {
       TarifaResponseDto TarifaResponseDto = new TarifaResponseDto();
       TarifaResponseDto.setTarifa(tarifa.getTarifa());
       TarifaResponseDto.setFecha_inicio(tarifa.getFecha_inicio());
       TarifaResponseDto.setTipo_tarifa(tarifa.getTipo_tarifa());
       TarifaResponseDto.setId(tarifa.getId());
       return TarifaResponseDto;
    }
}
