package org.example.viaje.service;

import org.example.viaje.DTO.*;
import org.example.viaje.Model.Distancia;
import org.example.viaje.Model.Parada;
import org.example.viaje.entity.Tarifa;
import org.example.viaje.entity.Viaje;
import org.example.viaje.feignClients.MapaFeignClient;
import org.example.viaje.feignClients.PagoFeignClient;
import org.example.viaje.feignClients.ParadaFeignClient;
import org.example.viaje.repository.ViajeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
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

    @Autowired
    MapaFeignClient mapaFeignClient;

    @Autowired
    PausaService pausaService;

    @Autowired
    TarifaService tarifaService;


    @Autowired
    PagoFeignClient pagoFeignClient;

    @Transactional
    public ViajeResponseDTO save(Long monopatinId, Long usuarioId, Long paradaDestinoId) {

        Parada p = this.paradaFeignClient.getParadaQueContieneMonopatin(monopatinId);
        Viaje viaje = new Viaje();
        viaje.setId_monopatin(monopatinId);
        viaje.setId_usuario(usuarioId);
        viaje.setId_parada_origen(p.getId());
        viaje.setHora_inicio(LocalDateTime.now());
        viaje.setId_parada_destino(paradaDestinoId);
        viaje.setFecha_inicio(LocalDate.now());
        viaje.setFecha_fin(null);

        viajeRepository.save(viaje);

        return mapToResponseDTO(viaje);
    }

    @Transactional
    public void endViaje(Long idViaje) throws Exception {
        try{
            Viaje viaje = this.viajeRepository.findById(idViaje)
                    .orElseThrow(ChangeSetPersister.NotFoundException::new);
            Distancia distancia = this.mapaFeignClient.getDistanciaEntreParada(viaje.getId_parada_origen(), viaje.getId_parada_destino());
            viaje.setFecha_fin(LocalDate.now());
            viaje.setHora_fin(LocalDateTime.now());
            viaje.setKm(distancia.getDistancia());
            viajeRepository.save(viaje);

            List<PausaResponseDto> pausas = this.pausaService.getPausasPorViaje(idViaje);
            List<Long> minutosPausados = new ArrayList<>();
            boolean cobrarTarifaExtra = false;

            for (PausaResponseDto pausa : pausas) {
                if (pausa.getHora_inicio() != null && pausa.getHora_frin() != null) {
                    Duration duracionPausa = Duration.between(pausa.getHora_inicio(), pausa.getHora_frin());
                    if (duracionPausa.toMinutes() < 15) {
                        minutosPausados.add(duracionPausa.toMinutes());
                    } else {
                        cobrarTarifaExtra = true;
                    }
                } else {
                    throw new IllegalArgumentException("Las horas de inicio o fin de la pausa son null");
                }
            }
            TarifaResponseDto tarifa = null;
            if(cobrarTarifaExtra){
                tarifa= this.tarifaService.getTarifaExtraEnPlazoValido();
            }else{
                tarifa = this.tarifaService.getTarifaNormalEnPlazoValido();
            }
            if (tarifa == null) {
                throw new IllegalStateException("La tarifa obtenida es null");
            }

            double totalViaje = distancia.getDistancia() * tarifa.getTarifa();

            Long totalMinutosPausados= 0L; //descontar esto al tiempo de viaje y asginarselo al monopatin
            for (Long minuto : minutosPausados) {
                totalMinutosPausados+=minuto;
            }

            PagoRequestDto pago = new PagoRequestDto();
            pago.setUserId(viaje.getId_usuario());
            pago.setViajeId(idViaje);
            pago.setMonto(totalViaje);

            try{
                if (!this.pagoFeignClient.pagar(pago)){
                    throw new IllegalStateException("El viaje fue cerrado pero hubo un error al pagar");
                }
            } catch (Exception e) {
                throw new Exception(e.getMessage());
            }
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }

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
