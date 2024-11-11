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

import java.util.ArrayList;
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


            Double distancia= Math.sqrt(Math.pow(paradaDestino.getX() - paradaOrigen.getX(), 2) + Math.pow(paradaDestino.getY() - paradaOrigen.getY(), 2));
            DistanciasResponseDto responseDto = new DistanciasResponseDto();
            responseDto.setIdParadaOrigen(idOrigen);
            responseDto.setIdParadaDestino(idDestino);
            responseDto.setDistancia(distancia);
            return responseDto;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }



    public List<MonopatinResponseDto> getMonopatinByUbicacion(UbicacionRequestDto monopatinRequestDto) {
        try {
            List<MonopatinResponseDto> monopatinResponseDto = new ArrayList<>();
            List<Monopatin> monopatinesCercanos = this.monopatinFeignClient.getMonopatinesEnRadio1km(Math.toIntExact(monopatinRequestDto.getX()), Math.toIntExact(monopatinRequestDto.getY()));
            for (Monopatin monopatin : monopatinesCercanos) {
                monopatinResponseDto.add(new MonopatinResponseDto(monopatin.getId(),monopatin.getTiempo_uso(),monopatin.getKilometros(),monopatin.getX(),monopatin.getY()));
            }
            return monopatinResponseDto;
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener el monopatín por ubicación: " + e.getMessage(), e);
        }
    }

}
