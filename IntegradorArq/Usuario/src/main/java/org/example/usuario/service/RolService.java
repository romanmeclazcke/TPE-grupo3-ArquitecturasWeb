package org.example.usuario.service;

import org.example.usuario.DTO.RolRequestDto;
import org.example.usuario.DTO.UsuarioRequestDto;
import org.example.usuario.entity.Rol;
import org.example.usuario.entity.Usuario;
import org.example.usuario.repository.RolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RolService {

    @Autowired
    RolRepository rolRepository;


    public Rol save(RolRequestDto RolRequestDto) throws Exception {
        try {
           Rol rol = this.mapearDtoAEntididad(RolRequestDto);

            return this.rolRepository.save(rol);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }



    private Rol mapearDtoAEntididad(RolRequestDto RolRequestDto) throws Exception {
        Rol rol = new Rol();
        rol.setTipo_rol(RolRequestDto.getTipo_rol());
        return rol;
    }
}
