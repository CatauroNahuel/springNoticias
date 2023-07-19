package com.example.proyecto.egg.servicios;


import com.example.proyecto.egg.entidades.Usuario;
import com.example.proyecto.egg.enumeraciones.Rol;
import com.example.proyecto.egg.excepciones.MiException;
import com.example.proyecto.egg.repositorios.UsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServicios implements UserDetailsService {

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    @Transactional
    public Usuario crearUsuario(String nombreUsuario, String email, String password, String pasword2) throws MiException {

        validar(nombreUsuario, email, password, pasword2);

        Usuario usuario = new Usuario();

        usuario.setNombreUsuario(nombreUsuario);
        usuario.setEmail(email);
        usuario.setPassword(new BCryptPasswordEncoder().encode(password));
        usuario.setFechaAlta(new Date());
        usuario.setRol(Rol.USER);
        usuario.setActivo(true);

        usuarioRepositorio.save(usuario);
        return usuario;
    }

    public List<Usuario> listarUsuarios(){

        List<Usuario> usuarios = new ArrayList<>();

        usuarios = usuarioRepositorio.findAll();

        return usuarios;
    }

    @Transactional
    public void modificarUsuario(String id, String nombreUsuario, String email, String password, String pasword2) throws MiException{

        validar(nombreUsuario, email, password, pasword2);

        Optional<Usuario> respuesta = usuarioRepositorio.findById(id);

        if(respuesta.isPresent()){

            Usuario usuario=respuesta.get();

            usuario.setNombreUsuario(nombreUsuario);
            usuario.setEmail(email);
            usuario.setPassword(new BCryptPasswordEncoder().encode(password));

            usuarioRepositorio.save(usuario);
        }

    }

    public Usuario getOne(String id){
        return usuarioRepositorio.getOne(id);
    }


    public static void validar(String nombreUsuario, String email, String password, String password2) throws MiException{

        if(nombreUsuario==null || nombreUsuario.isEmpty()){
            throw new MiException("Deve completar el nombreUsuario del Usuario");
        }

        if (email==null || email.isEmpty()){
            throw new MiException("Deve completar el email del Usuario");
        }

        if (password==null || password.isEmpty()){
            throw new MiException("Deve completar la contraseña del Usuario");
        }

        if (password.length() <=5){
            throw new MiException("La contraseña debe tener al menos 6 digitos");
        }

        if (!password.equals(password2)){
            throw new MiException("Las contraseñas ingresada deben ser iguales");
        }
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Usuario usuario=usuarioRepositorio.buscarPorEmail(email);
        if (usuario!=null){
            List<GrantedAuthority> permisos = new ArrayList<>();
            GrantedAuthority p = new SimpleGrantedAuthority("ROLE_" + usuario.getRol());
            permisos.add(p);
            return new User(usuario.getEmail(), usuario.getPassword(),permisos);
        }else {
            return null;
        }
    }
}

