package org.example.mantenimiento.service;

import org.example.mantenimiento.DTO.MantenimientoRequestDto;
import org.example.mantenimiento.DTO.MantenimientoResponseDto;
import org.example.mantenimiento.DTO.MonopatinEnMantenimientoResponseDto;
import org.example.mantenimiento.entity.Mantenimiento;
import org.example.mantenimiento.feignClients.MonopatinFeignClient;
import org.example.mantenimiento.repository.MantenimientoRepository;
import org.example.mantenimiento.service.exception.NotFoundException;
import org.example.monopatin.entity.Monopatin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class MantenimientoService {
    @Autowired
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


        if (fechaInicio.isAfter(LocalDate.now())) {
            responseDto.setMensaje("La fecha de inicio no puede ser futura");
            responseDto.setExito(false);
            return responseDto;
        }

        //Registro el mantenimiento
        Mantenimiento mantenimiento = this.mapearDtoAEntidad(mantenimientoRequestDto, idMonopatin);
        this.mantenimientoRepository.save(mantenimiento);

        monopatin.setDisponible(false); //Lo marco como no disponible para su uso
        this.monopatinFeignClient.updateMonopatin(idMonopatin, monopatin);

        //Preparo respuesta exitosa
        responseDto = mapToResponseDTO(mantenimiento);
        return responseDto;
    }

    @Transactional
    public MantenimientoResponseDto endMantenimiento(Long idMonopatin)throws Exception {
        try{
            Mantenimiento mantenimiento= this.mantenimientoRepository.getUltimoMantenimiento(idMonopatin).orElseThrow(()-> new NotFoundException("El monopatin no se encuentra en mantenimiento"));
            mantenimiento.setFecha_fin(LocalDate.now());
            mantenimientoRepository.save(mantenimiento);
            return this.mapearEntidadADto(mantenimiento);
        } catch (Exception e) {
            throw new Exception("Error al finalizar mantenimiento");
        }
    }


    public MonopatinEnMantenimientoResponseDto elMonopatinSeEncuentraEnMantenimiento(Long idMonopatin) {
        MonopatinEnMantenimientoResponseDto responseDto = new MonopatinEnMantenimientoResponseDto();

        try {
            Monopatin monopatin = this.monopatinFeignClient.getMonopatinById(idMonopatin);

            if (monopatin == null) {
                responseDto.setMensaje("El monopatín con ID " + idMonopatin + " no existe");
                responseDto.setEstado(false);
                return responseDto;
            }

            boolean monopatinEstaEnMantenimiento = this.mantenimientoRepository.obtenerSiEstaEnMantenimiento(idMonopatin);

            if (!monopatinEstaEnMantenimiento) {
                responseDto.setEn_mantenimiento(false);
                responseDto.setIdMonopatin(idMonopatin);
                responseDto.setMensaje("El monopatín con ID " + idMonopatin + " no está en mantenimiento");
                responseDto.setEstado(true);
            } else {
                responseDto.setEn_mantenimiento(true);
                responseDto.setIdMonopatin(idMonopatin);
                responseDto.setMensaje("El monopatín con ID " + idMonopatin + " está en mantenimiento");
                responseDto.setEstado(true);
            }

        } catch (Exception e) {
            responseDto.setMensaje("No se pudo encontrar el monopatín con ID " + idMonopatin);
            responseDto.setEstado(false);
        }

        return responseDto;
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

    private Mantenimiento mapearDtoAEntidad(MantenimientoRequestDto mantenimientoRequestDto,Long idMonopatin) {
        Mantenimiento m = new Mantenimiento();
        m.setFecha_inicio(mantenimientoRequestDto.getFecha_inicio());
        m.setId_monopatin(idMonopatin);
        m.setAcciones(mantenimientoRequestDto.getAcciones());
        return m;
    }
}
