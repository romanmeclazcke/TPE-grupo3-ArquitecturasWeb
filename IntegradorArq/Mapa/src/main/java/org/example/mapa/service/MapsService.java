package org.example.mapa.service;


import feign.FeignException;
import org.example.mapa.DTO.DistanciasResponseDto;
import org.example.mapa.DTO.MonopatinResponseDto;
import org.example.mapa.DTO.UbicacionRequestDto;
import org.example.mapa.Model.Monopatin;
import org.example.mapa.Model.Parada;
import org.example.mapa.feignClients.MonopatinFeignClient;
import org.example.mapa.feignClients.ParadaFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class MapsService {

    @Autowired
    ParadaFeignClient paradaFeignClient;

    @Autowired
    MonopatinFeignClient monopatinFeignClient;


    public DistanciasResponseDto getDistanciaEntreParadas(Long idOrigen, Long idDestino){
        try{
            Parada paradaOrigen = this.paradaFeignClient.getParadaById(idOrigen);
            Parada paradaDestino = this.paradaFeignClient.getParadaById(idDestino);
            if(paradaOrigen==null||paradaDestino==null){
                throw new RuntimeException("Una o ambas paradas no fueron encontradas");
            }

            Random random = new Random();
            Double distancia = (double) (random.nextInt(100) + 1); // Genera un número entre 1 y 100
            DistanciasResponseDto responseDto = new DistanciasResponseDto();
            responseDto.setIdParadaOrigen(idOrigen);
            responseDto.setIdParadaDestino(idDestino);
            responseDto.setDistancia(distancia);
            return responseDto;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }



    public MonopatinResponseDto getMonopatinByUbicacion(UbicacionRequestDto monopatinRequestDto) {
        try {
            MonopatinResponseDto monopatinResponseDto = new MonopatinResponseDto();
            List<Parada> paradas = this.paradaFeignClient.getParadas();

            if (paradas.isEmpty()) {
                throw new RuntimeException("No se encontraron paradas");
            }

            for (Parada parada : paradas) {
                if (parada.getMonopatines().size() > 0) {
                    Monopatin monopatin = this.monopatinFeignClient.getMonopatinById(parada.getMonopatines().get(0));
                    if (monopatin == null) {
                        throw new RuntimeException("No se encontró el monopatín con ID: " + parada.getMonopatines().get(0));
                    }
                    monopatinResponseDto.setId(monopatin.getId());
                    monopatinResponseDto.setKilometros(monopatin.getKilometros());
                    monopatinResponseDto.setTiempo_uso(monopatin.getTiempo_uso());
                    return monopatinResponseDto;
                }
            }
            return new MonopatinResponseDto(); // Retorna un DTO vacío

        } catch (Exception e) {
            throw new RuntimeException("Error al obtener el monopatín por ubicación: " + e.getMessage(), e);
        }
    }

}
