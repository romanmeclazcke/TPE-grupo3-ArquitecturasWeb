package org.example.pago.service;

import org.example.pago.DTO.CuentaRequestDto;
import org.example.pago.DTO.PagoRequestDto;
import org.example.pago.DTO.ResumenPagosDTO;
import org.example.pago.FeignClient.UsuarioFeignClient;
import org.example.pago.Model.Cuenta;
import org.example.pago.entity.Pago;
import org.example.pago.repository.PagoRepository;
import org.example.pago.repository.PagoRepositoryCustom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class PagoService {

    @Autowired
    UsuarioFeignClient usuarioFeignClient;

    @Autowired
    PagoRepository pagoRepository;

    @Autowired
    PagoRepositoryCustom pagoRepositoryCustom;

    public boolean pagar(PagoRequestDto pagoRequestDto) throws Exception {
        try{
            List<Cuenta> cuentas = this.usuarioFeignClient.getCuentasByUser(pagoRequestDto.getUserId());
            if(cuentas.size()==0){
                throw  new Exception("No posees cuentas");
            }

            Double saldoTotal=0.0;
            for (Cuenta cuenta : cuentas) {
                saldoTotal+=cuenta.getCredito();
            }

            if(saldoTotal<pagoRequestDto.getMonto()){
                throw  new Exception("Saldo insuficuente");
            }

            Double pendientePorPagar =pagoRequestDto.getMonto();
            for (Cuenta cuenta : cuentas) {
                Double creditoCuenta = cuenta.getCredito();
                if(creditoCuenta>pendientePorPagar){
                    pendientePorPagar=0.0;
                    CuentaRequestDto cuentaDto = new CuentaRequestDto(cuenta.getCredito()-pagoRequestDto.getMonto());
                    this.usuarioFeignClient.updateCuenta(cuenta.getId(), cuentaDto);
                }else{
                    pendientePorPagar-=cuenta.getCredito();
                    CuentaRequestDto cuentaDto = new CuentaRequestDto(0.0);
                    this.usuarioFeignClient.updateCuenta(cuenta.getId(), cuentaDto);
                }
            }
            if (pendientePorPagar==0.0){
                this.pagoRepository.save(this.mapearDtoAEntidad(pagoRequestDto));
                return true;
            }else{
                return false;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    public ResumenPagosDTO getTotalFacturadoEntre(int anio, int mesAnterior, int mesPosterior) throws Exception {
        try {
            LocalDate fechaInicio = LocalDate.of(anio, mesAnterior, 1);
            LocalDate fechaFin = LocalDate.of(anio, mesPosterior, 1);
            Double totalFacturado = this.pagoRepositoryCustom.getTotalFacturadoEntre(fechaInicio, fechaFin);
            return new ResumenPagosDTO(totalFacturado);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }


    private Pago mapearDtoAEntidad(PagoRequestDto pagoRequestDto) {
        Pago pago = new Pago();
        pago.setUserId(pagoRequestDto.getUserId());
        pago.setMonto(pagoRequestDto.getMonto());
        pago.setViajeId(pagoRequestDto.getViajeId());
        pago.setFechaEmitido(LocalDate.now());
        return pago;
    }


}
