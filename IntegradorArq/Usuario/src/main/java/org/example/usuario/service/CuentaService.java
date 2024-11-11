package org.example.usuario.service;


import jakarta.persistence.EntityNotFoundException;
import org.example.usuario.DTO.CuentaCargarSaldoDto;
import org.example.usuario.DTO.CuentaRequestDto;
import org.example.usuario.DTO.CuentaResponseDto;
import org.example.usuario.entity.Cuenta;
import org.example.usuario.entity.Usuario;
import org.example.usuario.repository.CuentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class CuentaService {

    @Autowired
    CuentaRepository cuentaRepository;

    public List<CuentaResponseDto> getCuentasByUser(Long userId) throws Exception {
        try {
            return this.cuentaRepository.getCuentasByUser(userId);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public CuentaResponseDto save(CuentaRequestDto CuentaRequestDto) throws Exception {
        try {
            Cuenta cuenta = this.mapearDtoAEntidad(CuentaRequestDto);
            this.cuentaRepository.save(cuenta);
            return this.mapearEntidadADto(cuenta);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public CuentaResponseDto editarCuenta(Long id, CuentaRequestDto cuentaRequestDto) throws Exception {
        try {
            Cuenta cuenta = cuentaRepository.findById(id)
                    .orElseThrow(() -> new Exception("Cuenta no encontrada"));
            cuenta.setCredito(cuentaRequestDto.getCredito());
            cuentaRepository.save(cuenta);
            return mapearEntidadADto(cuenta);
        } catch (Exception e) {
            throw new Exception("Error al editar cuenta: " + e.getMessage());
        }
    }

    public void anularCuenta(Long id) throws Exception {
        try {
            Cuenta cuenta = cuentaRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Cuenta no encontrada"));
            cuenta.setActiva(false);
            this.cuentaRepository.save(cuenta);
        } catch (Exception e) {
            throw new Exception("Error al anular cuenta: " + e.getMessage());
        }
    }


    public CuentaResponseDto cargarSaldo(Long idCuenta, CuentaCargarSaldoDto cuentaCargarSaldoDto) throws Exception {
        try {
            Cuenta cuenta = cuentaRepository.findById(idCuenta).orElseThrow(() -> new Exception("Cuenta no encontrada"));

            cuenta.setCredito(cuenta.getCredito() + cuentaCargarSaldoDto.getCredito());
            cuentaRepository.save(cuenta);
            return this.mapearEntidadADto(cuenta);
        } catch (Exception e) {
            throw new RuntimeException("Error al cargar la cuenta" + e.getMessage());
        }
    }

    private Cuenta mapearDtoAEntidad(CuentaRequestDto CuentaRequestDto){
        Cuenta cuenta = new Cuenta();
        cuenta.setFecha_alta(new Date());
        cuenta.setCredito(CuentaRequestDto.getCredito());
        return cuenta;
    }

    private CuentaResponseDto mapearEntidadADto(Cuenta cuenta){
        CuentaResponseDto cuentaResponseDto = new CuentaResponseDto();
        cuentaResponseDto.setId(cuenta.getId());
        cuentaResponseDto.setFecha_alta(cuenta.getFecha_alta());
        cuentaResponseDto.setCredito(cuenta.getCredito());
        return cuentaResponseDto;
    }
}
