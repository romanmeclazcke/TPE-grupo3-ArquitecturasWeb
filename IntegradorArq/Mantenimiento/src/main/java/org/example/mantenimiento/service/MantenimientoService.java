package org.example.mantenimiento.service;

import org.example.mantenimiento.DTO.MantenimientoRequestDto;
import org.example.mantenimiento.DTO.MantenimientoResponseDto;
import org.example.mantenimiento.entity.Mantenimiento;
import org.example.mantenimiento.feignClients.MonopatinFeignClient;
import org.example.mantenimiento.repository.MantenimientoRepository;
import org.example.mantenimiento.service.exception.NotFoundException;
import org.example.monopatin.entity.Monopatin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;
import java.util.Optional;

@Service
public class MantenimientoService {
    MonopatinFeignClient monopatinFeignClient;
    @Autowired
    MantenimientoRepository mantenimientoRepository;

    @Transactional
    public MantenimientoResponseDto save(Long idMonopatin, MantenimientoRequestDto mantenimientoRequestDto) throws Exception {
        MantenimientoResponseDto responseDto = new MantenimientoResponseDto();

        //Valido que el monopatín exista
        Monopatin monopatin = monopatinFeignClient.getMonopatinById(idMonopatin);
        if (monopatin == null) {
            responseDto.setMensaje("El monopatín con ID " + idMonopatin + " no existe");
            responseDto.setExito(false);
            return responseDto;
        }

        //Valido que el monopatín no esté ya en mantenimiento
        if (mantenimientoRepository.existsById(idMonopatin)) {
            responseDto.setMensaje("El monopatín con ID " + idMonopatin + " ya está en mantenimiento");
            responseDto.setExito(false);
            return responseDto;
        }

        //Valido las fechas
        LocalDate fechaInicio;
        if (mantenimientoRequestDto.getFecha_inicio() != null)
            fechaInicio = mantenimientoRequestDto.getFecha_inicio();
        else
            fechaInicio = LocalDate.now();

        LocalDate fechaFin = mantenimientoRequestDto.getFecha_fin();

        if (fechaInicio.isAfter(LocalDate.now())) {
            responseDto.setMensaje("La fecha de inicio no puede ser futura");
            responseDto.setExito(false);
            return responseDto;
        }
        if (fechaFin != null && fechaFin.isBefore(fechaInicio)) {
            responseDto.setMensaje("La fecha de fin no puede ser previa a la de inicio");
            responseDto.setExito(false);
            return responseDto;
        }

        //Registro el mantenimiento
        Mantenimiento mantenimiento = new Mantenimiento();
        mantenimiento.setId_monopatin(idMonopatin);
        mantenimiento.setFecha_inicio(fechaInicio);
        mantenimiento.setFecha_fin(fechaFin);
        mantenimiento.setAcciones(mantenimientoRequestDto.getAcciones());
        mantenimientoRepository.save(mantenimiento);

        monopatin.setDisponible(false); //Lo marco como no disponible para su uso
        monopatinFeignClient.updateMonopatin(idMonopatin, monopatin);

        //Preparo respuesta exitosa
        responseDto = mapToResponseDTO(mantenimiento);
        return responseDto;
    }

    @Transactional
    public void endMantenimiento(Long idMonopatin) {
        if (mantenimientoRepository.existsById(idMonopatin)) {
            Mantenimiento mantenimiento = mantenimientoRepository.findById(idMonopatin).get();

            mantenimiento.setFecha_fin(LocalDate.now());
            mantenimientoRepository.save(mantenimiento);

            Monopatin monopatin = monopatinFeignClient.getMonopatinById(idMonopatin);
            if (monopatin != null) {
                monopatin.setDisponible(true);
                monopatinFeignClient.updateMonopatin(idMonopatin, monopatin);
            } else {
                throw new NotFoundException("Monopatín con ID: " + idMonopatin + " no encontrado");
            }
        }
    }

    private MantenimientoResponseDto mapToResponseDTO(Mantenimiento mantenimiento) {
        MantenimientoResponseDto responseDto = new MantenimientoResponseDto();

        responseDto.setId_monopatin(mantenimiento.getId_monopatin());
        responseDto.setFecha_inicio(mantenimiento.getFecha_inicio());
        responseDto.setFecha_fin(mantenimiento.getFecha_fin());
        responseDto.setAcciones(mantenimiento.getAcciones());
        responseDto.setMensaje("Mantenimiento registrado exitosamente");
        responseDto.setExito(true);

        return responseDto;
    }

    private MantenimientoResponseDto mapearEntidadADto(Mantenimiento guardado) {
        MantenimientoResponseDto respuesta = new MantenimientoResponseDto();
        respuesta.setId_monopatin(guardado.getId_monopatin());
        respuesta.setFecha_fin(guardado.getFecha_fin());
        respuesta.setFecha_inicio(guardado.getFecha_inicio());
        respuesta.setAcciones(guardado.getAcciones());
        return respuesta;
    }

    private Mantenimiento mapearDtoAEntidad(MantenimientoRequestDto mantenimientoRequestDto) {
        Mantenimiento m = new Mantenimiento();
        m.setFecha_inicio(mantenimientoRequestDto.getFecha_inicio());
        m.setFecha_fin(mantenimientoRequestDto.getFecha_fin());
        m.setId_monopatin(mantenimientoRequestDto.getId_monopatin());
        m.setAcciones(mantenimientoRequestDto.getAcciones());
        return m;
    }
}
