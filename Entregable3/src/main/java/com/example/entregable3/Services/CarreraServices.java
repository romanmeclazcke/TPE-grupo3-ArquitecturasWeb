package com.example.entregable3.Services;


import com.example.entregable3.DTO.ReporteCarreraDto;
import com.example.entregable3.Model.Carrera;
import com.example.entregable3.Repository.CarreraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("CarreraService")
public class CarreraServices implements BaseServices<Carrera>{

    @Autowired
    private CarreraRepository carreraRepository;

    public CarreraServices(CarreraRepository carreraRepository) {
        this.carreraRepository = carreraRepository;
    }

    @Override
    public List<Carrera> findAll() {
        return this.carreraRepository.findAll();
    }

    @Override
    public Carrera findById(Integer id) throws Exception {
        try {
            Optional<Carrera> buscada = this.carreraRepository.findById(id);
            return buscada.get();
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public Carrera save(Carrera entity) throws Exception {
        try {
            return this.carreraRepository.save(entity);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public Carrera update(Integer id, Carrera entity) throws Exception {
        try {
            Optional<Carrera> carrera = this.carreraRepository.findById(id);
            if (carrera.isPresent()) {
                Carrera existente = carrera.get();

                if (entity.getNombre() != null)
                    existente.setNombre(entity.getNombre());

                return this.carreraRepository.save(existente);
            } else {
                throw new Exception("Carrera con ID: " + id + " no encontrada");
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }

    }

    @Override
    public boolean delete(Integer id) throws Exception {
        try {
            if (this.carreraRepository.existsById(id)) {
                this.carreraRepository.deleteById(id);
                return true;
            } else
                throw new Exception();
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }


    public List<ReporteCarreraDto> getReportesCarrera(){
        return this.carreraRepository.getReporteCarreras();
    }


}

