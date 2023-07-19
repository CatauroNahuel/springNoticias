package com.example.proyecto.egg.servicios;


import com.example.proyecto.egg.entidades.Periodista;
import com.example.proyecto.egg.entidades.Usuario;
import com.example.proyecto.egg.enumeraciones.Rol;
import com.example.proyecto.egg.excepciones.MiException;
import com.example.proyecto.egg.repositorios.UsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.Optional;


@Service
public class PeriodistaServicios{

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    @Transactional
    public Periodista combertirEnPeriodista(Usuario usuario){

        Periodista periodista= (Periodista) usuario;
        periodista.setRol(Rol.PERIODISTA);
        usuarioRepositorio.save(periodista);
        if (periodista.getSueldoMensual()==null){
            periodista.setSueldoMensual(0);
        }
        return periodista;
    }

    @Transactional
    public Periodista combertirEnUsuario(Periodista periodista){

        periodista.setRol(Rol.USER);
        usuarioRepositorio.save(periodista);
        return periodista;
    }

    @Transactional
    public void modificarPeriodista(String id, String nombreUsuario, String email, String password, String password2, Integer sueldoMensual)throws MiException {

        validar(nombreUsuario, email, password, password2, sueldoMensual);

        Optional<Usuario> respuesta = usuarioRepositorio.findById(id);

        if(respuesta.isPresent()){

            Periodista periodista= (Periodista) respuesta.get();

            periodista.setNombreUsuario(nombreUsuario);
            periodista.setEmail(email);
            periodista.setPassword(new BCryptPasswordEncoder().encode(password));
            periodista.setSueldoMensual(sueldoMensual);
            usuarioRepositorio.save(periodista);
        }

    }

    public Periodista getOne(String id){
        return (Periodista) usuarioRepositorio.getOne(id);
    }

    private void validar(String nombreUsuario, String email, String password, String password2,Integer sueldoMensual) throws MiException{

        UsuarioServicios.validar(nombreUsuario, email, password, password2);

        if(sueldoMensual==null || sueldoMensual<0){
            throw new MiException("Deve completar el sueldo del Periodista");
        }
    }

}
