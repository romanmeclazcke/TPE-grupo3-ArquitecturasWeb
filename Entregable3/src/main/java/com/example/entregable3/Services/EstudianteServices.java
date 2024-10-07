package com.example.entregable3.Services;

import com.example.entregable3.DTO.EstudianteDTO;
import com.example.entregable3.Model.Carrera;
import com.example.entregable3.Model.Estudiante;
import com.example.entregable3.Repository.EstudianteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("EstudianteService")
public class EstudianteServices implements BaseServices<Estudiante>{

    @Autowired
    private EstudianteRepository estudianteRepository;

    @Override
    public List<Estudiante> findAll() {
        return this.estudianteRepository.findAll();
    }

    @Override
    public Estudiante findById(Integer id) throws Exception {
        try {
            Optional<Estudiante> buscado = this.estudianteRepository.findById(id);
            return buscado.get();
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public Estudiante save(Estudiante entity) throws Exception {
        try {
            return this.estudianteRepository.save(entity);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public Estudiante update(Integer id, Estudiante entity) throws Exception {
        try {
            Optional<Estudiante> estudiante = this.estudianteRepository.findById(id);
            Estudiante e = estudiante.get();
            e = this.estudianteRepository.save(entity);
            return e;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public boolean delete(Integer id) throws Exception {
        try {
            if (this.estudianteRepository.existsById(id)) {
                this.estudianteRepository.deleteById(id);
                return true;
            } else {
                throw new Exception();
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public  List<EstudianteDTO> getEstudiantesByEdad(){
        return this.estudianteRepository.getEstudiantesByEdad();
    }

    public  List<EstudianteDTO> getEstudianteByGenero(char genero){
        return this.estudianteRepository.getEstudianteByGenero(genero);
    }
}
