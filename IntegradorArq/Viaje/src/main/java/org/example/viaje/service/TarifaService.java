package org.example.viaje.service;
//import org.example.viaje.DTO.TarifaRequestDto;
//import org.example.viaje.DTO.TarifaResponseDto;
import org.example.viaje.entity.Tarifa;
import org.example.viaje.repository.TarifaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TarifaService {
    @Autowired
    TarifaRepository tarifaRepository;



    /*public TarifaResponseDto crearTarfia(TarifaRequestDto TarifaRequestDto) throws Exception{
        try{
            Tarifa  t= this.mapearDtoAEntididad(TarifaRequestDto);

            this.tarifaRepository.save(t);

            return this.mapearEntididadADto(t);
        }catch(Exception e){
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
    }*/
}
