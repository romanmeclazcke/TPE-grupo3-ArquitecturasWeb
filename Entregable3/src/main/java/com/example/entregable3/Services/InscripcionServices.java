package com.example.entregable3.Services;
import com.example.entregable3.DTO.CarreraConNumInscriptosDto;
import com.example.entregable3.DTO.EstudianteDTO;
import com.example.entregable3.Model.Carrera;
import com.example.entregable3.Model.Inscripcion;
import com.example.entregable3.Repository.InscripcionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("InscripcionService")
public class InscripcionServices implements BaseServices<Inscripcion>{

    @Autowired
    private InscripcionRepository inscripcionRepository;

    @Override
    public List<Inscripcion> findAll() throws Exception {
        return this.inscripcionRepository.findAll();
    }

    @Override
    public Inscripcion findById(Integer id) throws Exception {
        try {
            Optional<Inscripcion> buscada = this.inscripcionRepository.findById(id);
            return buscada.get();
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public Inscripcion save(Inscripcion entity) throws Exception {
        try {return this.inscripcionRepository.save(entity);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public Inscripcion update(Integer id, Inscripcion entity) throws Exception {
        try {
            Optional<Inscripcion> inscripcion = this.inscripcionRepository.findById(id);
            Inscripcion i = inscripcion.get();
            i = this.inscripcionRepository.save(entity);
            return i;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public boolean delete(Integer id) throws Exception {
        try {
            if (this.inscripcionRepository.existsById(id)) {
                this.inscripcionRepository.deleteById(id);
                return true;
            } else
                throw new Exception();
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }


    public List<CarreraConNumInscriptosDto> getCarreraConNumInscriptosDto() {
        return this.inscripcionRepository.getCarrerasOrderByInscriptos();
    }

    public List<EstudianteDTO> getEstudiantesByCarreraAndCiudad(String ciudad, Carrera carrera){
        return this.inscripcionRepository.getEstudiantesByCarreraAndCiudad(ciudad, carrera);
    }
}
