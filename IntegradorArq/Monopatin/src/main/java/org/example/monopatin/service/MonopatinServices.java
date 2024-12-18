package org.example.monopatin.service;

import org.example.monopatin.DTO.MonopatinDisponibilidadDTO;
import org.example.monopatin.DTO.MonopatinRequestDto;
import org.example.monopatin.DTO.MonopatinResponseDto;
import org.example.monopatin.DTO.MonopatinSumaKilometrosDto;
import org.example.monopatin.Model.Pausa;
import org.example.monopatin.entity.Monopatin;
import org.example.monopatin.feignClient.ViajeFeignClient;
import org.example.monopatin.repository.MonopatinRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MonopatinServices {

    @Autowired
    MonopatinRepository monopatinRepository;

    @Autowired
    ViajeFeignClient viajeFeignClient;

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

    public MonopatinResponseDto activarMonopatin(Long monopatinId, Long usuarioId, Long paradaDestinoId) throws Exception{
        Monopatin monopatin = this.monopatinRepository.findById(monopatinId)
                .orElseThrow(() -> new Exception("El monopatin requerido no existe"));
        if (monopatin.getDisponible() && this.viajeFeignClient.verificarParada(paradaDestinoId).getStatusCode().is2xxSuccessful()) {
            monopatin.setDisponible(false);
            this.monopatinRepository.save(monopatin);

            this.viajeFeignClient.createViaje(monopatinId,usuarioId,paradaDestinoId);

            return this.mapearEntidadADto(monopatin);
        } else {
            throw new Exception("El monopatin requerido, no se encuentra disponible");
        }
        
    }


    public MonopatinResponseDto sumarKilometros(Long monopatinId, MonopatinSumaKilometrosDto monopatinSumaKilometrosDto) throws Exception{
        try{
            Monopatin monopatin = this.monopatinRepository.findById(monopatinId).orElseThrow(()-> new Exception("El monopatin requerido no existe"));

            monopatin.setKilometros(monopatin.getKilometros()+monopatinSumaKilometrosDto.getKilometros());
            this.monopatinRepository.save(monopatin);
            return this.mapearEntidadADto(monopatin);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }


    public List<MonopatinResponseDto> getMonopatinesEnRadio1km(Integer x, Integer y) throws Exception {
        try{
            List<Monopatin> monopatines= this.monopatinRepository.getMonopatinesEnRadio1km(x,y);
            List<MonopatinResponseDto> respuesta = new ArrayList<>();
            for (Monopatin monopatin : monopatines) {
                respuesta.add(this.mapearEntidadADto(monopatin));
            }
            return respuesta;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }


    public List<MonopatinResponseDto> getMonopatinesPorKilometros() throws Exception {
        try{
            List<Monopatin> monopatines= this.monopatinRepository.getMonopatinesPorKilometros();
            List<MonopatinResponseDto> respuesta = new ArrayList<>();
            for (Monopatin monopatin : monopatines) {
                respuesta.add(this.mapearEntidadADto(monopatin));
            }
            return respuesta;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }


    public List<MonopatinResponseDto> getMonopatinesconTiempoPausa() throws Exception {
        try {
            List<Monopatin> monopatines = this.monopatinRepository.getMonopatinesPorKilometros();
            List<MonopatinResponseDto> respuesta = new ArrayList<>();
            for (Monopatin monopatin : monopatines) {
                List<Pausa> pausasMonopatin = this.viajeFeignClient.getPausasIdMonopatin(monopatin.getId());

                Duration tiempoTotalPausa = calcularTiempoTotalPausa(pausasMonopatin);

                Duration tiempoDeUso = monopatin.getTiempo_uso() != null ? monopatin.getTiempo_uso() : Duration.ZERO;

                Duration tiempoTotalUso = tiempoTotalPausa.plus(tiempoDeUso);

                MonopatinResponseDto dto = this.mapearEntidadADto(monopatin);
                dto.setTiempo_uso(tiempoTotalUso);

                respuesta.add(dto);
            }

            return respuesta;
        } catch (Exception e) {
            throw new Exception("Error al obtener los monopatines con tiempo de pausa: " + e.getMessage(), e);
        }
    }


    private Duration calcularTiempoTotalPausa(List<Pausa> pausas) {
        return pausas.stream()
                .map(pausa -> {
                    LocalDateTime inicio = pausa.getHora_inicio();
                    LocalDateTime fin = pausa.getHora_fin();
                    if (inicio != null && fin != null) {
                        return Duration.between(inicio, fin);
                    } else {
                        return Duration.ZERO;
                    }
                })
                .reduce(Duration.ZERO, Duration::plus);
    }

    public List<MonopatinResponseDto> getMonopatinesSinTiempoPausa() throws Exception {
        try{
            List<Monopatin> monopatines= this.monopatinRepository.getMonopatinesSinTiempoPausa();
            List<MonopatinResponseDto> respuesta = new ArrayList<>();
            for (Monopatin monopatin : monopatines) {
                respuesta.add(this.mapearEntidadADto(monopatin));
            }
            return respuesta;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }


    public MonopatinDisponibilidadDTO getDisponibilidad() throws Exception{
        try {
            MonopatinDisponibilidadDTO responseDTO = new MonopatinDisponibilidadDTO();
        responseDTO.setMonopatinesDisponibles(monopatinRepository.countByDisponibilidad(true));
        responseDTO.setMonopatinesEnMantenimiento(monopatinRepository.countByDisponibilidad(false));
        return responseDTO;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    private Monopatin mapearDtoAEntidad(MonopatinRequestDto monopatinRequestDto) {
        Monopatin monopatin = new Monopatin();
        monopatin.setTiempo_uso(monopatinRequestDto.getTiempo_uso());
        monopatin.setKilometros(monopatinRequestDto.getKilometros());
        monopatin.setDisponible(monopatinRequestDto.isDisponible());
        monopatin.setY(monopatinRequestDto.getY());
        monopatin.setX(monopatinRequestDto.getX());
        return monopatin;
    }

    private MonopatinResponseDto mapearEntidadADto(Monopatin monopatin) {
        MonopatinResponseDto monopatinResponseDto = new MonopatinResponseDto();
        monopatinResponseDto.setId(monopatin.getId());
        monopatinResponseDto.setY(monopatin.getY());
        monopatinResponseDto.setX(monopatin.getX());
        monopatinResponseDto.setTiempo_uso(monopatin.getTiempo_uso());
        monopatinResponseDto.setKilometros(monopatin.getKilometros());
        monopatinResponseDto.setDisponible(monopatin.getDisponible());
        return monopatinResponseDto;
    }
}
