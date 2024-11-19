package org.example.usuario.service;

import lombok.RequiredArgsConstructor;
import org.example.usuario.DTO.UsuarioRequestDto;
import org.example.usuario.DTO.UsuarioResponseDto;
import org.example.usuario.entity.Cuenta;
import org.example.usuario.entity.Rol;
import org.example.usuario.entity.Usuario;
import org.example.usuario.repository.CuentaRepository;
import org.example.usuario.repository.RolRepository;
import org.example.usuario.repository.UsuarioRepository;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UsuarioService {
    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    RolRepository rolRepository;

    @Autowired
    CuentaRepository cuentaRepository;


    public UsuarioResponseDto getUsuarioById(Long id) throws Exception {
        try {
            return this.mapearEntididadADto(usuarioRepository.findById(id).get());
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }


    public List<UsuarioResponseDto> getAll() throws Exception {
        try {
            List<Usuario> usuarios = this.usuarioRepository.findAll();

            // Conversión de entidades a DTOs usando streams
            return usuarios.stream()
                    .map(this::mapearEntididadADto)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }


    public UsuarioResponseDto save(UsuarioRequestDto UsuarioRequestDto) throws Exception {
        try {
            UsuarioResponseDto responseDto = new UsuarioResponseDto();
            Rol rol = this.rolRepository.getById(UsuarioRequestDto.getId_rol());
            if (rol == null) {
                responseDto.setMensaje("El rol con id " + UsuarioRequestDto.getId_rol() + " no existe.");
                responseDto.setExito(false);
                return responseDto;
            }
            Usuario usuario = this.mapearDtoAEntididad(UsuarioRequestDto);

            this.usuarioRepository.save(usuario);
            return this.mapearEntididadADto(usuario);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public UsuarioResponseDto agregarCuenta(Long userId, Long cuentaId) throws Exception {
        try {
            UsuarioResponseDto responseDto = new UsuarioResponseDto();
            Cuenta cuenta = this.cuentaRepository.getById(cuentaId);
            Usuario usuario = this.usuarioRepository.getById(userId);

            if (cuenta == null) {
                responseDto.setMensaje("La cuenta con id " + cuentaId + " no existe.");
                responseDto.setExito(false);
                return responseDto;
            }

            if (usuario == null) {
                responseDto.setMensaje("El usuario con id " + userId + " no existe.");
                responseDto.setExito(false);
                return responseDto;
            }

            usuario.getCuentas().add(cuenta);
            cuenta.getUsuarios().add(usuario);
            this.usuarioRepository.save(usuario);
            this.cuentaRepository.save(cuenta);
            return this.mapearEntididadADto(usuario);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public UsuarioResponseDto editarUsuario(Long userId, UsuarioRequestDto usuarioRequestDto) throws Exception {
        try {
            Usuario usuario = this.usuarioRepository.findById(userId)
                    .orElseThrow(() -> new Exception("Usuario no encontrado."));
            Rol rol = this.rolRepository.findById(usuarioRequestDto.getId_rol())
                    .orElseThrow(() -> new Exception("El rol con id " + usuarioRequestDto.getId_rol() + " no existe."));

            usuario.setNombre(usuarioRequestDto.getNombre());
            usuario.setApellido(usuarioRequestDto.getApellido());
            usuario.setEmail(usuarioRequestDto.getEmail());
            usuario.setTelefono(usuarioRequestDto.getNumeroCelular());
            usuario.setRol(rol);

            this.usuarioRepository.save(usuario);
            return this.mapearEntididadADto(usuario);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public void eliminarUsuario(Long userId) throws Exception {
        try {
            if (!usuarioRepository.existsById(userId)) {
                throw new Exception("Usuario no encontrado.");
            }
            usuarioRepository.deleteById(userId);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
//    public UsuarioResponseDto getUsuarioByEmail(String userEmail) {
//        Usuario usuario = this.usuarioRepository.getUsuarioByEmail(userEmail);
//        System.out.println("Service" + usuario);
//        if (usuario == null) {
//            throw new RuntimeException("Usuario no encontrado con el email: " + userEmail);
//        }
//        return this.mapearEntididadADto(usuario);
//    }

    public UsuarioResponseDto getUsuarioByEmail(String email) throws ChangeSetPersister.NotFoundException {
        Optional<Usuario> usuarioOptional = usuarioRepository.findByEmail(email);

        if (usuarioOptional.isEmpty()) {
            throw new ChangeSetPersister.NotFoundException();
        }

        Usuario usuario = usuarioOptional.get();

        // Inicializa las relaciones Lazy cargadas por Hibernate
        Hibernate.initialize(usuario.getRol());
        Hibernate.initialize(usuario.getCuentas());

        // Mapear Usuario a UsuarioResponseDto
        UsuarioResponseDto responseDto = mapearEntididadADto(usuario);
        responseDto.setMensaje("Usuario encontrado con éxito.");
        responseDto.setExito(true);

        return responseDto;
    }




    private Usuario mapearDtoAEntididad(UsuarioRequestDto UsuarioRequestDto) {
        Usuario usuario = new Usuario();
        usuario.setNombre(UsuarioRequestDto.getNombre());
        usuario.setApellido(UsuarioRequestDto.getApellido());
        usuario.setEmail(UsuarioRequestDto.getEmail());
        usuario.setPassword(UsuarioRequestDto.getPassword());
        usuario.setTelefono(UsuarioRequestDto.getNumeroCelular());
        usuario.setRol(this.rolRepository.getById(UsuarioRequestDto.getId_rol()));
        return usuario;
    }



    private UsuarioResponseDto mapearEntididadADto(Usuario usuario) {
        UsuarioResponseDto responseDto = new UsuarioResponseDto();

        responseDto.setId(usuario.getId());
        responseDto.setNombre(usuario.getNombre());
        responseDto.setApellido(usuario.getApellido());
        responseDto.setEmail(usuario.getEmail());
        responseDto.setNumeroCelular(usuario.getTelefono());
        responseDto.setPassword(usuario.getPassword());

        // Mapear Rol
        if (usuario.getRol() != null) {
            Rol rol = new Rol();
            rol.setId(usuario.getRol().getId());
            rol.setTipo_rol(usuario.getRol().getTipo_rol());
            responseDto.setRol(rol);
        }

        return responseDto;
    }


}
