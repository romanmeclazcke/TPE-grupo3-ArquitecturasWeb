package org.example.viaje.service;

//import org.example.monopatin.entity.Monopatin;
import org.example.viaje.Model.Parada;
import org.example.viaje.DTO.ViajeRequestDTO;
import org.example.viaje.DTO.ViajeResponseDTO;
import org.example.viaje.entity.Viaje;
//import org.example.viaje.feignClients.MonopatinFeignClient;
//import org.example.viaje.feignClients.UsuarioFeignClient;
import org.example.viaje.feignClients.ParadaFeignClient;
import org.example.viaje.repository.ViajeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ViajeService {

    @Autowired
    ViajeRepository viajeRepository;

    @Autowired
    ParadaFeignClient paradaFeignClient;

    @Transactional
    public ViajeResponseDTO save(Long monopatinId, Long usuarioId, Long paradaDestinoId) {

        Parada p = this.paradaFeignClient.getParadaQueContieneMonopatin(monopatinId);
        System.out.println("Parada"+p);
        Viaje viaje = new Viaje();
        viaje.setId_monopatin(monopatinId);
        viaje.setId_usuario(usuarioId);
        viaje.setId_parada_origen(p.getIdParada());
        viaje.setHora_inicio(LocalDateTime.now());
        viaje.setId_parada_destino(paradaDestinoId);
        viaje.setFecha_inicio(LocalDate.now());
        viaje.setFecha_fin(null);

        viajeRepository.save(viaje);

        return mapToResponseDTO(viaje);
    }

    @Transactional
    public void endViaje(Long idMonopatin, Long idUsuario) {
        List<Viaje> viajes = viajeRepository.findAllByIds(idMonopatin, idUsuario);

        Viaje activo = null;
        int i = 0;
        while (activo == null) {
            if (viajes.get(i).getFecha_fin() == null)
                activo = viajes.get(i);
            i++;
        }

        activo.setFecha_inicio(LocalDate.now());
        viajeRepository.save(activo);

        /*Monopatin monopatin = monopatinFeignClient.getMonopatinById(idMonopatin);
        monopatin.setDisponible(true);
        monopatinFeignClient.updateMonopatin(idMonopatin, monopatin);*/
    }


    public List<ViajeResponseDTO> getViajesPorMonopatin(Long idMonopatin) {
        try{
           List<Viaje> viajes= this.viajeRepository.getViajesPorMonopatin(idMonopatin);
           List<ViajeResponseDTO> viajesResponse= new ArrayList<>();
           for (Viaje viaje : viajes) {
               viajesResponse.add(this.mapearEntidadADto(viaje));
           }
           return viajesResponse;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Double getTotalFacturadoEntre(int anio,int mesAnterior, int mesPosterior) throws Exception {
        try {
            return this.viajeRepository.getTotalFacturadoEntre(anio,mesAnterior,mesPosterior);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    private ViajeResponseDTO mapToResponseDTO(Viaje viaje) {
        ViajeResponseDTO responseDto = new ViajeResponseDTO();

        responseDto.setIdMonopatin(viaje.getId_monopatin());
        responseDto.setFechaInicio(viaje.getFecha_inicio());
        responseDto.setFechaFin(viaje.getFecha_fin());
        responseDto.setKilometrosRecorridos(viaje.getKm());

        responseDto.setMensaje("Viaje registrado exitosamente");
        responseDto.setExito(true);

        return responseDto;
    }

    private Viaje convertirDTOaViaje(ViajeRequestDTO viajeDTO) {
        Viaje viaje = new Viaje();
        viaje.setId_monopatin(viajeDTO.getIdMonopatin());
        viaje.setKm(viajeDTO.getKilometrosRecorridos());
        
        return viaje;
    }

    public List<ViajeResponseDTO> getAll() throws Exception {
        try{
            List<Viaje> monopatines = viajeRepository.findAll();

            return monopatines.stream()
                    .map(this::mapearEntidadADto)
                    .collect(Collectors.toList());
        } catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    public List<Long> getMonopatinesConMasDeXViajes(int anio, int viajes) {
        return viajeRepository.getMonopatinesConMasDeXViajes(anio, viajes);
    }

    public ViajeResponseDTO mapearEntidadADto(Viaje viaje) {
        ViajeResponseDTO responseDto = new ViajeResponseDTO();
        responseDto.setIdMonopatin(viaje.getId_monopatin());
        responseDto.setIdUsuario(viaje.getId_usuario());
        responseDto.setFechaInicio(viaje.getFecha_inicio());
        responseDto.setFechaFin(viaje.getFecha_fin());
        responseDto.setKilometrosRecorridos(viaje.getKm());
        return responseDto;
    }
}
